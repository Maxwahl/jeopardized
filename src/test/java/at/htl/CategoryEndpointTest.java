package at.htl;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.closeTo;

@QuarkusTest
public class CategoryEndpointTest {

    @Test
    public void t01_getAllCategories() {
        given()
          .when().get("/categories")
          .then()
             .statusCode(200)
             .body("size()", is(20));
    }
    @Test
    public void t02_getSpecificCategory() {
        given()
                .when().get("/categories/4")
                .then()
                .statusCode(200)
                .body("title",is("prehistoric times"));
    }

    @Test
    public void t03_getAverageValueAllCategories(){
        Double d =given()
                .when().get("/categories/averageValue")
                .then()
                .statusCode(200).extract().body().as(Double.class);
        assertThat(d).isCloseTo(349.947, Percentage.withPercentage(0.01));
    }

    @Test
    public void t04_getAverageValueSpecificCategory(){
        Double d =given()
                .when().get("/categories/averageValue?category=4")
                .then()
                .statusCode(200).extract().body().as(Double.class);
        assertThat(d).isCloseTo(390.90, Percentage.withPercentage(0.01));
    }
}