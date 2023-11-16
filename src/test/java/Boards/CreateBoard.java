package Boards;

import BaseTests.BaseTests;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CreateBoard extends BaseTests {

    @Test
    public void testCreateBoard(){
        given()
                .spec(requestSpecification)
                .queryParam("name",boardName)
                .contentType(ContentType.JSON)
        .when()
                .post(boardsEndpoint)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("name",equalTo(boardName))
                .assertThat().contentType(ContentType.JSON);

        extractBoardIdFromMember(); //get BoardID
    }
    @Test
    public void testCreateBoardWithEmptyName(){
        given()
                .spec(requestSpecification)
                .queryParam("name","")
                .contentType(ContentType.JSON)
        .when()
                .post(boardsEndpoint)
        .then()
                .log().all()
                .assertThat().statusCode(400)
                .assertThat().body("message",equalTo("invalid value for name"))
                .assertThat().contentType(ContentType.JSON);
    }
    @Test
    public void testCreateBoardWithSpecialChar(){
        given()
                .spec(requestSpecification)
                .queryParam("name","$$$$")
                .contentType(ContentType.JSON)
        .when()
                .post(boardsEndpoint)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("name",equalTo("$$$$"))
                .assertThat().contentType(ContentType.JSON);
    }

}
