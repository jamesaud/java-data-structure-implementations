
public class Bsa {
	//return the position of an element (with value k) in sorted array 
	//if k is not in A, return A.length
	static int binary(Comparable[] A, Comparable k) {
		int l = -1;
		int r = A.length; // l and r are beyond array bounds
		while (l+1 != r) { // Stop when l and r meet
			int i = (l+r)/2; // Check middle of remaining subarray
			if ( k.compareTo(A[i])==0) return i; // Found it
			else if (k.compareTo(A[i])<0) r = i; // In left half
			else l = i; // In right half
		}
		return A.length; // Search value not in A
	} 
	
	public static void main(String[] args){
		Comparable[] int_arr = {1, 2, 4, 3, 5, 8};
		Comparable[] float_arr = {(float) 1.4,(float) 3.7,(float) 2.6, (float) 4.9};
		Comparable[] string_arr = {"hello", "how", "are", "you", "doing?"};
		System.out.println(binary(int_arr, 4));
		System.out.println(binary(float_arr, (float)3.7));
		System.out.println(binary(string_arr, "doing?"));
		System.out.println(binary(int_arr, 10));
	}
}
