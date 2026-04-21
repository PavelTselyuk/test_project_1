package api;

import api.entities.*;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class FakeStoreApiTest {

    private final String BASE_URL = "https://fakestoreapi.com/";

    @BeforeAll
    static void setupAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    // Products
    @Test
    public void getAllProductsSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(200)
        );

        List<Product> products = given().
                when()
                .get("products")
                .then().log().all()
                .extract().body().jsonPath().getList(".", Product.class);

        Assertions.assertFalse(products.isEmpty());
        Assertions.assertNotNull(products.getFirst().getTitle());
        products.forEach(x -> Assertions.assertTrue(x.getImage().contains("fakestoreapi")));
    }

    @Test
    public void getOneProductSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(200)
        );

        given().
                when()
                .get("products/2")
                .then().log().all()
                .body("title", notNullValue())
                .body("id", equalTo(2))
                .body("image", containsString("fakestoreapi.com/img"))
                .extract().as(Product.class);
    }

    @Test
    public void addProductSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(201)
        );
        Product product = new Product(
                0,
                "Quelling blade",
                100.9,
                "Amazing description",
                "awesomeness",
                "http://example.com.jpg"
        );


        Product productResponse = given()
                .body(product)
                .when()
                .post("products")
                .then().log().body()
                .extract().as(Product.class);

        Assertions.assertEquals(product, productResponse);
    }

    @Test
    public void updateProductSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(200)
        );
        Product product = new Product(
                25,
                "Butterfly",
                150.9,
                "Great description",
                "http://example-image.com.jpg",
                "Coolness"
        );


        Product productResponse = given()
                .body(product)
                .when()
                .put("products/3")
                .then().log().body()
                .extract().as(Product.class);

        Assertions.assertEquals(product, productResponse);
    }

    @Test
    public void deleteProductSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(200)
        );


        given()
                .when()
                .delete("products/2")
                .then().log().body();
    }

    // Login
    @Test
    public void successfulLogin() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(201)
        );
        String tokenPart = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9";
        LoginRequest loginRequest = new LoginRequest("kate_h", "kfejk@*_");

        LoginSuccess successLog = given()
                .body(loginRequest)
                .when()
                .post("auth/login")
                .then().log().all()
                .extract().as(LoginSuccess.class);
        Assertions.assertTrue(successLog.getToken().contains(tokenPart));
    }

    @Test
    public void failureLogin() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(401)
        );
        LoginRequest loginRequest = new LoginRequest("nonExistingUser", "wrongPassword");

        given()
                .body(loginRequest)
                .when()
                .post("auth/login")
                .then().log().body();
    }

    // Users
    @Test
    public void getAllUsersSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(200)
        );

        List<GetUserResponse> users = given().
                when()
                .get("users")
                .then().log().all()
                .extract().body().jsonPath().getList(".", GetUserResponse.class);

        Assertions.assertFalse(users.isEmpty());
        Assertions.assertNotNull(users.getFirst().getName());
        users.forEach(x -> Assertions.assertTrue(x.getEmail().contains("@gmail")));
    }

    @Test
    public void getOneUserSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(200)
        );

        GetUserResponse user = given().
                when()
                .get("users/1")
                .then().log().all()
                .extract().as(GetUserResponse.class);


        Assertions.assertNotNull(user);
        Assertions.assertEquals("johnd", user.getUsername());
    }

    @Test
    public void addUserSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(201)
        );
        User user = new User(
                0,
                "username",
                "test@test.com",
                "pass1111"
        );

        Integer id = given()
                .body(user)
                .when()
                .post("users")
                .then().log().all()
                .extract().jsonPath().getInt("id");
        Assertions.assertNotNull(id);
        Assertions.assertInstanceOf(Integer.class, id);
    }

    @Test
    public void updateUserSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(200)
        );
        User user = new User(
                15,
                "updated_username",
                "updated_email",
                "updated_password"
        );

        User userResponse = given()
                .body(user)
                .when()
                .put("users/150")
                .then().log().body()
                .extract().as(User.class);
        Assertions.assertEquals(user, userResponse);

    }

    @Test
    public void deleteUserSuccess() {
        Specifications.installSpecification(
                Specifications.requestSpecification(BASE_URL),
                Specifications.responseSpecification(200)
        );

        given()
                .when()
                .delete("users/150")
                .then().log().body();
    }
}
