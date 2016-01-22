//package DNA;
import java.util.Random;

public class RandomDNA {
  public static void main(String[] args){
    dna_generate();
    }
  
  public static String dna_generate(){
    String result = "";
    String letters = "actg";
    Random rnd = new Random();

    for (int i=1; i<21; i++){
      int t = rnd.nextInt(4);
      result += letters.charAt(t);
      }
    return result;
  }
}
