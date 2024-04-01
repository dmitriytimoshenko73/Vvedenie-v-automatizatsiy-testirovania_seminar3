package org.max.lesson3.seminar.accuweather;

import io.restassured.http.Method;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.restassured.RestAssured.given;

public class GetWeatherTenDayTest extends AccuweatherAbstractTest{

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 44, 15})
    void get_ten_day_return_401(int code) {

        given()
                .queryParam("apikey", getApiKey())
                .pathParam("version", "v1")
                .pathParam("location", code)
                .when()
                .request(Method.GET,getBaseUrl()+"/forecasts/{version}/daily/10day/{location}")
                .then()
                .statusCode(401);
    }
}
