package main;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Graphe {

	// VOILA C AU DEBU
	private LinkedList<Noeud> noeuds;

	private HashMap<Integer, Noeud> hmap;

	public Graphe() {
		setNoeuds(new LinkedList<Noeud>());
		setHmap(new HashMap<Integer, Noeud>());
	}

	public Graphe(int k) {
		this();

		for (int i = 0; i < k; i++) {
			getNoeuds().add(new Noeud(i + 1));
			/* Adding elements to HashMap */
			hmap.put(i + 1, getNoeuds().getLast());
		}
	}

	/**
	 * @since JAVA 11
	 * @param file
	 * @throws Exception
	 */
	public Graphe(String file) throws Exception {
		this();

		String path = file;
		int index = 0;
		try {
			// default StandardCharsets.UTF_8
			String content = Files.readString(Paths.get(path));
			int x = 0, y = 0;
			String[] jarjar = content.split("\n");
			for (int i = 1; i < jarjar.length; ++i) {
				x = (int) jarjar[i].charAt(0) - '0';
				y = (int) jarjar[i].charAt(2) - '0';
				addNoeud(x);
				addNoeud(y);
				addArc(x, y);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addNoeud(Noeud n) {

		if (this.getHmap().get(n.getId()) == null) {
			this.getNoeuds().add(n);
			this.getHmap().put(n.getId(), n);
		}
	}

	public void addNoeud(int n) {
		if (this.getHmap().get(n) == null) {
			Noeud node = new Noeud(n);
			this.getNoeuds().add(node);
			this.getHmap().put(n, node);
		}

	}

	// n est la position
	public Noeud getNoeud(int n) {
		return (this.getHmap().get(n));
	}

	public void addArc(int x, int y) throws Exception {

		if (this.getHmap().get(x) != null && this.getHmap().get(y) != null) {
			if (!(this.getHmap().get(x).hasSuccesseur(y)))
				new Arc(this.getHmap().get(x), this.getHmap().get(y));
		}

	}

	// mon algo parcours
	public void parcours(Graphe g) {
		for (Noeud n : g.getNoeuds()) {
			n.setMark(false);
		}
		String str;
		for (int i = 1; i <= g.getNoeuds().size(); ++i) {
			if (!g.getNoeud(i).isMark()) {
				System.out.println();
				profR(this, i);
			}

		}
	}

	public void profR(Graphe g, int n) {
		g.getNoeud(n).setMark(true);
		System.out.println(n);

		LinkedList<Arc> t = g.getNoeud(n).getSucc();
		int size = t.size();
		for (int i = 0; i < size; ++i) {
			if (!t.get(i).getCible().isMark())
				profR(g, t.get(i).getCible().getId());
		}
		 System.out.println();

	}

	public void parcoursprofR() {
		// Initialisation de mark à False
		for (Noeud node : this.noeuds)
			node.setMark(false);
		// Pout tout noeud non marké,
		for (Noeud node : this.noeuds) {
			if (!node.isMark()) {
				// lancer le parcours en profondeur profR
				this.profR(node, "");
			}
		}

	}

	public void profR(Noeud n, String buffer) {
		// marker n
		n.setMark(true);
		// afficher n
		// System.out.println("Mark " +n.getId());
		// this.buffer += n.getId()+"\n"+this.buffer;
		System.out.println(buffer + n.getId());

		// Pour tout successeur non marké, appeler profR(successeur)
		for (Arc arc : n.getSucc()) {
			// System.out.println(" on va de "+n.getId()+"->"+arc.getCible().getId());
			if (!arc.getCible().isMark()) {
				this.profR(arc.getCible(), buffer + "--");
			}
		}
	}

	public void parcoursprofI() {
		// Initialisation de mark à False
		for (Noeud node : this.noeuds)
			node.setMark(false);
		// Pout tout noeud non marké,
		for (Noeud node : this.noeuds) {
			if (!node.isMark()) {
				// lancer le parcours en profondeur prof (
				this.profI(node);
			}
		}

	}

	public void profI(Noeud n) {
		Stack<Noeud> st;
		st = new Stack<Noeud>();
		n.setMark(true);
		st.push(n);
		System.out.println(n.getId());
		Noeud node;
		boolean trouve = true;
		while (!st.isEmpty()) {
			node = st.peek();
			trouve = true;
			for (Arc arc : node.getSucc())
				trouve = trouve && arc.getCible().isMark();
			if (trouve) {
				node = st.pop();

			} else {
				for (Arc arc : node.getSucc()) {
					if (!arc.getCible().isMark()) {
						arc.getCible().setMark(true);
						st.push(arc.getCible());
						System.out.println(arc.getCible().getId());
					}
				}
			}
		}
	}

	public void parcourslargeur() {
		// Initialisation de mark à False
		for (Noeud node : this.noeuds)
			node.setMark(false);
		// Pout tout noeud non marké,
		for (Noeud node : this.noeuds) {
			if (!node.isMark()) {
				// lancer le parcours en profondeur prof (
				this.largeur(node);
			}
		}

	}

	public void largeur(Noeud n) {
		LinkedList<Noeud> file;
		file = new LinkedList<Noeud>();
		n.setMark(true);
		file.addFirst(n);
		System.out.println(n.getId());
		Noeud node;
		while (!file.isEmpty()) {
			node = file.getLast();
			file.removeLast();
			for (Arc arc : node.getSucc()) {
				if (!arc.getCible().isMark()) {
					arc.getCible().setMark(true);
					file.addFirst(arc.getCible());
					System.out.println(arc.getCible().getId());
				}
			}
		}
	}

	public String toString() {
		return "Graphe [noeuds=" + getNoeuds() + "]";
	}

	public LinkedList<Noeud> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(LinkedList<Noeud> noeuds) {
		this.noeuds = noeuds;
	}

	public HashMap<Integer, Noeud> getHmap() {
		return hmap;
	}

	public void setHmap(HashMap<Integer, Noeud> hmap) {
		this.hmap = hmap;
	}

}
