package com.github.venkatp0326.tests;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class APITest {

    static class Employee {
        String id;
        String employee_name;
    }

    @Test
    public void testGet() {

        //Do get
        String targetEmployeeID = "10";
        Response resp = given().when().get("http://dummy.restapiexample.com/api/v1/employee/"+targetEmployeeID);

        //Validate response code
        System.out.println("Response code = " + resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(),200);
        System.out.println("Response String: "+ resp.asString());

        //deserialize data
        JsonObject json = JsonParser.parseString(resp.asString()).getAsJsonObject();
        Gson gson = new Gson();
        Employee employee = gson.fromJson(json.get("data"), Employee.class);

        //validate employee data
        System.out.println("Employee ID = " + employee.id);
        System.out.println("Employee Name = " + employee.employee_name);
        Assert.assertEquals(employee.id,targetEmployeeID);

    }


    @Test
    public void deleteTest() {
        String targetEmployeeID = "1";
        Response resp = given().when().delete("http://dummy.restapiexample.com/public/api/v1/delete/"+targetEmployeeID);

        //Validate response code
        System.out.println("Response code = " + resp.getStatusCode());
        Assert.assertEquals(resp.getStatusCode(),200);

        //Get response as string
        String responseStr = resp.asString();
        System.out.println("Response String: "+ responseStr);
        JsonObject json = JsonParser.parseString(resp.asString()).getAsJsonObject();

        //validate target and message
        Assert.assertEquals(json.get("data").getAsString(),targetEmployeeID);
        Assert.assertEquals(json.get("message").getAsString(),"Successfully! Record has been deleted");
    }
}
