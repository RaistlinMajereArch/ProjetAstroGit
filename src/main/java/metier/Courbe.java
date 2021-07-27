package metier;

import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

public class Courbe extends JPanel{
	List<int []> x;
	List<int []> y;

	
	public Courbe(List<int[]> x, List<int[]> y) {
		this.x = x;
		this.y = y;
	}


	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//g.drawLine(0, 0, 100, 100);
		g.translate(getWidth()/2, getHeight()/2);
		for (int i=0;i<this.x.size();i++) {
			g.drawPolyline(this.x.get(i),this.y.get(i),this.x.get(i).length);
		}
	}
}
