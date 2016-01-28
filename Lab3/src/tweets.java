
public class tweets {
	public String content;
	public String creator;

	public static void main(String[] args){
		tweets myTweet = new tweets("Tweet Tweet Tweet!", "James A");
		myTweet.describe();
	}
	
	public tweets(String content, String creator){
		this.content = content;
		this.creator = creator;
	}
	
	public void describe(){
		System.out.println("Tweet:\n" + this.content);
		System.out.println("\nCreator:\n" + this.creator);
	}
}
