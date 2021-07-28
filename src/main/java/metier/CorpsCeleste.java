package metier;

import java.util.List;

public abstract class CorpsCeleste {
	protected final double G=6.6743E-20;
	protected int id;
	protected double masse;
	protected double diametre;
	protected double x;
	protected double y;
	protected double vx;
	protected double vy;
	protected boolean etat=true;
	protected String nom;
	protected int id_parent;
	
	

	public CorpsCeleste(int id, double masse, double diametre, double x, double y, double vx, double vy, boolean etat,
			String nom,int id_parent) {
		super();
		this.id = id;
		this.masse = masse;
		this.diametre = diametre;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.etat = etat;
		this.nom = nom;
		this.id_parent = id_parent;
	}
		public CorpsCeleste(double masse, double diametre, double x, double y, double vx, double vy, String nom,int id_parent) {
		super();
		this.masse = masse;
		this.diametre = diametre;
		this.x = x;
		this.y = y;
		this.vx = vx;
		this.vy = vy;
		this.nom = nom;
		this.id_parent = id_parent;
	}

	public double[] calculForce(CorpsCeleste c) {
		double [] force= {0,0};
		double distance=Math.sqrt(Math.pow((this.x-c.x),2)+Math.pow((this.y-c.y),2));
		force[0]=G*(this.masse*c.masse)*((c.x-this.x)/Math.pow(distance,3));
		force[1]=G*(this.masse*c.masse)*((c.y-this.y)/Math.pow(distance,3));
		/*double distance=Math.sqrt(Math.pow((this.x-c.x),2)+Math.pow((this.y-c.y),2));
		double forceVector=G*(this.masse*c.masse)/Math.pow(distance,2);
		double angle=Math.atan2((c.y-this.y),(c.x-this.x));
		force[0]=Math.cos(angle)*forceVector;
		force[1]=Math.sin(angle)*forceVector;*/
		
		return force;
	}
	
	public double[] calculAcceleration(List<double[]> forces) {
		double [] accelerations= {0,0};
		double [] sommeForces= {0,0};
		double [] force= {0,0};
		
		for (int i=0; i<forces.size();i++) {
			force=forces.get(i);
			sommeForces[0]+=force[0];
			sommeForces[1]+=force[1];
		}
		accelerations[0]=sommeForces[0]/masse;
		accelerations[1]=sommeForces[1]/masse;
		return accelerations;
	}
	
	public void calculVitesse(double[] accelerations) {
		vx+=accelerations[0]*86400;
		vy+=accelerations[1]*86400;
	}
	
	public void calculPosition() {
		x+=vx*86400;
		y+=vy*86400;
	}
	
	public void fusionne(CorpsCeleste c) {
		if (this.masse>=c.masse) {
			this.masse+=c.masse;
			c.setEtat(false);
		}else {
			c.masse+=this.masse;
			this.setEtat(false);
		}
		
	}

	
	
	public int getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public int getIdParent() {
		return id_parent;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setIdParent(int id_parent) {
		this.id = id_parent;
	}
	public void setDiametre(double diametre) {
		this.diametre = diametre;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getG() {
		return G;
	}

	public double getMasse() {
		return masse;
	}

	public double getDiametre() {
		return diametre;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getVx() {
		return vx;
	}

	public double getVy() {
		return vy;
	}

	public boolean isEtat() {
		return etat;
	}

	public void setMasse(double masse) {
		this.masse = masse;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setVx(double vx) {
		this.vx = vx;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	@Override
	public String toString() {
		return "CorpsCeleste [G=" + G + ", id=" + id + ", masse=" + masse + ", diametre=" + diametre + ", x=" + x
				+ ", y=" + y + ", vx=" + vx + ", vy=" + vy + ", etat=" + etat + ", nom=" + nom + ", id_parent=" + id_parent +"]";
	}
	
}
