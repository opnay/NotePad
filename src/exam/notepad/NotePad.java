package exam.notepad;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class NotePad extends JFrame {
	
	private JMenuBar menubar = new JMenuBar();
	private JMenu fileMenu, editMenu, viewMenu, helpMenu;

	// fileMenu
	private JMenuItem mNew, mOpen, mSave, mSaveAs, mPage, mPrint, mExit;
	
	// editMenu
	private JMenuItem mUndo, mCut, mCopy, mPaste, mFind, mSelectAll;
	
	// viewMenu
	private JCheckBoxMenuItem mLine, mStatus;
	private JMenuItem mFont;
	
	// helpMenu
	private JMenuItem mHelper, mAbout;
	
	// toolbar
	private JToolBar toolbar;
	private JButton tNew, tOpen, tSave, tExit;
	private JButton tCut, tCopy, tPaste;
	
	// textArea
	private JTextArea txtArea;
	private JScrollPane jsp;
	private JLabel status;
	
	private File curFile;
	
	public NotePad(String title) {
		super(title);
		
		/**
		 * Menu
		 */
		fileMenu = new JMenu("파일");
		editMenu = new JMenu("편집");
		viewMenu = new JMenu("보기");
		helpMenu = new JMenu("도움말");
		
		menubar.add(fileMenu);
		menubar.add(editMenu);
		menubar.add(viewMenu);
		menubar.add(helpMenu);
		
		mNew = new JMenuItem("새 문서");
		mOpen = new JMenuItem("열기");
		mSave = new JMenuItem("저장");
		mSaveAs = new JMenuItem("다른이름으로 저장");
		mPage = new JMenuItem("미리보기");
		mPrint = new JMenuItem("인쇄");
		mExit = new JMenuItem("닫기");
		
		fileMenu.add(mNew);
		fileMenu.add(mOpen);
		fileMenu.add(mSave);
		fileMenu.add(mSaveAs);
		fileMenu.addSeparator();
		fileMenu.add(mPage);
		fileMenu.add(mPrint);
		fileMenu.addSeparator();
		fileMenu.add(mExit);
		
		mUndo = new JMenuItem("실행 취소");
		mCut = new JMenuItem("잘라내기");
		mCopy = new JMenuItem("복사");
		mPaste = new JMenuItem("붙여넣기");
		mFind = new JMenuItem("찾기");
		mSelectAll = new JMenuItem("전체 선택");
		
		editMenu.add(mUndo);
		editMenu.add(mCut);
		editMenu.add(mCopy);
		editMenu.add(mPaste);
		editMenu.add(mFind);
		editMenu.addSeparator();
		editMenu.add(mSelectAll);
		
		mLine = new JCheckBoxMenuItem("자동 줄넘김");
		mStatus = new JCheckBoxMenuItem("상태 표시줄");
		mFont = new JMenuItem("폰트 설정");
		
		viewMenu.add(mLine);
		viewMenu.add(mStatus);
		viewMenu.add(mFont);
		
		mHelper = new JMenuItem("도움말");
		mAbout = new JMenuItem("자세히");
		
		helpMenu.add(mHelper);
		helpMenu.add(mAbout);
		
		this.setJMenuBar(menubar);
		
		/**
		 * ToolBar
		 */
		toolbar = new JToolBar();
		
		tNew = new JButton(new ImageIcon("images/new.gif"));
		tOpen = new JButton(new ImageIcon("images/open.gif"));
		tSave = new JButton(new ImageIcon("images/save.gif"));
		tExit = new JButton(new ImageIcon("images/exit.gif"));
		tCut = new JButton(new ImageIcon("images/cut.gif"));
		tCopy = new JButton(new ImageIcon("images/copy.gif"));
		tPaste = new JButton(new ImageIcon("images/paste.gif"));
		
		toolbar.add(tNew);
		toolbar.add(tOpen);
		toolbar.add(tSave);
		toolbar.addSeparator();
		toolbar.add(tCut);
		toolbar.add(tCopy);
		toolbar.add(tPaste);
		toolbar.addSeparator();
		toolbar.add(tExit);
		
		toolbar.setFloatable(false);
		
		this.add(toolbar, BorderLayout.NORTH);
		
		/**
		 * AddListener
		 */
		
		mNew.addActionListener(new EventHandler());
		mOpen.addActionListener(new EventHandler());
		mSave.addActionListener(new EventHandler());
		mSaveAs.addActionListener(new EventHandler());
		mExit.addActionListener(new EventHandler());

		mUndo.addActionListener(new EventHandler());
		mCut.addActionListener(new EventHandler());
		mCopy.addActionListener(new EventHandler());
		mPaste.addActionListener(new EventHandler());
		
		mFind.addActionListener(new EventHandler());
		mSelectAll.addActionListener(new EventHandler());

		mLine.addActionListener(new EventHandler());
		mStatus.addActionListener(new EventHandler());

		mFont.addActionListener(new EventHandler());
		mHelper.addActionListener(new EventHandler());
		mAbout.addActionListener(new EventHandler());

		tNew.addActionListener(new EventHandler());
		tOpen.addActionListener(new EventHandler());
		tSave.addActionListener(new EventHandler());
		tExit.addActionListener(new EventHandler());

		tCut.addActionListener(new EventHandler());
		tCopy.addActionListener(new EventHandler());
		tPaste.addActionListener(new EventHandler());
		
		/**
		 * TextArea
		 */
		txtArea = new JTextArea();
		jsp = new JScrollPane(txtArea);
		status = new JLabel("NotePad");
		
		this.add(jsp, BorderLayout.CENTER);
		this.add(status, BorderLayout.SOUTH);

		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void newDocument() {
		if(curFile == null) {
			// 저장
		}
		
		txtArea.setText("");
		curFile = null;
	}
	
	class EventHandler implements	ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			// 새문서
			if(o.equals(mNew) || o.equals(tNew))
				newDocument();

			// 열기
			if(o.equals(mOpen) || o.equals(tOpen));
			
			// 저장
			if(o.equals(mSave) || o.equals(tSave));
			
			// 다른이름으로 저장
			if(o.equals(mSaveAs));
			
			// 종료
			if(o.equals(mExit) || o.equals(tExit));
			
			// 실행취소
			if(o.equals(mUndo));
			
			// 잘라내기
			if(o.equals(mCut) || o.equals(tCut));
			
			// 복사하기
			if(o.equals(mCopy) || o.equals(mCopy));
			
			// 붙여넣기
			if(o.equals(mPaste) || o.equals(mPaste));
			
			// 찾기
			if(o.equals(mFind));
			
			// 전체선택
			if(o.equals(mSelectAll));
			
			// 자동 줄바꿈
			if(o.equals(mLine));
			
			// 상태 표시줄
			if(o.equals(mStatus));
			
			// 폰트
			if(o.equals(mFont));
			
			// 도움말
			if(o.equals(mHelper));
			
			// 자세히
			if(o.equals(mAbout));
			
		}
		
	}
	
}