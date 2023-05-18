package Post;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.cert.Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLPeerUnverifiedException;

//import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class JavaPost {

	public static void main(String[] args) throws IOException {
		JavaPost postOBJ = new JavaPost();
		postOBJ.postRequest();
	}
	
	public void postRequest() throws IOException {
		final String  messageContent = "{\n" + "\"userId\":007, \r\n" + "\"id\":001, \r\n" + "\"title\": \"JAVA POST REQUEST\", \r\n" 
	                                          + "\"body\":\"POST REQUEST IS USED TO CREATE A RESOURCE AT SERVER SIDE.\""+ "\n}";
		
		System.out.println(messageContent);
		
		String url = "https://my-json-server.typicode.com/typicode/demo/posts";
		
		URL urlObj = new URL(url);
		
		HttpURLConnection postCon =  (HttpURLConnection) urlObj.openConnection();
			
		postCon.setRequestMethod("POST");
		
		postCon.setRequestProperty("userId", "abcde");
		
		postCon.setRequestProperty("Content-Type", "application/json");
		
		postCon.setDoOutput(true);
		
		OutputStream outputObj = postCon.getOutputStream();
		
		outputObj.write(messageContent.getBytes());
		
		outputObj.flush();
		
		outputObj.close();
		
		int responseCode = postCon.getResponseCode();
		System.out.println("Response from the server: \n");
		System.out.println("The POST Response Code is: " + responseCode);
		System.out.println("The POST Response Message is: " + postCon.getResponseMessage());
		
		if(responseCode == HttpURLConnection.HTTP_CREATED) {
			InputStreamReader inp = new InputStreamReader(postCon.getInputStream());
			BufferedReader br = new BufferedReader(inp);
			String input = null;
			StringBuffer sb = new StringBuffer();
			
			while((input = br.readLine()) != null) {
				sb.append(input);
			}
			
			br.close();
			postCon.disconnect();
			
			System.out.println(sb.toString());
		}
		else{
			System.out.println("POST Request didn't work");
		}
		
		
		
	}
}