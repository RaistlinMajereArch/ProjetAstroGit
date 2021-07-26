package metier;

public class Satellite extends CorpsCeleste{

	public Satellite(int id, double masse, double diametre, double x, double y, double vx, double vy, boolean etat,
			String nom, int id_parent) {
		super(id, masse, diametre, x, y, vx, vy, etat, nom, id_parent);
		// TODO Auto-generated constructor stub
	}
	
	
	public Satellite(double masse, double diametre, double x, double y, double vx, double vy, String nom, int id_parent) 
	{
		super(masse, diametre, x, y, vx, vy, nom, id_parent);
		
	}


	@Override
	public String toString() {
		return "Satellite [G=" + G + ", id=" + id + ", masse=" + masse + ", diametre=" + diametre + ", x=" + x + ", y="
				+ y + ", vx=" + vx + ", vy=" + vy + ", etat=" + etat + ", nom=" + nom + ", id_parent=" + id_parent
				+ "]";
	}

	

	
}
