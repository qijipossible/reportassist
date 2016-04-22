package NLP;

import java.sql.*;
import java.util.*;

import com.hankcs.hanlp.*;
import com.mysql.jdbc.Connection;

public class NLP1 
{
	//正文文本压缩，输入正文文本，输出压缩后的正文文本
	//核心算法有待提高
	public String stringsummary(String inputstr) 
	{
		String summary = new String();
		String[] input = inputstr.split("\n");
		for(int i = 0; i < input.length; ++i) {
			int length = input[i].length();
			if(length==0)
				continue;
			if(length < 30)
				summary += (input[i]);
			else if(i < 2 || i == input.length - 1)
				summary += (HanLP.getSummary(input[i], length / 2));
			else 
				summary += (HanLP.getSummary(input[i], length / 5));
			summary += "\n";
		}
		return summary;
	}
	//输入关键字从数据库中查找相关记录，输出每条记录的基本信息和正文文摘，以数组的形式返回
	public List<String> summary(String searchword) 
	{
		List<String> result = new ArrayList<String>();
		final String DB_URL = "jdbc:mysql://localhost:3306/webmagic";
		final String USER = "root";
		final String PASS = "123456";
		Connection conn = null;
		Statement stmt = null;
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
		     conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		     stmt = conn.createStatement();
			 String sql;
//		     sql = "SELECT * FROM webpage WHERE find_in_set(\"" + searchword + "\", content) " 
//		    		 + "OR find_in_set(\"" + searchword + "\", title)";
		     
		     sql = "SELECT * FROM webpage WHERE content LIKE '%"
					+ searchword + "%' OR title LIKE'%" + searchword + "%'";
		     
		     ResultSet rs = stmt.executeQuery(sql);
		     while(rs.next()){
		    	 String text = rs.getString("content"), time = rs.getString("savetime"), title = rs.getString("title");
		    	 String author = rs.getString("author"), from = rs.getString("baseUrl");
		    	 String tmp = new String();
		    	 tmp += "题目：" + "\n" + title + "\n";
		    	 tmp += "作者：" + "\n" + author + "\n";
		    	 tmp += "时间：" + "\n" + time + "\n";
		    	 tmp += "来源: " + "\n" + from + "\n";
		    	 tmp += "摘要：" + "\n" + stringsummary(text) + "\n";
		    	 result.add(tmp);
		     }
		     rs.close();
		}
		catch(SQLException se){
		      se.printStackTrace();
		}
		catch(Exception exc){
		      exc.printStackTrace();
		}
		finally{
			try{
				if(stmt!=null)
					stmt.close();
		    }
			catch(SQLException se2){
		    }
		    try{
		    	if(conn!=null)
		            conn.close();
		    }
		    catch(SQLException se){
		         se.printStackTrace();
		    }
		}
		return result;
	}
	//输入关键字从数据库中查找相关记录，将所有报告压缩成一篇文摘
	public String report(String keyword)
	{
		String result = new String();
		String goverment =new String();
		String news= new String();
		int govermentnum = 0,newsnum = 0;
		final String DB_URL = "jdbc:mysql://localhost:3306/webmagic";
		final String USER = "root";
		final String PASS = "123456";
		Connection conn = null;
		Statement stmt = null;
		
		try {
			 Class.forName("com.mysql.jdbc.Driver");
		     conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
		     stmt = conn.createStatement();
			 String sql;
		     
		     sql = "SELECT * FROM webpage WHERE content LIKE '%"
					+ keyword + "%' OR title LIKE'%" + keyword + "%'";
		     
		     ResultSet rs = stmt.executeQuery(sql);
		     while(rs.next()){
		    	 String text = rs.getString("content"), from = rs.getString("type");
		    	 if(from == "科技部" || from == "工信部" || from == "发改委"){
		    		 goverment += text;
		    		 govermentnum++;
		    	 }
		    	 else if(from == "新闻"){
		    		 news += text;
		    		 newsnum ++;
		    	 }
		     }
		     rs.close();
		}
		catch(SQLException se){
		      se.printStackTrace();
		}
		catch(Exception exc){
		      exc.printStackTrace();
		}
		finally{
			try{
				if(stmt!=null)
					stmt.close();
		    }
			catch(SQLException se2){
		    }
		    try{
		    	if(conn!=null)
		            conn.close();
		    }
		    catch(SQLException se){
		         se.printStackTrace();
		    }
		}
		result += HanLP.extractSummary(goverment,govermentnum * 5);
		result += "\n以下是来自新闻的参考：\n";
		result += HanLP.extractSummary(news,newsnum * 2);
		return result;
	}
}

