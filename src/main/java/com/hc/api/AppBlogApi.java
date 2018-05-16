package com.hc.api;

import com.hc.entity.*;
import com.hc.service.BlogService;
import com.hc.service.BlogTypeService;
import com.hc.service.MyBlogService;
import com.hc.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by hc on 2017/3/11.
 */
@Controller
@RequestMapping("/api/blog")
public class AppBlogApi extends BaseController {

    @Autowired
    MyBlogService myBlogService;
    @Autowired
    BlogService blogService;
    @Autowired
    BlogTypeService blogTypeService;


    /**
     * 分页查询博客
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Object list(@RequestBody SearchVo searchVo){
        PageBean result = myBlogService.list(searchVo);
        return ajaxSuccess(result);
    }

    /**
     * 搜索Lucene
     */
    @RequestMapping("search")
    @ResponseBody
    public Object search(@RequestBody SearchVo searchVo)throws Exception{
        PageBean search = myBlogService.search(searchVo.getKeyWord(), searchVo.getPage());
        return ajaxSuccess(search);
    }
    /**
     * 获取记录总数
     */
    @RequestMapping("/getTotal")
    @ResponseBody
    public Object getTotal(){
        Long total = blogService.getTotal(null);
        return ajaxSuccess(total);
    }
    /**
     * 根据id查询文章详情
     */
    @RequestMapping("/get/{id}")
    @ResponseBody
    public Object get(@PathVariable("id") Integer id){
        myBlogService.VistorCount();
        Blog blog = blogService.findById(id);
        return blog;
    }

    /**
     * 获取上一篇 下一篇
     */
    @RequestMapping("/next_and_previous/{id}")
    @ResponseBody
    public Object next_and_previous(@PathVariable("id") Integer id){
        Map<String, Blog> lastAndPrivious = myBlogService.getLastAndPrivious(id);
        return lastAndPrivious;
    }

    /**
     * 按月份分类
     */
    @RequestMapping("getByTime")
    @ResponseBody
    public Object getByTime(){
        return ajaxSuccess(myBlogService.getByTime());
    }

    /**
     * 按类别分类
     */
    @RequestMapping("/getAllType")
    @ResponseBody
    public Object getAllType(){
        List<BlogType> blogTypes =blogTypeService.countList();
        return ajaxSuccess(blogTypes);
    }
    /**
     *按照阅读量查询文章
     */
    @RequestMapping("/listByReadTime/{page}")
    @ResponseBody
    public Object listByReadTime(@PathVariable("page") String page){
        PageBean bean = myBlogService.listByReadTime(Integer.parseInt(page));
        return ajaxSuccess(bean);
    }

    /**
     * 获取轮播图
     */
    @RequestMapping("/banner")
    @ResponseBody
    public Object banner(){
        return ajaxSuccess(myBlogService.getBanner());
    }


    @RequestMapping("/count")
    @ResponseBody
    public Object getClickCount(){
        Map<String, Integer> clickCount = myBlogService.getModuleClick();
        return ajaxSuccess(clickCount);
    }

    @RequestMapping("/getEachBlogClick")
    @ResponseBody
    public Object getEachBlogClick(){
        List<Blog> eachBlogClick = myBlogService.getEachBlogClick();
        return ajaxSuccess(eachBlogClick);
    }

    @RequestMapping("/searchWordCount")
    @ResponseBody
    public Object searchWordCount(){
        List<SearchWord> searchWords = myBlogService.searchWordlist();
        return ajaxSuccess(searchWords);
    }

    @RequestMapping("/systemInfo")
    @ResponseBody
    public Object index(HttpServletRequest request) {
        Map<String, Object> stringObjectMap = pushSystemStatus(request);
        return ajaxSuccess(stringObjectMap);
    }

    private Map<String,Object> pushSystemStatus(HttpServletRequest request) {
        Map<String,Object> sysInfo = new HashMap<String,Object>();
        float freeMemory = (float) Runtime.getRuntime().freeMemory();
        float totalMemory = (float) Runtime.getRuntime().totalMemory();
        float usedMemory = (totalMemory - freeMemory);
        float memPercent = Math.round(freeMemory / totalMemory * 100) ;
        String os = System.getProperty("os.name");
        String javaVersion = System.getProperty("java.version");

        sysInfo.put("totalMemory", totalMemory / 1024 / 1024);
        sysInfo.put("usedMemory", usedMemory / 1024 / 1024);
        sysInfo.put("memPercent", memPercent);
        sysInfo.put("os", os);
        sysInfo.put("javaVersion", javaVersion);
        return sysInfo;
    }

    /**
     * 获取最近游客访问记录统计
     */
    @RequestMapping("/getVisitor/{day}")
    @ResponseBody
    public Object getVisitor(@PathVariable String day) {
        List<VisitorRecord> record = myBlogService.getRecord(Integer.parseInt(day));
        return ajaxSuccess(record);
    }

    /**
     * 获取最近登录记录
     */
    @RequestMapping("/loginRecode/{count}")
    @ResponseBody
    public Object loginRecode(@PathVariable String count) {
        List<LoginRecord> loginRecords = myBlogService.getLoginRecords(Integer.parseInt(count));
        return ajaxSuccess(loginRecords);
    }

    @RequestMapping("/musicList")
    @ResponseBody
    public Object musicList() {
        List<Music> musicList = myBlogService.MusicList();
        return ajaxSuccess(musicList);
    }

    @RequestMapping("/cus")
    @ResponseBody
    public Object cus() {
        Map<String,Object> result = new HashMap<>();
        result.put("success",true);
        return ajaxSuccessReturnMap(result);
    }

}
