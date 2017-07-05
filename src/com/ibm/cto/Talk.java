package com.ibm.cto;
import java.io.*;
import java.util.Map;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ibm.watson.developer_cloud.conversation.v1.ConversationService;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageRequest;
import com.ibm.watson.developer_cloud.conversation.v1.model.MessageResponse;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.util.*;
import com.google.gson.internal.LinkedTreeMap;
import com.ibm.watson.developer_cloud.discovery.v1.Discovery;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.CreateDocumentRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.CreateDocumentResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.DeleteDocumentRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.DeleteDocumentResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.Document;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.GetDocumentRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.GetDocumentResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.UpdateDocumentRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.document.UpdateDocumentResponse;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.QueryRequest;
import com.ibm.watson.developer_cloud.discovery.v1.model.query.QueryResponse;
import com.ibm.watson.developer_cloud.http.HttpMediaType;

/**
 * Servlet implementation class Talk
 */
@WebServlet("/Talk")
public class Talk extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Talk() {
        super();
    }
                /**
                * @throws IOException 
                 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
                */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestMessage = request.getParameter("message");
	
		String output ="";
		String contextString = request.getParameter("context");
										JSONObject contextObject = new JSONObject();
		if(contextString != null) {
						contextObject = JSONObject.parseObject(contextString);
						System.out.println(contextObject);
		}
		System.out.println("Context: ");
		System.out.println(contextObject);
		Map<String, Object> contextMap = Utility.toMap(contextObject);
		if(requestMessage == null || requestMessage.isEmpty()){
						requestMessage = "Greetings";
		}
		System.out.println("requestMessage" + requestMessage  );
		if(requestMessage.contains(", ") || requestMessage.endsWith(".com")){
        // throw new NullPointerException();
        System.out.println("before the url"  );
        System.out.println("requestMessage:" + requestMessage  );
        try {
            String requestMessageUpdate = requestMessage.replace(" ", "%20");
			URL e = new URL("http://watsonservicedev.mybluemix.net/rest/WatsonService/discoveryService/uploadContent/AAInfo%20" +requestMessageUpdate);
            System.out.println("URL: "+e.toString());   
            HttpURLConnection conn = (HttpURLConnection) e.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "TEXT/PLAIN");
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}

			conn.disconnect();
		} catch (MalformedURLException arg7) {
						arg7.printStackTrace();
		} catch (IOException arg8) {
						arg8.printStackTrace();
		}

		}
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/JSON");
		response.setCharacterEncoding("utf-8");
		if(!requestMessage.contains("Pandora")){
		    System.out.println("InsideSupport"); 
			ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_09_20);
			service.setUsernameAndPassword(Configuration.getInstance().CONVERSATION_USERNAME, Configuration.getInstance().CONVERSATION_PASSWORD);
			MessageRequest newMessage = new MessageRequest.Builder().context(contextMap).inputText(requestMessage).build();
			MessageResponse r = service.message(Configuration.getInstance().CONVERSATION_WORKSPACE_ID, newMessage).execute();
			System.out.println("InsideSupportvalue "+r.toString()); 
		    response.getWriter().append(r.toString());
		}else{
			try {
				URL e = new URL("https://alscdiscovery.mybluemix.net/rest/WatsonService/ALSCdiscoveryService/Pandora%20Access%20Request");
				System.out.println("URL: "+e.toString()); 
			
								 
				HttpURLConnection conn = (HttpURLConnection) e.openConnection();
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/xml ");
				if (conn.getResponseCode() != 200) {
								throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
				}

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				System.out.println("Output from Server .... \n");
				System.out.println("Inside else output"+br.toString());

				
				while ((output = br.readLine()) != null) {						
					
					
					System.out.println(output);
					

				//	String postData = "{\"output\": {\"text\": [\"data\" ]}}";
				    
					String postData = "{\"output\": {\"text\": [" 
					+ "\"" + output + "\"" + "]}}" ;
					System.out.println("output "+ output + " " +postData);
			    	output+= output;
				}
				response.getWriter().append(postData);
					conn.disconnect();
				
			
				
		/*	ConversationService service = new ConversationService(ConversationService.VERSION_DATE_2016_09_20);
			service.setUsernameAndPassword(Configuration.getInstance().CONVERSATION_USERNAME, Configuration.getInstance().CONVERSATION_PASSWORD);
				MessageRequest newMessage1 = new MessageRequest.Builder().context(contextMap).inputText(requestMessage).build();
				
			    MessageResponse r1 = service.message(Configuration.getInstance().CONVERSATION_WORKSPACE_ID, newMessage1).execute();
			    Map<String, Object> inMap = r1.getOutput();
				inMap.put("text", output);
				r1.setOutput(inMap);*/
			//	System.out.println(r1.toString()); 
			System.out.println("requestMessageUpdate "+output); 
			/*String postData = "{\"output\": {\"text\": [" 
					+ "\"" + output + "\"" + "]}}" ;
/*String postData = "{\"output\": {\"text\": [" 
					+ "\"" + output + "\"" + "]}}" ;*/
					
			
			} catch (MalformedURLException arg7) {
							arg7.printStackTrace();
			} catch (IOException arg8) {
							arg8.printStackTrace();
			}catch(InterruptedException ex) {
				System.out.println(ex.getMessage()); 
				ex.printStackTrace(); 
				Thread.currentThread().interrupt();
			}
				
		//System.out.println(r.toString()); 
	}						

    }
}
