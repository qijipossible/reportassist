package crawl;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.MysqlPipeline;
import us.codecraft.webmagic.processor.example.Ifengnews;
import us.codecraft.webmagic.processor.example.Miit;
import us.codecraft.webmagic.processor.example.Most;
import us.codecraft.webmagic.processor.example.Patent;
import us.codecraft.webmagic.processor.example.Wanfang;
import us.codecraft.webmagic.processor.example.chinanews;
import us.codecraft.webmagic.processor.example.sdpc;

public class Crawler {
	String key;
	boolean[] option;
	Spider spider1 = null;
	Spider spider2 = null;
	Spider spider3 = null;
	Spider spider4 = null;
	Spider spider5 = null;
	Spider spider6 = null;
	// 0科技部，1工信部，2发改委，3论文，4专利，5新闻

	String[] website = { "www.most.gov.cn", "www.miit.gov.cn",
			"www.sdpc.gov.cn" };

	public Crawler(String key, boolean[] option) {
		this.key = key;
		this.option = option;
		start();
	}

	public void start() {

		if (option[0] == true) {
			spider1 = Spider.create(new Most());
			spider1.addUrl(
					"http://cn.bing.com/search?q=site%3A" + website[0] + "+%22"
							+ key + "%22+filetype%3Ahtml")
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).thread(5).start();

		}
		if (option[1] == true) {
			spider2 = Spider.create(new Miit());
			spider2.addUrl(
					"http://cn.bing.com/search?q=site%3A" + website[1] + "+%22"
							+ key + "%22+filetype%3Ahtml")
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).thread(5).start();

		}
		if (option[2] == true) {
			spider3 = Spider.create(new sdpc());
			spider3.addUrl(
					"http://cn.bing.com/search?q=site%3A" + website[2] + "+%22"
							+ key + "%22+filetype%3Ahtml")
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).thread(5).start();
			return;
		}
		if (option[3] == true) {
			spider4 = Spider.create(new Ifengnews());
			spider4.addUrl(
					"http://zhannei.baidu.com/cse/search?q=" + key
							+ "&s=16378496155419916178").addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).thread(5).start();

		}
		if (option[4] == true) {
			spider5 = Spider.create(new Wanfang());
			spider5.addUrl("http://s.wanfangdata.com.cn/Paper.aspx?q=" + key)
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).thread(5).start();

		}
		if (option[5] == true) {
			spider6 = Spider.create(new Patent());
			spider6.addUrl(
					"http://www.soopat.com/Home/Result?SearchWord=" + key
							+ "&PatentIndex=0&Sort=1&Valid=2")
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).thread(5).start();

		}

	}

	public void stop() {
		if (spider1 != null)
			spider1.close();
		if (spider2 != null)
			spider2.close();
		if (spider3 != null)
			spider3.close();
		if (spider4 != null)
			spider4.close();
		if (spider5 != null)
			spider5.close();
		if (spider6 != null)
			spider6.close();
	}
}
