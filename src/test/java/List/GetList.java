package List;

import BaseTests.BaseTests;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class GetList extends BaseTests {
    @Test
    public void testGetListFromBoard(){
        extractListIdFromBoard("0");
        given()
                .spec(requestSpecification)
                .pathParam("id",listID)
        .when()
                .get(listEndpoint+"{id}")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("id",equalTo(listID));
    }
}
