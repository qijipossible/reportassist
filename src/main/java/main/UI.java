package main;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UI extends JFrame implements ActionListener {

	JTextField mainKeywordField = new JTextField(30);
	JButton mainSearchButton = new JButton("搜索");
	JLabel lClass1 = new JLabel("官方文件：");
	JLabel lClass2 = new JLabel("非官方文件：");
	JCheckBox cb1 = new JCheckBox("科技部",true);
	JCheckBox cb2 = new JCheckBox("工信部",true);
	JCheckBox cb3 = new JCheckBox("发改委",true);
	JCheckBox cb4 = new JCheckBox("论文",true);
	JCheckBox cb5 = new JCheckBox("专利",true);
	JCheckBox cb6 = new JCheckBox("新闻",true);
	
	
	public UI() throws HeadlessException {
		super();
		this.setExtendedState(MAXIMIZED_BOTH);//最大化窗口
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		UI ui = new UI();
		ui.setVisible(true);

	}

}
