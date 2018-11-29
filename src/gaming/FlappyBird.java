/**
 * @author ${hisham_maged10}
 *https://github.com/hisham-maged10
 * ${DesktopApps}
 */
package gaming;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
public class FlappyBird implements ActionListener,MouseListener,KeyListener
{
	
	public final int WIDTH=1280;
	public final int HEIGHT=720;
	public Renderer renderer;
	public static FlappyBird flappybird;
	public Rectangle bird;
	public ArrayList<Rectangle> columns;
	public int ticks,motion,score;
	private static Image bg;
	private static Image pic;
	public Random random;
	private static boolean end,start;
	public int speed=8;
	public static void main(String[] args)
	{
		loadpics();
		flappybird=new FlappyBird();
	}
	
	public FlappyBird() 
	{
		JFrame jframe=new JFrame("Flappybirds by Hisham Maged");
		Timer timer=new Timer(14, this);
		renderer=new Renderer();
		random=new Random();
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframe.add(renderer);
		jframe.addKeyListener(this);
		jframe.addMouseListener(this);
		jframe.setResizable(false);
		jframe.setSize(WIDTH,HEIGHT);
		jframe.setVisible(true);
		bird=new Rectangle(WIDTH/2 -70,HEIGHT/2 -70,40,40);
		bg= new ImageIcon("src/images/799254_flappy-generator-plus-create-your-own-flappy-bird-game_9600x950_h.png").getImage();
		pic= new ImageIcon("src/images/Untitled-1.png").getImage();
		columns = new ArrayList<Rectangle>();
		addcolumn(true);
		addcolumn(true);
		addcolumn(true);
		addcolumn(true);
		timer.start();
	}
	public void actionPerformed(ActionEvent e)
	{
		int speed=8;
		if(start)
		{
		for(int i=0;i < columns.size();i++)
		{
			Rectangle column=columns.get(i);
			column.x-=speed;
		}
		ticks++;
		
		if(ticks%2 == 0 && motion<15 )
		{
			motion+=2;
		}
		for(int i=0;i<columns.size();i++)
		{
			Rectangle column=columns.get(i);
			if(column.x+column.width<0)
			{
				columns.remove(column);
				if(column.y ==0 )
				{
				addcolumn(false);
				}	
			}
		}
		bird.y+=motion;
		for(Rectangle column:columns)
		{
			if(column.y==0 && bird.x+100/2 > column.x+column.width/2-speed && bird.x+100/2 < column.x+column.width/2 +speed)
			{
				score++;
			}
			if(column.intersects(bird))
			{
				end=true;
				bird.x=column.x-bird.width;
			}
		}
	
		if(bird.y> HEIGHT-60)
		{
			end=true;
		}
		if(bird.y+motion>=HEIGHT-60)
		{
			end=true;
			bird.y=HEIGHT-60-75;
		}
		
		}
	
		renderer.repaint();
		
	}
	public void addcolumn(boolean begin)
	{
		int space=310;
		int width=100;
		int height=100+random.nextInt(300);
		if(begin)
		{
		columns.add(new Rectangle(WIDTH + width*2 + columns.size()*550 ,HEIGHT-height-60,width,height));
		columns.add(new Rectangle(WIDTH+width*2+(columns.size()-1)*550,0,width,HEIGHT-height-space));
		}else
		{
			columns.add(new Rectangle(columns.get(columns.size()-1).x+600 ,HEIGHT-height-60,width,height));
			columns.add(new Rectangle(columns.get(columns.size()-1).x,0,width,HEIGHT-height-space));
		}
		
	
	}
		
	
	public void DrawColumns(Graphics g,Rectangle column)
	{
		g.setColor(Color.GREEN.darker().darker().darker());
		g.fillRect(column.x, column.y, column.width, column.height);
	}
	
	public void repaint(Graphics g)
	{
		g.drawImage(bg,0,0 ,null);
		g.drawImage(pic,bird.x,bird.y,null);
		for(Rectangle column : columns)
		{
			DrawColumns(g,column);
		}
		g.setColor(Color.DARK_GRAY);
		g.setFont(new Font("Arial",1,25));
		g.drawString("Made By Hisham Maged", WIDTH/2-600, HEIGHT-70);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial",1,100));
		if(!start)
		{
		g.drawString("Click to start !", WIDTH/2-300, HEIGHT/2-30);
		}	
		if(end)
		{
		g.drawString("Game over !", WIDTH/2-300, HEIGHT/2-30);
		g.drawString("Your Score is "+ score, WIDTH/2-350, HEIGHT/2+150);
		}
		if(!end && start)
		{
			g.drawString(String.valueOf(score), WIDTH/2-50, 100);
		}
		
	}
	public void jump()
	{
		if(end)
		{
			bird=new Rectangle(WIDTH/2 -70,HEIGHT/2 -70,40,40);
			bg= new ImageIcon("src/images/799254_flappy-generator-plus-create-your-own-flappy-bird-game_9600x950_h.png").getImage();
			pic= new ImageIcon("src/images/Untitled-1.png").getImage();
			motion=0;
			score=0;
			columns.clear();
			addcolumn(true);
			addcolumn(true);
			addcolumn(true);
			addcolumn(true);
			end=false;
			
		}
		if(!start)
		{
			start=true;
			
		}
		else if(!end)
		{
			if(motion >0 )
			{
				motion=0;
			}
			motion-=12;
		}
	}
	
	public static void loadpics()
	{
		bg= new ImageIcon("src/images/799254_flappy-generator-plus-create-your-own-flappy-bird-game_9600x950_h.png").getImage();
		pic= new ImageIcon("src/images/Untitled-1.png").getImage();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		jump();
		
	}
	public void mouseEntered(MouseEvent arg0) {
		
	}
	public void mouseExited(MouseEvent arg0) {
		
	}

	public void mousePressed(MouseEvent arg0) {
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
	}

	public void keyPressed(KeyEvent arg0) {
		
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE)
		{
			jump();
		}
		
	}

	public void keyTyped(KeyEvent arg0) {
		
	}
	
}
