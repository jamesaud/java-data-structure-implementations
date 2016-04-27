//a simple implementation of Graph using adjacency list
//C343 2016, with Prim's algorithm (mstPrim) to be implemented by students

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;

public class AdjGraph implements Graph {
	private boolean digraph;
	private int totalNode;
	private Vector<String> nodeList;
	private int totalEdge;
	private Vector<LinkedList<Integer>> adjList; //Adjacency list
	private Vector<LinkedList<Integer>> adjWeight; //Weight of the edges
	private Vector<Boolean> visited;
	private Vector<Integer> nodeEnum; //list of nodes pre visit
	private int[][] weights = new int[10][10];
	public AdjGraph() {
		init();
	}
	public AdjGraph(boolean ifdigraph) {
		init();
		digraph =ifdigraph;
	}
	public void init() {
		nodeList = new Vector<String>(); 
		adjList = new Vector<LinkedList<Integer>>(); 
		adjWeight = new Vector<LinkedList<Integer>>();
		visited = new Vector<Boolean>();
		nodeEnum = new Vector<Integer>();
		totalNode = totalEdge = 0;
		digraph = false;
	}
	//set vertices
	public void setVertex(String[] nodes) {
		for(int i = 0; i < nodes.length; i ++) {
			nodeList.add(nodes[i]);
			adjList.add(new LinkedList<Integer>());
			adjWeight.add(new LinkedList<Integer>());
			visited.add(false);
			totalNode ++;
		}
	}
	//add a vertex
	public void addVertex(String label) {
		nodeList.add(label);
		visited.add(false);
		adjList.add(new LinkedList<Integer>());
		adjWeight.add(new LinkedList<Integer>());
		totalNode ++;
	}
	public int getNode(String node) {
		for(int i = 0; i < nodeList.size(); i ++) {
			if(nodeList.elementAt(i).equals(node)) return i;
		}
		return -1;
	}
	//return the number of vertices
	public int length() {
		return nodeList.size();
	}
	//add edge from v1 to v2
	public void setEdge(int v1, int v2, int weight) {
		LinkedList<Integer> tmp = adjList.elementAt(v1);
		if(adjList.elementAt(v1).contains(v2) == false) {
			tmp.add(v2);
			adjList.set(v1,  tmp);
			totalEdge ++;
			LinkedList<Integer> tmp2 = adjWeight.elementAt(v1);
			tmp2.add(weight);
			adjWeight.set(v1,  tmp2);
		}
	}
	
	public void setWeight(String v1, String v2, int weight){
		int index_v1 = getNode(v1);
		int index_v2 = getNode(v2);
		weights[index_v1][index_v2] = weight;
	}
	
	public void setEdge(String v1, String v2, int weight) {
		if((getNode(v1) != -1) && (getNode(v2) != -1)) {
			//add edge from v1 to v2
			setEdge(getNode(v1), getNode(v2), weight);
			//for digraph, add edge from v2 to v1 as well
			if(digraph == false) setEdge(getNode(v2), getNode(v1), weight);
		}
		setWeight(v1, v2, weight);
		setWeight(v2, v1, weight);
	}

