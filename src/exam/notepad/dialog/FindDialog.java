package exam.notepad.dialog;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import exam.notepad.NotePad;

@SuppressWarnings("serial")
public class FindDialog extends JDialog {
	
	private JTextArea txtArea;
	
	// 찾기, 바꾸기
	private JPanel pFind;
	private JLabel lFind, lReplace;
	private JTextField txtFind, txtReplace;
	
	// 찾기, 바꾸기, 취소 버튼
	private JPanel pButton;
	private JButton btnFind, btnReplace, btnCancel;
	
	// 옵션
	private JPanel pOption;
	private ButtonGroup gOption;
	private JRadioButton rbUp, rbDown;
	private JCheckBox cbUpper;
	
	private TitledBorder titledBorder;
	
	public FindDialog(JFrame parent) {
		super(parent);

		this.txtArea = ((NotePad) parent).getTxtArea();
        
		/**
		 * 찾기
		 */
		lFind = new JLabel("찾기", JLabel.CENTER);
		lReplace = new JLabel("바꾸기", JLabel.CENTER);
		
		txtFind = new JTextField();
		txtReplace = new JTextField();

		pFind = new JPanel(new GridLayout(2, 2));
		
		pFind.add(lFind);
		pFind.add(txtFind);
		pFind.add(lReplace);
		pFind.add(txtReplace);
		
		/**
		 * 옵션
		 */
		rbUp = new JRadioButton("위로 찾기");
		rbDown = new JRadioButton("아래로 찾기");
		
		cbUpper = new JCheckBox("대소문자 구분");
		
		//아래로 찾기 기본값
		rbDown.setSelected(true);
		
		gOption = new ButtonGroup();
		gOption.add(rbUp);
		gOption.add(rbDown);
		
		pOption = new JPanel(new GridLayout(2, 2));
		pOption.add(rbUp);
		pOption.add(rbDown);
		pOption.add(cbUpper);
		
		titledBorder = new TitledBorder("옵션");
		pOption.setBorder(titledBorder);
		
		/**
		 * 버튼
		 */
		btnFind = new JButton("찾기");
		btnReplace = new JButton("바꾸기");
		btnCancel = new JButton("취소");
		
		btnFind.addActionListener(new EventHandler());
		btnReplace.addActionListener(new EventHandler());
		btnCancel.addActionListener(new EventHandler());
		
		pButton = new JPanel(new GridLayout(0, 3, 10, 10));
		pButton.add(btnFind);
		pButton.add(btnReplace);
		pButton.add(btnCancel);		
		
		this.add(pFind, BorderLayout.NORTH);
		this.add(pOption, BorderLayout.CENTER);
		this.add(pButton, BorderLayout.SOUTH);
		
		this.setSize(250, 250);
		// NotePad의 중앙에 위치
		this.setLocation(
				(int) parent.getLocation().getX() + (parent.getSize().width / 2) - (this.getSize().width / 2),
				(int) parent.getLocation().getY() + (parent.getSize().height / 2) - (this.getSize().height / 2));
		this.setResizable(false);
		this.setVisible(true);
	}
	
	public void findOperation() {
		// 찾는 단어
		String strFind = txtFind.getText();

		// 현재 커서 위치
		int curCursorStart = txtArea.getSelectionStart();
		int curCursorEnd = txtArea.getSelectionEnd();
		
		if(rbDown.isSelected()) {
			// 찾는 범위
			String curText = txtArea.getText().substring(curCursorEnd, txtArea.getText().length());
			
			int index;
			// 대소문자 구분 옵션
			if(cbUpper.isSelected())
				index = curText.indexOf(strFind);
			else
				index = curText.toLowerCase().indexOf(strFind.toLowerCase());
			
			int strIndex = index + txtArea.getSelectionEnd();
			if(index >= 0) {
				txtArea.setSelectionStart(strIndex);
				txtArea.setSelectionEnd(strFind.length() + strIndex);
			} else {
				 JOptionPane.showMessageDialog(this, "더이상 찾는 단어가 없습니다.");
			}
		} else {
			String curText;
			
			int index = 0;
			int strIndex = 0;
			while(true) {
				curText = txtArea.getText().substring(index, curCursorStart);
				// 이전에 찾은 index값.
				strIndex = index;
				
				// 대소문자 구분 옵션
				if(cbUpper.isSelected())
					index = curText.indexOf(strFind);
				else
					index = curText.toLowerCase().indexOf(strFind.toLowerCase());
				
				// 더이상 못찾을 경우
				if(index < 0)
					break;
				
				index += strIndex + strFind.length();
			}
			
			if(strIndex <= 0)
				 JOptionPane.showMessageDialog(this, "더이상 찾는 단어가 없습니다.");
			else {
				txtArea.setSelectionStart(strIndex - strFind.length());
				txtArea.setSelectionEnd(strIndex);
			}
			
		}
	}
	
	public void replaceOperation() {
		findOperation();
		 
		if(txtFind.getText().equals(txtArea.getSelectedText())) {
			int curCursorStart = txtArea.getSelectionStart();
			txtArea.replaceSelection(txtReplace.getText());
			txtArea.setSelectionStart(curCursorStart);
			txtArea.setSelectionEnd(curCursorStart + txtReplace.getText().length());
		}
	}
	
	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			if(o.equals(btnFind)) {
				if(!txtFind.getText().isEmpty())
					findOperation();
			}
			
			if(o.equals(btnReplace)) {
				if(!txtFind.getText().isEmpty())
					replaceOperation();
			}
			
			if(o.equals(btnCancel)) {
				dispose();
			}
			
		}
		
	}
}
