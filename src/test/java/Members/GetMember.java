package Members;

import BaseTests.BaseTests;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetMember extends BaseTests {

    @Test
    public void testGetMember(){
        given()
                .spec(requestSpecification)
                .pathParam("id",(Object) memberUsername)
                .header(acceptHeader)
                .log().all()
        .when()
                .get(membersEndpoint+"{id}")
        .then()
                .log().all()
                .assertThat().statusCode(200)
                .assertThat().body("fullName",equalTo(fullName))
                .assertThat().body("username",equalTo(memberUsername));
    }
}

