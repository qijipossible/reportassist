package output;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MakeReport {
	// final int NUMBER_gov = 10;
	// final int NUMBER_news = 10;

	// final int SELECT_gov=0;
	// final int SELECT_news=1;

	final int ICON = 0;
	final int WORDS = 1;

	String keyword = null;
	String filePath = null;
	Information t = Information.getInstance();

	public MakeReport(String keyword, String filePath) {
		this.keyword = keyword;
		this.filePath = filePath;
		excute();
	}

	public void excute() {

		HtmlFile file = new HtmlFile(filePath, keyword);

		file.writeStatistics("1������ͳ��", WORDS);
		file.writeStatistics(t.getSource_jpg(), ICON);
		file.writeStatistics(t.getMotion_jpg(), ICON);
		file.writeStatistics(t.getYear_comments_jpg(), ICON);

		file.writeStatistics("2���������", WORDS);
		file.writeinfor("��ͬ�����ע������:");
		String[] str={"����:","ý��:","����:"};
		if (t.getSource_theme() != null) {
			for (int i = 0; i < t.getSource_theme().size(); i++) {
				file.writeinfor(str[i]+t.getSource_theme().get(i));
			}
		}
		file.writeinfor("���Է�ֵ��ȵ�����:");
		if (t.getYear_theme() != null) {
			for (int i = 0; i < t.getYear_theme().size(); i++)
				file.writeinfor(t.getYear_theme().get(i));
		}
		file.writeStatistics("3��̬�ȷ���", WORDS);
		file.writeinfor("ȫ�����������ָ��:" + t.getGlobal_attitude());
		file.writeinfor("��������̬��ָ��:" + t.getGov_attitude());
		file.writeinfor("ý��������:" + t.getMedia_attitude());
		file.writeinfor("���ڵ������:" + t.getPublic_attitude());

		file.writeStatistics("4���ȵ����", WORDS);
		file.writeinfor("�ȵ�����:");
		if (t.getHot_theme() != null) {
			for (int i = 0; i < t.getHot_theme().size(); i++)
				file.writeinfor(t.getHot_theme().get(i));
		}
		file.writeinfor("��Ҫ�۵�:");
		if (t.getOther() != null) {
			for (int i = 0; i < t.getOther().size(); i++)
				file.writeinfor(t.getOther().get(i));
		}
		file.finish();
	}

	/**
	 * classify the abstracts
	 * 
	 * @param info
	 * @return
	 */
	// public List<Map<String, String>> classify(List<Map<String, String>> info,
	// int type) {
	// List<Map<String, String>> result=new ArrayList<Map<String, String>>();
	// for (Map map : info) {
	// if (type == SELECT_news) {
	// if (map.get("type").toString().equals("����"))
	// result.add(map);
	// } else if (type==SELECT_gov){
	// if(map.get("type").toString().equals("�Ƽ���")||map.get("type").toString().equals("����ί")||
	// map.get("type").toString().equals("���Ų�"))
	// result.add(map);
	// }
	//
	// }
	//
	// return result;
	// }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Information t = Information.getInstance();
		t.setGlobal_attitude("globalattitude");
		Information tt = Information.getInstance();
		tt.setGov_attitude("govattitude");
		// t.setHot_theme("hottheme");
		tt.setMedia_attitude("mediaattitude");
		new MakeReport("test123", ".\\output\\1.html");

	}

}
