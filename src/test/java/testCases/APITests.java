package testCases;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.*;

public class APITests {

	public Response response;
	public String jsonAsString;
	@Test
	public void myFirstAPITest1()
	{
		given().when().get("https://e2e61-webapp.test-ti-platform.com/AGL/1.0/a/eng/PCTV/CN_APAC_HK/GETGAMES").
		then().assertThat().body("resultCode", equalTo("OK")).and().assertThat().body("resultObj.gamesArray.name", hasSize(32))
		.and().assertThat().statusCode(200).and().assertThat().header("content-type", equalTo("application/json; charset=utf-8"));
	}
	
	@Test
	public void myFirstAPITest2()
	{
		String channel = "PCTV";
		response=given().pathParam("channel1", channel).when().get("https://e2e61-webapp.test-ti-platform.com/AGL/1.0/a/eng/{channel1}/CN_APAC_HK/GETGAMES").
		then().contentType(ContentType.JSON).extract().response();
		jsonAsString = response.asString();
		//System.out.println(JsonPath.from(jsonAsString).get("resultObj.gamesArray"));
		ArrayList<String> list = response.path("resultObj.gamesArray");
		System.out.println(list.size());
		System.out.println("print the first one "+response.path("resultObj.gamesArray[0].name"));
		for(int i=0;i<list.size();i++)
		{
			System.out.println(response.path("resultObj.gamesArray["+i+"].name"));
		}
	}
	
	@Test
	public void myFirstAPITest3()
	{
		String channel = "PCTV";
		String queryParam = "CN_APAC_PH";
		response=given().pathParam("channel1", channel).and().given().param("filter_propertyName", queryParam).when().get("https://e2e61-webapp.test-ti-platform.com/AGL/1.0/a/eng/{channel1}/CN_APAC_PH/TRAY/SEARCH/VOD").
		then().contentType(ContentType.JSON).extract().response();
		jsonAsString = response.asString();
		//System.out.println(JsonPath.from(jsonAsString).get("resultObj.gamesArray"));
		ArrayList<String> list = response.path("resultObj.containers");
		System.out.println(list.size());
		System.out.println("print the first one "+response.path("resultObj.containers[0].name"));
		for(int i=0;i<list.size();i++)
		{
			System.out.println(response.path("resultObj.containers["+i+"].id"));
		}
	}
	
	@Test
	public void myFirstAPITest4()
	{
		HashMap<String, String> mapper = new HashMap<String, String>();
		mapper.put("username", "foxtel_tccndemo3@mailinator.com");
		mapper.put("password", "foxtel123");
		String channel = "PCTV";
		response=given().pathParam("channel1", channel).and().contentType(ContentType.JSON).body(mapper).when().post("https://e2e61-webapp.test-ti-platform.com/AGL/1.0/a/eng/{channel1}/CN_APAC_PH/USER/SESSION").
		then().contentType(ContentType.JSON).extract().response();
		jsonAsString = response.asString();
		//System.out.println(JsonPath.from(jsonAsString).get("resultObj.gamesArray"));
		ArrayList<String> list = response.path("resultObj.properties");
		System.out.println(list.size());
		System.out.println("print the first one "+response.path("resultObj.properties[0]"));
		System.out.println("ResultCode code "+response.path("resultCode"));
		System.out.println("Response code "+response.statusCode());
		System.out.println("header value "+response.header("content-type"));
		System.out.println("Cookie header value "+response.cookie("avs_cookie"));
		for(int i=0;i<list.size();i++)
		{
			System.out.println(response.path("resultObj.properties["+i+"]"));
		}
	}
}
