package main;

public class Small_world extends Graphe{
	public Small_world(int n, int d) {
		super();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < i + d; j++) {
				add(i, j % n);
			}
		}
	}
	
	private void add(int v, int w) {
    	Noeud vNode = new Noeud(v);
		Noeud wNode = new Noeud(w);
		addNoeud(vNode);
		addNoeud(wNode);
		try {
			addArc(v, w);
			addArc(w, v);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
