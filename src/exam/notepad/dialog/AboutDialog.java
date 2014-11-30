package exam.notepad.dialog;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class AboutDialog extends JDialog {
	
	private JPanel mPanel;
	
	private JLabel name;
	
	private JButton btnOk;
	
	public AboutDialog(JFrame parent) {
		super(parent);
		
		mPanel = new JPanel();
		
		name = new JLabel("Made By OPNay(김인섭)", SwingConstants.CENTER);
		
		mPanel.add(name);
		
		btnOk = new JButton("확인");
		btnOk.addActionListener(new EventHandler());

		
		this.add(mPanel, BorderLayout.CENTER);
		this.add(btnOk, BorderLayout.SOUTH);
		this.setModalityType (ModalityType.APPLICATION_MODAL);
		this.setSize(300, 150);
		this.setResizable(false);
		// NotePad의 중앙에 위치
		this.setLocation(
				(int) parent.getLocation().getX() + (parent.getSize().width / 2) - (this.getSize().width / 2),
				(int) parent.getLocation().getY() + (parent.getSize().height / 2) - (this.getSize().height / 2));
		this.setVisible(true);
	}
	
	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
}
