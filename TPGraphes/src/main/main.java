package main;

import java.util.ArrayList;

import kmeans.FloydWarshall;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("test");
		Graphe rg = new Graphe("Arcs-test.csv");
		//Graphe g = new Graphe(36);
		//g.addArc(36, 37);
		//System.out.println(g.toString());
		//RandomGraphe rg = new RandomGraphe(5, 0.3);
		System.out.println(rg.toString());
		
		
		//rg.export();
		//g.parcours(g);
		


		
        int v, e;
        v = rg.getNoeuds().size();
        ArrayList<Arc> ars = rg.getArcs();
        e = ars.size();
        MatriceAdja graph = new MatriceAdja(v);
          
		System.out.println("ok");       
        System.out.println("Enter the edges: <to> <from>");
            for(Arc a : ars) 
            {
            	//System.out.println("from "+ a.getSource().getId() + " to "+a.getCible().getId());
                graph.makeEdge(a.getCible().getId(), a.getSource().getId(), 1);
            }
 
            System.out.println("The adjacency matrix for the given graph is: ");
            System.out.print("  ");
            for (int i = 1; i <= v; i++)
                System.out.print(i + " ");
            System.out.println();
 
            for (int i = 1; i <= v; i++) 
            {
                System.out.print(i + " ");
                for (int j = 1; j <= v; j++) 
                    System.out.print(graph.getEdge(i, j) + " ");
                System.out.println();
            }
 
            
            
           
		
		
		
		
		
		
		
		
		
		
		
	}
	

}
