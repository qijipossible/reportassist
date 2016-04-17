package main;

import javax.swing.*;

import chart.Chart;
import NLP.NLP;
import database.Record;
import database.SQLop;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Result_face extends JFrame implements ItemListener, ActionListener {
	JTabbedPane p;
	JComboBox list;
	JLabel label1_1 = new JLabel("题目：");
	JLabel label2_1 = new JLabel("题目：");
	JTextArea label1 = new JTextArea("");
	JTextArea label2 = new JTextArea("");
	JTextPane label3 = new JTextPane();
	JTextArea label_content = new JTextArea("");
	JTextField tx1_1 = new JTextField(20);
	JTextField tx2_1 = new JTextField(20);
	JButton bt1_1 = new JButton("查找全文");
	JButton bt2_1 = new JButton("查找全文");
	
	JDialog frame = new JDialog();

	SQLop database = new SQLop();

	String keyword = null;

	public Result_face(String keyword) {
		this.keyword = keyword;
		setTitle("结果");
		label2.setEditable(false);
		label1.setEditable(false);
		show_abstract();
		show_record();
		show_statistics();
		
		label_content.setLineWrap(true);
		frame.add(new JScrollPane(label_content), BorderLayout.CENTER);
		frame.setTitle("全文");
		frame.setBounds(200, 200, 500, 500);
		frame.setModal(true);

		JPanel mng_user = new JPanel();
		JPanel mng_src = new JPanel();
		JPanel mng_sys = new JPanel();

		validate();

		JPanel panel1_1 = new JPanel();
		panel1_1.add(label1_1);
		panel1_1.add(tx1_1);
		panel1_1.add(bt1_1);

		mng_user.setLayout(new BorderLayout());
		mng_user.add(new JScrollPane(label1), BorderLayout.CENTER);
		mng_user.add(panel1_1, BorderLayout.SOUTH);

		// ____________________________________________________
		
		JPanel panel2_1 = new JPanel();
		panel2_1.add(label2_1);
		panel2_1.add(tx2_1);
		panel2_1.add(bt2_1);
		

		mng_src.setLayout(new BorderLayout());
		mng_src.add(new JScrollPane(label2), BorderLayout.CENTER);
		mng_src.add(panel2_1, BorderLayout.SOUTH);

		// ——————————————————————————————————————————————————————————————————

		mng_sys.setLayout(new BorderLayout());
		mng_sys.add(new JScrollPane(label3), BorderLayout.CENTER);

		p = new JTabbedPane(JTabbedPane.TOP);
		p.add("统计信息", mng_sys);
		p.add("信息摘要", mng_src);
		p.add("抓取信息", mng_user);

		p.validate();
		add(p, BorderLayout.CENTER);
		validate();

		bt1_1.addActionListener(this);
		bt2_1.addActionListener(this);
	

		setBounds(100, 100, 1000, 800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void show_statistics() {
		new Chart(keyword);
		// text.setBounds(0, 0, 442, 291);
		ImageIcon img = new ImageIcon(".\\barchartTEST.jpg");
		label3.insertIcon(img);
		ImageIcon img2 = new ImageIcon(".\\linechartTEST.jpg");
		label3.insertIcon(img2);
	}

	public void show_record() {
		database.initialize();
		List<Record> records = database.getRecordL();
		label1.setText("发布日期" + "\t\t\t" + "来源" + "\t\t\t" + "题目"
				+ "\t\t\t\t\t\t" + "地址");
		for (int i = 0; i < records.size(); i++) {
			Record record = records.get(i);
			label1.append("\n" + record.getSavetime() + "\t\t\t"
					+ record.getType() + "." + record.getAuthor() + "\t\t\t"
					+ record.getTitle().trim() + "\t\t\t\t\t\t"
					+ record.getBaseUrl());
		}
		database.close();
	}

	public void show_abstract() {
		List<Map> result = new NLP().summary(keyword);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
			label2.append(result.get(i).get("url").toString() + "\n"
					+ result.get(i).get("title").toString() + "\n"
					+ result.get(i).get("time").toString() + "\t"
					+ result.get(i).get("type").toString() + "."
					+ result.get(i).get("author").toString() + "\n"
					+ result.get(i).get("abstract").toString() + "\n\n\n");
		}
		database.close();
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == bt1_1)
		{
			database.initialize();
			Record record = database.getRecordByTitle(tx1_1.getText());
			database.close();
			label_content.setText(record.getContent());
			frame.setVisible(true);
		}else if(e.getSource() == bt2_1){
			database.initialize();
			Record record = database.getRecordByTitle(tx2_1.getText());
			database.close();
			label_content.setText(record.getContent());
			frame.setVisible(true);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub

	}
}
