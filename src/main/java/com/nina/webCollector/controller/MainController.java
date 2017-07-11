package com.nina.webCollector.controller;

import com.nina.webCollector.webCollector.LLWebCollector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

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
    public void test(String url,HttpServletResponse response){
//
//        String str = HttpClientUtil.doGet("http://www.hao123.com");
//
//        //System.out.println("----"+str);
//
//
//        //测试返回json
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("test", "11");
//        String json = JSON.toJSONString(map);
//        response.setContentType("text/html;charset=utf-8");
//        try{
//
//            response.getWriter().write(json);
//            response.getWriter().flush();
//            response.getWriter().close();
//        }catch(Exception e){
//            e.printStackTrace();
//        }

        LLWebCollector.getInstance().addInstantJobWithURL("http://www.mutangxiaxia.com/2017/06/29/untitled-1498707091378/");
    }

//    // 用户管理
//    @RequestMapping(value = "/users", method = RequestMethod.GET)
//    public String users(ModelMap modelMap){
//        // 找到user表里面的所有记录
//        List<UserEntity> userEntityList = userRepository.findAll();
//
//        // 将所有的记录传递给返回的jsp页面
//        modelMap.addAttribute("userList", userEntityList);
//
//        // 返回pages目录下的userManage.jsp
//        return "userManage";
//    }
//
//    // 添加用户表单页面
//    @RequestMapping(value = "/addUser", method = RequestMethod.GET)
//    public String addUser(){
//        return "addUser";
//    }
//
//    // 添加用户处理
//    @RequestMapping(value = "/addUserPost", method = RequestMethod.POST)
//    public String addUserPost(@ModelAttribute("user") UserEntity userEntity){
//        // 向数据库添加一个用户
//        //userRepository.save(userEntity);
//
//        // 向数据库添加一个用户，并将内存中缓存区的数据刷新，立即写入数据库，之后才可以进行访问读取
//        userRepository.saveAndFlush(userEntity);
//
//        // 返回重定向页面
//        return "redirect:/users";
//    }
//
//    // 查看用户详细信息
//    // @PathVariable可以收集url中的变量，需匹配的变量用{}括起来
//    // 例如：访问 localhost:8080/showUser/1 ，将匹配 userId = 1
//    @RequestMapping(value = "/showUser/{userId}", method = RequestMethod.GET)
//    public String showUser( @PathVariable("userId") Integer userId, ModelMap modelMap ){
//        UserEntity userEntity = userRepository.findOne(userId);
//        modelMap.addAttribute("user", userEntity);
//        return "userDetail";
//    }
//
//    // 更新用户信息页面
//    @RequestMapping(value = "/updateUser/{userId}", method = RequestMethod.GET)
//    public String updateUser(@PathVariable("userId") Integer userId, ModelMap modelMap){
//        UserEntity userEntity = userRepository.findOne(userId);
//        modelMap.addAttribute("user", userEntity);
//        return "updateUser";
//    }
//    // 处理用户修改请求
//    @RequestMapping(value = "/updateUserPost", method = RequestMethod.POST)
//    public String updateUserPost(@ModelAttribute("user") UserEntity userEntity){
//        userRepository.updateUser(
//                userEntity.getFirstName(),
//                userEntity.getLastName(),
//                userEntity.getPassword(),
//                userEntity.getId()
//        );
//        return "redirect:/users";
//    }
//
//    // 删除用户
//    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
//    public String deleteUser(@PathVariable("userId") Integer userId){
//        // 删除id为userId的用户
//        userRepository.delete(userId);
//        // 立即刷新数据库
//        userRepository.flush();
//        return "redirect:/users";
//    }
}