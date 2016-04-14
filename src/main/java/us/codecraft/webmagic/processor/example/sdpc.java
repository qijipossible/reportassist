package us.codecraft.webmagic.processor.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.pipeline.*;

/**
 * @author code4crafter@gmail.com <br>
 */
public class sdpc implements PageProcessor {

	public static final String URL_LIST = "http://www\\.baidu\\.com/s\\.*";
	// public static final String
	// KEJI_WEB_SITE="http://www\\.most\\.gov\\.cn/\\.*";
	// public static final String KEJI_WEB_SITE="www.most.gov.cn";
	// public static final String KEJI_WEB_SITE="www.most.gov.cn";

	// public static final String URL_POST =
	// "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

	private Site site = Site.me()
			// .setDomain("blog.sina.com.cn")
			.setSleepTime(3000)
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public void process(Page page) {
		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			page.addTargetRequests(page.getHtml()
					.xpath("//div[@id='content_left']").links()
					.regex("http://www\\.baidu\\.com/link\\?url=.*").all());
			page.addTargetRequests(page.getHtml().xpath("//div[@id='page']")
					.links().all());
			// 文章页
		} else {

			if (page.getHtml().xpath("title/text()").toString() != null) {
				page.putField("title",
						page.getHtml().xpath("title/text()"));

				page.putField("content", page.getHtml()
						.xpath("body/tidyText()"));

//				page.putField("time",
//						page.getHtml()
//								.xpath("div[@class='gray12 lh22']/text()"));

//				page.putField("author",
//						page.getHtml()
//								.xpath("div[@class='gray12 lh22']/text()"));
				page.putField("baseURL", page.getUrl());
				page.putField("type", "发改委");
			}

		}
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new sdpc())
				.addUrl("http://www.baidu.com/s?wd=机床%20site:www.most.gov.cn")
				// "http://www.baidu.com/ns?word=机床"http://www.baidu.com/s?wd=机床
				// site:www.most.gov.cn
				.addPipeline(new ConsolePipeline())
				.addPipeline(new FilePipeline()).run();
	}
}
