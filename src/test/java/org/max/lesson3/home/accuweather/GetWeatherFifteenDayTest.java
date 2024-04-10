package org.max.lesson3.home.accuweather;

import io.qameta.allure.*;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.max.lesson3.home.accuweather.AccuweatherAbstractTest;
import org.max.lesson3.home.accuweather.weather.Weather;
import org.max.lesson3.home.accuweather.weather.DailyForecast;
//import org.max.lesson3.seminar.accuweather.weather.Weather;

import java.util.List;

import static io.restassured.RestAssured.given;


public class GetWeatherFifteenDayTest extends AccuweatherAbstractTest {


    @Test
    @DisplayName("GetWeatherFifteenDayTest")
    @Description("GET GetWeatherFifteenDay")
    @Link("")
    @Severity(SeverityLevel.MINOR)
    @Owner("Тимошенко Дмитрий")
    @Story(value = "Тестирование метода GetWeatherFifteenDay")
    void getWeatherFifteenDay_shouldReturn() {

        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/294021")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

//        Assertions Assertions = null;
        Assertions.assertEquals(15,response.getDailyForecasts().size());
        Assertions.assertEquals("F", response.getDailyForecasts().get(0).getTemperature().getMaximum().getUnit());
        Assertions.assertEquals("F", response.getDailyForecasts().get(0).getTemperature().getMinimum().getUnit());
        Assertions.assertNotNull(response.getHeadline());

    }

    @Test
    void getForCastList() {
        List<DailyForecast> dailyForecasts = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl() + "/forecasts/v1/daily/15day/294021")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body()
                .jsonPath().getList("DailyForecasts", DailyForecast.class);
        Assertions.assertEquals(15,dailyForecasts.size());
        Assertions.assertEquals("F",dailyForecasts.get(0).getTemperature().getMaximum().getUnit());
    }

    @Test
    void getString(){
        String response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/15day/294021")
                .then()
                .statusCode(401)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().asString();
        Assertions.assertTrue(response.contains("DailyForecasts"));
        Assertions.assertTrue(response.contains("Headline"));
        }
}


