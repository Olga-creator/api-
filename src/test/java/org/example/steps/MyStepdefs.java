package org.example.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Если;
import io.cucumber.java.ru.Тогда;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MyStepdefs {
    static final private Map<String, Object> storage = new HashMap<String, Object>();
    public static final String RESPONSE = "response";
    @Дано("используем стандартные параметры запроса")
    public void используемСтандартныеПараметрыЗапроса() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2/") // задаём базовый адрес каждого ресурса
                .addHeader("api_key", System.getProperty("api.key")) // задаём заголовок с токеном для авторизации
                // обязательно учитывайте, что любые приватные данные необходимо хранить в отдельных файлах, которые НЕ публикуютя
                // в открытых репозиториях (в закрытых тоже лучше не публиковать)
                .setAccept(ContentType.JSON) // задаём заголовок accept
                .setContentType(ContentType.JSON) // задаём заголовок content-type
                .log(LogDetail.ALL) // дополнительная инструкция полного логгирования для RestAssured
                .build(); // после этой команды происходит формирование стандартной "шапки" запроса.
    }

    @Если("сделать GET запрос на endpoint {string}")
    public void сделатьGETЗапросНаEndpoint(String endpoint) {
       Response response = given()
                .when()
                .post("/endpoint");
       storage.put(RESPONSE, response);
    }

    @Тогда("код ответа будет {int}")
    public void кодОтветаБудет(int code) {
        Response response = (Response) storage.get(RESPONSE);
        response.then().statusCode(code);
    }
}
