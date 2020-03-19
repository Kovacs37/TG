package kmeans;

import java.util.ArrayList;
import java.util.List;

import main.Noeud;
 
public class Cluster {
	
	public List<Noeud> Noeuds;
	public Noeud centroid;
	public int id;
	
	//Creates a new Cluster
	public Cluster(int id) {
		this.id = id;
		this.Noeuds = new ArrayList();
		this.centroid = null;
	}
 
	public List getNoeuds() {
		return Noeuds;
	}
	
	public void addNoeud(Noeud Noeud) {
		Noeuds.add(Noeud);
	}
 
	public void setNoeuds(List Noeuds) {
		this.Noeuds = Noeuds;
	}
 
	public Noeud getCentroid() {
		return centroid;
	}
 
	public void setCentroid(Noeud centroid) {
		this.centroid = centroid;
	}
 
	public int getId() {
		return id;
	}
	
	public void clear() {
		Noeuds.clear();
	}
	
	public void plotCluster() {
		System.out.println("[Cluster: " + id+"]");
		System.out.println("[Centroid: " + centroid + "]");
		System.out.println("[Noeuds: \n");
		for(Noeud p : Noeuds) {
			System.out.println(p);
		}
		System.out.println("]");
	}
 
}