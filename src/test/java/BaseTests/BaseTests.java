package BaseTests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class BaseTests {

    //KEY and TOKEN
    protected String key = "cac5e1cb26c4f0c97399b0631e7571c1";
    protected String token = "ATTAa12a75a76d5e93378d38419c78d4a8020a1fc5ea8fe844abdc2fb423058b986327B22C98";

    //MemberID and FullName
    protected String memberUsername = "medoelshwimy0";
    protected String memberID ="65560b36f445d17c193dab0a";
    protected String fullName = "Mohamed Ahmed Ebrahim";
    protected String email = "medoelshwimy0@gmail.com";
    protected String boardName = "Testing Board";
    protected String boardIndex = "0";
    protected String listName = "Testing List";
    protected String cardName = "Testing Card";

    protected static String memberBoardID;
    protected static String boardID;
    protected static String listID;
    protected static String cardID;

    //URL and Endpoints
    protected String mainURL = "https://api.trello.com/1/";
    protected String boardsEndpoint = "boards/";
    protected String membersEndpoint = "members/";
    protected String listEndpoint = "lists/";
    protected String cardsEndpoint = "cards";


    //Headers
    protected Header acceptHeader = new Header("Accept","application/json");

    //RequestSpecification - ResponseSpecification
    protected static RequestSpecification requestSpecification;
    //protected static ResponseSpecification responseSpec;
    protected Response response;

    //Methods
    @BeforeClass
    public void createRequestSpec(){
        HashMap<String,String> keyAndToken = new HashMap<>();
        keyAndToken.put("key",key);
        keyAndToken.put("token",token);

        requestSpecification = new RequestSpecBuilder()
                                        .setBaseUri(mainURL)
                                        .addQueryParams(keyAndToken)
                                        .build();
    }

    //Extracts
    public void extractBoardIdFromMember(){
        memberBoardID = given()
                            .spec(requestSpecification)
                            .pathParam("id", memberUsername)
                            .header(acceptHeader)
                        .when()
                            .get(membersEndpoint+"{id}")
                        .then()
                            .extract().path("idBoards[" + boardIndex + "]");
    }
    public void extractBoardIdUsingPost(){
        boardID = given()
                    .spec(requestSpecification)
                    .queryParam("name",boardName)
                    .contentType(ContentType.JSON)
                .when()
                    .post(boardsEndpoint)
                .then()
                    .extract().path("id");
    }
    public void extractListIdFromBoard(String listIndex){
        extractBoardIdFromMember();
         listID = given()
                     .spec(requestSpecification)
                     .pathParam("id",memberBoardID)
                 .when()
                     .get(boardsEndpoint+"{id}"+"/lists")
                 .then()
                     .extract().path("[" + listIndex + "].id");
    }
    @Test
    public void extractCardIdFromMember(){
        extractBoardIdFromMember();
        cardID = given()
                    .spec(requestSpecification)
                    .pathParam("id",memberBoardID)
                .log().all()
                .when()
                    .get(boardsEndpoint+"{id}"+"/cards")
                .then()
                .log().all()
                    .extract().path("[0].id");
    }
}
