package com.nina.webCollector.webCollector;

import java.io.FileNotFoundException;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;

import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import com.nina.webCollector.model.*;
import com.nina.webCollector.model.*;
import com.nina.webCollector.mapper.*;
import com.nina.webCollector.LLHelper.*;

import javax.swing.text.html.parser.DocumentParser;

/**
 * Created by shengqiang on 2017/7/9.
 */
public class MonitorWebsiteNews extends BreadthCrawler {

    private DocumentMapper docMapper ;
    private TagmapMapper tagMapper ;

    /**
     * 重写构造函数
     * @param crawlPath 爬虫路径
     * @param autoParse 是否自动解析
     */
    public MonitorWebsiteNews(String crawlPath, boolean autoParse) throws FileNotFoundException {
        super(crawlPath, autoParse);
        // 逗号进行分割，字符编码为GBK

    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        // 继承覆盖visit方法，该方法表示在每个页面进行的操作
        // 参数page和next分别表示当前页面和下个URL对象的地址

        News news = null;
        try {
            news = ContentExtractor.getNewsByHtml(page.html(), page.url());
            DocumentWithBLOBs doc = new DocumentWithBLOBs();
            doc.setNewsTime((int)(long)DateUtil.date2TimeStamp(news.getTime(), "yyyy-MM-dd HH:mm:ss"));
            doc.setTitle(news.getTitle());
            doc.setUrl(news.getUrl());
            doc.setContent(news.getContent());
            doc.setContenthtml(news.getContentElement().html());

            this.docMapper.insert(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(news);


    }
}
