package main;

import java.util.ArrayList;

public class PartitionSimple implements IPartitionMethod{
	
	public ArrayList<Partition> partition(Graphe graph, int k) throws Exception {
		int list_size = graph.getHmap().size();
		if (list_size <= k) {
			int noeudposition = 0;
			
			int nbelement = (int)list_size/k;
			ArrayList<Partition> partitions = new ArrayList<Partition>();
			
			for (int i = 0; i < k-1; i++) {
				Partition partition = new Partition();
				for (int j = 0; j < nbelement; j++) {
					partition.addNoeud(graph.getNoeud(noeudposition));
					noeudposition++;
				}
				partitions.add(partition);
			}
			Partition partition = new Partition();
			while(noeudposition < list_size) {
				partition.addNoeud(graph.getNoeud(noeudposition));
				noeudposition++;
			}
			partitions.add(partition);
			return partitions;
		}
		else {
			throw new Exception("le nombre de partition et plus grand que le nombre d'element");
		}
	}
}
