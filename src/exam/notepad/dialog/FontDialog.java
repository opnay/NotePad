package exam.notepad.dialog;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import exam.notepad.NotePad;

@SuppressWarnings("serial")
public class FontDialog extends JDialog {
	
	private JTextArea txtArea;
	private Font curFont;
	private String curName;
	private int curStyle;
	private int curSize;
	
	private final String[] FONT_NAMES;
	private final String[] FONT_STYLE = { "Normal", "Bold", "Itelic", "Bold Itelic"};
	private final String[] FONT_SIZE = { "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20" };
	
	private JPanel pLabel, pList, pButton;
	
	// 폰트 이름, 스타일, 사이즈, 미리보기
	private JLabel lFontName, lFontStyle, lFontSize, lFontView1, lFontView2;
	private JTextField txtFontName, txtFontStyle, txtFontSize;
	
	// 폰트이름, 스타일, 사이즈 리스트
	private JList<String> listFontName, listFontStyle, listFontSize;
	private JScrollPane scrollFontName, scrollFontSize;
	
	// 버튼 확인, 취소
	private JButton btnOk, btnCancel;
	
	public FontDialog(JFrame parent) {
		super(parent);
		txtArea = ((NotePad) parent).getTxtArea();
		curFont = txtArea.getFont();
		
		// 시스템 폰트 리스트 받기
		FONT_NAMES = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		
		// 라벨 및 텍스트 필드
		lFontName = new JLabel("폰트", SwingConstants.CENTER);
		lFontStyle = new JLabel("스타일", SwingConstants.CENTER);
		lFontSize = new JLabel("크기", SwingConstants.CENTER);
		
		txtFontName = new JTextField();
		txtFontStyle = new JTextField();
		txtFontSize = new JTextField();

		pLabel = new JPanel(new GridLayout(2, 3, 10, 10));
		pLabel.add(lFontName);
		pLabel.add(lFontStyle);
		pLabel.add(lFontSize);
		pLabel.add(txtFontName);
		pLabel.add(txtFontStyle);
		pLabel.add(txtFontSize);
		
		// 리스트
		listFontName = new JList<String>(FONT_NAMES);
		listFontStyle = new JList<String>(FONT_STYLE);
		listFontSize = new JList<String>(FONT_SIZE);
		
		scrollFontName = new JScrollPane(listFontName);
		scrollFontSize = new JScrollPane(listFontSize);
		
		scrollFontName.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollFontSize.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		listFontName.setVisibleRowCount(10);
		listFontSize.setVisibleRowCount(10);
		
		listFontName.addListSelectionListener(new ListHandler());
		listFontStyle.addListSelectionListener(new ListHandler());
		listFontSize.addListSelectionListener(new ListHandler());
		
		listFontName.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFontStyle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listFontSize.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		pList = new JPanel(new GridLayout(0, 3, 10, 10));
		pList.add(listFontName);
		pList.add(listFontStyle);
		pList.add(listFontSize);
		
		// 버튼
		btnOk = new JButton("확인");
		btnCancel = new JButton("취소");
		
		JPanel pBottom = new JPanel(new GridLayout(3, 0, 5, 5));
		lFontView1 = new JLabel("ABCDEF123456");
		lFontView2 = new JLabel("가나다라마바");
		pBottom.add(lFontView1);
		pBottom.add(lFontView2);
		pButton = new JPanel(new GridLayout(0, 2, 20, 20));
		pButton.add(btnOk);
		pButton.add(btnCancel);
		pBottom.add(pButton);
		
		btnOk.addActionListener(new EventHandler());
		btnCancel.addActionListener(new EventHandler());
		
		/**
		 * 기본값 설정
		 */
		curName = curFont.getName();
		curStyle = curFont.getStyle();
		curSize = curFont.getSize();
		
		listFontName.setSelectedValue(curName, true);
		listFontStyle.setSelectedIndex(curStyle);
		listFontSize.setSelectedValue(Integer.toString(curSize), true);
		
		this.add(pLabel, BorderLayout.NORTH);
		this.add(pList, BorderLayout.CENTER);
		this.add(pBottom, BorderLayout.SOUTH);
		
		this.setSize(350, 400);
		// NotePad의 중앙에 위치
		this.setLocation(
				(int) parent.getLocation().getX() + (parent.getSize().width / 2) - (this.getSize().width / 2),
				(int) parent.getLocation().getY() + (parent.getSize().height / 2) - (this.getSize().height / 2));
		this.setVisible(true);
	}
	
	public void updateFont() {
		String fontName = listFontName.getSelectedValue();
		String fontSize = listFontSize.getSelectedValue();
		int fontStyle = listFontStyle.getSelectedIndex();
		
		if(fontSize == null)
			fontSize = "10";
		
		curFont = new Font(fontName, fontStyle, Integer.parseInt(fontSize));
		
		lFontView1.setFont(curFont);
		lFontView2.setFont(curFont);
	}
	
	public void applyFont() {
		txtArea.setFont(curFont);
	}
	
	class ListHandler implements ListSelectionListener {
		
		int curIndex = 0;
		
		@SuppressWarnings("unchecked")
		@Override
		public void valueChanged(ListSelectionEvent e) {
			JList<String> o = (JList<String>) e.getSource();
			
			if(o.equals(listFontName))
				txtFontName.setText(o.getSelectedValue());
			
			if(o.equals(listFontSize))
				txtFontSize.setText(o.getSelectedValue());
			
			if(o.equals(listFontStyle))
				txtFontStyle.setText(o.getSelectedValue());
			
			updateFont();
		}
		
	}
	
	class EventHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();

			if(o.equals(btnOk)) {
				applyFont();
				dispose();
			}
			
			if(o.equals(btnCancel))
				dispose();
		}
		
	}
	
}
