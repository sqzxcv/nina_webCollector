package com.nina.webCollector.webCollector;

//import com.csvreader.CsvWriter;


import cn.edu.hfut.dmic.contentextractor.ContentExtractor;
import cn.edu.hfut.dmic.contentextractor.News;
import com.nina.webCollector.LLHelper.DateUtil;
import com.nina.webCollector.LLHelper.MYSQLManager;
import com.nina.webCollector.mapper.DocumentMapper;
import com.nina.webCollector.model.DocumentWithBLOBs;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by shengqiang on 2017/7/9.
 */

//@SpringBootApplication
@MapperScan("com.nina.webCollector.mapper")
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

    public News addInstantJobWithURL(String url) {


        News news = null;
        try {
            news = ContentExtractor.getNewsByUrl(url);
            this.mysqlManager.saveNews2DB(news);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(news);
        return news;
    }

    public void addJob2MonitorWebSiteNews(String url) {

        try {
            LLMonitorWebsiteNews monitorWebsite = new LLMonitorWebsiteNews("crawler", true);
            monitorWebsite.addSeed(url);
            monitorWebsite.addRegex(String.format("%s/*",url));
            /*do not fetch jg|png|gif*/
            monitorWebsite.addRegex("-.*\\.(jpg|png|gif).*");
            monitorWebsite.setThreads(30);
            monitorWebsite.setTopN(100);//(500000000);
//            monitorWebsite.setResumable(true);
            monitorWebsite.start(5);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
