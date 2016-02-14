import java.util.ArrayList;

public class ADTimp extends twodimarray{
	private static ArrayList<ArrayList<Integer>> al;
	
	public static void main(String[] args){
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		list1.add(1);
		list1.add(2);
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(10);
		list2.add(23);
	
		ADTimp test = new ADTimp();
		System.out.println("Empty? " + test.empty());
		test.insert(0, list1);
		test.insert(1, list2);
		System.out.println("Insert Rows" + test);
		System.out.println("elements: " + test.elements());
		int[] del = {0,1};
		test.delete(del);
		System.out.println("delete" + test);
		test.insertValue(del, 5);
		System.out.println("AddValue: " + test);
		System.out.println("Empty? " + test.empty());
		test.initialize();
		System.out.println("Initialize: " + test);
	}
	
	public String toString(){
		return this.al.toString();
	}
	
	public ADTimp(){
		al = new ArrayList<ArrayList<Integer>>();
	}

	public void insert(int x, ArrayList<Integer> n){
		al.add(x, n);
	}
	//xy array is the position, n is the value to add 
	public void insertValue(int[] xy, int n){
		ArrayList<Integer> row = al.get(xy[0]);
	    row.add(xy[1], n);
	    al.remove(xy[0]);
	    al.add(xy[0], row);
	}
	
	public int elements(){
		int total = 0;
		for (ArrayList<Integer> arls : al){
			total += arls.size();
		}
		return total;
	}
	
	public boolean empty(){
		if (al.size() == 0){return true;}
		else {return false;}
	}
	
	//position row,column that needs to be deleted
	public void delete(int[] xy){
		ArrayList<Integer> newRow = al.get(xy[0]);
		newRow.remove(xy[1]);
		al.add(xy[0], newRow);
		al.remove(xy[0] + 1);
	}
	
	public void initialize(){
		al = new ArrayList<ArrayList<Integer>>();
	}
}
