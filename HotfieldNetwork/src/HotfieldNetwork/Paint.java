package HotfieldNetwork;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Paint extends JPanel{

	 protected void paintComponent(Graphics g)
	  {
		 super.paintComponent(g);
		 g.setColor(Color.BLACK);
		 
		 g.drawLine(0, 0, 100, 100);
	  }
}
