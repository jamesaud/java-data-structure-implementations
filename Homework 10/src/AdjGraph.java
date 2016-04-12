//a simple implementation of Graph using adjacency list
//C343 2014

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Vector;
import java.util.Scanner;
import java.util.List;

public class AdjGraph implements Graph {
	private boolean digraph;
	private int totalNode;
	private Vector<String> nodeList;
	private int totalEdge;
	private Vector<LinkedList<Integer>>  adjList; //Adjacency list
	private Vector<Boolean> visited;
	private Vector<Integer> nodeEnum; //list of nodes pre visit
	private int[][] weights;
	
	public AdjGraph() {
		init();
	}
	
	public void setWeights(int[][] weights){
		this.weights = weights;
	}
	
	public AdjGraph(boolean ifdigraph) {
		init();
		digraph =ifdigraph;
	}
	public void init() {
		nodeList = new Vector<String>(); 
		adjList = new Vector<LinkedList<Integer>>(); 
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
			visited.add(false);
			totalNode ++;
		}
	}
	//add a vertex
	public void addVertex(String label) {
		nodeList.add(label);
		visited.add(false);
		adjList.add(new LinkedList<Integer>());
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
		}
	}
	public void setEdge(String v1, String v2, int weight) {
		if((getNode(v1) != -1) && (getNode(v2) != -1)) {
			//add edge from v1 to v2
			setEdge(getNode(v1), getNode(v2), weight);
			setEdge(getNode(v2), getNode(v1), weight);
			//for digraph, add edge from v2 to v1 as well
			if(digraph == false) {
				setEdge(getNode(v2), getNode(v1), weight);
				setEdge(getNode(v1), getNode(v2), weight);
			}
			setWeight(v1, v2, weight);
			setWeight(v2, v1, weight);
		}
		
	}
	
	public void setWeight(String v1, String v2, int weight){
		int index_v1 = getNode(v1);
		int index_v2 = getNode(v2);
		weights[index_v1][index_v2] = weight;
	}

	//it is important to keep track if a vertex is visited or not (for traversal)
	public void setVisited(int v) {
		visited.set(v, true);
		nodeEnum.add(v);
	}
	public boolean ifVisited(int v) {
		return visited.get(v);
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
	
	public void myFloyd(){
	   int n = totalNode;
	   int[][] ans = new int[n][n];
	   
	   for(int i=0; i<n; i++)
		   for(int j = 0; j<ans[0].length; j++){
			   	if(i == j) {
		       ans[i][j] = 0;
		     } 
			   	else if ( adjList.get(i).contains(j)){
			   		ans[i][j] = weights[i][j];
			   		ans[j][i] = weights[j][i];
		     }
			    else {
			       ans[i][j] = Integer.MAX_VALUE;
			     }
		   }
	   
		   for (int k=0; k<n; k++) {
		     for (int i=0; i<n; i++) {
		       for (int j=0; j<n; j++) {
		         if (ans[i][k] != Integer.MAX_VALUE && ans[k][j] != Integer.MAX_VALUE && ans[i][j]>ans[i][k]+ans[k][j]){
		           ans[i][j] = ans[i][k]+ans[k][j];
		         }
		       }
		     }
		   }
		   
		   int max_path = 0;
		   for (int i=0; i< ans.length; i++){
			   int[] distances = ans[i];
			   System.out.print("Node " + i + "| ");
			   for (int j=0; j<distances.length;j++){
				   if (distances[j]>max_path){max_path = distances[j];}
				   if (distances[j] == Integer.MAX_VALUE){System.out.print("i");}
				   else System.out.print(distances[j] + "   ");
			   }
			   System.out.println();
		   }
		   System.out.println("\nMAX PATH = " + max_path);
		   
	 }
	
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter Number of Vertexes and Edges: ");
		String firstLine = sc.nextLine();
		String[] split_first = firstLine.split(" ");
		int num_edge = Integer.parseInt(split_first[1]);
		int num_vertex = Integer.parseInt(split_first[0]);
		System.out.print("Enter vertexes and edges: \nExample: 1 6 13 E\n");
		
		List<String[]> information = new ArrayList<String[]>();
		for (int i=0; i<num_edge;i++){
			System.out.print(">");
			String line = sc.nextLine();
			String[] split = line.split(" ");
			information.add(split);
		}
		
		String[] all_nodes = new String[num_vertex];
		for (int i=0; i<num_vertex; i++){
			all_nodes[i] = Integer.toString(i+1);
		}
		
		AdjGraph ag = new AdjGraph();
		ag.setVertex(all_nodes);
		
		int[][] weights = new int[num_vertex][num_vertex];
		ag.setWeights(weights);
		
		for (int i=0; i<information.size();i++){
			String[] info = information.get(i);
			String v1 = info[0];
			String v2 = info[1];
			int weight = Integer.parseInt(info[2]);
			ag.setEdge(v1, v2, weight);
		}
		ag.myFloyd();
	}
	
}
