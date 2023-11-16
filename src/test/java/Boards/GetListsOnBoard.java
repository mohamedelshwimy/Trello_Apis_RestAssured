package Boards;

import BaseTests.BaseTests;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class GetListsOnBoard extends BaseTests {

    @Test
    public void getListsOnBoard(){
        extractBoardIdFromMember();
        given()
                .spec(requestSpecification)
                .pathParam("id",memberBoardID)
        .when()
                .get(boardsEndpoint+"{id}"+"/lists")
        .then()
                .log().all();
    }
}
