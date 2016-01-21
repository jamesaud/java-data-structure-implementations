import java.util.ArrayList;
import java.util.Random;

public class MyCard implements Card{

	public ArrayList<String> deck = new ArrayList<String>();
	
	public static void main(String[] args){
		MyCard m = new MyCard();
		m.initialize();
		for(int i=0; i<10; i++){
			System.out.println("Draw: " + m.drawCard());
			}
	}
	
	public void initialize(){
		String[] cards = {"1", "2", "3", "4", "5", "6", "7", "8", 
"9", "10", "J", "Q", "K", "A"};
		for (String item : cards){
			this.deck.add(item + "S");
			this.deck.add(item + "C");
			this.deck.add(item + "D");
			this.deck.add(item + "H");
	};
	System.out.println("DECK: " + this.deck);
	}
	
	public String drawCard(){
		Random r = new Random();
		return deck.get(r.nextInt(52));
	}
	
	
	
	
}

