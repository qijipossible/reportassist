package us.codecraft.webmagic.pipeline;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import database.SQLop;

/**
 * Write results in console.<br>
 * Usually used in test.
 * 
 * @author code4crafter@gmail.com <br>
 * @since 0.1.0
 */
public class MysqlPipeline implements Pipeline {

	@Override
	public void process(ResultItems resultItems, Task task) {
		SQLop database = new SQLop();
		database.initialize();
		String url = null;
		String content = null;
		Date time = null;
		String title = null;
		String author = "";
		String type = null;
		String other = null;
		List<String> comments = null;
		List<Date> times = null;
		System.out.println("get page: " + resultItems.getRequest().getUrl());
		for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
			// String temp = entry.getValue().toString();
			if (entry.getValue().toString() == null)
				continue;
			if (entry.getKey().equals("title")) {
				title = entry.getValue().toString();
			} else if (entry.getKey().equals("content")) {
				content = entry.getValue().toString().replaceAll("　", "\n")
						.replaceAll(" ", "\n").replaceAll(" ", "\n");
			} else if (entry.getKey().equals("time")) {
				String temp = entry.getValue().toString();
				temp = temp.replace("年", "-");
				temp = temp.replace("月", "-");
				temp = temp.replace("：", "");
				temp = temp.replaceAll("[\u4e00-\u9fa5]+", "");
				temp = temp.replaceAll("【", "");
				temp = temp.replaceAll("】", "");
				if (temp.length() > 10)
					temp = temp.substring(0, 10);
				if (temp.length() < 10) {
					if (temp.length()
							- temp.indexOf("-", temp.indexOf("-") + 1) < 4)
						temp = temp.substring(0,
								temp.indexOf("-", temp.indexOf("-") + 1) + 1)
								+ "0"
								+ temp.substring(temp.indexOf("-",
										temp.indexOf("-") + 1) + 1, temp
										.length() - 1);
				}
				try {
					time = java.sql.Date.valueOf(temp);
				} catch (Exception e) {
					System.out.println(e + "\n数据库存入的时间信息格式有误");
				}
			} else if (entry.getKey().equals("baseURL")) {
				url = entry.getValue().toString();
			} else if (entry.getKey().equals("author")) {
				author = entry.getValue().toString();
			} else if (entry.getKey().equals("type")) {
				type = entry.getValue().toString();
			} else if (entry.getKey().equals("other")) {
				other = entry.getValue().toString();
			} else if (entry.getKey().equals("comment")) {
				comments = (List<String>) entry.getValue();
			} else if (entry.getKey().equals("times")) {
				List<String> temps = (List<String>) entry.getValue();
				times=new ArrayList<Date>();
				for (String temp : temps) {
					temp = temp.replace("年", "-");
					temp = temp.replace("月", "-");
					temp = temp.replace("：", "");
					temp = temp.replaceAll("[\u4e00-\u9fa5]+", "");
					temp = temp.replaceAll("【", "");
					temp = temp.replaceAll("】", "");
					if (temp.length() > 10)
						temp = temp.substring(0, 10);
					if (temp.length() < 10) {
						if (temp.length()
								- temp.indexOf("-", temp.indexOf("-") + 1) < 4)
							temp = temp
									.substring(0, temp.indexOf("-",
											temp.indexOf("-") + 1) + 1)
									+ "0"
									+ temp.substring(temp.indexOf("-",
											temp.indexOf("-") + 1) + 1, temp
											.length() - 1);
					}
					try {
						times.add(java.sql.Date.valueOf(temp));
					} catch (Exception e) {
						System.out.println(e + "\n数据库存入的时间信息格式有误");
						times.add(null);
					}
				}

			}

		}
		if (comments != null) {
			for (String comment : comments) {
				for (Date atime : times)
					database.insert(url, comment, atime, comment, author,
							"评论（百姓）", other);
			}
		} else if (content != null && !content.replaceAll("\n", "").equals(""))
			database.insert(url, content, time, title, author, type, other);
		database.close();
	}
}
