package main;

public class RandomGraphe extends Graphe{
	
	/**
	 * Permet de générer un graphe aléatoire selon le modèle de Gilbert
	 * @param n : le nombre de noeuds
	 * @param p la probabilité des arcs [0, 1]
	 */
	public RandomGraphe(int n, double p) {
		super();
		int v = 1;
		int w = -1;
		while(v < n) {
			double r = Math.random();
			w = w + 1 + (int)(Math.log(1 - r) / Math.log(1 - p));
			while(v <= w && v < n) {
				w = w - v;
				v = v + 1;
			}
			if (v < n) {
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
	}
}
