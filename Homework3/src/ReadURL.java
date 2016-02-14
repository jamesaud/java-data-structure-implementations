import java.io.*;
import java.net.*;
import java.util.Scanner;
import java.util.ArrayList;

public class ReadURL {
	static ArrayList<String> content = new ArrayList<String>();
	
    public static void main(String[] argv) throws IOException {
        //create URL
        URL url = new URL("http://homes.soic.indiana.edu/classes/spring2016/csci/c343-yye/tweet-data-Jan16.txt");
         
        //step 1: create a scanner
        Scanner in = new Scanner(url.openStream());
         
        
        //read
        while (in.hasNext()) {
            //nextLine() reads a line;
            //Scanner class has other methods to allow the user to read values of various types, eg.., nextInt()
            String str = in.nextLine();
            //do something with the str
            //here just to print
            content.add("\n" + str);
        }
      
        //Step 3: close the scanner
        in.close();
        System.out.println(content);
    }
    
}