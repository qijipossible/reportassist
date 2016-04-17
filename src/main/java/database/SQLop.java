package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.HashMap;

public class SQLop {
	String tableName = "webpage";
	String user = "root";
	String password = "123456";
	String dataName = "webmagic";
	String mysql_url = "jdbc:mysql://localhost/" + dataName;

	Connection conn = null;
	Statement statemt = null;
	ResultSet results = null;

	/*
	 * initialize the database link
	 */
	public void initialize() {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			System.out.println(e);
		}

		try {
			conn = DriverManager.getConnection(mysql_url, user, password);
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}

		return;
	}

	public void close() {
		try {
			if (results != null) {
				results.close();
			}
			if (statemt != null) {
				statemt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
			System.out.println("VendorError: " + e.getErrorCode());
		}

		return;
	}

	/*
	 * insert
	 */
	public void insert(String url, String content, Date savetime, String title,
			String author, String type) {
		PreparedStatement preStatement = null;
		if (!isRepeat(title)) {
			try {
				preStatement = conn.prepareStatement("INSERT INTO " + tableName
						+ " (baseUrl, content, savetime, title, author, type)"
						+ " VALUES(?,?,?,?,?,?)");
				if (url != null)
					preStatement.setString(1, url);
				if (content != null)
					preStatement.setString(2, content);
//				if (savetime != null)
					preStatement.setDate(3, savetime);
				if (title != null)
					preStatement.setString(4, title);
				if (author != null)
					preStatement.setString(5, author);
				if (type != null)
					preStatement.setString(6, type);
				preStatement.executeUpdate();
			} catch (SQLException e) {
				System.out.println("SQLException: " + e.getMessage());
				System.out.println("SQLState: " + e.getSQLState());
				System.out.println("VendorError: " + e.getErrorCode());
			} finally {
				try {
					if (preStatement != null)
						preStatement.close();
				} catch (SQLException e) {
					System.out.println("SQLException: " + e.getMessage());
					System.out.println("SQLState: " + e.getSQLState());
					System.out.println("VendorError: " + e.getErrorCode());
				}
			}
		}

		return;
	}

	/*
	 * @param countType: 0 for all, 1 for source site, 2 for year
	 */
	public HashMap<String, Integer> count(int countType, String keyword) {
		final int SITE = 1;
		final int YEAR = 2;

		Statement statemt = null;
		ResultSet results = null;
		HashMap<String, Integer> resultMap = new HashMap<String, Integer>();
		try {
			statemt = conn.createStatement();

			// 建立一个检索所有包含关键词的记录的视图
			String sql = "DROP VIEW IF EXISTS tmp";
			statemt.execute(sql);
			sql = "CREATE VIEW tmp AS SELECT * FROM webpage WHERE content LIKE '%"
					+ keyword + "%' OR title LIKE'%" + keyword + "%'";
			statemt.execute(sql);

			switch (countType) {
			case SITE:
				sql = "SELECT type,COUNT(*) FROM tmp GROUP BY type";
				results = statemt.executeQuery(sql);
				while (results.next()) {
					resultMap.put(results.getString(1),
							new Integer(results.getInt(2)));
				}
				break;
			case YEAR:
				sql = "SELECT year(savetime),COUNT(*) FROM tmp GROUP BY year(savetime) ORDER BY year(savetime)";
				results = statemt.executeQuery(sql);
				while (results.next()) {
<<<<<<< HEAD
					if(results.getString(1) == null) continue; 
=======
					if(results.getString(1) == null) continue;
>>>>>>> origin/master
					resultMap.put(results.getString(1),
							new Integer(results.getInt(2)));
				}
				break;
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			try {
				if (statemt != null)
					statemt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return resultMap;
	}

	public boolean isRepeat(String title) {
		Statement statemt = null;
		ResultSet results = null;
		try {
			statemt = conn.createStatement();

			String sql = "select title from webpage where title='" + title
					+ "'";
			results=statemt.executeQuery(sql);
			if(results.next()==false)
				return false;
			

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			try {
				if (statemt != null)
					statemt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return true;
	}

	// 按年份和网站统计包含关键词的记录数目，输入关键词，返回一个包含统计信息的字符串
	public String countAllResult(String keyword) {
		Statement statemt = null;
		ResultSet results = null;
		String countresult = new String();
		try {
			statemt = conn.createStatement();

			// 建立一个检索所有包含关键词的记录的视图
			String sql = "DROP VIEW IF EXISTS tmp";
			statemt.execute(sql);
			sql = "CREATE VIEW tmp AS SELECT * FROM webpage WHERE content LIKE '%"
					+ keyword + "%' OR title LIKE'%" + keyword + "%'";
			statemt.execute(sql);

			// 包含关键词的统计总数
			sql = "SELECT COUNT(*) FROM tmp";
			results = statemt.executeQuery(sql);
			results.first();
			countresult += "共检索到包含\'" + keyword + "\'关键词的记录"
					+ results.getString(1) + "条";

			// 按年统计记录总数
			countresult += "按年份统计包含\'" + keyword + "\'关键词的记录数如下";
			sql = "SELECT year(savetime),COUNT(*) FROM tmp GROUP BY year(savetime) ORDER BY year(savetime)";
			results = statemt.executeQuery(sql);
			results.first();
			while (results.next()) {
				countresult += results.getString(1) + "年：  "
						+ results.getString(2) + "\n";
			}

			// 按网站统计记录总数
			countresult += "按网站统计包含\'" + keyword + "\'关键词的记录数如下";
			sql = "SELECT baseUrl,COUNT(*) FROM tmp GROUP BY baseUrl";
			results = statemt.executeQuery(sql);
			results.first();
			while (results.next()) {
				countresult += results.getString(1) + "：  "
						+ results.getString(2) + "\n";
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception exc) {
			exc.printStackTrace();
		} finally {
			try {
				if (statemt != null)
					statemt.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return countresult;
	}
}
