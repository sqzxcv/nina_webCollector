package com.nina.webCollector.webCollector;

//import com.csvreader.CsvWriter;


import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import com.nina.webCollector.LLHelper.DateUtil;
import com.nina.webCollector.LLHelper.MYSQLManager;
import com.nina.webCollector.mapper.DocumentMapper;
import com.nina.webCollector.model.DocumentWithBLOBs;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.Statement;

/**
 * Created by shengqiang on 2017/7/9.
 */

public class LLWebCollector {

    @Autowired
    private DocumentMapper docMapper;
    private MYSQLManager mysqlManager = null;

    public static class SingletonHolder {
        private static final LLWebCollector INSTANCE = new LLWebCollector();
    }

    private LLWebCollector() {

        this.mysqlManager = new MYSQLManager();
    }

    public static final LLWebCollector getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addInstantJobWithURL(String url) {


        News news = null;
        try {
            news = ContentExtractor.getNewsByUrl(url);
//            this.mysqlManager.getDefaultDBConnection();
//            Statement statement =  this.mysqlManager.getStatement();
//            int result = this.mysqlManager.create( statement, String.format("insert into document(url,content,news_time,contentHtml,title) values('%s','%s',%d,'%s','%s')",
//                    news.getUrl(),news.getContent(),(int)(long) DateUtil.date2TimeStamp(news.getTime(), "yyyy-MM-dd HH:mm:ss"),
//                    news.getContentElement().html(),news.getTitle()));
//            if (result != 0) {
//                System.out.format("文章[%s]插入成功",news.getTitle());
//            } else {
//                System.out.format("文章[%s]插入失败",news.getTitle());
//            }

//
            DocumentWithBLOBs doc = new DocumentWithBLOBs();
            doc.setNewsTime((int)(long) DateUtil.date2TimeStamp(news.getTime(), "yyyy-MM-dd HH:mm:ss"));
            doc.setTitle(news.getTitle());
            doc.setUrl(news.getUrl());
            doc.setContent(news.getContent());
            doc.setContenthtml(news.getContentElement().html());
            this.docMapper.insert(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(news);
    }
}
