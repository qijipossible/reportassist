package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSplitPane;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JSeparator;

import java.awt.Component;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class DetailFrame extends JFrame {

	private JPanel contentPane;

	public DetailFrame(String abs, String article) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);//最大化窗口
		this.setTitle("详细信息");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 2));
		
		JPanel panel = new JPanel();
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("摘要",JLabel.CENTER);
		panel.add(lblNewLabel, BorderLayout.NORTH);
		
		JTextPane absTextPane = new JTextPane();
		absTextPane.setText(abs);
		absTextPane.setEditable(false);
		panel.add(absTextPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("全文",JLabel.CENTER);
		panel_1.add(lblNewLabel_1, BorderLayout.NORTH);
		
		JTextPane articleTextPane = new JTextPane();
		articleTextPane.setText(article);
		articleTextPane.setEditable(false);

		JScrollPane scrollPane_1 = new JScrollPane(articleTextPane);
		panel_1.add(scrollPane_1, BorderLayout.CENTER);
		
		JScrollPane scrollPane = new JScrollPane(absTextPane);
		panel.add(scrollPane, BorderLayout.CENTER);
	}

}
