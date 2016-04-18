package us.codecraft.webmagic.processor.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.pipeline.*;

/**
 * @author code4crafter@gmail.com <br>
 */
public class Patent implements PageProcessor {

	public static final String URL_LIST = "http://www\\.soopat\\.com/Home/Result\\?SearchWord=。*";
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
					.xpath("//h2[@class='PatentTypeBlock']").links()
					.regex("http://www\\.soopat\\.com/Patent/.*").all());
			page.addTargetRequests(page.getHtml().xpath("//div[@id='SoopatPager']")
					.links().all());
			page.setSkip(true);
			// 文章页
		} else {
			String temp =page.getHtml().xpath("//span[@class='detailtitle']/strong/i/text()").toString();
			if (temp == null) {
				page.setSkip(true);
				return;
			}
			page.putField("time", temp.substring(temp.indexOf("申请日：")+4, temp.length()));
			page.putField("title", page.getHtml().xpath("//span[@class='detailtitle']/h1/text()").toString());

			temp=page.getHtml().xpath("//td[@class='sum f14']/allText()").toString();
			page.putField("content",temp.substring(temp.indexOf("摘要")+3, temp.length()));
			
			temp=page.getHtml().xpath("//table[@class='datainfo']/tbody/tr[2]/allText()").toString();
			page.putField("author", temp.substring(temp.indexOf("申请人：")+4, temp.length()));

			page.putField("baseURL", page.getUrl());
			page.putField("type", "专利");
		}

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new Patent())
				.addUrl("http://www.soopat.com/Home/Result?SearchWord=推荐系统&PatentIndex=0&Sort=1&Valid=2")
				// "http://www.baidu.com/ns?word=机床"http://www.baidu.com/s?wd=机床
				// site:www.most.gov.cn
				.addPipeline(new ConsolePipeline()).run();
	}
}
