package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import pojo_classes.GetCourseDetails;
import pojo_classes.api;
import pojo_classes.webAutomation;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestCases {
	String access_token;

	//Test case 1 - Get access token from authorization server.
	@Test(priority=1)
	public void get_access_token()
	{
		String auth_server_response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.formParam("grant_type", "client_credentials")
				.formParam("scope", "trust")
				.when().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
				.then().assertThat().statusCode(200).body("token_type", equalTo("Bearer")).extract().response().asString();

		JsonPath js = new JsonPath(auth_server_response);
		access_token = js.get("access_token");
		System.out.println("your access token is = " + access_token);
	}

	//Test case 2 - By using token id, access the end-point, and validate linkedIn id value from response.
	@Test(priority=2)
	public void access_endpoint()
	{
		GetCourseDetails endpoint_response = given().queryParam("access_token", access_token)
				.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourseDetails.class);

		String actualLinkedIn = endpoint_response.getLinkedIn();
		
		System.out.println(actualLinkedIn);
		
		String expectedLinkedIn = "https://www.linkedin.com/in/rahul-shetty-trainer/";
		Assert.assertTrue(actualLinkedIn.equals(expectedLinkedIn));
	}

	
	//Test case 3 - Print the webAutomation course titles and prices.
	@Test(priority=3)
	public void webAutomation_details()
	{
		GetCourseDetails endpoint_response2 = given().queryParam("access_token", access_token)
				.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourseDetails.class);
		
		List<webAutomation> webAutomation = endpoint_response2.getCourses().getWebAutomation();
		for(int i=0;i<webAutomation.size();i++)
		{
			String webAutomationTitle = webAutomation.get(i).getCourseTitle();
			String webAutomationPrice = webAutomation.get(i).getPrice();
			System.out.println(webAutomationTitle +" = "+ webAutomationPrice);
		}
	}
	
	//Test case 4 - printout the price associated with specific courseTitle
	@Test(priority=4)
	public void webAutomation_prices()
	{
		GetCourseDetails endpoint_response = given().queryParam("access_token", access_token)
				.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourseDetails.class);
		
		List<webAutomation> webAutomation = endpoint_response.getCourses().getWebAutomation();
		for(int i=0;i<webAutomation.size();i++)
		{
			String courseTitle = webAutomation.get(i).getCourseTitle();
			if(courseTitle.equalsIgnoreCase("Protractor"))
			{
				String Courseprices = webAutomation.get(i).getPrice();
				System.out.println("Price of the Protractor courseTitle is = "+Courseprices);
			}
		}
	}
	
	//Test case 5 - compare the actual Vs expected courseTitles available under api courses
	@Test(priority=5)
	public void validate_courseTitles()
	{
		GetCourseDetails endpoint_response3 = given().queryParam("access_token", access_token)
				.when().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourseDetails.class);
		
		//create array-list to store actual api course titles
		ArrayList<String> actualTitles = new ArrayList<String>();
		
		List<api> api_courses = endpoint_response3.getCourses().getApi();
		for(int i=0;i<api_courses.size();i++)
		{
			String course_titles = api_courses.get(i).getCourseTitle();
			actualTitles.add(course_titles);
		}
		
		//create array-list to store expected api course titles
		String[] expected = {"Rest Assured Automation using Java","SoapUI Webservices testing"};
		List<String> expectedTitles = Arrays.asList(expected);
		
		//compare actual and expected course titles of api courses.
		Assert.assertTrue(actualTitles.equals(expectedTitles));
		
		
	}
	
	

}
