import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
/**
 * 
 * @author krobinson20
 *This is the function that stores all the urls as well as the emails and then outputs them at the end
 */

class webpagereader implements Runnable {
	  private  Set<String> emails = new HashSet<String>(); //set to hold all the emails
	  HashMap<String, Boolean> map = new HashMap<String, Boolean>(); //hashmap to hold all of the urls
    private URL _url; //the current url to go to
    private String holder; //holder url to be checked if it has been visited yet or not
    private String previousUrl; //the most recently used url
    public webpagereader()
    {}
    public void run() {
    	   map.put("https://www.whitworth.edu/Academic/Department/MathComputerScience/", false); //the first entry in the hash map 
    	   holder = "https://www.whitworth.edu/Academic/Department/MathComputerScience/"; //the first url to go to
    	   
    	   
    	   for(int i=0;i<100;i++) // loop through 100 different urls 
    	   {
    		   
           	map.put(holder, true); //set the value for the current url to true
        try {
        	previousUrl = holder;
        	
        	_url = new URL (holder);
        	
        	System.out.printf("%s\n" , holder);
           //initalized the reader for the url
        	BufferedReader rdr = new BufferedReader(new InputStreamReader(_url.openStream()));
            String line;
         
            //read the file until the end of the line
            while ((line = rdr.readLine()) != null) {
            	
            	//look for the pattern for urls and then put the url into the hashmap
            	Pattern link = Pattern.compile("http(.*?)\"");
            	Matcher matcher1 = link.matcher(line);
            	if(matcher1.find())
            	{
            		holder = "http"+matcher1.group(1);
            		map.put(holder, false);
            	}          
            	//look for the pattern for the emails and put them into the set 
                if(line.contains(("mailto:")))
                {
                	Pattern email = Pattern.compile(("\"mailto:(.*?)\""));
                	Matcher matcher = email.matcher(line);
                	if(matcher.find())
                	{
                		emails.add(matcher.group(1));
                	}
                }
              }              
        }
        catch (Exception ex) {
            System.out.printf("Oops: %s\n", ex.getMessage());
        }
        //set the next url to be visited 
        for(Entry<String, Boolean> entry: map.entrySet())
        {
        	//get the current key for the hashmap
        	String key = entry.getKey();
        
        	//get the true/false value for the current entry
        	Boolean hasbeenvisited =entry.getValue();
        	
        	if(hasbeenvisited == false)
        	{
        		//if the url has not been visited then set the holder variable to the 
        		//current key so that when it loops back up it will go to the next url
        		holder = key;
        		map.put(holder,  true);
        		break;
        	}
        }
    }
    	   
    	   System.out.print(emails);
    	   System.out.printf("\n");
    }
}
 

