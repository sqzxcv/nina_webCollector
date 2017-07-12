package com.nina.webCollector.webCollector;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Date;

/**
 * Created by shengqiang on 2017/7/11.
 */
public class LLQuartzJob implements Job {

    public LLQuartzJob(String simpleTrigger, String triggerGroup1) {

    }

    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            System.out.println("In SimpleQuartzJob - executing its JOB at "
                    + new Date() + " by " + context.getScheduler().getSchedulerName());
        } catch (Exception e) {

        }
    }
}
