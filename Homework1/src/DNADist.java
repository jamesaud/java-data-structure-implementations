public class DNADist {
		  public static void main(String[] args){
			 String dna1 = RandomDNA.dna_generate();
			 String dna2 = RandomDNA.dna_generate();
			 System.out.println("Hamming Distance: " + Hamming(dna1,dna2));
		    }
		  
		  public static int Hamming(String dna1, String dna2){
			    int count = 0;
			    for(int i=0; i<dna1.length(); i++){
			    	if (dna1.charAt(i) != dna2.charAt(i)){
			    		count++;
			    	}
			    }
			    System.out.println("DNA1: " + dna1);
			    System.out.println("DNA2: " + dna2);
			    return count;	
		  }
}
