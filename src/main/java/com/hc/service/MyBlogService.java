package com.hc.service;

import com.hc.entity.*;
import com.hc.lucene.BlogIndex;
import com.hc.mapper.*;
import com.hc.util.BlogUtil;
import com.hc.util.DateUtil;
import com.hc.util.RESUtil;
import com.hc.util.StringUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by hc on 2017/3/11.
 */
@Service
public class MyBlogService {
    @Autowired
    BlogMapper blogMapper;
    @Autowired
    SearchWordMapper searchWordMapper;
    @Autowired
    VisitorRecordMapper visitorRecordMapper;
    @Autowired
    LoginRecordMapper loginRecordMapper;
    @Autowired
    MusicMapper musicMapper;

    private BlogIndex blogIndex=new BlogIndex();

    public List<Blog> getByTime(){
        return  blogMapper.countList();
    }

    /**
     * 获取博客列表 带分页
     */
    public PageBean list(SearchVo search){
        Integer page=1;
        if(search.getPage() != null){
            page=search.getPage();
        }

        PageBean pageBean=new PageBean(page,10);
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        if(search.getBlogType() != null){
             map.put("typeId",search.getBlogType());
        }
        if(search.getReleaseDateStr() != null && StringUtil.isNotEmpty(search.getReleaseDateStr())){
             map.put("releaseDateStr",search.getReleaseDateStr());
        }
        List<Blog> list = blogMapper.list(map);
        for(Blog blog:list){
            BlogUtil.fillBlogImg(blog,"jpg");
        }

        for(Blog blog:list){
            BlogUtil.fillBlogImg(blog,"png");
        }

        pageBean.setResultSize(list.size());
        pageBean.setResults(list);
        return pageBean;
    }

    /**
     * lucene搜索分词
     */
    public PageBean search(String word,Integer page)throws Exception{
        if(page<=0 || page == null){
            page=1;
        }
        List<Blog> blogList=blogIndex.searchBlog(word.trim());
        PageBean pageBean = new PageBean(page,10);
        pageBean.setResultSize(blogList.size());
        pageBean.setResults(blogList);
        return pageBean;
    }

    /**
     *按照阅读量查询博客
     */
    public PageBean listByReadTime(Integer page){
        if(page<=0){
            page=1;
        }
        PageBean pageBean=new PageBean(page,10);
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("start", pageBean.getStart());
        map.put("size", pageBean.getPageSize());
        List<Blog> list = blogMapper.getBlogByClickHitTime(map);
        pageBean.setResultSize(list.size());
        pageBean.setResults(list);
        return pageBean;
    }

    /**
     * 获取上一篇 下一篇
     * @param id
     * @return
     */
    public Map<String,Blog> getLastAndPrivious(int id){
        Blog last = blogMapper.getLastBlog(id);
        Blog next = blogMapper.getNextBlog(id);
        Map<String,Blog> r = new HashMap<>();
        r.put("last",last);
        r.put("next",next);
        return r;
    }

    /**
     * 获取轮播图
     */
    public List<String> getBanner(){
        String imgs = RESUtil.getString("img");
        String[] imgArr=imgs.split("\\+");
        List<String> list = Arrays.asList(imgArr);
        return list;
    }

    /**
     *统计每个模块的阅读人数 算法实现
     */
    public Map<String,Integer> getClickCount(){
        List<Blog> blogs = blogMapper.selectByCountClick();
        List<String> result=new ArrayList<String>();
        for (int i = 0; i <blogs.size() ; i++) {
            result.add(blogs.get(i).getBlogType().getTypeName()+"#"+blogs.get(i).getClickHit());
        }
        for (int i=0;i<result.size()-1;i++){
            for(int j=i+1;j<result.size();j++){
                if(result.get(i).split("\\#")[0].equals(result.get(j).split("\\#")[0])){
                    result.set(i,result.get(i).split("\\#")[0]+"#"+(Integer.parseInt(result.get(i).split("\\#")[1])+Integer.parseInt(result.get(j).split("\\#")[1])));
                    result.remove(j);
                    j=i;
                }
            }
        }
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (int i = 0; i <result.size() ; i++) {
            map.put(result.get(i).split("\\#")[0],Integer.parseInt(result.get(i).split("\\#")[1]));
        }
        return map;
    }

