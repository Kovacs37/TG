package main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Graphe {
	public Graphe() {
		setNoeuds(new LinkedList<Noeud>());
		setHmap(new HashMap<Integer, Noeud>());
	}

	public Graphe(int k) {
		this();

		for (int i = 0; i < k; i++) {
			getNoeuds().add(new Noeud(i + 1));
			/* Adding elements to HashMap */
			truc.put(i + 1, getNoeuds().getLast());
		}
	}

	// compatible only java 11 and +
	public Graphe(String file) throws Exception {
		this();

		String path = file;
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

	public void parcours(Graphe g) {
		// attention peut être utiliser la hashmasp ???

		for (Noeud n : g.getNoeuds()) {
			n.setMark(false);
		}
		for (int i = 0; i < g.getNoeuds().size(); ++i) {
			if (!g.getNoeud(i).isMark()) {
				profR(this, i);
				// profI(this, i);
				// largeur, this, i);
			}

		}
	}

	public void profR(Graphe g, int n) {
		g.getNoeud(n).setMark(true);
		System.out.println(n);
		// for(int i =n;i<g.)
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
		return truc;
	}

	public void setHmap(HashMap<Integer, Noeud> hmap) {
		this.truc = hmap;
	}

	// attention doit Ãªtre snas doublons
	private LinkedList<Noeud> noeuds;

	private HashMap<Integer, Noeud> truc;

	// Export d’un graphe sous format CSV selon la liste de ses arcs
	// Format Source : Target
	public void export() {
		String buff = "Source,Target\n";
		String sep = ",";
		for (Noeud n : this.noeuds) {
			for (Arc a : n.getSucc()) {
				buff += a.getCible().getId() + sep + a.getSource().getId() + "\n";
			}
		}
		File outputFile = new File(this.getClass() + ".csv");
		FileWriter out;
		try {
			out = new FileWriter(outputFile);
			out.write(buff);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
