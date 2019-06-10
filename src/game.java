package de.luca.paddlepong;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class game extends JPanel implements KeyListener, ActionListener{
	
	private int width = 800;
	private int height = 500;
	
	private int xPadR = 760;
	private int yPadR = height/2 -50;
	private int xPadL = 20;
	private int yPadL = height/2 -50;
	private int xBall = width/2;
	private int yBall = height/2;
	private double rand = Math.random() + 0.5;
	private int dirBall = (int) rand;
	private int direction = 4;
	private int directionL = 4;
	private int directionR = 4;
	private int pointsE = 0;
	private int highscoreE;
	private int pointsMR = 0;
	private int highscoreMR;
	private int pointsML = 0;
	private int highscoreML;
	private String winner;
	
	private Image ball;
	private Image padR;
	private Image padL;
	private Timer timer;
	private JLabel label;
	private JLabel labelP;
	private JLabel labelH;
	private JLabel labelW;
	private char choice;
	
	
	/*
	 * TODO
	 * 1. Stoppe das / die Paddle wenn W, S, UP oder down nicht gedrückt ist  
	 * 
	 */
	
	
	
	
	

	public game(){
		
		addKeyListener(this);
		setPreferredSize(new Dimension(width, height));
		setFocusable(true);
		//setBackground(Color.WHITE);
		
		ImageIcon iconB = new ImageIcon(getClass().getResource("/ball.png"));
		ball = iconB.getImage();
		ImageIcon iconP = new ImageIcon(getClass().getResource("/pad.png"));
		padR = iconP.getImage();
		ImageIcon iconP2 = new ImageIcon(getClass().getResource("/pad.png"));
		padL = iconP2.getImage();
		
		timer = new Timer(40, this);
		
		label = new JLabel(" Drücke \"E\" für den Einzelspieler-Modus oder drücke \"M\" für den Mehrspieler-Modus. Das Spiel startet sofort nach Auswahl");
		label.setLocation(width/2 - 100, 20);
		label.setSize(new Dimension(200, 100));
		add(label);
		
	}
	

	
	
	//ActionListener
	@Override
	public void actionPerformed(ActionEvent e) {
		
		switch(choice) {
		case 'E':
			padMovementE();
			ballMovement();
			repaint();
			break;
		case 'M':
			padMovementM();
			ballMovement();
			repaint();
			break;
		default:
			break;
		}
		
		
		
	}	
	
	
	

	//KeyListener
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		//Bewegungen für linkes Paddle im Mehrspieler
		//Bewegungen für beide Paddle im Einzelspieler
		if(key == KeyEvent.VK_W){
			if(choice == 'M') {
				directionL = 0;
			}
			if(choice == 'E') {
				direction = 0;
			}
		}
		if(key == KeyEvent.VK_S){
			if(choice == 'M') {
				directionL = 1;
			}
			if(choice == 'E') {
				direction = 1;
			}
		}
		
		
		/*
		//Stoppe wenn keine Taste gedrückt
		if(key != KeyEvent.VK_W || key != KeyEvent.VK_S){
			if(choice == 'M') {
				directionL = 4;
			}
			if(choice == 'E') {
				direction = 4;
			}
		}
		*/
		
		
		//Bewegung rechtes Paddle im Mehrspieler
		if(key == KeyEvent.VK_UP){
			directionR = 0;
		}
		if(key == KeyEvent.VK_DOWN){
			directionR = 1;
			//Einstellung für Einzelspieler
		}
		if(key == KeyEvent.VK_E){
			choice = 'E';
			pointsE = 0;
			timer.start();
			remove(label);
			
			if(labelP != null){				
				remove(labelP);
			}
			if(labelH != null){				
				remove(labelH);
			}
			if(labelW != null){				
				remove(labelW);
			}
		}
		//Einstellung für Mehrspieler
		if(key == KeyEvent.VK_M){
			choice = 'M';
			pointsMR = 0;
			pointsML = 0;
			timer.start();
			remove(label);
			
			if(labelP != null){				
				remove(labelP);
			}
			if(labelH != null){				
				remove(labelH);
			}
			if(labelW != null){				
				remove(labelW);
			}
		}
		//Exit aus Spiel
		if(key == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	
	
	
	//Zeichnet Elemente
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(padR, xPadR, yPadR, this);
		g.drawImage(padL, xPadL, yPadL, this);
		g.drawImage(ball, xBall, yBall, this);
		Toolkit.getDefaultToolkit().sync();

	}
	
	
	
	/*
	// 
	//eigene
	//Methoden
	//
	*/
	
	
	
	
	/*
	//PADDLE
	*/
	
	//Paddle Bewegung für Mehrspieler
	public void padMovementM() {
		//Bewegung linkes Paddle
		switch(directionL){
		case 0: //Paddle links bewegt sich hoch = W gedrückt
			yPadL-=10;
			break;
		case 1: //Paddle links bewegt sich runter = S gedrückt
			yPadL+=10;
			break;
		case 2: //wenn oberer Rand berührt
			yPadL+=10;
			break;
		case 3: //wenn unterer Rand berührt
			yPadL-=10;
			break;
		case 4: //Startposition, nicht bewegend
			yPadR-=0;
			yPadL-=0;
			yPadR+=0;
			yPadL+=0;
			break;
		default:
			break;
		}
		//Bewegung rechtes Paddle
			switch(directionR){
			case 0: //Paddle rechts bewegt sich hoch = W gedrückt
				yPadR-=10;
				break;
			case 1: //Paddle rechts bewegt sich runter = S gedrückt
				yPadR+=10;
				break;
			case 2: //wenn oberer Rand berührt
				yPadR+=10;
				break;
			case 3: //wenn unterer Rand berührt
				yPadR-=10;
				break;
			case 4: //Startposition, nicht bewegend
				yPadR-=0;
				yPadL-=0;
				yPadR+=0;
				yPadL+=0;
				break;
			default:
				break;
			}
		
		
		//Wenn linkes Paddle oberen/unteren Rand berührt
		if(yPadL <= 0){
			directionL = 2;
		}
		if(yPadL +100 >= height){
			directionL = 3;
		}
		//Wenn rechtes Paddle oberen/unteren Rand berührt
		if(yPadR <= 0){
			directionR = 2;
		}
		if(yPadR +100 >= height){
			directionR = 3;
		}
		
	}
	
	
		//Paddle Bewegung für Einzelspieler
		public void padMovementE() {
			//Bewegung beide Paddle
			switch(direction){
			case 0: //Paddles bewegen sich hoch = W gedrückt
				yPadL-=10;
				yPadR-=10;
				break;
			case 1: //Paddles bewegen sich runter = S gedrückt
				yPadL+=10;
				yPadR+=10;
				break;
			case 2: //wenn oberer Rand berührt
				yPadL+=10;
				yPadR+=10;
				break;
			case 3: //wenn unterer Rand berührt
				yPadL-=10;
				yPadR-=10;
				break;
			case 4: //Startposition, nicht bewegend
				yPadR-=0;
				yPadL-=0;
				yPadR+=0;
				yPadL+=0;
				break;
			default:
				break;
			}
			
			
			//Wenn linkes Paddle oberen/unteren Rand berührt
			if(yPadL <= 0){
				direction = 2;
			}
			if(yPadL +100 >= height){
				direction = 3;
			}
			//Wenn rechtes Paddle oberen/unteren Rand berührt
			if(yPadR <= 0){
				direction = 2;
			}
			if(yPadR +100 >= height){
				direction = 3;
			}
			
		}
	
	
	/*
	//BALL
	*/
	
	public void ballMovement(){
		//Bewegung Ball
		switch(dirBall){
		case 0: //rechts
			xBall+=10;
			yBall+=10;
			break;
		case 1: //links
			xBall-=10;
			yBall+=10;
			break;
		case 2: 
			xBall-=10;
			yBall-=10;
			break;
		case 3: 
			xBall+=10;
			yBall-=10;
			break;
		default:
			break;
		}
		
		
		//Wenn Ball rechten/linken Rand berührt
		if(xBall == width) {
			winner = "links";
		}
		if(xBall == 0) {
			winner = "rechts";
		}
		
		if(xBall == width || xBall == 0){
			xBall = width/2;
			yBall = height/2;
			yPadL = height/2 - 50;
			yPadR = height/2 - 50;
			timer.stop();
			
			
			labelP = new JLabel(); 
			labelP.setLocation(width/2 - 85, height/2 - 220);
			labelP.setSize(400, 20);
			labelH = new JLabel(); 
			labelH.setSize(400, 20);
			
			
			
			//Highscore und Punkte im Einzelsieler
			if(choice == 'E'){
				labelH.setLocation(width/2 - 70, height/2 - 190);
				if(highscoreE < pointsE){
					highscoreE = pointsE;
				}
				labelP.setText(" Deine erreichten Punkte (E): " + pointsE);
				labelH.setText(" Dein Highscore (E): " + highscoreE);
			}
			
			//Highscore und Punkte im Mehrspieler
			if(choice == 'M'){
				labelH.setLocation(width/2 - 103, height/2 - 190);
				if(highscoreMR < pointsMR){
					highscoreMR = pointsMR;
				}
				if(highscoreML < pointsML){
					highscoreML = pointsML;
				}
				labelP.setText(" Punkte links: " + pointsML + "      Punkte rechts: " + pointsMR);
				labelH.setText(" Highscore links: " + highscoreML + "      Highscore rechts: " + highscoreMR);
			}
			
			//Gewinner im Mehrspieler
			labelW = new JLabel(); 
			labelW.setLocation(width/2 - 85, height/2 - 160);
			labelW.setSize(400, 20);
			
			if(choice == 'M') {
				if(winner == "rechts") {
					labelW.setText("Der Gewinner ist der rechte Spieler");
					System.out.println("Works");
				}
				if(winner == "links") {
					labelW.setText("Der Gewinner ist der linke Spieler");
				}
			}
			
			
			
			add(label);
			add(labelP);
			add(labelH);
			add(labelW);
			
		}
		
		
		//Wenn Ball Rand unten berührt
		if(yBall + 20 == height){
			if(dirBall == 1){
				dirBall = 2;
			}
			if(dirBall == 0){
				dirBall = 3;
			}
			
		}
		//Wenn Ball Rand oben berührt
		if(yBall == 0){
			if(dirBall == 2){
				dirBall = 1;
			}
			if(dirBall == 3){
				dirBall = 0;
			}
			
		}
		
		hitPad();
		
	}
	



	public void hitPad(){
		
		//Wenn Ball rechtes Paddle berührt
		if(xBall + 20 == xPadR){
			if(yBall >= yPadR && yBall <= yPadR + 100 ){
				if(dirBall == 3){
					dirBall = 2;
					if(choice=='E'){
						pointsE+=10;
					}
					if(choice=='M'){
						pointsMR+=10;
					}
				}
				if(dirBall == 0){
					dirBall = 1;
					if(choice=='E'){
						pointsE+=10;
					}
					if(choice=='M'){
						pointsMR+=10;
					}
				}
			}
		}
		
		
		//Wenn Ball linkes Paddle berührt
		if(xBall == xPadL + 20){
			if(yBall >= yPadL && yBall <= yPadL + 100 ){
				if(dirBall == 2){
					dirBall = 3;
					if(choice=='E'){
						pointsE+=10;
					}
					if(choice=='M'){
						pointsML+=10;
					}
				}
				if(dirBall == 1){
					dirBall = 0;
					if(choice=='E'){
						pointsE+=10;
					}
					if(choice=='M'){
						pointsML+=10;
					}
				}
			}
		}
		
		//Wenn Ball genau obere Ecke von rechtem Paddle berührt
		if((xBall + 20 == xPadR && (yBall +20 >= yPadR && yBall <= yPadR))){
			if(dirBall == 0){
				dirBall = 2;
			}
			
			if(dirBall == 3){
				dirBall = 1;
			}
			
		}
		//Wenn Ball genau untere Ecke von rechtem Paddle berührt
		if((xBall + 20 == xPadR && (yBall +20 >= yPadR + 100 && yBall <= yPadR + 100))){
			if(dirBall == 0){
				dirBall = 2;
			}
			
			if(dirBall == 3){
				dirBall = 1;
			}
			
		}
		
		//Wenn Ball genau obere Ecke von linkem Paddle berührt
		if((xBall == xPadL && (yBall +20 >= yPadL && yBall <= yPadL))){
			if(dirBall == 2){
				dirBall = 0;
			}
			
			if(dirBall == 1){
				dirBall = 3;
			}
			
		}
		//Wenn Ball genau untere Ecke von linkem Paddle berührt
		if((xBall == xPadL && (yBall +20 >= yPadL + 100 && yBall <= yPadL + 100))){
			if(dirBall == 2){
				dirBall = 0;
			}
			
			if(dirBall == 1){
				dirBall = 3;
			}
			
		}
	
		//Ball berührt rechtes Paddle oben
		if(xBall + 20 >= xPadR && xBall + 20 <= xPadR + 20){
			if(yBall + 20 >= yPadR && yBall + 20 <= yPadR +100){
				if(dirBall == 0){
					dirBall=2;
					if(choice=='E'){
						pointsE+=10;
					}
					if(choice=='M'){
						pointsMR+=10;
					}
				}
			}
		}
		
		//Ball berührt linkes Paddle oben
		if(xBall >= xPadL && xBall <= xPadL + 20){
			if(yBall +20 >= yPadL && yBall  +20<= yPadL +100){
				if(dirBall == 1){
					dirBall = 3;
					if(choice=='E'){
						pointsE+=10;
					}
					if(choice=='M'){
						pointsML+=10;
					}
				}
			}
		}
		
		//Ball berührt rechtes Paddle unten
		if(xBall + 20 >= xPadR && xBall + 20 <= xPadR + 20){
			if(yBall >= yPadR && yBall <= yPadR +100){
				if(dirBall == 3){
					dirBall = 1;
					if(choice=='E'){
						pointsE+=10;
					}
					if(choice=='M'){
						pointsMR+=10;
					}
				}
			}
		}
		
		//Ball berührt linkes Paddle unten
		if(xBall >= xPadL && xBall <= xPadL + 20){
			if(yBall >= yPadL && yBall <= yPadL +100){
				if(dirBall == 2){
					dirBall = 0;
					if(choice=='E'){
						pointsE+=10;
					}
					if(choice=='M'){
						pointsML+=10;
					}
				}
			}
		}
		
	}

}
