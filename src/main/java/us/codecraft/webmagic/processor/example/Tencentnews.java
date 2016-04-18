package us.codecraft.webmagic.processor.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.pipeline.*;

/**
 * @author code4crafter@gmail.com <br>
 */
public class Tencentnews implements PageProcessor {

	public static final String URL_LIST = "https://www\\.sogou\\.com/web\\?num=100&site=news\\.qq\\.com&query=。*";
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
					.xpath("//div[@class='results']").links()
					.regex("http://www\\.sogou\\.com/link?url=.*").all());
			page.addTargetRequests(page.getHtml().xpath("//div[@id='pagebar_container']")
					.links().all());
			page.setSkip(true);
			// 文章页
		} else {
			String temp = page.getHtml().xpath("//div[@class='left-t']/text()")
					.toString();
			if (temp == null) {
				page.setSkip(true);
				return;
			}
			page.putField("time", temp.substring(1, 11));
			page.putField("title", page.getHtml().xpath("title/text()"));

			page.putField("content",
					page.getHtml().xpath("//div[@class='left_zw']/tidyText()")
							);

			page.putField("author", "中国新闻网");

			page.putField("baseURL", page.getUrl());
			page.putField("type", "新闻");
		}

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new Tencentnews())
				.addUrl("https://www.sogou.com/web?num=100&site=news.qq.com&query=%22数控机床%22&num=100")
				// "http://www.baidu.com/ns?word=机床"http://www.baidu.com/s?wd=机床
				// site:www.most.gov.cn
				.addPipeline(new ConsolePipeline()).run();
	}
}
