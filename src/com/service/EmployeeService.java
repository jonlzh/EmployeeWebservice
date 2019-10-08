package com.service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import com.method.Methods;
import com.pojo.Employee;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;

@Path("/EmployeeService")
public class EmployeeService extends Application
{

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/AllEmployee")
    public JsonArray getAllEmployee()
    {
        Methods m = new Methods();
        ArrayList<Employee> eList = m.showAllEmployee();
// http://localhost:8080/EmployeeService/rest/EmployeeService/AllEmployee
        JsonArrayBuilder array = Json.createArrayBuilder();

        for(Employee e : eList)
        {
            array.add(Json.createObjectBuilder()
                    .add("employee_id", e.getEmployee_id())
                    .add("fname", e.getFname())
                    .add("lname", e.getLname())
                    .add("email", e.getEmail())
                    .add("contact", e.getContact())
                    .add("role", e.getRole())
                    .add("department", e.getDepartment())
                    .add("deleted", e.getDeleted()).build());
        }

        System.out.println(array);
        return array.build();
    }
	
	@POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/Login")
    public JsonArray getAllEmployee(@Context HttpServletRequest request)
    {
		String email = request.getParameter("email");
        String pw = request.getParameter("password");

        if((email == null || email.equalsIgnoreCase(""))
                || (pw == null || pw.equalsIgnoreCase("")))
        {
            //request.setAttribute("msg","Fields cannot be empty");
//            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
        else
        {
            Methods m = new Methods();
            boolean check = m.login(email,pw);
            if(check == true)
            {
                Employee e = m.showOwnDetails(email);
                array.add(Json.createObjectBuilder()
                        .add("employee_id", e.getEmployee_id())
                        .add("fname", e.getFname())
                        .add("lname", e.getLname())
                        .add("email", e.getEmail())
                        .add("contact", e.getContact())
                        .add("role", e.getRole())
                        .add("department", e.getDepartment())
                        .add("deleted", e.getDeleted()).build());
             
//                request.setAttribute("eDetail", e);
//                request.getRequestDispatcher("/OwnDetail.jsp").forward(request, response);
                return array.build();
            }
            else
            {
            	array.add(Json.createObjectBuilder()
            			.add("msg","Retry!")).build();
//                request.setAttribute("msg","Retry!");
//                request.getRequestDispatcher("/index.jsp").forward(request, response);
            	return array.build();
            }
            
        }
    }
	
	
	@GET
    @Path("/test")
	@Produces("text/plain")
	  public String sayPlainTextHello() {
	    return "test msg!";
	  }
}




