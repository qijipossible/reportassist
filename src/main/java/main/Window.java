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

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Window {

	private JFrame frame;
	private JTextField textField;
	private JButton button;
	private Component verticalGlue;
	private Component verticalGlue_1;
	private JPanel panel_1;
	private JPanel panel_2;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	private JCheckBox chckbxNewCheckBox_2;
	private Component verticalStrut;
	private Component verticalStrut_1;
	private JPanel panel_3;
	private JPanel panel_4;
	private JCheckBox chckbxNewCheckBox_3;
	private JCheckBox chckbxNewCheckBox_4;
	private JCheckBox chckbxNewCheckBox_5;
	private JPanel panel_5;
	private Component verticalGlue_2;
	private Component verticalGlue_3;
	private Component verticalGlue_4;
	private Component verticalGlue_5;

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
	public Window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
		int screenHeight = dimension.height;
		int glueHeight = screenHeight/2-100;
		
		frame = new JFrame();
		frame.setTitle("智能信息管理系统");
		frame.setBackground(Color.WHITE);
		frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);//最大化窗口
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
		
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		verticalGlue = Box.createVerticalGlue();
		verticalGlue_1 = Box.createVerticalGlue();
		verticalStrut = Box.createVerticalStrut(glueHeight);
		verticalStrut_1 = Box.createVerticalStrut(glueHeight);
		
		frame.getContentPane().add(verticalStrut);
		frame.getContentPane().add(verticalGlue);
		frame.getContentPane().add(panel);
		frame.getContentPane().add(verticalGlue_1);
		frame.getContentPane().add(verticalStrut_1);
		
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
		
		button = new JButton("搜索");
		button.setFont(new Font("微软雅黑", Font.PLAIN, 15));
		button.addActionListener((ActionListener) this);
		panel_5.add(button);
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		verticalGlue_3 = Box.createVerticalGlue();
		panel_1.add(verticalGlue_3);
		
		panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.Y_AXIS));
		
		verticalGlue_4 = Box.createVerticalGlue();
		panel_2.add(verticalGlue_4);
		
		panel_3 = new JPanel();
		panel_2.add(panel_3);
		
		chckbxNewCheckBox = new JCheckBox();
		chckbxNewCheckBox.setText("科技部");
		panel_3.add(chckbxNewCheckBox);
		
		chckbxNewCheckBox_1 = new JCheckBox("工信部");
		panel_3.add(chckbxNewCheckBox_1);
		
		chckbxNewCheckBox_2 = new JCheckBox("发改委");
		panel_3.add(chckbxNewCheckBox_2);
		
		panel_4 = new JPanel();
		panel_2.add(panel_4);
		
		chckbxNewCheckBox_3 = new JCheckBox("论文");
		panel_4.add(chckbxNewCheckBox_3);
		
		chckbxNewCheckBox_4 = new JCheckBox("专利");
		panel_4.add(chckbxNewCheckBox_4);
		
		chckbxNewCheckBox_5 = new JCheckBox("新闻");
		panel_4.add(chckbxNewCheckBox_5);
		
		verticalGlue_5 = Box.createVerticalGlue();
		panel_2.add(verticalGlue_5);
		
	}
	
	public void actionPerformed(ActionEvent e){
		Object object = e.getSource();
		if(object == button){
			//TODO textField
			
		}
	}

}
