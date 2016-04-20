package output;

import java.util.List;
import java.util.Map;

import database.SQLop;
import NLP.NLP;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SQLop database = new SQLop();
		database.initialize();
		List<Map> result = new NLP().summary("04专项");
		
		HtmlFile file=new HtmlFile(".\\output\\1.html","04专项");
		file.writeStatistics("D:\\barchartTEST.jpg");
		file.writeStatistics("D:\\linechartTEST.jpg");
		file.writeGov(result,4);
		file.writeNew(result,3);
		file.finish();
		database.close();
	}

}
