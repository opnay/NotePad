package exam.notepad;

import java.awt.BorderLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;

import exam.notepad.dialog.FindDialog;
import exam.notepad.dialog.FontDialog;

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
	
	// Clipboard
	private Clipboard mClipboard = getToolkit().getSystemClipboard();
	
	// Undomanager
	private Document editorDocument;
	private UndoManager mUndoManager = new UndoManager();
	
	public NotePad(String title) {
		super(title);
		
		/**
		 * Menu
		 */
		fileMenu = new JMenu("파일(F)");
		editMenu = new JMenu("편집(E)");
		viewMenu = new JMenu("보기(V)");
		helpMenu = new JMenu("도움말(H)");
		
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
		 * Short cut key
		 */
		// 메뉴 단축키 Alt +
		fileMenu.setMnemonic('F');
		editMenu.setMnemonic('E');
		viewMenu.setMnemonic('V');
		helpMenu.setMnemonic('H');

		// 단축키 Ctrl +
		mNew.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
		mOpen.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
		mSave.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
		mSaveAs.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.SHIFT_MASK + InputEvent.CTRL_MASK));

		mUndo.setAccelerator(KeyStroke.getKeyStroke('Z', InputEvent.CTRL_MASK));
		mCut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
		mCopy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		mPaste.setAccelerator(KeyStroke.getKeyStroke('P', InputEvent.CTRL_MASK));
		mFind.setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.CTRL_MASK));
		mSelectAll.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_MASK));
		
		mHelper.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_MASK));
		
		/**
		 * TextArea
		 */
		txtArea = new JTextArea();
		jsp = new JScrollPane(txtArea);
		status = new JLabel("NotePad");
		
		this.add(jsp, BorderLayout.CENTER);
		this.add(status, BorderLayout.SOUTH);
		// 상태표시줄 표시. (기본값)
		mStatus.setState(true);

		// UndoManager
		editorDocument = txtArea.getDocument();
		editorDocument.addUndoableEditListener(new UndoableEditListener() {

			@Override
			public void undoableEditHappened(UndoableEditEvent e) {
				mUndoManager.addEdit(e.getEdit());
			}
			
		});
		
		this.setSize(500,500);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void newDocument() {
		if(!txtArea.getText().isEmpty()) {
			int result = JOptionPane
					.showConfirmDialog(this, "파일을 저장하시겠습니까?", "저장", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION)
				saveDocument(false);
			else if(result == JOptionPane.CANCEL_OPTION)
				return;
		}
		
		txtArea.setText("");
		curFile = null;
	}
	
	public void openDocument() {
		if(!txtArea.getText().isEmpty()) {
			int result = JOptionPane
					.showConfirmDialog(this, "파일을 저장하시겠습니까?", "저장", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION)
				saveDocument(false);
			else if(result == JOptionPane.CANCEL_OPTION)
				return;
		}
		
		JFileChooser openChooser = new JFileChooser();
		int openResult = openChooser.showOpenDialog(this);
		
		if(openResult == JFileChooser.APPROVE_OPTION) {
			openFile(openChooser.getSelectedFile());
		}
	}
	
	public void openFile(File file) {
		try {
			Scanner sc = new Scanner(file);
			txtArea.setText("");
			while(sc.hasNext()) {
				txtArea.append(sc.nextLine());
			}
			
			sc.close();
			curFile = file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void saveDocument(boolean isSaved) {
		if(!isSaved && curFile != null) {
			// 저장, 덮어쓰기
			saveFile(curFile);
		} else {
			// 다른이름으로 저장
			saveAsDocument();
		}
	}
	
	public void saveAsDocument() {
		JFileChooser chooser = new JFileChooser();
		int result = chooser.showSaveDialog(this);
		
		if(result == JFileChooser.APPROVE_OPTION)
			saveFile(chooser.getSelectedFile());
		
	}
	
	public void saveFile(File file) {
		try {
			PrintWriter mPrintWriter = new PrintWriter(file);
			mPrintWriter.write(txtArea.getText());
			mPrintWriter.flush();
			
			mPrintWriter.close();
			curFile = file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void exit() {
		if(!txtArea.getText().isEmpty()) {
			int result = JOptionPane
					.showConfirmDialog(this, "파일을 저장하시겠습니까?", "저장", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if(result == JOptionPane.YES_OPTION)
				saveDocument(false);
			else if(result == JOptionPane.CANCEL_OPTION)
				return;
		}
		
		System.exit(0);
	}
	
	public void copy() {
		String data =txtArea.getSelectedText();
		
		// 선택된 텍스트가 없을때
		if(data == null || data.length() <= 0)
			return;
		
		StringSelection selection = new StringSelection(txtArea.getSelectedText());
		mClipboard.setContents(selection,selection);
	}
	
	public JTextArea getTxtArea() {
		return txtArea;
	}
	
	class EventHandler implements	ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object o = e.getSource();
			
			// 새문서
			if(o.equals(mNew) || o.equals(tNew))
				newDocument();

			// 열기
			if(o.equals(mOpen) || o.equals(tOpen))
				openDocument();
			
			// 저장
			if(o.equals(mSave) || o.equals(tSave))
				saveDocument(false);
			
			// 다른이름으로 저장
			if(o.equals(mSaveAs))
				saveDocument(true);
			
			// 종료
			if(o.equals(mExit) || o.equals(tExit))
				exit();
			
			// 실행취소
			if(o.equals(mUndo)) {
				try {
					if(mUndoManager.canUndo())
						mUndoManager.undo();
				} catch (CannotUndoException exception) {
					System.out.println(exception.getMessage());
				}
			}
			
			// 잘라내기
			if(o.equals(mCut) || o.equals(tCut)) {
				copy();
				txtArea.replaceSelection("");
			}
			
			// 복사하기
			if(o.equals(mCopy) || o.equals(mCopy))
				copy();
			
			// 붙여넣기
			if(o.equals(mPaste) || o.equals(mPaste)) {
				Transferable mClipData = mClipboard.getContents(NotePad.this);
				try {
					String clipData = (String) mClipData.getTransferData(DataFlavor.stringFlavor);
					txtArea.replaceSelection(clipData);
				} catch (UnsupportedFlavorException | IOException e1) {
					e1.printStackTrace();
				}
			}
			
			// 찾기
			if(o.equals(mFind))
				new FindDialog(NotePad.this);
			
			// 전체선택
			if(o.equals(mSelectAll)) {
				txtArea.setSelectionStart(0);
				txtArea.setSelectionEnd(txtArea.getText().length());
			}
			
			// 자동 줄바꿈
			if(o.equals(mLine))
				txtArea.setLineWrap(mLine.getState());
			
			// 상태 표시줄
			if(o.equals(mStatus))
				status.setVisible(mStatus.getState());
			
			// 폰트
			if(o.equals(mFont))
				new FontDialog(NotePad.this);
			
			// 도움말
			if(o.equals(mHelper));
			
			// 자세히
			if(o.equals(mAbout));
			
		}
		
	}
	
}