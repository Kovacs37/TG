package main;
public class Arc {

	public Arc(Noeud x, Noeud y) {
		source=x;
		cible=y;
		this.source.getSucc().add(this);
	}
	public String toString() {
		return "Arc [source=" + source.getId() + ", cible=" + cible.getId() + "]";
		}
	
	
	public Noeud getSource() {
		return source;
	}
	public void setSource(Noeud source) {
		this.source = source;
	}
	public Noeud getCible() {
		return cible;
	}
	public void setCible(Noeud cible) {
		this.cible = cible;
	}


	Noeud source;
	Noeud cible;
}
