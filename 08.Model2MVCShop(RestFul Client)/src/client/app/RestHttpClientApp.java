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
		// �ּ��� �ϳ��� ó���ذ��� �ǽ�
		////////////////////////////////////////////////////////////////////////////////////////////
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get,Post ��� Request : JsonSimple lib ���
//		RestHttpClientApp.addUserTest_JsonSimple();
		RestHttpClientApp.addUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get ��� Request : CodeHaus lib ���
//		RestHttpClientApp.getUserTest_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get,Post ��� Request : CodeHaus lib ���
//		RestHttpClientApp.updateUser_Get_Codehaus();
//		RestHttpClientApp.updateUser_Post_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 2.1 Http Post ��� Request : JsonSimple lib ���
//		RestHttpClientApp.LoginTest_JsonSimple();
		
//		System.out.println("\n====================================\n");
//		// 1.2 Http Post ��� Request : CodeHaus lib ���
//		RestHttpClientApp.LoginTest_Codehaus();		
	
//		System.out.println("\n====================================\n");
//		// 1.2 Http Get,Post ��� Request : CodeHaus lib ���
//	    RestHttpClientApp.listUser_Post_Codehaus();	    
//	    RestHttpClientApp.listUser_Get_Codehaus();
		
//		System.out.println("\n====================================\n");
//		// 1.1 Http Get ��� Request : JsonSimple lib ���
//		RestHttpClientApp.getUserTest_JsonSimple();
				
	}
	
	//================================================================//
		//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
		public static void addUserTest_JsonSimple() throws Exception{
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:80/user/json/addUser";
			HttpPost httpPost = new HttpPost(url);
			httpPost.setHeader("Accept","application/json");
			httpPost.setHeader("Content-Type","application/json");
			
			//[ ��� 1 : String ���]
//				String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//				HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
			
			//[ ��� 2 : JSONObject ���]
			JSONObject json = new JSONObject();
			json.put("userId","hhj032323");
			json.put("password","12345");
			json.put("userName","dafdfd");
			json.put("phone","010-1111-1111");
			
			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//httpclien.execute method ������ü�� server�� request�� ������ ��. 
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> �����б�(JSON Value Ȯ��)
			JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			System.out.println(jsonobj);
			
			//username�� notnull���������� �ɷ��־���. 
		
		}
				
		public static void addUserTest_Codehaus() throws Exception{
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			
			String url = "http://127.0.0.1:80/user/json/addUser";
			HttpPost httpPost = new HttpPost(url);
			
			httpPost.setHeader("Accept", "application/json");
			httpPost.setHeader("Content-Type", "application/json");
			
//			//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
//			//[ ��� 2 : JSONObject ���]
//			JSONObject json = new JSONObject();
//			json.put("userId", "admin");
//			json.put("password", "1234");
//			HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
			//[ ��� 3 : codehaus ���]
			
			User user01 =  new User();
			user01.setUserId("Lovebitcamp");
			user01.setPassword("00000");
			user01.setUserName("hyeonJi");
			
			ObjectMapper objectMapper01 = new ObjectMapper();
			//Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(user01);
			System.out.println(jsonValue);
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"UTF-8");
			httpPost.setEntity(httpEntity01);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			//JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
			System.out.println(jsonobj);
		
			ObjectMapper objectMapper = new ObjectMapper();
			User user = objectMapper.readValue(jsonobj.toString(), User.class);
			System.out.println(user);
			 
		}
	//================================================================//	
			
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
		public static void updateUser_Get_Codehaus() throws Exception{
			
			//ȸ������ ������ ���ؼ� �ʿ��� data�� �Ǿ ������. ^0^ 
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			

			//String url="http://127.0.0.1:80/user/json/updateUser/admin/HongGildong/010-1234-5678/GangJinGu/hhj0323@naver.com";

			String url="http://127.0.0.1:80/user/json/updateUser/admin?userName=HoHoHo&"
					+"phone=010-1234-5678&addr=GangJinGu&email=hhj0323@naver.com";
			
			// HttpGet : Http Protocol �� GET ��� Request
			HttpGet httpGet = new HttpGet(url);
			httpGet.setHeader("Accept", "application/json");
			httpGet.setHeader("Content-Type","application/json");
			
			// HttpResponse : Http Protocol ���� Message �߻�ȭ
			HttpResponse httpResponse = httpClient.execute(httpGet);
			
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);

			
			//�о�� data�� JSON���� �Ľ��ؾ��Ѵٰ� ������. 
			System.out.println();
			System.out.println("[parsing]");
			System.out.println(jsonobj);
			
			ObjectMapper objectMapper = new ObjectMapper();
		
			User user = objectMapper.readValue(jsonobj.toString(), User.class);
			System.out.println();
			System.out.println(user);
		}
	//================================================================//	
		
		//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
		public static void updateUser_Post_Codehaus() throws Exception{
			
			//ȸ������ ������ ���ؼ� �ʿ��� data�� �Ǿ ������. ^0^ 
			
			// HttpClient : Http Protocol �� client �߻�ȭ 
			HttpClient httpClient = new DefaultHttpClient();
			

			//String url="http://127.0.0.1:80/user/json/updateUser/admin/HongGildong/010-1234-5678/GangJinGu/hhj0323@naver.com";

			String url="http://127.0.0.1:80/user/json/updateUser";
			
			// HttpGet : Http Protocol �� GET ��� Request
			
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
			
			//Object ==> JSON Value �� ��ȯ
			String jsonValue = objectMapper01.writeValueAsString(user01);
			
			System.out.println(jsonValue);
			
			HttpEntity httpEntity01 = new StringEntity(jsonValue,"UTF-8"); //�̰� �� 
			
			httpPost.setEntity(httpEntity01);
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
						
			//==> Response Ȯ��
			System.out.println(httpResponse);
			System.out.println();

			//==> Response �� entity(DATA) Ȯ��
			HttpEntity httpEntity = httpResponse.getEntity();
			
			//==> InputStream ����
			InputStream is = httpEntity.getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			
			//==> �ٸ� ������� serverData ó�� 
			System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
			String serverData = br.readLine();
			System.out.println(serverData);
			
			//==> API Ȯ�� : Stream ��ü�� ���� ���� 
			JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);

			
			//�о�� data�� JSON���� �Ľ��ؾ��Ѵٰ� ������. 
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
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:80/user/json/getUser/admin";
				
		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println("httpResponse"+httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		
		HttpEntity httpEntity = httpResponse.getEntity();
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	}
	
	//1.2 Http Protocol GET Request : JsonSimple + codehaus 3rd party lib ���
	public static void getUserTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= 	"http://127.0.0.1:80/user/json/getUser/admin";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}