    /**
     * 统计每个模块的阅读人数 废弃
     * @return
     */
    public Map<String,Integer> getClickCountFromDB(){
        List<Blog> blogs = blogMapper.countSearchWord();

        Map<String,Integer> map = new HashMap<String, Integer>();
        for (Blog blog : blogs) {
            map.put(blog.getKeyWord(),blog.getClickHit());
        }
        return map;
    }

    public Map<String,Integer> getModuleClick(){
        List<Blog> blogs = blogMapper.countModuleClick();
        Map<String,Integer> map = new HashMap<String, Integer>();
        for (Blog blog : blogs) {
            map.put(blog.getTitle(),blog.getClickHit());
        }
        return map;
    }

    public List<Blog> getEachBlogClick(){
        return blogMapper.selectByCountClick();
    }

    /**
     * 关键词
     */
    public List<SearchWord> searchWordlist(){
        return searchWordMapper.list();
    }

    public void insertOrUpdate(SearchWord searchWord){
        SearchWord old = searchWordMapper.findByWord(searchWord.getWord());
        if(old!=null){
            if(searchWord.getWord().equals(old.getWord())){
                searchWord.setCount((old.getCount()+1));
                searchWord.setId(old.getId());
                searchWordMapper.update(searchWord);
            }
        }else{
            searchWord.setCount(1);
            searchWordMapper.insert(searchWord);
        }
    }


    /**
     * 每天第一次访问计数器为1，当天中随后每次访问加1
     */
    public void VistorCount(){
        Date now = new Date();
        String nowDateStr = DateUtil.formatDate(now, "yyyy-MM-dd");
        List<VisitorRecord> list = visitorRecordMapper.list(null);
        Integer idIndex=null;
        for (VisitorRecord record : list) {
            if(DateUtil.formatDate(record.getDate(),"yyyy-MM-dd").equals(nowDateStr)){
                idIndex=record.getId();
                System.out.println(record.getId());
                VisitorRecord forUpdate= visitorRecordMapper.getById(record.getId());
                forUpdate.setCount(record.getCount()+1);
                visitorRecordMapper.update(forUpdate);
            }
        }
        if(idIndex==null){
            VisitorRecord forInsert=new VisitorRecord();
            forInsert.setCount(1);
            forInsert.setDate(new Date());
            visitorRecordMapper.insert(forInsert);
        }
    }

    /**
     * 获取最近游客访问次数
     * @param day
     * @return
     */
    public List<VisitorRecord> getRecord(int day){
        Map<String,Object> map = new HashMap<>();
        int max=visitorRecordMapper.count();
        int lastOfRecord=day;
        if(day>=max){
            lastOfRecord=max;
        }
        map.put("start",max-lastOfRecord);
        map.put("size",lastOfRecord);
        List<VisitorRecord> list = visitorRecordMapper.list(map);
        return list;
    }

    /**
     * 获取登录记录
     */
    public List<LoginRecord> getLoginRecords(int time){
        Map<String,Object> map = new HashMap<>();
        int max=loginRecordMapper.count();
        int lastOfRecord=time;
        if(time>=max){
            lastOfRecord=max;
        }
        map.put("start",max-lastOfRecord);
        map.put("size",lastOfRecord);
        List<LoginRecord> list = loginRecordMapper.list(map);
        return list;
    }

    /**
     * 产生一条登录记录
     * @param request
     * @param status
     * @param loginName
     */
    public void createOneLoginRecord(HttpServletRequest request,String status,String loginName){
        String ip = request.getRemoteAddr();
        LoginRecord loginRecord=new LoginRecord();
        loginRecord.setStatus(status);
        loginRecord.setIp(ip);
        loginRecord.setLoginName(loginName);
        loginRecord.setDate(new Date());
        loginRecordMapper.insert(loginRecord);
    }


    public List<Music> MusicList(){
        return musicMapper.list();
    }


}
