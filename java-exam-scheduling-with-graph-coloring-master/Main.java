package cse225;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	Graph g = new Graph();
	
	private void readAndcreateGraph(String fileName) throws IOException
	{
		File file = new File("new.txt");
        BufferedReader br = null;
    
        try {
            FileReader fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line;
            
            while( (line = br.readLine()) != null ) {	
                 String[] tmp = line.split("\\t|,|;|\\.|\\?|!|-|:|@|\\[|\\]|\\(|\\)|\\{|\\}|_|\\*|/");
              
                 g.addVertex(tmp[1]);
                 
                 for(int y=1;y<tmp.length;y++) {
                	 if(g.hasVertex(tmp[y])) {
                		 for(int z=1; z<tmp.length; z++) {
                			 if(y != z)
                				 	g.addEdge(tmp[y],tmp[z]);
                			 else
                				 continue;
                		 }
                	 }
                 }
                
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }
     
	}
	
	private void printAdjList() {
		System.out.println("**************** ADJACENCY LÝST *****************");
		   System.out.println(g.toString()); 
			System.out.println("*************************************************");
	}

	private void graphColoringAlgo() {
		g.DFS();
		g.printRes();
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
      Main mn = new Main();
      mn.readAndcreateGraph("new.txt");
      mn.printAdjList();
      mn.graphColoringAlgo();
      
      
	}

}
