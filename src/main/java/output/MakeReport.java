package output;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import database.SQLop;
import NLP.NLP;

public class MakeReport {
	final int NUMBER_gov = 5;
	final int NUMBER_news = 3;
	
	final int SELECT_gov=0;
	final int SELECT_news=1;
	
	final int ICON=0;
	final int WORDS=1;
	
	String keyword = null;
	List<Map> result = null;
	String filePath = null;

	public MakeReport(String keyword, List<Map> result, String filePath) {
		this.keyword = keyword;
		this.result = result;
		this.filePath = filePath;
		excute();
	}

	public void excute() {

		HtmlFile file = new HtmlFile(filePath, keyword);

		file.writeStatistics("1、科技部、工信部、发改委网站发布有关“"+keyword+"”文件统计：", WORDS);
		file.writeStatistics("D:\\reportassist\\output\\year_gov.jpg",ICON);
		file.writeStatistics("D:\\reportassist\\output\\site.jpg",ICON);
		
		file.writeStatistics("2、近几年来有关“"+keyword+"”内容的新闻报道：", WORDS);
		file.writeStatistics("D:\\reportassist\\output\\year_news.jpg",ICON);
		file.writeStatistics("D:\\reportassist\\output\\news_source.jpg",ICON);
		
		file.writeStatistics("3、近几年来有关“"+keyword+"”内容的论文发表情况：", WORDS);
		file.writeStatistics("D:\\reportassist\\output\\year_paper.jpg",ICON);
		file.writeStatistics("D:\\reportassist\\output\\journal.jpg",ICON);
		
		file.writeStatistics("4、近几年来有关“"+keyword+"”内容的专利申请授权情况：", WORDS);
		file.writeStatistics("D:\\reportassist\\output\\year_patent.jpg",ICON);
		file.writeStatistics("D:\\reportassist\\output\\patent_applicant.jpg",ICON);
		file.writeStatistics("D:\\reportassist\\output\\patent_type.jpg",ICON);


		List<Map> info = classify(result,SELECT_gov);
		file.writeGov(info, NUMBER_gov);
		info = classify(result,SELECT_news);
		file.writeNew(info, NUMBER_news);
		file.finish();
	}

	/**
	 * classify the abstracts
	 * 
	 * @param info
	 * @return
	 */
	public List<Map> classify(List<Map> info, int type) {
		List<Map> result=new ArrayList<Map>(); 
		for (Map map : info) {
			if (type == SELECT_news) {
				if (map.get("type").toString().equals("新闻"))
					result.add(map);
			} else if (type==SELECT_gov){
				if(map.get("type").toString().equals("科技部")||map.get("type").toString().equals("发改委")|| map.get("type").toString().equals("工信部"))
					result.add(map);
			}
				
		}

		return result;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLop database = new SQLop();
		database.initialize();
		List<Map> result = new NLP().summary("数控机床");

		MakeReport report = new MakeReport("数控机床", result,
				"D:\\reportassist\\output\\03.html");

	}

}
