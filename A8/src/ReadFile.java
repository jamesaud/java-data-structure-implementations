import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {
	private String path;
	
	public ReadFile(String path){
		this.path = path;
	}
	
	public List<String> ReadFile() throws IOException{
		List<String> lines = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(path)))
		{
			//int linecount = 0;
			String sCurrentLine;
			while ((sCurrentLine = br.readLine()) != null) {
			//	System.out.println(sCurrentLine);
				lines.add(sCurrentLine);
			}
		
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return lines;
	}
}