//================================================================//	
	
//================================================================//
	//2.1 Http Protocol POST Request : FromData ���� / JsonSimple 3rd party lib ���
	public static void LoginTest_JsonSimple() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:80/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 1 : String ���]
//			String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}"; -> 
//			HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
		
		//[ ��� 2 : JSONObject ���]
		JSONObject json = new JSONObject();
		json.put("userId", "admin");
		json.put("password", "1234");
		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");

		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost); //httpclien.execute method ������ü�� server�� request�� ������ ��. 
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		String serverData = br.readLine();
		System.out.println(serverData);
		
		//==> �����б�(JSON Value Ȯ��)
		JSONObject jsonobj = (JSONObject)JSONValue.parse(serverData);
		System.out.println(jsonobj);
	
	}
	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	public static void LoginTest_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:80/user/json/login";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
//		//[ ��� 1 : String ���]
//		String data =  "{\"userId\":\"admin\",\"password\":\"1234\"}";
//		HttpEntity httpEntity01 = new StringEntity(data,"utf-8");
	
//		//[ ��� 2 : JSONObject ���]
//		JSONObject json = new JSONObject();
//		json.put("userId", "admin");
//		json.put("password", "1234");
//		HttpEntity httpEntity01 = new StringEntity(json.toString(),"utf-8");
		
		//[ ��� 3 : codehaus ���]
		User user01 =  new User();
		user01.setUserId("admin");
		user01.setPassword("1234");
		ObjectMapper objectMapper01 = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper01.writeValueAsString(user01);
		HttpEntity httpEntity01 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity01);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		 User user = objectMapper.readValue(jsonobj.toString(), User.class);
		 System.out.println(user);
	}	
	
	//2.2 Http Protocol POST ��� Request : FromData���� 
	//==> JsonSimple + codehaus 3rd party lib ���
	
	public static void listUser_Post_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url = "http://127.0.0.1:80/user/json/listUser";
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("Accept", "application/json");
		httpPost.setHeader("Content-Type", "application/json");
		
		//[ ��� 3 : codehaus ���]
		Search search = new Search();
		search.setCurrentPage(3);
		ObjectMapper objectMapper = new ObjectMapper();
		//Object ==> JSON Value �� ��ȯ
		String jsonValue = objectMapper.writeValueAsString(search);
		System.out.println("string = "+jsonValue);
		HttpEntity httpEntity03 = new StringEntity(jsonValue,"utf-8");
		
		httpPost.setEntity(httpEntity03);
		HttpResponse httpResponse = httpClient.execute(httpPost);
		
		//==> Response Ȯ��
		System.out.println(httpResponse);
		System.out.println();
		
		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
		
		
//		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("map \n\n"+map);
	}	
	
	public static void listUser_Get_Codehaus() throws Exception{
		
		// HttpClient : Http Protocol �� client �߻�ȭ 
		HttpClient httpClient = new DefaultHttpClient();
		
		String url= "http://127.0.0.1:80/user/json/listUser";

		// HttpGet : Http Protocol �� GET ��� Request
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "application/json");
		httpGet.setHeader("Content-Type", "application/json");
		
		// HttpResponse : Http Protocol ���� Message �߻�ȭ
		HttpResponse httpResponse = httpClient.execute(httpGet);
		
		//==> Response Ȯ��
		System.out.println(httpResponse); //
		System.out.println();

		//==> Response �� entity(DATA) Ȯ��
		HttpEntity httpEntity = httpResponse.getEntity();

		//�ܺ��� file�� �������� io..*^^*
		
		//==> InputStream ����
		InputStream is = httpEntity.getContent();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		
		//==> �ٸ� ������� serverData ó�� 
		//System.out.println("[ Server ���� ���� Data Ȯ�� ] ");
		//String serverData = br.readLine();
		//System.out.println(serverData);
		
		//==> API Ȯ�� : Stream ��ü�� ���� ���� 
		JSONObject jsonobj = (JSONObject)JSONValue.parse(br);
		System.out.println(jsonobj);
	
		ObjectMapper objectMapper = new ObjectMapper();
		
		Map<String, Object> map = objectMapper.readValue(jsonobj.toString(), Map.class);
		System.out.println("map \n\n"+map);
	}
}