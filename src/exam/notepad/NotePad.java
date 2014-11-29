package exam.notepad;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	private JMenuItem mHelper;
	
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
		editMenu.add(mSelectAll);
		
		mLine = new JCheckBoxMenuItem("자동 줄넘김");
		mStatus = new JCheckBoxMenuItem("상태 표시줄");
		mFont = new JMenuItem("폰트 설정");
		
		viewMenu.add(mLine);
		viewMenu.add(mStatus);
		viewMenu.add(mFont);
		
		mHelper = new JMenuItem("도움말");
		
		helpMenu.add(mHelper);
		
		this.setJMenuBar(menubar);
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}