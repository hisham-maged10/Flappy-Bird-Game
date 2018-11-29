/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
package gaming;

import javax.swing.JPanel;
import java.awt.Graphics;

public class Renderer extends JPanel
{

	private static final long serialVersionUID = 1L;
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		FlappyBird.flappybird.repaint(g);
	}
}
