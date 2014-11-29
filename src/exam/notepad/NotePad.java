package exam.notepad;

import javax.swing.JFrame;

public class NotePad extends JFrame {
	
	public NotePad(String title) {
		super(title);
		
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}