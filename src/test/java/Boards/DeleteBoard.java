package Boards;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class DeleteBoard extends GetBoard{

    @Test
    public void testDeleteBoard(){
        extractBoardIdUsingPost();
        given()
                .spec(requestSpecification)
                .pathParam("id",(Object)boardID)
        .when()
                .delete(boardsEndpoint+"{id}")
        .then()
                .log().all()
                .assertThat().body("value",equalTo(null))
                .assertThat().statusCode(200);
    }
    @Test
    public void testDeleteSpecificBoard(){
        extractBoardIdFromMember();
        given()
                .spec(requestSpecification)
                .pathParam("id", (Object) memberBoardID)
                .when()
                .delete(boardsEndpoint + "{id}")
                .then()
                .log().all();
    }
}
