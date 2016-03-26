import java.io.IOException;
import java.nio.file.Paths;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.List;

public class WordSearch {
	private String path;
	private List<String> lines;
	private Hashtable<String, List<Integer>> word_count= new Hashtable<String, List<Integer>>();

	public void getText() throws IOException{
		ReadFile rf = new ReadFile(path);
		List<String> lns = rf.ReadFile();
		lines = lns;
	}
	
	public WordSearch(String path){
		this.path = path;
	}
	
	public Hashtable getWordCount(){
		return word_count;
	}
	
	public void countWords(){
		for (int i=0;i<lines.size();i++){
			//get the line and get the list of words
			String[] words = lines.get(i).split(" ");
			for (String word : words){
				if (word_count.get(word) == null){
					//if the word isn't in the dictionary, add it with the line number (index + 1)
					List<Integer> lineNumber = new ArrayList<Integer>();
					lineNumber.add(i+1);
					word_count.put(word, lineNumber);
				}
				else {
					word_count.get(word).add(i+1);
				}
				//else
			}
		}
		
	} 
	
	public void searchWord(String word){
		if (word_count.get(word) == null){
			System.out.println(word + "|| does not exist");
		}
		else System.out.println(word + " appears in lines: " + word_count.get(word));
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println(Paths.get(".").toAbsolutePath().normalize().toString() + "\\test.txt");
		WordSearch ws = new WordSearch(Paths.get(".").toAbsolutePath().normalize().toString() + "\\test.txt");
		ws.getText();
		//System.out.println(ws.lines);
		Hashtable<String, List<String>> word_counts = ws.getWordCount();
		ws.countWords();
		
		//for (String key : word_counts.keySet()) {
		//    System.out.println(key + ":" + word_counts.get(key));		
	//	}
		//FINDS THE WORD AND THE LINES THAT IT APPEARS IN
		ws.searchWord("algorithem");
		ws.searchWord("data");
	}
}
