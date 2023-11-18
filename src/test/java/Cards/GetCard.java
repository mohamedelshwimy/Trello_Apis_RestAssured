package Cards;

import BaseTests.BaseTests;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class GetCard extends BaseTests {

    @Test
    public void testGetCard(){
        extractCardIdFromMember();
        given()
                .spec(requestSpecification)
                .pathParam("id",cardID)
                .header(acceptHeader)
        .when()
                .get(cardsEndpoint+"/{id}")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("name",equalTo(cardName));
    }
}
