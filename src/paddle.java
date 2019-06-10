package de.luca.paddlepong;

import javax.swing.JFrame;

public class paddle extends JFrame{
	
	public paddle(){
		add(new game());
		
		setTitle("Paddle");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		//setLocationRelativeTo(null);
		setLocation(1024/2, 512/2);
		setResizable(false);
		
		pack();
		
	}
	
	public static void main(String[] args) {
		paddle frm = new paddle();
		frm.setVisible(true);
		
		
	}

}
