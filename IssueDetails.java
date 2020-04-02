import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.restassured.path.json.JsonPath;

public class IssueDetails {
	public static void issueDetails(){
		String username = "YashaswiniS";
		String password = "jira1@345";
		String url = "http://localhost:8090/rest/api/2/search";		
		String[] command = {"curl", "-H", "Accept:application/json", "-u", username+":"+password , url};
		 ProcessBuilder process = new ProcessBuilder(command); 
	     Process p;
	     try
	     {
	         p = process.start();
	          BufferedReader reader =  new BufferedReader(new InputStreamReader(p.getInputStream()));
	             StringBuilder builder = new StringBuilder();
	             String line = null;
	             while ( (line = reader.readLine()) != null) {
	                     builder.append(line);
	                     builder.append(System.getProperty("line.separator"));
	             }
	             String result = builder.toString();
	           //  System.out.print(result);
	             JsonPath js = new JsonPath(result);
	             ArrayList IssueKey = js.get("issues.key");
	    	     System.out.println("The Issue Keys are:" +" "+IssueKey);
	    	     ArrayList Issue_id = js.get("issues.id");
	    	     System.out.println("The Issue Id's are:" +" "+Issue_id);
	     }
	     catch (IOException e)
	     {   System.out.print("error");
	         e.printStackTrace();
	     }
	     
}
}
