package metier;

import java.awt.Graphics;
import javax.swing.JPanel;

public class Rectangle extends JPanel {
	int timestep;
	int nbTimestep;

	public Rectangle(int timestep, int nbTimestep) {
		this.timestep = timestep;
		this.nbTimestep = nbTimestep;
	}
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawRoundRect(10,10,nbTimestep,40,10,10);
		g.fillRoundRect(10,10,timestep,40,10,10);

	}
}
