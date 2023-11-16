package Members;

import BaseTests.BaseTests;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class UpdateMember extends BaseTests {
    //New name for the member. Cannot begin or end with a space
    String newFullName = "Mohamed Ebrahim";
    String startSpaceFullName = " Mohamed Ebrahim";
    String endSpaceFullName = "Mohamed Ebrahim ";
    @Test
    public void testUpdateMember(){
        given()
                .spec(requestSpecification)
                .pathParam("id",(Object) memberID)
                //.queryParam("fullName",newFullName)
                //.queryParam("locale","Egypt")
                .header(acceptHeader)
                .log().all()
        .when()
                .put(membersEndpoint+"{id}")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("fullName",equalTo(fullName))
                .assertThat().body("email",equalTo(email));
    }
}
