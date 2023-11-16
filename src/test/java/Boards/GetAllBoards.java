package Boards;

import BaseTests.BaseTests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAllBoards extends BaseTests {
    @Test
    public void getSpecificBoardByBoardIndex(){
        extractBoardIdFromMember();
        given()
                .spec(requestSpecification)
                .log().all()
        .when()
                .get(membersEndpoint+"me/boards")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("[" + boardIndex + "].id",equalTo(memberBoardID));
    }
    @Test
    public void getAllMemberBoards(){
        given()
                .spec(requestSpecification)
                .log().all()
        .when()
                .get(membersEndpoint+"me/boards")
        .then()
                .log().ifValidationFails()
                .assertThat().statusCode(200);
    }
}

