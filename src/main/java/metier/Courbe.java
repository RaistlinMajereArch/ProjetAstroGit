package metier;

import java.awt.Graphics;

import javax.swing.JPanel;

public class Courbe extends JPanel{
	int [] x;
	int [] y;

	public Courbe(int[] x, int[] y) {
		this.x = x;
		this.y = y;
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//g.drawLine(0, 0, 100, 100);
		g.translate(getWidth()/2, getHeight()/2);
		g.drawPolyline(this.x,this.y,this.x.length);
	}
}
