package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class PartitionClass {
	ArrayList<Partition> partition(Graphe graph, int k) throws Exception{
		int[] class_part = classChoice(k-1);
		ArrayList<Partition> partitions = new ArrayList<Partition>();
		
		HashMap<Integer, Noeud> hmap = new HashMap<Integer, Noeud>(graph.getHmap());
		int nbnoeud = graph.getHmap().size();
		
		for (int i = 0; i < k-1; i++) {
			Partition partition = new Partition();
			for (int j = 0; j < nbnoeud; j++) {
				if (class_part[i] == graph.getNoeud(j).getSucc().size() ) {
					partition.addNoeud(hmap.remove(j));
					nbnoeud--;
				}
			}
			partitions.add(partition);
		}
		
		Partition partition = new Partition();
		while (nbnoeud > 0) {
			partition.addNoeud(hmap.remove(nbnoeud));
			nbnoeud--;
		}
		partitions.add(partition);
		
		return partitions;
	}
	
	private int[] classChoice(int k) {
		int[] class_part = new int[k];
		 Scanner sc = new Scanner(System.in);
		for (int i = 0; i < k; i++) {
			class_part[i] = sc.nextInt();
		}
		sc.close();
		return class_part;
	}
}
