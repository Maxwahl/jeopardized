package at.htl;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class ClueEndpointTest {


    @Test
    public void t01_defaultOffset(){
        given()
                .when().get("/clues")
                .then()
                .statusCode(200)
                .body("[0].id",is(1));
    }
    @Test
    public void t02_defaultLimit(){
        given()
                .when().get("/clues")
                .then()
                .statusCode(200)
                .body("size",is(20));
    }

    @Test
    public void t03_customOffset(){
        given()
                .when().get("/clues?offset=10")
                .then()
                .statusCode(200)
                .body("[0].id",is(11)).and().body("size",is(20));
    }
    @Test
    public void t04_customLimit(){
        given()
                .when().get("/clues?limit=10")
                .then()
                .statusCode(200)
                .body("[0].id",is(1)).and().body("size",is(10));
    }
    @Test
    public void t05_getClue(){
        given()
                .when().get("/clues/4")
                .then()
                .statusCode(200)
                .body("answer",is("Harry S. Truman"));
    }
}
