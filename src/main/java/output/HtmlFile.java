package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.Map;

public class HtmlFile {

	final int ICON = 0;
	final int WORDS = 1;
	final int NEWS = 1;
	final int GOV = 0;

	//FileWriter out;
	OutputStreamWriter out;
	BufferedWriter writer;
	String keyword;

	/**
	 * 
	 * @param file_path
	 *            �ļ�·��+�ļ���
	 * @param key
	 *            �ؼ��
	 */
	public HtmlFile(String file_path, String key) {
		this.keyword = key;
		File file = new File(file_path);
		try {
			//out = new FileWriter(file);
			out=new OutputStreamWriter(new FileOutputStream(file),"UTF8");
			writer = new BufferedWriter(out);
			writer.write("<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" > <title>�йء�"
					+ keyword
					+ "���ĵ���ͳ��</title></head><body>"
					+ "<h1 align=\"center\">�йء�"
					+ keyword
					+ "����ͳ�ƽ���</h1>");
					//+ "<h1><font face=\"΢���ź�\"size=\"5\">һ����Ϣͳ�ƽ��</font></h1><div align=\"center\">");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * дͳ����Ϣ��ͼ��
	 */
	public void writeStatistics(String info, int type) {
		try {
			if (type == ICON)
				writer.write("<img src=\"" + info
						+ "\" width=\"500\" height=\"400\" />");
			else if (type == WORDS)
				writer.write("<br/><h3 align=\"left\"><font size=\"4\">" + info
						+ "</font><h3><br/>");
		} catch (Exception e) {
			System.out.println();
		}
	}

	/**
	 * д����������Ϣ
	 * 
	 * @param piece
	 *            ���뱨���е���ժƪ��
	 */
//	public void writeGov(List<Map<String, String>> infor, int piece) {
//		try {
//			if (piece > infor.size())
//				piece = infor.size();
//			writer.write("</div><h1><font face=\"΢���ź�\"size=\"5\">������������ժҪ��</font></h1>");
//			// writeSummery(summary, GOV);
//			writer.write("<p><font face=\"΢���ź�\"size=\"3\">����Ϊ����������ߣ�</font></p>");
//			for (int i = 0; i < piece; i++) {
//				writer.write("<p>");
//				writer.write("<font face=\"����\" size=\"3\">" + (i + 1)
//						+ "��&nbsp&nbsp" + infor.get(i).get("time").toString()
//						+ "&nbsp&nbsp</font>" + "<font size=\"3\">"
//						+ infor.get(i).get("type").toString() + "������<B>"
//						+ infor.get(i).get("title").toString()
//						+ "</B>����</font>"
//						+ "<font size=\"3\"><br/>&nbsp&nbsp����ժҪ��"
//						+ infor.get(i).get("abstract").toString()
//						+ "</font></p><br/>");
//
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	public void writeinfor(String infor) {
		try {

			writer.write("<p><font face=\"΢���ź�\"size=\"3\">"+infor+"</font></p>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * д������Ϣ
	 * 
	 * @param infor
	 * @param piece���뱨���е���ժ����
	 */
//	public void writeNew(List<Map<String, String>> infor, int piece) {
//		try {
//			if (piece > infor.size())
//				piece = infor.size();
//			writer.write("<h1><font face=\"΢���ź�\"size=\"5\">��������ű�����</font></h1>");
//			writeSummery(summary, NEWS);
//			writer.write("<p><font face=\"΢���ź�\"size=\"3\">����Ϊ�����йر�����</font></p>");
//			for (int i = 0; i < piece; i++) {
//				writer.write("<p>");
//				writer.write("<font face=\"����\" size=\"3\">" + (i + 1)
//						+ "��&nbsp&nbsp" + infor.get(i).get("time").toString()
//						+ "&nbsp&nbsp</font>" + "<font size=\"3\">"
//						+ infor.get(i).get("author").toString() + "������<B>"
//						+ infor.get(i).get("title").toString()
//						+ "</B>����</font>"
//						+ "<font size=\"3\"><br/>&nbsp&nbsp����ժҪ��"
//						+ infor.get(i).get("abstract").toString()
//						+ "</font></p><br/>");
//
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	/**
	 * All the information summary an article
	 * 
	 * @param infor
	 * @param type
	 *            0:gov file,1 newS file
	 */
//	public void writeSummery(String infor, int type) {
//		try {
//			if (type == GOV)
//				writer.write("<p>" + "<font size=\"3\">&nbsp&nbsp<B>������</B>"
//						+ infor.substring(1, infor.indexOf("�������������ŵĲο���") - 2)
//						+ "��</font></p><br/>");
//			else
//				writer.write("<p>"
//						+ "<font size=\"3\">&nbsp&nbsp<B>������</B>"
//						+ infor.substring(infor.indexOf("�������������ŵĲο���") + 13,
//								infor.length() - 1) + "��</font></p><br/>");
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

	/**
	 * д�����
	 */
	public void finish() {
		try {
			writer.write("</body></html>");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
