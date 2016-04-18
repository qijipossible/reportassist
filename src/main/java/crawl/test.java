package crawl;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class test extends JFrame implements ActionListener {
	JButton button1 = new JButton("stop");
	String key = "04专项";
	boolean[] option = { true, true, false, false, false, false };
	Crawler crawler = new Crawler(key, option);

	public test() throws HeadlessException {
		setSize(225, 80);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		FlowLayout flowLayout = new FlowLayout();
		setLayout(flowLayout);
		add(button1);
		setVisible(true);
		button1.addActionListener(this);
		crawler.start();
	}

	public void actionPerformed(ActionEvent e) {
		Object object = e.getSource();
		if (object == button1) {
			crawler.stop();
			System.out.println("stop now!");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new test();

	}

}
