package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.pojo.Employee;

public class DBController {

    private Connection connect = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    public boolean login(String email, String p)
    {
        boolean check = false;
        String temp = "";
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();
            ResultSet result = st.executeQuery("SELECT password From employee WHERE email='" + email + "'");

            while (result.next()) {
                temp = result.getString("password");
            }
            close();
            if(p.equals(temp))
            {
                check = true;
            }
            else
            {
                check = false;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return check;
    }

    public Employee showOwnDetails(String email)
    {
        Employee em = new Employee();
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * From employee WHERE email='" + email + "'");

            while(rs.next())
            {
                em.setEmployee_id(rs.getInt("employee_id"));
                em.setFname(rs.getString("fname"));
                em.setLname(rs.getString("lname"));
                em.setEmail(rs.getString("email"));
                em.setContact(rs.getString("contact"));
                em.setRole(rs.getString("role"));
                em.setDepartment(rs.getString("department"));
                em.setDeleted(rs.getString("deleted"));
                em.setPassword((rs.getString("password")));
                close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return em;
    }

    public ArrayList<Employee> showAllEmployee()
    {
        ArrayList<Employee> allEmployee=new ArrayList<Employee>();
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM employee");

            while(rs.next())
            {
                Employee em = new Employee();
                em.setEmployee_id(rs.getInt("employee_id"));
                em.setFname(rs.getString("fname"));
                em.setLname(rs.getString("lname"));
                em.setEmail(rs.getString("email"));
                em.setContact(rs.getString("contact"));
                em.setRole(rs.getString("role"));
                em.setDepartment(rs.getString("department"));
                em.setDeleted(rs.getString("deleted"));
                allEmployee.add(em);

                close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allEmployee;
    }

    public ArrayList<Employee> showExistingEmployee()
    {
        ArrayList<Employee> allEmployee=new ArrayList<Employee>();
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("SELECT * FROM employee WHERE deleted='N';");

            while(rs.next())
            {
                Employee em = new Employee();
                em.setEmployee_id(rs.getInt("employee_id"));
                em.setFname(rs.getString("fname"));
                em.setLname(rs.getString("lname"));
                em.setEmail(rs.getString("email"));
                em.setContact(rs.getString("contact"));
                em.setRole(rs.getString("role"));
                em.setDepartment(rs.getString("department"));
                allEmployee.add(em);

                close();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return allEmployee;
    }

    public int deleteEmployee(Integer id)
    {
        int count =0;
        try {
            Connection con = DBUtil.getConnection();

            // insert the data
            String query = "UPDATE employee SET deleted = 'Y' WHERE employee_id= ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            count = ps.executeUpdate();
            System.out.println("Update status to deactivate for " + id);

            close();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int updateDetails(Integer id, String fname, String lname, String email, String contact, String role, String department, String deleted)
    {
        int count = 0;
        try {
            Connection con = DBUtil.getConnection();

            // insert the data
            String query = "UPDATE employee SET fname = ?, lname = ?, email = ?, contact = ?, role = ?, department = ?, deleted = ? WHERE employee_id= ?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, email);
            ps.setString(4, contact);
            ps.setString(5, role);
            ps.setString(6, department);
            ps.setInt(8, id);
            ps.setString(7, deleted);
            count = ps.executeUpdate();

            close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int addEmployee(String fname, String lname, String email, String contact, String role, String password)
    {
        int count =0;
        try {
            Connection con = DBUtil.getConnection();
            Statement st = con.createStatement();

            // insert the data
            count = st.executeUpdate("INSERT INTO employee (fname,lname,email,contact, role, password)"
                    + "VALUES ('"+ fname + "','" + lname + "','" + email + "','" + contact + "','" + role + "','" + password + "')");

            close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    // You need to close the resultSet
    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
