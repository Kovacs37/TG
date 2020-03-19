package kmeans;

import java.util.ArrayList;
import java.util.List;

import main.Noeud;

 
public class KMeans {
 
	//Number of Clusters. This metric should be related to the number of Noeuds
    private int NUM_CLUSTERS = 3;    
    //Number of Noeuds
    private int NUM_NoeudS = 15;
    //Min and Max X and Y
    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 10;
    
    private List<Noeud> Noeuds;
    private List<Cluster> clusters;
    
    public KMeans() {
    	this.Noeuds = new ArrayList();
    	this.clusters = new ArrayList();    	
    }
    
    public static void main(String[] args) {
    	
    	KMeans kmeans = new KMeans();
    	kmeans.init();
    	kmeans.calculate();
    }
    
    //Initializes the process
    public void init() {
    	
    	//Create Clusters
    	//Set Random Centroids
    	for(int i = 0; i < NUM_CLUSTERS; i++) {
    		Cluster cluster = new Cluster(i);
    		Noeud centroid = Noeud.createRandomNoeud(MIN_COORDINATE,MAX_COORDINATE);
    		cluster.setCentroid(centroid);
    		clusters.add(cluster);
    	}
    	
    	//Print Initial state
    	plotClusters();
    }
 
	private void plotClusters() {
    	for (int i = 0; i &lt; NUM_CLUSTERS; i++) {
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
        	
        	List lastCentroids = getCentroids();
        	
        	//Assign Noeuds to the closer cluster
        	assignCluster();
            
            //Calculate new centroids.
        	calculateCentroids();
        	
        	iteration++;
        	
        	List currentCentroids = getCentroids();
        	
        	//Calculates total distance between new and old Centroids
        	double distance = 0;
        	for(int i = 0; i &lt; lastCentroids.size(); i++) {
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
    
    private List getCentroids() {
    	List centroids = new ArrayList(NUM_CLUSTERS);
    	for(Cluster cluster : clusters) {
    		Noeud aux = cluster.getCentroid();
    		Noeud Noeud = new Noeud(aux.getX(),aux.getY());
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
            for(int i = 0; i &lt; NUM_CLUSTERS; i++) {
            	Cluster c = clusters.get(i);
                distance = Noeud.distance(Noeud, c.getCentroid());
                if(distance &lt; min){
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
            double sumX = 0;
            double sumY = 0;
            List list = cluster.getNoeuds();
            int n_Noeuds = list.size();
            
            for(Noeud Noeud : list) {
            	sumX += Noeud.getX();
                sumY += Noeud.getY();
            }
            
            Noeud centroid = cluster.getCentroid();
            if(n_Noeuds &gt; 0) {
            	double newX = sumX / n_Noeuds;
            	double newY = sumY / n_Noeuds;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }
}





