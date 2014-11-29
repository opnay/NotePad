package exam.notepad;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class NotePad extends JFrame {
	
	private JMenuBar menubar = new JMenuBar();
	private JMenu fileMenu, editMenu, viewMenu, helpMenu;
	
	public NotePad(String title) {
		super(title);
		
		fileMenu = new JMenu("파일");
		editMenu = new JMenu("편집");
		viewMenu = new JMenu("보기");
		helpMenu = new JMenu("도움말");
		
		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(viewMenu);
		menubar.add(helpMenu);
		
		this.setJMenuBar(menubar);
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}