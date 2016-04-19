package main;

import javax.swing.AbstractCellEditor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;

import database.SQLop;
import NLP.NLP;

import javax.swing.Box;
import javax.swing.JTabbedPane;

public class ResultPanel extends JPanel {
	private JTable table;
	private StyledDocument styleModel;
	private JTextPane textPane;
	private SQLop sqlop;
	private JPanel panel;

	private List<Map<String, String>> result;
	private int resultSize;

	String keyword = null;
	SQLop database = new SQLop();

	public ResultPanel() {
		
		iniStyleModel();
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_4 = new JPanel();
		add(panel_4, BorderLayout.CENTER);
		panel_4.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_4.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JButton button_back = new JButton("返回搜索界面");
		panel_2.add(button_back, BorderLayout.EAST);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panel_4.add(tabbedPane);
		
		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("搜索结果", null, panel_3, null);
		tabbedPane.addTab("全体数据", null, new JPanel(), null);
		panel_3.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_up = new JPanel();
		panel_up.setVisible(false);
		panel_3.add(panel_up, BorderLayout.NORTH);
		panel_up.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel label = new JLabel("筛选：");
		panel_up.add(label);
		
		JCheckBox checkBox = new JCheckBox("科技部");
		panel_up.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("工信部");
		panel_up.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("发改委");
		panel_up.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("论文");
		panel_up.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("专利");
		panel_up.add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("新闻");
		panel_up.add(checkBox_5);
		
		JButton button = new JButton("筛选");
		panel_up.add(button);
		//button_back.addMouseListener(new MouseAdapter() {
		//	@Override
		//	public void mouseClicked(MouseEvent arg0){
		//		UIswitch_back();
		//	}
		//});
		
		JPanel panel_main = new JPanel();
		panel_3.add(panel_main, BorderLayout.CENTER);
		panel_main.setLayout(new BorderLayout(0, 0));
		
		panel = new JPanel();
		panel_main.add(panel);
		
		
	}
	
	public void getResult(String keyword){
		this.keyword = keyword;
		sqlop = new SQLop();
		sqlop.initialize();
		result = sqlop.search(keyword);
		sqlop.close();
		resultSize = result.size();

		table = new JTable(new DefaultTableModel(resultSize,1){
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		});
		table.setRowHeight(80);
		table.setDefaultRenderer(Object.class, new TableFiller());
		table.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int row = table.rowAtPoint(evt.getPoint());
		        int col = table.columnAtPoint(evt.getPoint());
		        if (row >= 0 && col >= 0) {
		        	String article = result.get(row).get("content");
		        	new DetailFrame(NLP.stringsummary(article), article).setVisible(true);;
		        }
		    }
		});
		
		panel.setLayout(new BorderLayout(0, 0));
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane, BorderLayout.CENTER);
	}
	
	class TableFiller implements TableCellRenderer{

		@Override
		public Component getTableCellRendererComponent(JTable arg0,
				Object arg1, boolean arg2, boolean arg3, int row, int column) {
			textPane = new JTextPane();
			StyledDocument sd = getNewStyledDocument();
			insertDoc(sd, result.get(row).get("type"), "STYLE_type");
			insertDoc(sd, "  " + result.get(row).get("title").toString() + "\n", "STYLE_title");
			if(result.get(row).get("time") != null)
				insertDoc(sd, result.get(row).get("time").toString() + "\t", "STYLE_author");
			insertDoc(sd, result.get(row).get("author").toString() + "\n", "STYLE_author");
			insertDoc(sd, result.get(row).get("url").toString() + "\n", "STYLE_url");
			textPane.setStyledDocument(sd);
			return textPane;
		}
		
	}
	
	public StyledDocument getNewStyledDocument() {
		
		return new DefaultStyledDocument();
	}
	
	

	public StyledDocument insertDoc(StyledDocument styledDoc, String content,
			String currentStyle) {
		if(content == null) content = "";
		try {
			int length = styledDoc.getLength();
			styledDoc.insertString(length, content,
					styleModel.getStyle(currentStyle));
		} catch (BadLocationException e) {
			System.err.println("BadLocationException: " + e);
		}
		return styledDoc;
	}
	
	private void iniStyleModel(){
		styleModel = new DefaultStyledDocument();
		createStyle("STYLE_url", styleModel, 16, false, false, true, Color.GRAY, Color.WHITE, "Times New Roman");
		createStyle("STYLE_title", styleModel, 20, true, false, false, Color.BLACK, Color.WHITE, "黑体");
		createStyle("STYLE_abstract", styleModel, 16, false, false, false, Color.BLACK, Color.WHITE, "宋体");
		createStyle("STYLE_author", styleModel, 16, false, false, false, Color.BLACK, Color.WHITE, "楷体");
		createStyle("STYLE_type", styleModel, 20, false, false, false, Color.WHITE, Color.BLACK, "黑体");	
	}

	public void createStyle(String style, StyledDocument doc, int size,
			boolean bold, boolean italic, boolean underline, Color color, Color bcolor, String fontName) {
		Style sys = StyleContext.getDefaultStyleContext().getStyle(
				StyleContext.DEFAULT_STYLE);
		try {
			doc.removeStyle(style);
		} catch (Exception e) {
		} // 先删除这种Style,假使他存在

		Style s = doc.addStyle(style, sys); // 加入
		StyleConstants.setFontSize(s, size); // 大小
		StyleConstants.setBold(s, bold); // 粗体
		StyleConstants.setItalic(s, italic); // 斜体
		StyleConstants.setUnderline(s, underline); // 下划线
		StyleConstants.setForeground(s, color); // 颜色
		StyleConstants.setFontFamily(s, fontName); // 字体
		StyleConstants.setBackground(s, bcolor);//背景颜色
	}
	
}