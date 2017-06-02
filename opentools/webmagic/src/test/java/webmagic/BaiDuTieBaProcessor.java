package webmagic;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.jsoup.Jsoup;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.xsoup.Xsoup;

public class BaiDuTieBaProcessor implements PageProcessor {
	
	/*
	 * ����ͼƬ
	 */

	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

	@Override
	public void process(Page page) {

		List<String> contents = page.getHtml().css("div.d_post_content").$("img").all();
		for(String image:contents){
			String src = Xsoup.compile("//img/@src").evaluate(Jsoup.parse(image)).get();
			try {
				download(src, System.currentTimeMillis() + ".jpg", "d:/shencaiimg");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		page.addTargetRequests(page.getHtml().links().regex("/p/4969064487\\?pn=\\d").all());

	}

	@Override
	public Site getSite() {
		return site;
	}

	public static void main(String[] args) {
		Spider.create(new BaiDuTieBaProcessor()).addUrl("https://tieba.baidu.com/p/4969064487").thread(5).run();
	}
	
	public static void download(String urlString, String filename,String savePath) throws Exception {
	    // ����URL
	    URL url = new URL(urlString);
	    // ������
	    URLConnection con = url.openConnection();
	    //��������ʱΪ5s
	    con.setConnectTimeout(5*1000);
	    // ������
	    InputStream is = con.getInputStream();
	
	    // 1K�����ݻ���
	    byte[] bs = new byte[1024];
	    // ��ȡ�������ݳ���
	    int len;
	    // ������ļ���
	   File sf=new File(savePath);
	   if(!sf.exists()){
		   sf.mkdirs();
	   }
	   OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
	    // ��ʼ��ȡ
	    while ((len = is.read(bs)) != -1) {
	      os.write(bs, 0, len);
	    }
	    // ��ϣ��ر���������
	    os.close();
	    is.close();
	}

}
