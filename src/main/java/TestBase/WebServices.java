
package TestBase;

import java.io.File;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class WebServices {


	static String IP_ADDR;
	static String PORT;
	
	public static Response get_Response(String url){
		
		return RestAssured.given().get(url);

		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).get(url);

	}

	public static Response get_Response_Headers(String url,Map<String,String> headersMap ){

		return RestAssured.given().headers(headersMap).get(url);
		
	//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).get(url);

	}

	public static Response get_Response_Headers_Params(String url,Map<String,String> headersMap,Map<String,String>paramsMap ){

		return RestAssured.given().params(paramsMap).headers(headersMap).get(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).params(paramsMap).headers(headersMap).get(url);

	}

	public static Response get_S3_Response_Headers_Params(String url,Map<String,String> headersMap,Map<String,String>paramsMap ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).params(paramsMap).headers(headersMap).urlEncodingEnabled(false).get(url);

	}

	public static Response get_S3_Response_Params(String url,Map<String,String>paramsMap ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).params(paramsMap).urlEncodingEnabled(false).get(url);

	}


	public static Response get_Response_BasicAuthentiation(String url,String user,String password){

		return RestAssured.given().auth().basic(user, password).get(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).get(url);

	}

	public Response get_Per_Response_BasicAuthentiation(String url,String user,String password){

		return RestAssured.given().auth().basic(user, password).get(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).get(url);

	}

	
	public static Response get_Response_BasicAuthentiation_Headers(String url,String user,String password,Map<String,String> headersMap ){

		return RestAssured.given().auth().basic(user, password).headers(headersMap).get(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).headers(headersMap).get(url);

	}



	public static Response post_Request(String url){

		
		return RestAssured.given().post(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).post(url);

	}
	

	public static Response post_RequestWithBody(String url,String file){

		
		return RestAssured.given().body(file).post(url);
	}

	public static Response post_Multipart(String url, String filePath) {
		
		
	return 	RestAssured.given().
        multiPart(new File(filePath)).
        post("http://54.169.34.162:5252/upload");

        
        
	}
	public static Response post_Request_Headers(String url,Map<String,String> headersMap ){

		return RestAssured.given().headers(headersMap).post(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).post(url);

	}

	public static Response post_Request_BasicAuthentiation(String url,String user,String password){

		return RestAssured.given().auth().basic(user, password).post(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).post(url);

	}

	public static Response post_Request_BasicAuthentiation_Headers(String url,String user,String password,Map<String,String> headersMap ){

		//return RestAssured.given().auth().basic(user, password).headers(headersMap).post(url);
		
		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).headers(headersMap).post(url);

	}

	public static Response post_Request_BasicAuthentiation_Headers_Body(String url,String user,String password,Map<String,String> headersMap,String body ){

		return RestAssured.given().auth().basic(user, password).headers(headersMap).body(body).post(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).headers(headersMap).body(body).post(url);

	}

	public static Response post_Request_Headers_Body(String url,Map<String,String> headersMap,String body ){

		return RestAssured.given().headers(headersMap).body(body).post(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(body).post(url);

	}

	public static Response post_Request_Headers_BodyFilePath(String url,Map<String,String> headersMap,String filePath ){

		return RestAssured.given().headers(headersMap).body(new File(filePath)).post(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(new File(filePath)).post(url);

	}

	public static Response post_Request_Headers_BodyFile(String url,Map<String,String> headersMap,File file ){

		return RestAssured.given().headers(headersMap).body(file).post(url);
		
		//return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(file).post(url);
		

	}
	
	
	public static Response delete_Request(String url){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).delete(url);

	}

	public static Response delete_Request_Headers(String url,Map<String,String> headersMap ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).delete(url);

	}

	public static Response delete_Request_BasicAuthentiation(String url,String user,String password){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).delete(url);

	}

	public static Response delete_Request_BasicAuthentiation_Headers(String url,String user,String password,Map<String,String> headersMap ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).headers(headersMap).delete(url);

	}

	public static Response delete_Request_BasicAuthentiation_Headers_Body(String url,String user,String password,Map<String,String> headersMap,String body ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).headers(headersMap).body(body).delete(url);

	}

	public static Response delete_Request_Headers_Body(String url,Map<String,String> headersMap,String body ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(body).delete(url);

	}

	public static Response delete_Request_Headers_BodyFilePath(String url,Map<String,String> headersMap,String filePath ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(new File(filePath)).delete(url);

	}

	public static Response delete_Request_Headers_BodyFile(String url,Map<String,String> headersMap,File file ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(file).delete(url);

	}

	public static Response put_Request(String url){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).put(url);

	}

	public static Response put_Request_Headers(String url,Map<String,String> headersMap ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).put(url);

	}

	public static Response put_Request_BasicAuthentiation(String url,String user,String password){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).put(url);

	}

	public static Response put_Request_BasicAuthentiation_Headers(String url,String user,String password,Map<String,String> headersMap ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).headers(headersMap).put(url);

	}

	public static Response put_Request_BasicAuthentiation_Headers_Body(String url,String user,String password,Map<String,String> headersMap,String body ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).auth().basic(user, password).headers(headersMap).body(body).put(url);

	}

	public static Response put_Request_Headers_Body(String url,Map<String,String> headersMap,String body ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(body).put(url);

	}

	public static Response put_Request_Headers_BodyFilePath(String url,Map<String,String> headersMap,String filePath ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(new File(filePath)).put(url);

	}

	public static Response put_Request_Headers_BodyFile(String url,Map<String,String> headersMap,File file ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(headersMap).body(file).put(url);

	}

	public static Response put_Request_Headers_Params(String url,Map<String,String> paramMap,File file ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).params(paramMap).put(url);

	}

	public static Response put_S3_Request_Headers_Params(String url,Map<String,String> paramMap,Map<String,String> header ){

		return RestAssured.given().proxy(IP_ADDR,Integer.valueOf(PORT)).headers(header).params(paramMap).urlEncodingEnabled(false).put(url);

	}
	
	public static String fetchvideo() {
		
		
		return get_Response("http://54.169.34.162:5252/video").then().assertThat().statusCode(200).extract().body().asString();
		
	}
	
	public static void main(String[] args) {
		
		String path = System.getProperty("user.dir")+File.separator+"employees.json";
		
		Response res = post_Multipart("",path );
		
		System.out.println(res.then().extract().body().asString());
	}
}
	
