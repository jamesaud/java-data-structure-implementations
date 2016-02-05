import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public class TweetCollection {
	
	public static void main(String[] args) throws IOException{
		ArrayList<String> content = new ArrayList<String>();
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
            content.add(str);
        }
      
        //Step 3: close the scanner
        in.close();
        
        //All of the tweets are now save in the ArrayList content. We have two methods:
        //print_tweet_data prints off the creator, and then content for each tweet 
        //find_tweet prints off the creator and content for a specific user name
        
		TweetCollection tc = new TweetCollection();
		tc.print_tweet_data(content);
		System.out.println("\n\nLet's find the tweets by NoSQLDigest");
		tc.find_tweet("NoSQLDigest", content);
		
	}
	
	
	public tweets convert_tweet(String line){
		String creator = "";
		String content = "";
		for (int i = 0; i < line.length(); i++){
			if (line.charAt(i) == ' '){
				creator = line.substring(0, i);
				content = line.substring(i, line.length()-1);
				break;
			}	
		}
		tweets t = new tweets(content, creator);
		return t;
	}
	
	public ArrayList<tweets> collect_tweets(ArrayList<String> tweetData){
		ArrayList<tweets> allTweets = new ArrayList<tweets>();
		for (String line: tweetData){
			tweets t = convert_tweet(line);
			allTweets.add(t);
		}
		return(allTweets);
	}

	public void print_tweet_data(ArrayList<String> tweetData){
		ArrayList<tweets> tw = collect_tweets(tweetData);
		for (tweets t : tw){
			if (t.content != null && t.creator != null){
				t.describe();
			}
		}
	}
	
	public void find_tweet(String creator, ArrayList<String> tweetData){
		ArrayList<tweets> tw = collect_tweets(tweetData);
		for (tweets t : tw){
			if (t.creator.equals(creator)){
				t.describe();
			}
		}
	}
}