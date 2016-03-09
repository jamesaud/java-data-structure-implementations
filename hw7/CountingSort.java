
public class CountingSort {
	public static void main(String[] args){
		CountingSort cs = new CountingSort();
		int[] arr = new int[]{4, 5, 2, 1, 0};
		int[] sorted = cs.countSort(arr, 5);
		System.out.println("Initial:");
		for (int i:arr){System.out.print(i);}
		System.out.println();
		System.out.println("Sorted:");
		for (int i:sorted){System.out.print(i);}
		
	}	
	
	int[] countSort(int[] A, int m) {
		m++;
	    int c[] = new int[m];
	    for (int i = 0; i < A.length; i++)
	        c[A[i]]++;
	    for (int i = 1; i < m; i++)
	        c[i] += c[i-1];
	    int b[] = new int[A.length];
	    for (int i = A.length-1; i >= 0; i--)
	    	b[--c[A[i]]] = A[i];
	    return b;
	    }
		
	}
