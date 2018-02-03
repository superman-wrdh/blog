package com.hc.api;

import com.hc.entity.Blog;
import com.hc.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by hc on 2017/3/5.
 */
@Controller
@RequestMapping("/test/api")
public class TestRestApi {
    @Autowired
    BlogService blogService;

    /**
     * 输入json
     * 输出json
     * /test/api/testJ2J.do
     */
    @RequestMapping("testJ2J.do")
    @ResponseBody
    public Object test(@RequestBody Man man){
        return man;
    }

    /**
     *
     * RestFul Style
     * OutPut Json
     * style    /test/api/get/102.do
     */
    @RequestMapping("/get/{id}")
    @ResponseBody
    public Object get(@PathVariable("id") Integer id){
        Blog blog = blogService.findById(id);
        return blog;
    }
}
class Man{
    String name;
    String age;

    public String getName() {
        return name;
    }

    public Man() {
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public Man(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
