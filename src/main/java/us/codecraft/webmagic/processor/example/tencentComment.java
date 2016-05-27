package us.codecraft.webmagic.processor.example;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.pipeline.*;

/**
 * @author code4crafter@gmail.com <br>
 */
public class tencentComment implements PageProcessor {

	public static final String URL_LIST = "https://www\\.sogou\\.com/sogou\\?site=news\\.qq\\.com&query=。*";

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
					.xpath("//div[@id='main']").links()
					.regex("http://news\\.qq\\.com/a.*").all());
			page.addTargetRequests(page.getHtml().xpath("//div[@id='pagebar_container']")
					.links().all());
			page.setSkip(true);
			// 文章页
		} else {
			String temp = page.getHtml().xpath("//div[@id='ArticleCnt']/tidyText()").toString();
			if(temp==null)
				temp=page.getHtml().xpath("//div[@class='bd']/allText()").toString();
//			if (temp.indexOf("发布时间") != -1) {
//				temp = temp.substring(temp.indexOf("发布时间") + 5,
//						temp.indexOf("发布时间") + 15);
//				page.putField("time", temp);
//			} else {
//				page.setSkip(true);
//				return;
//			}
			page.putField("title", page.getHtml().xpath("title/text()"));

			page.putField("content", temp);

//			temp = page.getHtml().xpath("body/tidyText()").toString();
//			if (temp.indexOf("来源") != -1
//					&& (temp.indexOf("】", temp.indexOf("来源") + 3) - (temp
//							.indexOf("来源") + 3)) < 10) {
//				temp = temp.substring(temp.indexOf("来源") + 3,
//						temp.indexOf("】", temp.indexOf("来源") + 3));
//				page.putField("author", temp);
//			}

			page.putField("baseURL", page.getUrl());
			page.putField("type", "评论");
		}

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new tencentComment())
				.addUrl("https://www.sogou.com/sogou?site=news.qq.com&query=%B9%AB%B3%B5%B8%C4%B8%EF&pid=sogou-wsse-b58ac8403eb9cf17-0004")
				.addPipeline(new MysqlPipeline())
				.addPipeline(new ConsolePipeline())
				.run();
	}
}
