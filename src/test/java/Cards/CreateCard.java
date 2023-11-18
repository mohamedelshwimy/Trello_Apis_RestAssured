package Cards;

import BaseTests.BaseTests;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class CreateCard extends BaseTests {

    @Test
    public void testCreateCard(){
        extractListIdFromBoard("0");
        given().spec(requestSpecification)
                .queryParam("idList",listID)
                .queryParam("name",cardName)
                .header(acceptHeader)
                .contentType(ContentType.JSON)
                .log().all()
        .when()
                .post(cardsEndpoint)
        .then()
                .log().all()
                .assertThat().statusCode(200);
    }

}
