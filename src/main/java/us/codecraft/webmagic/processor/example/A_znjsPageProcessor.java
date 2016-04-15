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
public class A_znjsPageProcessor implements PageProcessor {
	public static final String URL_LIST = "http://znjs.most.gov.cn/wasdemo/search?searchword=w+";
	private Site site = Site.me()
			// .setDomain("blog.sina.com.cn")
			.setSleepTime(3000)
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

	@Override
	public void process(Page page) {
		// 列表页
		if (page.getUrl().regex(URL_LIST).match()) {
			Html h=page.getHtml();
			page.addTargetRequests(page.getHtml()
					.xpath("/x:html/x:body/x:table[2]/x:tbody/x:tr/x:td[2]/x:table/x:tbody/x:tr[3]/x:td/x:table[1]/x:tbody/x:tr/x:td/x:table/x:tbody/x:tr/x:td[2]/x:table[5]/x:tbody/x:tr/x:td").links()
					.all());
			// 文章页
		} else {
			page.putField("title",
					page.getHtml().xpath("id('Title')"));
			page.putField(
					"content",
					page.getHtml()
					.regex("[\u4E00-\u9FA5]"));
			page.putField(
					"time",
					page.getHtml()
							.xpath("/x:html/x:body/x:table[1]/x:tbody/x:tr/x:td/x:div[5]")
							.regex("日期：\\w+"));
			page.putField(
					"author",
					page.getHtml()
							.xpath("/x:html/x:body/x:table[1]/x:tbody/x:tr/x:td/x:div[5]")
							.regex("来源：\\w+"));
			page.putField(
					"baseURL",
					page.getUrl());
		}
		
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new News()).addUrl("http://znjs.most.gov.cn/wasdemo/search?searchword=机床")
				.addPipeline(new ConsolePipeline()).run();
	}
}