	//it is important to keep track if a vertex is visited or not (for traversal)
	public void setVisited(int v) {
		visited.set(v, true);
		nodeEnum.add(v);
	}
	public boolean ifVisited(int v) {
		return visited.get(v);
	}
	public LinkedList<Integer> getNeighbors(int v) {
		return adjList.get(v);
	}
	public int getWeight(int v, int u) {
    		LinkedList<Integer> tmp = getNeighbors(v);
    		LinkedList<Integer> weight = adjWeight.get(v);
    		if(tmp.contains(u)) return weight.get(tmp.indexOf(u));
    		else return Integer.MAX_VALUE;
    	}
	public void clearWalk() {
		//clean up before traverse
		nodeEnum.clear();
		for(int i = 0; i < nodeList.size(); i ++) visited.set(i, false);
	}
	public void walk(String method) {
		clearWalk();
		//traverse the graph 
		for(int i = 0; i < nodeList.size(); i ++) {
			if(ifVisited(i) == false) {
				if(method.equals("BFS")) BFS(i); //i as the start node
				else if(method.equals("DFS")) DFS(i); //i as the start node
				else {
					System.out.println("unrecognized traversal order: " + method);
					System.exit(0);
				}
			}
		}
		System.out.println(method + ":");
		displayEnum();
	}
	public void DFS(int v) {
		setVisited(v);
		LinkedList<Integer> neighbors = adjList.elementAt(v);
		for(int i = 0; i < neighbors.size(); i ++) {
			int v1 = neighbors.get(i);
			if(ifVisited(v1) == false) DFS(v1); 
		}
	}
	public void BFS(int s) {
		ArrayList<Integer> toVisit = new ArrayList<Integer>();
		toVisit.add(s);
		while(toVisit.size() > 0) {
			int v = toVisit.remove(0); //first-in, first-visit
			setVisited(v);
			LinkedList<Integer> neighbors = adjList.elementAt(v);
			for(int i = 0; i < neighbors.size(); i ++) {
				int v1 = neighbors.get(i);
				if((ifVisited(v1) == false) && (toVisit.contains(v1) == false)) {
					toVisit.add(v1);
				}
			}
		}
	}
	public void display() {
		System.out.println("total nodes: " + totalNode);
		System.out.println("total edges: " + totalEdge);
	}
	public void displayEnum() {
		for(int i = 0; i < nodeEnum.size(); i ++) {
			System.out.print(nodeList.elementAt(nodeEnum.elementAt(i)) + " ");
		}
		System.out.println();
	}
	
	
	public void mstPrim() {
		int[] dist = new int[nodeList.size()];
		//set S to include all the nodes,
		Vector<String> S = nodeList;
		// set dist[0] to 0
		dist[0] = 0;
		// set dist[v] to infinity for all other nodes v (v != 0)
		for (int i=1; i<S.size();i++){
			dist[i] = Integer.MAX_VALUE;
		}
		// set cost (of the MST) to 0 (initial MST is empty)
		int MST_cost = 0;
		int currentNode = 0;
		System.out.println(S);
		//find vertex u with the smallest dist[u] among all unsettled vertices (S). 
		int original_s = S.size() -1;
		while (S.size() > 0){
			System.out.println();
			System.out.println(S);
			//Store minimum distance from current node
			//Find node with minimum distance
			int min_dist = Integer.MAX_VALUE;
			int min_dist_node = -1;
			//Get minimum distance node in S
			for (int i=0;i<S.size();i++){
				System.out.println("Distance of " + i + ": " + dist[i]);
				if (dist[i] < min_dist){
					min_dist_node=original_s - i;
					min_dist=dist[i];
					System.out.println("new min dist node: " + i);
				}
			}

			int abs_min_dist=Integer.MAX_VALUE;
			LinkedList<Integer> neighbors = getNeighbors(min_dist_node);
			System.out.println("neighbors " + neighbors);
			for (int neighbor : neighbors){
				//Distance from current node to the neighbor
				int dist1 = weights[min_dist_node][neighbor];
				if (dist1<abs_min_dist){
					abs_min_dist=dist1;
				}
				//update dist for every neighbor
				int current_dist = dist[neighbor];
				int compare_dist = weights[min_dist_node][neighbor];
				if (current_dist>compare_dist){dist[neighbor]=compare_dist;}

				System.out.println("Neighbor dist " + neighbor + " | " + dist[neighbor]);
			}
			//Remove the node that's the min neighbor
			System.out.println("Removing node: " + S.get(min_dist_node));
			S.removeElementAt(min_dist_node);
			System.out.println("Min dist to add: " + abs_min_dist);
			//update the cost
			MST_cost+=abs_min_dist;
			original_s--;
			}
		System.out.println("MST Cost: " + MST_cost);
		}
	

	public static void main(String argv[]) {
		//make some graphs to test your implementation of mstPrim()
		AdjGraph ag = new AdjGraph();
		String[] nodes = {"a","b","c", "d", "e", "f"};
		ag.setVertex(nodes);
		ag.setEdge("a", "b", 4);
		ag.setEdge("b", "c", 4);
		ag.setEdge("c", "d", 3);
		ag.setEdge("d", "e", 2);
		ag.setEdge("d", "f", 10);
		//ag.setEdge("e", "f", 1);
		//ag.display();
		ag.mstPrim();
	}
}