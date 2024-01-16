package com.gorest.testsuite;

import com.gorest.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class PostsAssertionTest extends TestBase {
    static ValidatableResponse response;

    @BeforeClass
    public static void response() {
        response = given()
                .when()
                .get("/posts?page=1&per_page=25")
                .then().statusCode(200);
        response.log().all();
    }

    //1. Verify that the total record is 25
    @Test
    public void test001(){
        response.body("size()", equalTo(25));
    }

    //2. Verify that the title of id = 94000 is equal to "	Temeritas tardus omnis nam quia conspergo confero."
    @Test
    public void test002(){
        response.body("[0].title", equalTo("Temeritas tardus omnis nam quia conspergo confero."));
    }

    //3. Check the single user_id in the Array list (5914254)
    @Test
    public void test003(){
        response.body("user_id", hasItem(5914254));
    }

    //4. Check the multiple ids in the ArrayList (5914254, 5914253, 5914251)
    @Test
    public void test004(){
        response.body("user_id", hasItems(5914254, 5914253, 5914251));
    }

    //5. Verify the body of userid = 5914254 is equal "Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."
    @Test
    public void test005(){
        response.body("[0].body", equalTo("Depulso auris vereor. Acceptus suffragium repudiandae. Cotidie cubicularis deprecator. Virtus validus aliquid. Adduco somnus quibusdam. Despecto nihil vinum. Claudeo nam ullus. Sursum tutamen rerum. Cenaculum tabula adultus. Charisma thema super. Vobis cavus clibanus. Quo quod avaritia. Condico apparatus nulla. Textilis depopulo acidus."));
    }
}
