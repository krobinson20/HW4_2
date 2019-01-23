import java.awt.FlowLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * 
 * @author krobinson20
 *This program starts at a urn and then combs through it and finds any additional urls and emails.
 *it will store all the emails and urls and then output the emails at the end
 */

public class Web_Spider {
 // private static Map<String, Boolean> URLS = new HashMap<String, Boolean>();

	public static void main(String args[]) {	    
//calls the spider function that actually combs the webpage
   try {
	 webpagereader w = new webpagereader();
     w.run();
   }
   catch(Exception ex)
   {     }
}
}

