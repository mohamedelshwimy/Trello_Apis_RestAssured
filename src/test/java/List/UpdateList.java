package List;

import BaseTests.BaseTests;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
public class UpdateList extends BaseTests {
    String newListName = "Updated List Name";
    @Test
    public void testUpdateList(){
        extractListIdFromBoard("0");
        given()
                .spec(requestSpecification).pathParam("id",listID)
                .queryParam("name",newListName)
        .when()
                .put(listEndpoint+"{id}")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("name",equalTo(newListName));
    }
}
