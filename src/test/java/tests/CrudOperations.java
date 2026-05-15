package tests;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CrudOperations extends BaseTest{
	int postId;


	@DataProvider(name = "userData")
	public Object[][] getUserData() {
	    return new Object[][]{
	        {"Priyanka", "priya@test.com", "QA"},
	        {"Dolly", "doll@test.com", "Automation"},
	        {"TestUser", "test@test.com", "Developer"}
	    };
	}

	// ---------- 1️⃣ GET ALL USERS ----------
	@Test(priority = 3)
	public void getAllUsers() {
		RestAssured.useRelaxedHTTPSValidation();
		
	    Response response =
	            given()
	                .when()
	                .get("/users");
	    
	    System.out.println("GET ALL USERS:");
	    System.out.println("Response: " + response.asString());
	    Assert.assertEquals(response.statusCode(), 200);
	}

	//----------Get User By Id------------------
	@Test(priority = 2)
	public void getUserById() {
		RestAssured.useRelaxedHTTPSValidation();
		
		 Response response =
	                given()
	                    .pathParam("id", postId)
	                .when()
	                    .get("/users/{id}");

	        System.out.println("GET USER BY ID:");
	        System.out.println(response.asString());
	        
	        JsonPath js = response.jsonPath();
	        Assert.assertEquals(js.getInt("id"), postId);
	}
	
	// -----------Create User ------------------	

	@Test(priority = 1, dataProvider = "userData")
	public void createUser(String name, String email, String role) {
	
	    RestAssured.useRelaxedHTTPSValidation();
	
	    String requestBody = "{\n" +
	            "  \"name\": \"" + name + "\",\n" +
	            "  \"email\": \"" + email + "\",\n" +
	            "  \"role\": \"" + role + "\"\n" +
	            "}";

	        Response response =
	                given()
	                    .header("Content-Type", "application/json")
	                    .body(requestBody)
	                .when()
	                    .post("/users");
	        
	        
	        System.out.println("POST CREATE USER:");
	        System.out.println(response.asString());
	       
	    	//validation
			Assert.assertEquals(response.statusCode(), 201);
			
	        JsonPath js = response.jsonPath();
			postId = js.getInt("id");
			System.out.println("POST ID : "+postId);
			Assert.assertNotNull(postId);
			
		

	}
	//-------------------Update user ----------------------		
			@Test(priority = 4)
			public void updateUser() {
				RestAssured.useRelaxedHTTPSValidation();
				String update = "{\n" +
		        		"  \"id\": " + postId + ",\n" +
		                "  \"name\": \"Dolly\",\n" +
		                "  \"email\": \"doll@test.com\",\n" +
		                "  \"role\": \"QandA\"\n" +
		                "}";
		        Response updateResponse =
		        		given()
		        		.header("Content-Type","application/json")
		        		.body(update)
		        	.when()
		        		.put("/users/"+ postId)
		        	.then()
		        	.statusCode(200)
		        	.extract().response();	

		        System.out.println("UPDATE USER BY : "+postId);
		        System.out.println(updateResponse.asString());
		        
		        JsonPath js = updateResponse.jsonPath();
		        Assert.assertEquals(js.getString("name"), "Dolly");
			  }
	//---------------------delete-------------------------------		
		@Test(priority = 5)
		public void deleteUser() {       
    		given()
    	.when()
    		.delete("/users/"+postId)
    	.then()
    	.statusCode(200);
    
    System.out.println("DELETE successful for Post ID: " + postId);

		}
		// ---------Delete All---------------	
		
	@Test(priority = 6)
	public void deleteAllUsers() {
		RestAssured.useRelaxedHTTPSValidation();
        Response response =
    given()
    .when()
        .get("/users");

        List<String> ids = response.jsonPath().getList("id");

        for (String id : ids) {
		    given()
		    .when()
		        .delete("/users/" + id)
		    .then()
		        .statusCode(200);
}

        System.out.println("SUCCESSFULLY DELETED ALL THE USERS");
	}
}
