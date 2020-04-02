import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.restassured.path.json.JsonPath;

public class LeadTime {
//	public static void main(String[] args) throws ParseException {
		public static void leadTime() throws ParseException {
					String username = "User_name";
					String password = "password";
					String url = "http://jiraserver/rest/api/2/issue/{issue_id}";		
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
				             
		// Prints the date and time of Ticket Creation		             
				    	     String created = js.get("fields.created");
				    	     System.out.println("Issue Creation Date and Time:" +" "+created);
				    	     String substr = created.substring(11, 19);
				    	     String datestring = created.substring(0, 10);
				    	     String Start = datestring + " " + substr ;
				    	     System.out.println(Start);
				    	     
		// Prints the date and time of Ticket Resolution		    	     		    	     
				    	     String resolutionDate = js.get("fields.resolutiondate");
				    	     System.out.println("Issue Resolution Date and Time:" +" "+resolutionDate);
				    	     String substr1 = resolutionDate.substring(11, 19);		    	  
				    	     String datestring1 = resolutionDate.substring(0, 10);    	  
				    	     String End = datestring1 + " " + substr1 ;
				    	     System.out.println(End);
		
		//difference in date and time		    	     
				    	     String dateStart = Start;
				    			String dateStop = End;
				    			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

				    			Date d1 = null;
				    			Date d2 = null;

				    				d1 = format.parse(dateStart);
				    				d2 = format.parse(dateStop);

		//the lead time is 
				    				//in milliseconds
				    				long diff = d2.getTime() - d1.getTime();
				    				long diffSeconds = diff / 1000 % 60;
				    				long diffMinutes = diff / (60 * 1000) % 60;
				    				long diffHours = diff / (60 * 60 * 1000) % 24;
				    				long diffDays = diff / (24 * 60 * 60 * 1000);
				    				System.out.print("The Lead time is:" + " ");
				    				System.out.print(diffDays + " days, ");
				    				System.out.print(diffHours + " hours, ");
				    				System.out.print(diffMinutes + " minutes, ");
				    				System.out.print(diffSeconds + " seconds.");
				    						    	    
				     }
				     catch (IOException e)
				     {   System.out.print("error");
				         e.printStackTrace();
				     }
				     
		}
	}

