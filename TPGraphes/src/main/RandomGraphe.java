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
			w = w + 1 + (int)Math.floor((Math.log(1 - r) / Math.log(1 - p)));
			while(v <= w && v < n) {
				w = w - v;
				v = v + 1;
			}
			if (v < n) {
				add(v, w);
			}
		}
	}
	
	/**
	 * Permet de générer un graphe aléatoire selon le modèle de Erdös-Rényi
	 * @param n : le nombre de noeuds
	 * @param m : nombres d'arcs 
	 */
	public RandomGraphe(int n, int m) {
		super();
		for(int i =0; i < m - 1; i++) {
			//int max = binomialCoeff(n, 2);
			//int r = (int)(Math.random()* max);
			int[] tab = bijection(i);
			add(tab[0],tab[1]);
		}
	}
	private int[] bijection(int i) {
		int[] tab = new int[2];
		tab[0] = 1 + (int)Math.floor(-(1/2) + Math.sqrt((1/4) + 2 * i));
		tab[1] = i - (tab[0]*(tab[0]-1))/2;
		
		return tab;
	}
    // Returns value of Binomial  
    // Coefficient C(n, k) 
    private static int binomialCoeff(int n, int k)  
    { 
      
        // Base Cases 
        if (k == 0 || k == n) 
            return 1; 
          
        // Recur 
        return binomialCoeff(n - 1, k - 1) +  
                    binomialCoeff(n - 1, k); 
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
