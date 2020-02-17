package main;

public class main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		
		System.out.println("test");
		Graphe g = new Graphe("Arcs-toy2.csv");
		//Graphe g = new Graphe(36);
		//g.addArc(36, 37);
		System.out.println(g.toString());
		g.export();
	}
	

}
