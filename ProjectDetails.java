import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.restassured.path.json.JsonPath;

public class ProjectDetails {
	public static void projectsDetails(){
	//public static void Test() {
		String username = "User_name";
		String password = "password";
		String url = "http://jiraserver/rest/api/2/issue/createmeta";		
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
	          //   System.out.print(result);
                 JsonPath js = new JsonPath(result);
	    	     ArrayList projects = js.get("projects.name");
	    	     System.out.println("The Project names are:" +" "+projects);
//	    	     ArrayList issuetypes = js.get("projects.issuetypes.id");
//	    	     System.out.println("The Issuetype ID'S are:" +" "+issuetypes);
	    	     ArrayList project_id = js.get("projects.id");
	    	     System.out.println("The Project ID'S are:" +" " +project_id);
	    	     ArrayList key = js.get("projects.key");
	    	     System.out.println("The Project Keys are:" + " " +key);
	            
	     }
	     catch (IOException e)
	     {   System.out.print("error");
	         e.printStackTrace();
	     }
	     
}}
