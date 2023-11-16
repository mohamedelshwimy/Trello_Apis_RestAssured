package List;

import BaseTests.BaseTests;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateList extends BaseTests {

    @Test
    public void testCreateList(){
        extractBoardIdFromMember();
        given()
                .spec(requestSpecification)
                .queryParam("name",listName)
                .queryParam("idBoard",memberBoardID)
                .contentType(ContentType.JSON)
        .when()
                .post(listEndpoint)
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("name",equalTo(listName))
                .assertThat().body("idBoard",equalTo(memberBoardID))
                .assertThat().contentType(ContentType.JSON);
    }
}
