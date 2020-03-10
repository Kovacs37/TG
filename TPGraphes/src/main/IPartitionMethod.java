package main;

import java.util.ArrayList;

public interface IPartitionMethod {
	ArrayList<Partition> partition(Graphe graph, int k) throws Exception;
}
