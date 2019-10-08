package com.method;

import java.util.ArrayList;
import com.pojo.Employee;
import com.database.DBController;

public class Methods {

    public Employee showOwnDetails(String email)
    {
        DBController db = new DBController();
        return db.showOwnDetails(email);
    }

    public ArrayList<Employee> showAllEmployee()
    {
        DBController db = new DBController();
        return db.showAllEmployee();
    }

    public ArrayList<Employee> showCurrentEmployee()
    {
        DBController db = new DBController();
        return db.showExistingEmployee();
    }

    public int deleteEmployee(Integer a)
    {
        DBController db = new DBController();
        return db.deleteEmployee(a);
    }

    public int updateDetails(Integer id, String a, String b, String c, String d, String e, String f, String g)
    {
        DBController db = new DBController();
        return db.updateDetails(id, a, b, c, d, e, f, g);
    }

    public int addEmployee(String a, String b, String c, String d, String e, String f)
    {
        DBController db = new DBController();
        return db.addEmployee(a, b, c, d, e, f);
    }

    public boolean login(String a, String b)
    {
        DBController db = new DBController();
        return db.login(a, b);
    }
}
