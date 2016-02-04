import java.util.ArrayList;
//my 2d array is an arrayList that contains arrayLists

public abstract class twodimarray {
	public static void main(String[] args){
	}
	
	abstract public int elements();
	
	abstract public boolean empty();
	
	abstract public void delete(int[] xy);
	
	abstract public void initialize();

	//position in 2dimarray, value to insert
	abstract public void insertValue(int[] xy, int n);
	abstract public void insert(int x, ArrayList<Integer> al);
}