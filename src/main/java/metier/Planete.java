package metier;

import java.util.ArrayList;
import java.util.List;

public class Planete extends CorpsCeleste{

	public Planete(int id, double masse, double diametre, double x, double y, double vx, double vy, boolean etat,
			String nom, int id_parent) 
	{
		super(id, masse, diametre, x, y, vx, vy, etat, nom, 1);
		
	}
	
	public Planete(double masse, double diametre, double x, double y, double vx, double vy, String nom, int id_parent) 
	{
		super(masse, diametre, x, y, vx, vy, nom, 1);
		
	}
		

	@Override
	public String toString() {
		return "Planete [G=" + G + ", id=" + id + ", masse=" + masse + ", diametre=" + diametre + ", x=" + x + ", y="
				+ y + ", vx=" + vx + ", vy=" + vy + ", etat=" + etat + ", nom=" + nom + ", id_parent=" + id_parent
				+ "]";
	}



}
