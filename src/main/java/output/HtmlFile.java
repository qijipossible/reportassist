package output;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;

public class HtmlFile {
	FileWriter out;
	BufferedWriter writer;

	/**
	 * 
	 * @param file_path 文件路径+文件名
	 */
	public HtmlFile(String file_path) {
		File file = new File(file_path);
		try {
			out = new FileWriter(file);
			writer = new BufferedWriter(out);

			writer.write("<!DOCTYPE html><html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" > <title>报告</title></head><body>"
					+ "<h1><font face=\"微软雅黑\"size=\"5\">统计信息：</font></h1>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 写统计信息（图）
	 * @param icon_path 图路径+图名
	 */
	public void writeStatistics(String icon_path) {
		try {
			writer.write("<img src=\"" + icon_path
					+ "\" width=\"500\" height=\"400\" />");
		} catch (Exception e) {
			System.out.println();
		}
	}

	/**
	 * 写政府公文信息
	 * @param piece 放入报告中的文摘篇数
	 */
	public void writeGov(List<Map> infor,int piece) {
		try {
			if(piece>infor.size())
				piece=infor.size();
			writer.write("<h1><font face=\"微软雅黑\"size=\"5\">政府公文：</font></h1>");
			for(int i=0;i<piece;i++){
				writer.write("<p>");
				writer.write("<h2 align=\"center\">"
						+infor.get(i).get("title").toString()
						+"</h2>");
				writer.write("<div align=\"center\"><font face=\"楷体\" size=\"3\">"
						+infor.get(i).get("time").toString()
						+infor.get(i).get("type").toString()
						+infor.get(i).get("author").toString()
						+"</font><br /></div>");
				writer.write("<div  ><font size=\"3\">"
						+"摘要：&nbsp&nbsp"+infor.get(i).get("abstract").toString()
						+"</font></div>");
				writer.write("</p><br/><br/>");
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 写新闻信息
	 * @param infor
	 * @param piece放入报告中的文摘数量
	 */
	public void writeNew(List<Map> infor,int piece) {
		try {
			if(piece>infor.size())
				piece=infor.size();
			writer.write("<h1><font face=\"微软雅黑\"size=\"5\">相关报道：</font></h1>");
			for(int i=0;i<piece;i++){
				writer.write("<p>");
				writer.write("<h2 align=\"center\">"
						+infor.get(i).get("title").toString()
						+"</h2>");
				writer.write("<div align=\"center\"><font face=\"楷体\" size=\"3\">"
						+infor.get(i).get("time").toString()
						+infor.get(i).get("type").toString()
						+infor.get(i).get("author").toString()
						+"</font><br /></div>");
				writer.write("<div  ><font size=\"3\">"
						+"摘要：&nbsp&nbsp"+infor.get(i).get("abstract").toString()
						+"</font></div>");
				writer.write("</p><br/><br/>");
				
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/**
	 * 写入完毕
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
