package client.app;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.model2.mvc.service.domain.Search;
import com.model2.mvc.service.domain.User;



public class RestHttpClientApp {
	
	// main Method
	public static void main(String[] args) throws Exception{
		
		////////////////////////////////////////////////////////////////////////////////////////////
		// 주석을 하나씩 처리해가며 실습
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get,Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.addUserTest_JsonSimple();
//		RestHttpClientApp.addUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get,Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.updateUser_Get_Codehaus();
		RestHttpClientApp.updateUser_Post_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post 방식 Request : CodeHaus lib 사용
//		RestHttpClientApp.LoginTest_Codehaus();		
	
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get,Post 방식 Request : CodeHaus lib 사용
//	    RestHttpClientApp.listUser_Post_Codehaus();	    
//	    RestHttpClientApp.listUser_Get_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get 방식 Request : JsonSimple lib 사용
//		RestHttpClientApp.getUserTest_JsonSimple();
				
	}
	
	//================================================================//
		//2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
		public static void addUserTest_JsonSimple() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:80/user/json/addUser";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept","application/json");
			httpPost.setHeader("Content-Type","application/json");
			
			//[ 방법 1 : String 사용]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
			
			//[ 방법 2 : JSONObject 사용]
			JSONObject json = new JSONObject();
			json.put("userId","hhj032323");
			json.put("password","12345");
			json.put("userName","dafdfd");
			json.put("phone","010-1111-1111");
			
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//httpclien.execute method 실행자체가 server로 request를 보내는 것. 
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> 내용읽기(JSON Value 확인)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
			
			//username은 notnull제약조건이 걸려있었다. 
		
		}
				
		public static void addUserTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:80/user/json/addUser";
			HttpPost httpPost = new HttpPost(url);
			
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
//			//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
//			//[ 방법 2 : JSONObject 사용]
//			JSONObject json = new JSONObject();
//			json.put("userId", "admin");
//			json.put("password", "1234");
//			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
			//[ 방법 3 : codehaus 사용]
			
			User user01 =  new User();
			user01.setUserId("mmmmm");
			user01.setPassword("123444");
			user01.setUserName("hhjggee");
			
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(user01);
			System.out.println(jsonValue);
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"UTF-8");
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> 다른 방법으로 serverData 처리 
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> API 확인 : Stream 객체를 직접 전달 
			//JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			User user = objectMapper.readValue(jsonobj.toString(), User.class);
			System.out.println(user);
			 
		}
	//================================================================//	
			
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
		public static void updateUser_Get_Codehaus() throws Exception{
			
			//회원정보 수정을 위해서 필요한 data를 실어서 보낸다. ^0^ 
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			

			//String url="http://127.0.0.1:80/user/json/updateUser/admin/HongGildong/010-1234-5678/GangJinGu/hhj0323@naver.com";

			String url="http://127.0.0.1:80/user/json/updateUser/admin?userName=HoHoHo&"
					+"phone=010-1234-5678&addr=GangJinGu&email=hhj0323@naver.com";
			
			// HttpGet : Http Protocol 의 GET 방식 Request
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-Type","application/json");
			
			// HttpResponse : Http Protocol 응답 Message 추상화
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> 다른 방법으로 serverData 처리 
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> API 확인 : Stream 객체를 직접 전달 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);

			
			//읽어온 data를 JSON으로 파싱해야한다고 생각함. 
			System.out.println();
			System.out.println("[parsing]");
			System.out.println(jsonobj);
			
			ObjectMapper objectMapper = new ObjectMapper();
		
			User user = objectMapper.readValue(jsonobj.toString(), User.class);
			System.out.println();
			System.out.println(user);
		}
	//================================================================//	
		
		//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
		public static void updateUser_Post_Codehaus() throws Exception{
			
			//회원정보 수정을 위해서 필요한 data를 실어서 보낸다. ^0^ 
			
			// HttpClient : Http Protocol 의 client 추상화 
			HttpClient httpClient = new DefaultHttpClient();
			

			//String url="http://127.0.0.1:80/user/json/updateUser/admin/HongGildong/010-1234-5678/GangJinGu/hhj0323@naver.com";

			String url="http://127.0.0.1:80/user/json/updateUser";
			
			// HttpGet : Http Protocol 의 GET 방식 Request
			
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type","application/json");
			
			User user01 =  new User();
			
			user01.setUserId("admin");
			user01.setUserName("HoHoHo");
			user01.setPhone("010-1234-5678");
			user01.setAddr("GangJinGu");
			user01.setEmail("hhj0323@naver.com");
			
			ObjectMapper objectMapper01 = new ObjectMapper();
			
			//Object ==> JSON Value 로 변환
			String jsonValue = objectMapper01.writeValueAsString(user01);
			
			System.out.println(jsonValue);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"UTF-8"); //이거 모름 
			
			httpPost.setEntity(httpEntity01);
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
						
			//==> Response 확인
			System.out.println(httpResponse);
			System.out.println();

			//==> Response 중 entity(DATA) 확인
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream 생성
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> 다른 방법으로 serverData 처리 
			System.out.println("[ Server 에서 받은 Data 확인 ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> API 확인 : Stream 객체를 직접 전달 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);

			
			//읽어온 data를 JSON으로 파싱해야한다고 생각함. 
			System.out.println();
			System.out.println("[parsing]");
			System.out.println(jsonobj);
			
			ObjectMapper objectMapper = new ObjectMapper();
		
			User user = objectMapper.readValue(jsonobj.toString(), User.class);
			System.out.println();
			System.out.println(user);
		}
	//================================================================//			
	public static void getUserTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:80/user/json/getUser/admin";
				
		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println("httpResponse"+httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		
		HttpEntity httpEntity = httpResponse.getEntity();
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib 사용
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:80/user/json/getUser/admin";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData 전달 / JsonSimple 3rd party lib 사용
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:80/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 1 : String 사용]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}"; -> 
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ 방법 2 : JSONObject 사용]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost); //httpclien.execute method 실행자체가 server로 request를 보내는 것. 
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> 내용읽기(JSON Value 확인)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	
	//2.2 Http Protocol POST 방식 Request : FromData전달 
	//==> JsonSimple + codehaus 3rd party lib 사용
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:80/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ 방법 1 : String 사용]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ 방법 2 : JSONObject 사용]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ 방법 3 : codehaus 사용]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	
	//2.2 Http Protocol POST 방식 Request : FromData전달 
	//==> JsonSimple + codehaus 3rd party lib 사용
	
	public static void listUser_Post_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:80/user/json/listUser";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ 방법 3 : codehaus 사용]
		Search search = new Search();
		search.setCurrentPage(3);
		ObjectMapper objectMapper = new ObjectMapper();
		//Object ==> JSON Value 로 변환
		String jsonValue = objectMapper.writeValueAsString(search);
		System.out.println("string = "+jsonValue);
		HttpEntity httpEntity03 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity03);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response 확인
		System.out.println(httpResponse);
		System.out.println();
		
		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
		
		
//		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("map \n\n"+map);
	}	
	
	public static void listUser_Get_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol 의 client 추상화 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:80/user/json/listUser";

		// HttpGet : Http Protocol 의 GET 방식 Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol 응답 Message 추상화
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response 확인
		System.out.println(httpResponse); //
		System.out.println();

		//==> Response 중 entity(DATA) 확인
		HttpEntity httpEntity = httpResponse.getEntity();

		//외부의 file을 읽으려고 io..*^^*
		
		//==> InputStream 생성
		InputStream is = httpEntity.getContent();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> 다른 방법으로 serverData 처리 
		//System.out.println("[ Server 에서 받은 Data 확인 ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API 확인 : Stream 객체를 직접 전달 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> map = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("map \n\n"+map);
	}
}