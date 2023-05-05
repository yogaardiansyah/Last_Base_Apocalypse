package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import entity.Player;

import javax.swing.JPanel; 
 
public class UiPanel extends JPanel implements Runnable{
	/**
	 
	 */
	private static final long serialVersionUID = 1L;
	final int originalTileSize = 16;
	final int scale = 3;
	
	final int tileSize = originalTileSize * scale;
	final int maxScreenCol = 16;
	final int maxScreenRow = 12;
	final int screenWidth = tileSize * maxScreenCol;
	final int screenHeigth = tileSize * maxScreenRow;
	
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread runThread;
	Player player = new Player(this,keyH);
	
	int playerX = 100;
	int playerY = 100; 
	int playerSpeed = 4;
	
	
	public UiPanel() {
		this.setPreferredSize(new Dimension(screenWidth,screenHeigth));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	public void startRunThread() {
		runThread = new Thread(this);
		runThread.start();
	}
//	
//	@Override
//	public void run() {
//		double drawInterval = 1000000000/FPS;
//		double nextDrawTime = System.nanoTime() + drawInterval;
//	
//		while(runThread != null) {
//				
//			update();
//			
//			repaint();
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();
//				remainingTime = remainingTime/1000000;
//				
//				if(remainingTime < 0) {
//					remainingTime = 0;
//				}
//				
//				Thread.sleep((long) remainingTime);
//				
//				nextDrawTime += drawInterval;
//				
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//	} 
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		
		while(runThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;

			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
				
			}
		
		}
	}
		
	public void update() {
		
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;			
		}
		else if(keyH.downPressed == true) {
			playerY += playerSpeed;	
		}
		else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;	
		}
		else if(keyH.rightPressed == true) {
			playerX  += playerSpeed;	
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setColor(Color.white);
		
		g2.fillRect(playerX, playerY, tileSize, tileSize);
		
		g2.dispose();
	}
	
}
