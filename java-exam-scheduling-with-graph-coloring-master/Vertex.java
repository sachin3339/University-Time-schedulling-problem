package cse225;


public class Vertex implements Comparable<Vertex> {
	
	public enum Color {
		WHITE, GREY, BLACK
	}
	
	/**
	 * label for Vertex
	 */
	public String name;  
	/**
	 * length of shortest path from source
	 */

	public Vertex predecessor; // previous vertex
	
	public static final int INFINITY = Integer.MAX_VALUE;
	
	public Color color;

	public Vertex(String v)
	{
		name = v;
		predecessor = null;
		color = Color.WHITE;
	}

	/**
	 * The name of the Vertex is assumed to be unique, so it
	 * is used as a HashCode
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode()
	{
		return name.hashCode();
	}
	
	public String toString()
	{ 
		return name;
	}
	/**
	 * Compare on the basis of lexicographically
	 */
	public int compareTo(Vertex other)
	{
	    return name.compareTo(other.name);
	}
}
