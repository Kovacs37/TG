package main;

public class MatriceAdja 
{
    private final int noeuds;
    private int[][] adjacency_matrix;
 
    public MatriceAdja(int v) 
    {
        noeuds = v;
        adjacency_matrix = new int[noeuds + 1][noeuds + 1];
    }
 
    public void makeEdge(int to, int from, int edge) 
    {
        try 
        {
            adjacency_matrix[to][from] = edge;
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("The noeuds does not exists");
        }
    }
 
    public int getEdge(int to, int from) 
    {
        try 
        {
            return adjacency_matrix[to][from];
        }
        catch (ArrayIndexOutOfBoundsException index) 
        {
            System.out.println("The noeuds does not exists");
        }
        return -1;
    }
 
}
