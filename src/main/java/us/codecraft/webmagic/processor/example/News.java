package us.codecraft.webmagic.processor.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

import java.util.List;

/**
 * @author code4crafter@gmail.com <br>
 *         Date: 13-5-20 Time: 下午5:31
 */
public class News implements PageProcessor {
	public static final String URL_LIST = "http://news\\.baidu\\.com/ns\\?word=.*";
	private Site site = Site.me()
			// .setDomain("blog.sina.com.cn")
			.setSleepTime(3000)
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public void process(Page page) {
		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			Html h = page.getHtml();
			page.addTargetRequests(page.getHtml()
					.xpath("//div[@id='content_left']").links().all());
			page.addTargetRequests(page.getHtml().xpath("//div[@id='page']")
					.links().all());
			// 文章页
		} else {
			page.putField("title", page.getHtml().xpath("title/text()"));

			page.putField("content", page.getHtml().xpath("body/tidyText()"));

			// page.putField("time",
			// page.getHtml()
			// .xpath("div[@class='gray12 lh22']/text()"));

			// page.putField("author",
			// page.getHtml()
			// .xpath("div[@class='gray12 lh22']/text()"));
			page.putField("baseURL", page.getUrl());
			page.putField("type", "新闻");
		}

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new News()).addUrl("http://news.baidu.com/ns?word=机床")
				.addPipeline(new ConsolePipeline()).run();
	}
}
