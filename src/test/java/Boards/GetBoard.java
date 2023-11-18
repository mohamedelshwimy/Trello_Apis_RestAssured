package Boards;

import BaseTests.BaseTests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetBoard extends BaseTests {
    @Test
    public void testSingleGetBoard(){
        extractBoardIdFromMember();
        given()
                .spec(requestSpecification)
                .pathParam("id",memberBoardID)
                .header(acceptHeader)
                .log().all()
        .when()
                .get(boardsEndpoint+"{id}")
        .then()
                .log().all()
                .assertThat().statusCode(200);
    }

}
