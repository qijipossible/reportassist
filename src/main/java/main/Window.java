package main;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window.Type;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.Component;

import javax.swing.Box;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.GridBagConstraints;

import javax.swing.JCheckBox;

import NLP.NLP;
import crawl.Crawler;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Map;

import javax.swing.JProgressBar;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledDocument;

public class Window{

	private JFrame frame;
	private JTextField textField;
	private JButton button;
	private JPanel panel = new JPanel();
	private Component verticalGlue;
	private Component verticalGlue_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JCheckBox ck1;
	private JCheckBox ck2;
	private JCheckBox ck3;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JCheckBox ck4;
	private JCheckBox ck5;
	private JCheckBox ck6;
	private JPanel panel_5;
	private Component verticalGlue_2;
	private Component verticalGlue_3;
	private Component verticalGlue_4;
	private Component verticalGlue_5;
	private JProgressBar progressBar;
	
	private ResultFrame resultFrame;
	private Crawler crawler;
	
	private boolean isRunning = false;
	public String keyword = null;
	private JButton button_1;
	private JPanel panel_7;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Window(){
		initialize1();
		actionListner();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize1() {

		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = dimension.height;
		int glueHeight = screenHeight/2-100;
		
		frame = new JFrame();
		frame.setTitle("智能信息管理系统");
		frame.setBackground(Color.WHITE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);//最大化窗口
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		panel_7 = new JPanel();
		frame.getContentPane().add(panel_7);
		panel_7.setLayout(new BoxLayout(panel_7, BoxLayout.Y_AXIS));
		verticalStrut = Box.createVerticalStrut(glueHeight);
		panel_7.add(verticalStrut);
		
		verticalGlue = Box.createVerticalGlue();
		panel_7.add(verticalGlue);
		panel_7.add(panel);
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));
		
		verticalGlue_2 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_2);
		
		panel_5 = new JPanel();
		panel_1.add(panel_5);
		
		textField = new JTextField();
		textField.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel_5.add(textField);
		textField.setToolTipText("请键入您想要搜索的关键词");
		textField.setColumns(30);
		
		progressBar = new JProgressBar();
		panel_5.add(progressBar);
		progressBar.setStringPainted(true);
		progressBar.setString("正在搜索...");
		progressBar.setFont(new Font("宋体", Font.PLAIN, 15));
		progressBar.setVisible(false);
		progressBar.setIndeterminate(true);
		
		button = new JButton("搜索");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel_5.add(button);
		
		button_1 = new JButton("显示结果");
		button_1.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		panel_5.add(button_1);
		
		verticalGlue_3 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_3);
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		verticalGlue_4 = Box.createVerticalGlue();
		panel_2.add(verticalGlue_4);
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		ck1 = new JCheckBox("科技部",true);
		panel_3.add(ck1);
		
		ck2 = new JCheckBox("工信部",true);
		panel_3.add(ck2);
		
		ck3 = new JCheckBox("发改委",true);
		panel_3.add(ck3);
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		ck4 = new JCheckBox("论文",true);
		panel_4.add(ck4);
		
		ck5 = new JCheckBox("专利",true);
		panel_4.add(ck5);
		
		ck6 = new JCheckBox("新闻",true);
		panel_4.add(ck6);
		
		verticalGlue_5 = Box.createVerticalGlue();
		panel_2.add(verticalGlue_5);
		verticalGlue_1 = Box.createVerticalGlue();
		panel_7.add(verticalGlue_1);
		verticalStrut_1 = Box.createVerticalStrut(glueHeight);
		panel_7.add(verticalStrut_1);
	}
	
	private void actionListner(){

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				keyword = textField.getText();
				boolean[] options = new boolean[6];
				options[0] = ck1.isSelected();
				options[1] = ck2.isSelected();
				options[2] = ck3.isSelected();
				options[3] = ck4.isSelected();
				options[4] = ck5.isSelected();
				options[5] = ck6.isSelected();
				if(isRunning){
					crawler.stop();
				}else{
					crawler = new Crawler(keyword, options);
				}
				stateChange();
			}
		});
		
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				resultFrame = new ResultFrame(keyword);
				resultFrame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
				frame.setVisible(false);
			}
		});
	}
	
	

	private void stateChange(){
		

		isRunning = !isRunning;
		if(isRunning){
			button.setText("停止");
			textField.setVisible(false);
			progressBar.setString("正在搜索: " + keyword);
			progressBar.setVisible(true);
			
		}else{
			button.setText("搜索");
			progressBar.setVisible(false);
			textField.setVisible(true);
			
		}
	}
	
	

}
