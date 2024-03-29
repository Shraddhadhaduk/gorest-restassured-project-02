package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
    }

    //1. Extract the All Ids
    @Test
    public void test001() {
        List<Integer> allIds = response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of  all Ids is : " + allIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the all Names
    @Test
    public void test002() {
        List<String> allName = response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of  all name is : " + allName);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the name of 5th object
    @Test
    public void test003(){
    String name = response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of  all name is : " + name);
        System.out.println("------------------End of Test---------------------------");
}

    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004(){
        List<HashMap<String, ?>> names = response.extract().path("findAll{it.status == 'inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose status are inactive : " + names);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005(){
        List<HashMap<String, ?>> gender = response.extract().path("findAll{it.status == 'active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all gender whose status are active : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test006(){
        List<HashMap<String, ?>> name = response.extract().path("findAll{it.gender == 'female'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The names of all object whose gender are female : " + name);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007(){
        List<HashMap<String, ?>> email = response.extract().path("findAll{it.status == 'inactive'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the emails of the object where status inactive is : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test008(){
        List<HashMap<String, ?>> ids = response.extract().path("findAll{it.gender == 'male'}.id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the emails of the object where status inactive is : " + ids);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test009(){
        List<?> status = response.extract().path("status");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all status are : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name =Avantika Kaur
    @Test
    public void test010(){
        String email = response.extract().path("find{it.name == 'Avantika Kaur'}.email");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The all the emails of the object where name is : " + email);
        System.out.println("------------------End of Test---------------------------");
    }

    //11. Get gender of id = 5914155
    @Test
    public void test011(){
        String gender = response.extract().path("[0].gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The gender is : " + gender);
        System.out.println("------------------End of Test---------------------------");
    }
}
