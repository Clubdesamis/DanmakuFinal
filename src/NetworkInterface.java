import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class NetworkInterface {
	private final String httpHeader = "http://";
	private final String ipAddress = "144.217.83.146:8080";
	private final String loginPath = "/login";
	private final String publishScorePath = "/score/create";
	private final String getMapsPath = "/map/all";
	private final String registerPath = "/register";
	private final int connectTimeout = 2000;
	private final int readTimeout = 2000;

	public final static int USERNAME_EXISTS = 1;
	public final static int EMAIL_EXISTS = 2;
	public final static int USERNAME_NOT_FOUND = 1;
	public final static int INVALID_PASSWORD = 2;
	public final static int INVALID_TOKEN = 1;
	public final static int INVALID_ID = 1;
	public final static int INVALID_EMAIL = 1;
	public final static int INVALID_THREAD_VALUES = 2;
	public final static int EMAIL_NOT_VERIFIED = 1;
	public final static int INVALID_RESET_TOKEN = 1;
	public final static int INVALID_SERVER_RESPONSE = -1;
	public final static int NO_RESPONSE_FROM_SERVER = -2;
	public final static int VALID_RESPONSE = 0;

	public int validateLogin(String username, String password){
		try{
			System.out.println("Sending login validation with username : " + username + " password : " + password);
			URL urlDanmaku = new URL(httpHeader + ipAddress + loginPath);
			HttpURLConnection connection = (HttpURLConnection) urlDanmaku.openConnection();
			connection.setRequestMethod("GET");

			Map<String, String> arguments = new HashMap<>();
			arguments.put("username", username);
			arguments.put("password", password);

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			connection.setDoOutput(true);

			String json = "{" + (char)0x22 + "username" + (char)0x22  + ": " + (char)0x22 + username + (char)0x22 + "," + (char)0x22 + "password" + (char)0x22 + ": " + (char)0x22 + password + (char)0x22 + "}";
					

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			//out.writeBytes(getParamsString(arguments));
			out.writeBytes(json);
			out.flush();
			out.close();

			int status = connection.getResponseCode();

			//InputStream errorStream = connection.getErrorStream();
			//String test = errorStream.toString();




			BufferedReader bufferIn;
			if(status != 200){
				System.out.println("Invalid HTTP response code in login validation");
				System.out.println("Response code : " + Integer.toString(status));
				//return INVALID_SERVER_RESPONSE;
				bufferIn = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
			}
			else{
				bufferIn = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			}

			String inputLine;
			StringBuffer content = new StringBuffer();
			while((inputLine = bufferIn.readLine()) != null){
				content.append(inputLine);
			}
			if(status == 200){
				Variables.userId = getId(content.toString());
			}

			String returnValueString = "" + content.toString().charAt(9);
			int returnValue = Integer.parseInt(returnValueString);
			if(returnValue == 0){
				return VALID_RESPONSE;
			}
			else if(returnValue == 1){
				return INVALID_ID;
			}
			else if(returnValue == 2){
				return INVALID_PASSWORD;
			}
		}
		catch(Exception e){
			System.out.println(e.toString());
			return NO_RESPONSE_FROM_SERVER;
		}
		return NO_RESPONSE_FROM_SERVER;
	}

	public int getId(String content){
		String idString = "";
		boolean onId = false;
		for(int i = 0; i < content.length(); i++){
			if(content.charAt(i) == 'i' && content.charAt(i + 1) == 'd'){
				i += 4;
				onId = true;
			}
			if(onId){
				if ((content.charAt(i) >= '0') && (content.charAt(i) <= '9')){
					idString += content.charAt(i);
				}
				else{
					onId = false;
					i = content.length();
				}
			}
		}
		return Integer.parseInt(idString);
	}

	public String getMapID(String mapName){
		try{
			URL urlDanmaku = new URL(httpHeader + ipAddress + getMapsPath);
			HttpURLConnection connection = (HttpURLConnection) urlDanmaku.openConnection();
			connection.setRequestMethod("GET");

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			connection.setDoOutput(true);

			String json = "";

			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(json);
			out.flush();
			out.close();

			int status = connection.getResponseCode();

			BufferedReader bufferIn;
			if(status != 200){
				System.out.println("Invalid HTTP response code in map fetch");
				System.out.println("Response code : " + Integer.toString(status));
				//return INVALID_SERVER_RESPONSE;
				bufferIn = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
			}
			else{
				bufferIn = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			}

			String inputLine;
			StringBuffer content = new StringBuffer();
			while((inputLine = bufferIn.readLine()) != null){
				content.append(inputLine);
			}


		}
		catch(Exception e){

		}
		return "nullString";
	}

	public int postScore(String username, String id, int score){
		try{
			System.out.println("Sending score validation with username : " + username + " Map name : " + id + " Score : " + Integer.toString(score));
			URL urlDanmaku = new URL(httpHeader + ipAddress + publishScorePath);
			HttpURLConnection connection = (HttpURLConnection) urlDanmaku.openConnection();
			connection.setRequestMethod("POST");

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(connectTimeout);
			connection.setReadTimeout(readTimeout);
			connection.setDoOutput(true);


			//String json = "{" + (char)0x22 + "username" + (char)0x22  + ": " + (char)0x22 + username + (char)0x22 + "," + (char)0x22 + "password" + (char)0x22 + ": " + (char)0x22 + password + (char)0x22 + "}";

			String json = "{" + (char)0x22 + "ownerId" + (char)0x22 + ": " + (char)0x22 + Integer.toString(Variables.userId) + (char)0x22 + "," +
					(char)0x22 + "mapId" + (char)0x22 + ": " + (char)0x22 + id.substring(0, id.length() - 5) + (char)0x22 + "," + (char)0x22 + "value" +
					(char)0x22 + ": " + (char)0x22 + Integer.toString(score) + (char)0x22 + "}";



			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(json);
			out.flush();
			out.close();

			int status = connection.getResponseCode();

			BufferedReader bufferIn;

			if(status == 200){
				bufferIn = new BufferedReader(new InputStreamReader((connection.getInputStream())));
			}
			else{
				bufferIn = new BufferedReader(new InputStreamReader((connection.getErrorStream())));
			}

			String inputLine;
			StringBuffer content = new StringBuffer();
			while((inputLine = bufferIn.readLine()) != null){
				content.append(inputLine);
			}
			System.out.println(content.toString());

			if(status != 200){
				System.out.println("Invalid HTTP response code in score publication validation");
				System.out.println("Response code : " + Integer.toString(status));
				return INVALID_SERVER_RESPONSE;
			}
			return VALID_RESPONSE;

		}
		catch(Exception e){
			System.out.println(e.toString());
			return NO_RESPONSE_FROM_SERVER;
		}
	}

	public int registerUser(String username, String mail, String password){
		return -1;
	}


	public void test1(){
		try{
			URL urlDanmaku = new URL("http://144.217.83.146/login");
			HttpURLConnection connection = (HttpURLConnection) urlDanmaku.openConnection();
			connection.setRequestMethod("GET");

			Map<String, String> arguments = new HashMap<>();
			arguments.put("Username", "Clubdesamis");
			arguments.put("Password", "hello");

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			connection.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(getParamsString(arguments));
			out.flush();
			out.close();


			int status = connection.getResponseCode();
			System.out.println("response code is " + Integer.toString(status));

		}
		catch(Exception e){

		}
	}

	public void testCat(){
		try{
			URL urlCat = new URL("https://cat-fact.herokuapp.com/facts");
			HttpURLConnection connection = (HttpURLConnection) urlCat.openConnection();
			connection.setRequestMethod("GET");

			Map<String, String> arguments = new HashMap<>();
			//arguments.put("Username", "Clubdesamis");
			//arguments.put("Password", "hello");

			connection.setRequestProperty("Content-Type", "application/json");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			connection.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(getParamsString(arguments));
			out.flush();
			out.close();


			int status = connection.getResponseCode();
			System.out.println("response code is " + Integer.toString(status));

		}
		catch(Exception e){

		}
	}

	public void testWeather(){
		try{
			URL urlWeather = new URL("https://weatherbit-v1-mashape.p.rapidapi.com/forecast/minutely");
			HttpURLConnection connection = (HttpURLConnection) urlWeather.openConnection();
			connection.setRequestMethod("GET");

			Map<String, String> arguments = new HashMap<>();
			arguments.put("lat", "45.501690");
			arguments.put("lon", "-73.567253");

			//connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("X-RapidAPI-Key", "SIGN-UP-FOR-KEY");
			connection.setRequestProperty("X-RapidAPI-Host", "weatherbit-v1-mashape.p.rapidapi.com");
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);

			connection.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(getParamsString(arguments));
			out.flush();
			out.close();


			int status = connection.getResponseCode();
			System.out.println("response code is " + Integer.toString(status));

		}
		catch(Exception e){

		}

	}

	public static String getParamsString(Map<String, String> params)
			throws UnsupportedEncodingException {
		StringBuilder result = new StringBuilder();

		for (Map.Entry<String, String> entry : params.entrySet()) {
			result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
			result.append("&");
		}

		String resultString = result.toString();
		return resultString.length() > 0
				? resultString.substring(0, resultString.length() - 1)
				: resultString;
	}
}