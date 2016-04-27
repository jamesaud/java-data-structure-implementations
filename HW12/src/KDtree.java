import java.util.List;
import java.util.ArrayList;

// KD tree with insert and exact match functions
// getNeighbors(Key[] key, int r) is going to be implemented by the students

public class KDtree<Key extends Comparable<?super Key>, E> {
	private BinNode<Key, E> root;
	private int totalNode;
	private BinNode<Key, E> curr;   //works with find()
	private String enumStr;         //for enumeration
	private int dim;   //dimension of the key
	private int level; //which level; important for insertion & search
	public KDtree(int d) {
		root = curr = null;
		totalNode = 0;
		dim = d;
		level = 0;
	}
	public BinNode<Key, E> find(Key[] k) {
		if(root == null) return null;
		else {
			return find(root, 0, k);
		}
	}
	public BinNode<Key, E> find(BinNode<Key, E> entry, int thislevel, Key[] k) {
		if(entry == null) return null;
		curr = entry;
		level = thislevel; //update level
		if(entry.getKey() == k) {
			return curr;
		}
		else {
			if(entry.isLeaf()) return null;
			Key[] key2 = entry.getKey();
			if (k[level % dim].compareTo(key2[level % dim]) >= 0) { //make sure the "right" key is used
				return find(entry.getRight(), thislevel + 1, k); //note thislevel + 1
			}
			else {
				return find(entry.getLeft(), thislevel + 1, k);
			}
		}
	}
	public void insert(Key[] k, E v) {
		BinNode<Key, E> node = new BinNode <Key, E>(k, v);
		insert(node);
		//insert(root, node);
	}
	public void insert(BinNode<Key, E> node) {
		find(node.getKey());
		if(curr == null) {
			root = node;
		}
		else {
			Key[] key1 = node.getKey();
			Key[] key2 = curr.getKey();
			if (key1[level % dim].compareTo(key2[level % dim]) >= 0) {
				if(curr.getRight() != null) node.setRight(curr.getRight());
				curr.setRight(node);
			}
			else {
				if(curr.getLeft() != null) node.setLeft(curr.getLeft());
				curr.setLeft(node);
			}
		}
		totalNode ++;
	}
	public void preorder() {
		enumStr = "";
		System.out.println("Total node = " + totalNode);
		if(root != null) preorder(root);
		System.out.println("Preorder enumeration: " + enumStr);
	}
	private void preorder(BinNode<Key, E> node) {	
		if(node != null) System.out.println("root " + node.toString());
		if(node.getLeft() != null) System.out.println("   left " + node.getLeft().toString());
		if(node.getRight() != null) System.out.println("   right " + node.getRight().toString());
		
		if(node != null) {
			enumStr += node.toString();
		}
 		if(node.getLeft() != null) preorder(node.getLeft());
		if(node.getRight() != null) preorder(node.getRight());
	}
	
	//to be implemented 
	List<BinNode<Key, E>> Neighbors = new ArrayList<BinNode<Key, E>>();
	public void getNeighbors(Key[] key, int r) {
		BinNode<Key, E> node = find(key);
		if (r>=0){
			if(node.getLeft() != null){
				Neighbors.add(node.getLeft());
				getNeighbors(node.getLeft().getKey(), r--);
			}
			if(node.getRight() != null){
				Neighbors.add(node.getRight());
				getNeighbors(node.getRight().getKey(), r--);
			}
		}
	}

	//design your own examples to test the program!!
	public static void main(String[] args) {
		
		KDtree <Integer, String> kdt = new KDtree<Integer, String>(2);
		Integer[] dataA = {40, 45};
		kdt.insert(dataA, "A");
		Integer[] dataB = {15, 70};
		kdt.insert(dataB, "B");
		Integer[] dataC = {70, 10};
		kdt.insert(dataC, "C");
		Integer[] dataD = {69, 50};
		kdt.insert(dataD, "D");
		Integer[] dataE = {66, 85};
		kdt.insert(dataE, "E");
		Integer[] dataF = {85, 95};
		kdt.insert(dataF, "F");
		Integer[] dataG = {90, 100};
		kdt.insert(dataG, "G");
		
		kdt.preorder();
		
		System.out.println("Query location: " + dataG[0] + " " + dataG[1]);
		BinNode<Integer, String> node = kdt.find(dataG); //exact match
		if(node == null) {
			System.out.println("point not found");
		}
		else {
			System.out.println("point found: " + node.toString());
		}
		
		int r = 3;
		kdt.getNeighbors(dataA, r); //get close neighbors
		System.out.print("The neighbors within the range of " + r + " are: ");
		System.out.println(kdt.Neighbors);
		
		
		System.out.println();
		KDtree <Integer, String> kdt1 = new KDtree<Integer, String>(2);
		Integer[] dataA1 = {40, 45};
		kdt1.insert(dataA1, "A");
		Integer[] dataB1 = {15, 70};
		kdt1.insert(dataB1, "B");
		Integer[] dataC1 = {70, 10};
		kdt1.insert(dataC1, "C");
		Integer[] dataD1 = {69, 50};
		kdt1.insert(dataD1, "D");
		Integer[] dataE1 = {66, 85};
		kdt1.insert(dataE1, "E");
		Integer[] dataF1 = {85, 95};
		kdt1.insert(dataF1, "F");
		Integer[] dataG1 = {90, 100};
		kdt1.insert(dataG1, "G");
		
		int r1 = 3;
	
		
		kdt1.getNeighbors(dataA1, r1); //get close neighbors
		System.out.print("The neighbors within the range of the three dimentional kdtree in range " + r1 + " are: ");
		System.out.println(kdt1.Neighbors);
	}
}