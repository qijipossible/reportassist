package crawl;

import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.MysqlPipeline;
import us.codecraft.webmagic.processor.example.Miit;
import us.codecraft.webmagic.processor.example.Most;
import us.codecraft.webmagic.processor.example.Patent;
import us.codecraft.webmagic.processor.example.Wanfang;
import us.codecraft.webmagic.processor.example.chinanews;
import us.codecraft.webmagic.processor.example.sdpc;

public class Crawler {
	String key;
	boolean[] option;
	Spider spider = null;
	// 0科技部，1工信部，2发改委，3论文，4专利，5新闻

	String[] website = { "www.most.gov.cn", "www.miit.gov.cn",
			"www.sdpc.gov.cn" };

	public Crawler(String key, boolean[] option) {
		this.key = key;
		this.option = option;
	}

	public void start() {
		if (option[0] == true) {
			spider = Spider.create(new Most());
			spider.addUrl(
					"http://cn.bing.com/search?q=site%3A" + website[0] + "+%22"
							+ key + "%22+filetype%3Ahtml")
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).start();
		}
		if (option[1] == true) {
			spider = Spider.create(new Miit());
			spider.addUrl(
					"http://cn.bing.com/search?q=site%3A" + website[1] + "+%22"
							+ key + "%22+filetype%3Ahtml")
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).start();
		}
		if (option[2] == true) {
			spider = Spider.create(new sdpc());
			spider.addUrl(
					"http://cn.bing.com/search?q=site%3A" + website[2] + "+%22"
							+ key + "%22+filetype%3Ahtml")
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).start();
		}
		if (option[5] == true) {
			spider = Spider.create(new chinanews());
			spider.addUrl("http://sou.chinanews.com.cn/search.do?q=" + key)
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).start();
		}
		if (option[3] == true) {
			spider = Spider.create(new Wanfang());
			spider.addUrl("http://s.wanfangdata.com.cn/Paper.aspx?q=" + key)
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).start();
		}
		if (option[4] == true) {
			spider = Spider.create(new Patent());
			spider.addUrl("http://www.soopat.com/Home/Result?SearchWord="+key+"&PatentIndex=0&Sort=1&Valid=2")
					.addPipeline(new ConsolePipeline())
					.addPipeline(new MysqlPipeline()).start();
		}

	}
}
