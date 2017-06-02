package webmagic;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class GithubRepoPageProcessor implements PageProcessor {

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);
	
	private File f;
	
	private FileWriter fw;
	
	public GithubRepoPageProcessor() throws IOException{
		f = new File("d:/shencai.html");
		fw = new FileWriter(f, true);
	}

	@Override
	public void process(Page page) {
		
		List<String> contents = page.getHtml().css("div.d_post_content").all();
		
		for(String c:contents){
			try {
				fw.write(c);
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		page.addTargetRequests(page.getHtml().links().regex("/p/4969064487\\?pn=\\d").all());
	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) throws IOException {
		Spider.create(new GithubRepoPageProcessor()).addUrl("https://tieba.baidu.com/p/4969064487").thread(5).run();
	}
}
