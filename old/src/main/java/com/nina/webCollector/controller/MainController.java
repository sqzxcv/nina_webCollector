package com.nina.webCollector.controller;

import com.nina.webCollector.webCollector.LLWebCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashMap;
import java.util.Map;
import cn.edu.hfut.dmic.contentextractor.News;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.zero.common.util.http.HttpClientUtil;

/**
 * Created by sjj on 2015/10/24 0024.
 */
@Controller
public class MainController {


    // 首页
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }


    @RequestMapping(value="/presedocument")
    public void presedocument(String url, HttpServletRequest request,HttpServletResponse response){

        News news = LLWebCollector.getInstance().addInstantJobWithURL(url);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("url", news.getUrl());
        map.put("content", news.getContent());
        map.put("news_times", news.getTime());
        map.put("title", news.getTitle());
        String json = JSON.toJSONString(map);
        response.setContentType("text/html;charset=utf-8");
        try{

            response.getWriter().write(json);
            response.getWriter().flush();
            response.getWriter().close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping(value="/monitorwebsite")
    public void monitorWebsite(String url, HttpServletResponse response) {

        LLWebCollector.getInstance().addJob2MonitorWebSiteNews(url);
    }

}