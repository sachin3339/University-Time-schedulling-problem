package cse225;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import cse225.Vertex.Color;


public class Graph {
	
	private TreeMap<Vertex, TreeSet<Vertex>> myAdjList;
	private TreeMap<String, Vertex> myVertices;
	private static final TreeSet<Vertex> EMPTY_SET = new TreeSet<Vertex>();
	private int myNumVertices;
	private int myNumEdges;
	private ArrayList<Vertex> stack = new ArrayList<Vertex>();
	private ArrayList<Vertex> res = new ArrayList<Vertex>();
    private boolean visited[];
    int counter;
	/**
	 * Construct empty Graph
	 */
	public Graph() {
		myAdjList = new TreeMap<Vertex, TreeSet<Vertex>>();
		myVertices = new TreeMap<String, Vertex>();
		myNumVertices = myNumEdges = 0;

	}
	
	
	/**
	 * Add a new vertex name with no neighbors (if vertex does not yet exist)
	 * 
	 * @param name
	 *            vertex to be added
	 */
	public Vertex addVertex(String name) {
		Vertex v = null;
		v = myVertices.get(name);
		if (v == null) {
		    v = new Vertex(name);
			myVertices.put(name, v);
			myAdjList.put(v, new TreeSet<Vertex>());
			myNumVertices += 1;
		}
		return v;
	}

	/**
	 * Returns the Vertex matching v
	 * @param name a String name of a Vertex that may be in
	 * this Graph
	 * @return the Vertex with a name that matches v or null
	 * if no such Vertex exists in this Graph
	 */
	public Vertex getVertex(String name) {
		return myVertices.get(name);
	}

	/**
	 * Returns true iff v is in this Graph, false otherwise
	 * @param name a String name of a Vertex that may be in
	 * this Graph
	 * @return true iff v is in this Graph
	 */
	public boolean hasVertex(String name) {
		return myVertices.containsKey(name);
	}

	/**
	 * Is from-to, an edge in this Graph. The graph is 
	 * undirected so the order of from and to does not
	 * matter.
	 * @param from the name of the first Vertex
	 * @param to the name of the second Vertex
	 * @return true iff from-to exists in this Graph
	 */
	public boolean hasEdge(String from, String to) {

		if (!hasVertex(from) || !hasVertex(to))
			return false;
		return myAdjList.get(myVertices.get(from)).contains(myVertices.get(to));
	}
	
	/**
	 * Add to to from's set of neighbors, and add from to to's
	 * set of neighbors. Does not add an edge if another edge
	 * already exists
	 * @param from the name of the first Vertex
	 * @param to the name of the second Vertex
	 */
	public void addEdge(String from, String to) {
		Vertex v, w;
		if (hasEdge(from, to))
			return;
		myNumEdges += 1;
		if ((v = getVertex(from)) == null)
			v = addVertex(from);
		if ((w = getVertex(to)) == null)
			w = addVertex(to);
		myAdjList.get(v).add(w);
		myAdjList.get(w).add(v);
	}

	/**
	 * Return an iterator over the neighbors of Vertex named v
	 * @param name the String name of a Vertex
	 * @return an Iterator over Vertices that are adjacent
	 * to the Vertex named v, empty set if v is not in graph
	 */
	public Iterable<Vertex> adjacentTo(String name) {
		if (!hasVertex(name))
			return EMPTY_SET;
		return myAdjList.get(getVertex(name));
	}

	/**
	 * Return an iterator over the neighbors of Vertex v
	 * @param v the Vertex
	 * @return an Iterator over Vertices that are adjacent
	 * to the Vertex v, empty set if v is not in graph
	 */
	public Iterable<Vertex> adjacentTo(Vertex v) {
		if (!myAdjList.containsKey(v))
			return EMPTY_SET;
		return myAdjList.get(v);
	}

	/**
	 * Returns an Iterator over all Vertices in this Graph
	 * @return an Iterator over all Vertices in this Graph
	 */
	public Iterable<Vertex> getVertices() {
		return myVertices.values();
	}

	public int numVertices()
	{
		return myNumVertices;
	}
	
	public int numEdges()
	{
		return myNumEdges;
	}
	 // A function used by DFS
  public void DFS() {
	 
	 visited = new boolean[numVertices()];
	 counter = 0;
	 
	 for(Vertex u: myVertices.values()) {  
		  if(u.color == Color.WHITE) {
			  visitDFSIterative(u);
		  }
	  }
  }
  
  /**
	 * DFS with iterative method 
	 * Make use of stack
	 * @param u
	 */
  private void visitDFSIterative(Vertex u) {
	  res.add(u);
	  u.color = Color.GREY;
	  stack.add(u);
	  visited[counter]=true;
	  counter++;
	  
	  while(!stack.isEmpty()) {
		  Vertex v = stack.remove(stack.size()-1);
		
		  Iterator<Vertex> i = myAdjList.get(v).iterator();
		  
		 while(i.hasNext()){
			  Vertex tmp = i.next();
			  if(!visited[counter]) {
			  if(tmp.color == Color.WHITE) {
				  tmp.predecessor = v;
				  tmp.color = Color.GREY;
				  res.add(tmp);
				  stack.add(tmp); 
				  visited[counter]=true;
				  
			    }
			
		 }
	}
	
		  v.color = Color.BLACK;	
		 
	  }
	  u.color = Color.BLACK;
  }
  
  public void printRes() {
	  System.out.print("Final Exam Period 1 => ");
	  for(int a=0; a<res.size(); a++) {
		  System.out.print(res.get(a)+" ");
		  res.remove(a);
	  }
	  
	  System.out.println("\n");
	  System.out.print("Final Exam Period 2 => ");
	  for(int a=0; a<res.size(); a++) {
		  System.out.print(res.get(a)+" ");
		  res.remove(a);
	  }
	  
	  System.out.println("\n");
	  System.out.print("Final Exam Period 3 => ");
	  for(int a=0; a<res.size(); a++) {
		  System.out.print(res.get(a)+" ");
		  res.remove(a);
	  }
	  System.out.println("\n");
	  System.out.println("**********************************************");
  }
	
	/*
	 * Returns adjacency-list representation of graph
	 */
	public String toString() {
		String s = "";
		for (Vertex v : myVertices.values()) {
			s += v + "-> ";
			for (Vertex w : myAdjList.get(v)) {
				s += w + "-";
			}
			s += "\n";
		}
		return s;
	}
}
	
	