package main;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Graphe {
	public Graphe() {
		setNoeuds(new LinkedList<Noeud>());
		setHmap(new HashMap<Integer, Noeud>());
	}
	public Graphe(int k ) {
		this();
		
		for (int i=0; i<k; i++) {
			getNoeuds().add(new Noeud(i+1)) ;
			/*Adding elements to HashMap*/
		    truc.put(i+1, getNoeuds().getLast());
		}
	}
	
	
	// compatible only java 11 and +
	public Graphe(String file) throws Exception {
		this();
		
        String path = file;
        int index = 0;
        try {
            // default StandardCharsets.UTF_8
            String content = Files.readString(Paths.get(path));	
        	int x = 0,y=0, z=0;;
            
        	String[] jarjar = content.split("\n");
        	for(int i = 1;i<jarjar.length;++i) {
        		x= (int) jarjar[i].charAt(0) - '0';
        		y= (int) jarjar[i].charAt(2)- '0';
        	//	z= (int) jarjar[i].charAt(4)- '0';

        		
        		addNoeud(x);
        		addNoeud(y);
        		addArc(x,y);
        		
        		
        	}
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public void addNoeud(Noeud n) {

		if ( this.getHmap().get(n.getId())==null) {
			this.getNoeuds().add(n); 
			this.getHmap().put(n.getId(),n);
		}
	}
	public void addNoeud(int n) {
		if (this.getHmap().get(n)== null) {
			Noeud node= new Noeud(n);
			this.getNoeuds().add(node);
			this.getHmap().put(n, node);
		}
		
	}
	// n est la position
	public Noeud getNoeud(int n) {
		return(this.getHmap().get(n));
	}
	
	public void addArc(int x, int y) throws Exception {
		
		if (this.getHmap().get(x) !=null && this.getHmap().get(y) != null) {
			if ( !(this.getHmap().get(x).hasSuccesseur(y) ) ) 
				new Arc(this.getHmap().get(x),this.getHmap().get(y));
		}
		
	}
	
	
	public void parcours(Graphe g) {
		// attention peut être utiliser la hashmasp ???
		for(Noeud n : g.getNoeuds()) {
			n.setMark(false);
		}
		String str;
		for(int i =1;i<=g.getNoeuds().size();++i) {
			//System.out.println(g.getNoeud(1));
			if(!g.getNoeud(i).isMark()) {
				System.out.println();
				profR(this, i);
				//profI(this, i);
				// largeur, this, i);
				// salut moi c'est jean patrice
			}
			
		}
	}
	
	public void profR(Graphe g, int n) {
		g.getNoeud(n).setMark(true);
		System.out.println(n);
		
		// parcours de la liste d'arc succ 
		//	for(int i =n;i<g.)
		
		LinkedList<Arc> t= g.getNoeud(n).getSucc();
		int size = t.size();
		//System.out.println("size " + size);
		for(int i =0;i<size;++i) {
			//System.out.println("i " +i);
			System.out.print("-");
			if(!t.get(i).getCible().isMark()) profR(g,t.get(i).getCible().getId());
			//if (!g.getNoeud(i).isMark()) profR(g, i);
		
		}
		//System.out.println();
		
	}
	
	
	
	public void profI() {
		
	}
	public void ProfI(Noeud n) {
		
	}
	
	
	
	public String toString() {
		return "Graphe [noeuds=" + getNoeuds() + "]";
	}
	
	public LinkedList<Noeud> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(LinkedList<Noeud> noeuds) {
		this.noeuds = noeuds;
	}
	public HashMap<Integer, Noeud> getHmap() {
		return truc;
	}

	public void setHmap(HashMap<Integer, Noeud> hmap) {
		this.truc = hmap;
	}
	
	
	// attention doit Ãªtre snas doublons
	private LinkedList<Noeud> noeuds;
	
	private HashMap<Integer, Noeud> truc;

}
