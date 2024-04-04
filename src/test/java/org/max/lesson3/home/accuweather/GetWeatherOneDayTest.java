package org.max.lesson3.home.accuweather;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
//import org.max.lesson3.seminar.accuweather.AccuweatherAbstractTest;
//import org.max.lesson3.seminar.accuweather.weather.Weather;
import org.max.lesson3.home.accuweather.AccuweatherAbstractTest;
import org.max.lesson3.home.accuweather.weather.Weather;
import static io.restassured.RestAssured.given;

public class GetWeatherOneDayTest extends AccuweatherAbstractTest {

    @Test
    void getWeatherOneDay_shouldReturn() {

        Weather response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/294021")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().as(Weather.class);

        Assertions.assertEquals(1,response.getDailyForecasts().size());
        Assertions.assertEquals("F", response.getDailyForecasts().get(0).getTemperature().getMaximum().getUnit());
        Assertions.assertNotNull(response.getHeadline());
        Assertions.assertEquals("F", response.getDailyForecasts().get(0).getTemperature().getMinimum().getUnit());

    }
    @Test
    void getString(){
        String response = given()
                .queryParam("apikey", getApiKey())
                .when()
                .get(getBaseUrl()+"/forecasts/v1/daily/1day/294021")
                .then()
                .statusCode(200)
                .time(Matchers.lessThan(2000l))
                .extract()
                .response()
                .body().asString();
        Assertions.assertTrue(response.contains("DailyForecasts"));
        Assertions.assertTrue(response.contains("Headline"));
        Assertions.assertTrue(response.contains("Date"));

    }

}
