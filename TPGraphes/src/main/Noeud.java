package main;
import java.util.LinkedList;

public class Noeud {

	public Noeud(int id) {
		this.id=id;
		succ = new LinkedList<Arc>();
	}
	public String toString() {
		String descriptionNoeud = "" ;
		descriptionNoeud = "Noeud [id=" + id +",successeurs :";
		for (Arc arc : succ) 
			descriptionNoeud +="->" + arc.getCible().getId();
		descriptionNoeud += "]";
		return descriptionNoeud;
		
	}
	
	
	
    public static double distance(Noeud p, Noeud centroid) {
    	return 1;
    }
	
	
	
	public boolean hasSuccesseur(int j) {
		for(Arc ar : succ) {
			if(ar.getCible().getId()==j) return true;
		}
		return false;
	}
	
		public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public LinkedList<Arc> getSucc() {
		return succ;
	}


	public void setSucc(LinkedList<Arc> succ) {
		this.succ = succ;
	}
	public boolean isMark() {
		return mark;
	}
	public void setMark(boolean mark) {
		this.mark = mark;
	}
    public void setCluster(int n) {
        this.cluster_number = n;
    }
    
    public int getCluster() {
        return this.cluster_number;
    }
		private int id;
		private LinkedList<Arc> succ;
		private boolean mark;
	    private int cluster_number = 0;

	
}
