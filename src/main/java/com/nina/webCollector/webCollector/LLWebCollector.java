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
import org.quartz.CronExpression;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;
import java.util.Date;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleTrigger;
import org.quartz.impl.StdSchedulerFactory;
import com.nina.webCollector.webCollector.LLQuartzJob;

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

    public void addInstantJobWithURL(String url) {


        News news = null;
        try {
            news = ContentExtractor.getNewsByUrl(url);
            this.mysqlManager.getDefaultDBConnection();
            this.mysqlManager.getStatement();
            int result = this.mysqlManager.create(String.format("insert into document(url,content,news_time,contentHtml,title) values('%s','%s',%d,'%s','%s')",
                    news.getUrl(),news.getContent(),(int)(long) DateUtil.date2TimeStamp(news.getTime(), "yyyy-MM-dd HH:mm:ss"),
                    news.getContentElement().html(),news.getTitle()));
            if (result != 0) {
                System.out.format("文章[%s]插入成功",news.getTitle());
            } else {
                System.out.format("文章[%s]插入失败",news.getTitle());
            }
            this.mysqlManager.closeStatement();
            this.mysqlManager.closeConnection();
//
//            DocumentWithBLOBs doc = new DocumentWithBLOBs();
//            //doc.setNewsTime((int)(long) DateUtil.date2TimeStamp(news.getTime(), "yyyy-MM-dd HH:mm:ss"));
//            doc.setTitle(news.getTitle());
//            doc.setUrl(news.getUrl());
//            doc.setContent(news.getContent());
//            doc.setContenthtml(news.getContentElement().html());
//            this.docMapper.insert(doc);

        } catch (Exception e) {
            e.printStackTrace();
        }
//        System.out.println(news);
    }

    public void addJob2MonitorWebSiteNews(String url) {

        //初始化一个Schedule工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        //通过schedule工厂类获得一个Scheduler类
        Scheduler scheduler = schedulerFactory.getScheduler();
        //通过设置job name, job group, and executable job class初始化一个JobDetail
        JobDetail jobDetail = new JobDetail("jobDetail-s1","jobDetailGroup-s1", SimpleQuartzJob.class);
        //设置触发器名称和触发器所属的组名初始化一个触发器
        SimpleTrigger simpleTrigger = new LLQuartzJob("simpleTrigger","triggerGroup1");
        //获取当前时间，初始化触发器的开始日期
        long ctime = System.currentTimeMillis();
        simpleTrigger.setStartTime(new Date(ctime));
        //设置触发器触发运行的时间间隔(10 seconds here)
        simpleTrigger.setRepeatInterval(10000);
        //设置触发器触发运行的次数，这里设置运行100，完成后推出
        simpleTrigger.setRepeatCount(100);
        /**
         * set the ending time of this job.
         * We set it for 60 seconds from its startup time here
         * Even if we set its repeat count to 10,
         * this will stop its process after 6 repeats as it gets it endtime by then.
         * **/
        // simpleTrigger.setEndTime(new Date(ctime + 60000L));
        //设置触发器的优先级，模式为5
        // simpleTrigger.setPriority(10);
        //交给调度器调度运行JobDetail和Trigger
        scheduler.scheduleJob(jobDetail, simpleTrigger);
        //启动调度器
        scheduler.start();
    }
}
