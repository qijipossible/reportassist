package main;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

public class teest extends JFrame {
	private static final long serialVersionUID = 1L;

	// main function
	public static void main(String args[]) {
		teest test = new teest();
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
	}

	public teest() {
		// Initialization
		StyledDocument styledDoc = new DefaultStyledDocument();
		JTextPane textPane = new JTextPane(styledDoc);
		JScrollPane scrollPane = new JScrollPane(textPane);

		// Get Available Font Family Name
		String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getAvailableFontFamilyNames();

		// Content
		createStyle("Style01", styledDoc, 15, 0, 1, 0, Color.RED, "宋体");
		createStyle("Style02", styledDoc, 35, 1, 1, 1, Color.GREEN, "华文琥珀");
		createStyle("Style03", styledDoc, 25, 1, 0, 0, Color.BLUE, "隶书");
		createStyle("Style04", styledDoc, 18, 1, 0, 0, new Color(0, 128, 128),
				fontNames[0]);
		createStyle("Style05", styledDoc, 20, 0, 1, 0, new Color(128, 128, 0),
				fontNames[7]);
		createStyle("Style06", styledDoc, 22, 1, 0, 1, new Color(128, 0, 128),
				fontNames[16]);
		createStyle("Style07", styledDoc, 18, 1, 1, 0, Color.RED, "华文彩云");

		insertDoc(styledDoc, "Size=18,Italic,red,font=宋体/n", "Style01");
		insertDoc(styledDoc, "其实Java比C++简单多了/n", "Style02");
		insertDoc(styledDoc, "Java中的MVC机制很好/n", "Style03");
		insertDoc(styledDoc, "其实Java比C++简单多了/n", "Style07");

		insertDoc(styledDoc, "Java is very good/n", "Style04");
		insertDoc(styledDoc, "Java is very good/n", "Style05");
		insertDoc(styledDoc, "Java is very good/n", "Style06");

		// Layout
		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		this.setSize(new Dimension(500, 400));
		this.setTitle("JTextPane Test!");
		this.setLocation(new Point(200, 200));
	}

	public void insertDoc(StyledDocument styledDoc, String content,
			String currentStyle) {
		try {
			styledDoc.insertString(styledDoc.getLength(), content,
					styledDoc.getStyle(currentStyle));
		} catch (BadLocationException e) {
			System.err.println("BadLocationException: " + e);
		}
	}

	public void createStyle(String style, StyledDocument doc, int size,
			int bold, int italic, int underline, Color color, String fontName) {
		Style sys = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);
		try {
			doc.removeStyle(style);
		} catch (Exception e) {
		} // 先删除这种Style,假使他存在

		Style s = doc.addStyle(style, sys); // 加入
		StyleConstants.setFontSize(s, size); // 大小
		StyleConstants.setBold(s, (bold == 1) ? true : false); // 粗体
		StyleConstants.setItalic(s, (italic == 1) ? true : false); // 斜体
		StyleConstants.setUnderline(s, (underline == 1) ? true : false); // 下划线
		StyleConstants.setForeground(s, color); // 颜色
		StyleConstants.setFontFamily(s, fontName); // 字体
	}
}
