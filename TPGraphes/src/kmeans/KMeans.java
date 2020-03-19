package kmeans;

import java.util.ArrayList;
import java.util.List;

import main.Noeud;

 
public class KMeans {
 
    private int NUM_CLUSTERS = 3;    
    private int NUM_NoeudS = 15;
    
    private List<Noeud> Noeuds;
    private List<Cluster> clusters;
    
    public KMeans() {
    	this.Noeuds = new ArrayList<Noeud>();
    	this.clusters = new ArrayList<Cluster>();    	
    }
    
    
    //Initializes the process
    public void init() {
    	
    	//Create Clusters
    	//Set Random Centroids
    	for(int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster cluster = new Cluster(i);
    		Noeud centroid = new Noeud((int) Math.random());
    		cluster.setCentroid(centroid);
    		clusters.add(cluster);
    	}
    	
    	//Print Initial state
    	plotClusters();
    }
 
	private void plotClusters() {
    	for (int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster c = clusters.get(i);
    		c.plotCluster();
    	}
    }
    
	//The process to calculate the K Means, with iterating method.
    public void calculate() {
        boolean finish = false;
        int iteration = 0;
        
        // Add in new data, one at a time, recalculating centroids with each new one. 
        while(!finish) {
        	//Clear cluster state
        	clearClusters();
        	
        	List<Noeud> lastCentroids = getCentroids();
        	
        	//Assign Noeuds to the closer cluster
        	assignCluster();
            
            //Calculate new centroids.
        	calculateCentroids();
        	
        	iteration++;
        	
        	List<Noeud> currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	for(int i = 0; i < lastCentroids.size(); i++) {
        		distance += Noeud.distance(lastCentroids.get(i),currentCentroids.get(i));
        	}
        	System.out.println("#################");
        	System.out.println("Iteration: " + iteration);
        	System.out.println("Centroid distances: " + distance);
        	plotClusters();
        	        	
        	if(distance == 0) {
        		finish = true;
        	}
        }
    }
    
    private void clearClusters() {
    	for(Cluster cluster : clusters) {
    		cluster.clear();
    	}
    }
    
    private List<Noeud> getCentroids() {
    	List<Noeud> centroids = new ArrayList<Noeud>(NUM_CLUSTERS);
    	for(Cluster cluster : clusters) {
    		Noeud aux = cluster.getCentroid();
    		Noeud Noeud = new Noeud(aux.getId());
    		centroids.add(Noeud);
    	}
    	return centroids;
    }
    
    private void assignCluster() {
        double max = Double.MAX_VALUE;
        double min = max; 
        int cluster = 0;                 
        double distance = 0.0; 
        
        for(Noeud Noeud : Noeuds) {
        	min = max;
            for(int i = 0; i < NUM_CLUSTERS; i++) {
            	Cluster c = clusters.get(i);
                distance = main.Noeud.distance(Noeud, c.getCentroid());
                if(distance < min){
                    min = distance;
                    cluster = i;
                }
            }
            Noeud.setCluster(cluster);
            clusters.get(cluster).addNoeud(Noeud);
        }
    }
    
    private void calculateCentroids() {
        for(Cluster cluster : clusters) {
            double sum = 0;
            List<Noeud> list = cluster.getNoeuds();
            int n_Noeuds = list.size();
            
            for(Noeud Noeud : list) {
            	sum += Noeud.getId();
            }
            
            Noeud centroid = cluster.getCentroid();
            if(n_Noeuds > 0) {
            	double newX = sum / n_Noeuds;
                centroid.setId((int) newX);
            }
        }
    }
}





