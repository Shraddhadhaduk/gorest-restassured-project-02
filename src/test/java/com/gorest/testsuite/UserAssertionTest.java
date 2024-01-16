package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest extends TestBase {

    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/users?page=1&per_page=20")
                .then().statusCode(200);
        response.log().all();
    }

    //1. Verify that the total record is 20
    @Test
    public void test001(){
        response.body("size()", equalTo(20));
    }

    //2. Verify that the name of id = 5914155 is equal to "Bhishma Joshi"
    @Test
    public void test002(){
        response.body("[0].name", equalTo("Bhishma Joshi"));
    }

    //3. Check the single ‘Name’ in the Array list (Pres. Shresth Kakkar)
    @Test
    public void test003(){
        response.body("name", hasItem("Pres. Shresth Kakkar"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Dandapaani Agarwal, Pres. Shresth Kakkar, Anshula Joshi )
    @Test
    public void test004(){
        response.body("name", hasItems("Dandapaani Agarwal", "Pres. Shresth Kakkar", "Anshula Joshi"));
    }

    //5. Verify the email of userid = 5914153 is equal “pres_kakkar_shresth@kunze.example”
    @Test
    public void test005(){
        response.body("[2].email",equalTo("pres_kakkar_shresth@kunze.example"));
    }

    //6. Verify the status is “Active” of username is “Dandapaani Agarwal”
    @Test
    public void verifyTheStatus() {
        response.body("[8].status", equalTo("active"));
    }

    //7. Verify the Gender = male of username is "Amb. Dandapaani Pilla"
    @Test
    public void test007(){
        response.body("[9].gender", equalTo("male"));
    }
}
