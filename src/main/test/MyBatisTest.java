
import com.hc.entity.Blog;
import com.hc.entity.Link;
import com.hc.entity.VisitorRecord;
import com.hc.mapper.BlogMapper;
import com.hc.mapper.LinkMapper;
import com.hc.mapper.VisitorRecordMapper;
import com.hc.util.DateUtil;

import java.util.*;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by hc on 2017/4/3.
 */
public class MyBatisTest {

    public SqlSession getSession(){
        ApplicationContext ioc = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
        SqlSessionFactory sqlSession = (SqlSessionFactory) ioc.getBean("sqlSessionFactory");
        return  sqlSession.openSession();
    }

    @org.junit.Test
    public void test(){
        SqlSession session = getSession();
        LinkMapper linkMapper = session.getMapper(LinkMapper.class);
        Link link = new Link(66,"何超","http://www.66super.com",66); //Integer id, String linkName, String linkUrl, Integer orderNo
        linkMapper.add(link);
        session.commit();
    }

    @org.junit.Test
    public void testBlog(){
        SqlSession sessiion = getSession();
        BlogMapper blogMapper = sessiion.getMapper(BlogMapper.class);
        Blog blog= new Blog();
        blog.setId(999);
        blog.setReleaseDate(new Date());
        blog.setContent("测试");
        blogMapper.add(blog);
        sessiion.commit();
    }

    @org.junit.Test
    public void testGetDateYYYY_MM_DD(){
        Date date = new Date();
        String s = DateUtil.formatDate(date, "yyyy-MM-dd");
        System.out.println(s);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = null;
        try {
            parse = sdf.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(parse);
        //System.out.println(DateUtil.formatDateToDate(date,"yyyy-MM-dd"));
    }

    /**
     * 生成访问记录120天
     */
    @Test
    public void gentVisitorRecord(){
        SqlSession session = getSession();
        VisitorRecordMapper vistorMapper = session.getMapper(VisitorRecordMapper.class);
        VisitorRecord v=null;
        for (int i = 120; i >0 ; i--) {
            VisitorRecord vistor=new VisitorRecord();
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DAY_OF_YEAR,-i);
            Date d=cal.getTime();
            System.out.println(DateUtil.formatDate(d,"yyyy-MM-dd"));
            v=new VisitorRecord();
            v.setCount((int)(Math.random()*100));
            v.setDate(d);
            vistorMapper.insert(v);
            session.commit();
        }
    }

    @Test
    public void getRecord(){
        SqlSession session = getSession();
        VisitorRecordMapper vistorMapper = session.getMapper(VisitorRecordMapper.class);
        Map<String,Object> map = new HashMap<String,Object>();
        int max=vistorMapper.count();
        int lastOfRecord=30;
        map.put("start",max-lastOfRecord);
        map.put("size",lastOfRecord);
        List<VisitorRecord> list = vistorMapper.list(map);
        for (VisitorRecord record : list) {
            System.out.println("id-"+record.getId()+" count "+record.getCount()+" day "+DateUtil.formatDate(record.getDate(),"yyyy-MM-dd"));
        }
        session.commit();
    }

    /**
     * 测试getByXXX
     */
    @Test
    public void testGetById(){
        SqlSession session = getSession();
        VisitorRecordMapper vistorMapper = session.getMapper(VisitorRecordMapper.class);
        VisitorRecord byId = vistorMapper.getById(2);
        System.out.println("id "+byId.getId()+" -- count "+byId.getCount());
        session.commit();

    }

    @Test
    public void update(){
        SqlSession session = getSession();
        VisitorRecordMapper vistorMapper = session.getMapper(VisitorRecordMapper.class);
        VisitorRecord byId = vistorMapper.getById(121);
        byId.setCount(111);
        vistorMapper.update(byId);
        session.commit();
    }


    /**
     * 访问计数器
     */
    @Test
    public void testGetByDate(){
        SqlSession session = getSession();
        VisitorRecordMapper vistorMapper = session.getMapper(VisitorRecordMapper.class);
        Date now = new Date();
        String nowDateStr = DateUtil.formatDate(now, "yyyy-MM-dd");
        List<VisitorRecord> list = vistorMapper.list(null);
        Integer idIndex=null;
        System.out.println("size--"+list.size());
        for (VisitorRecord record : list) {
            if(DateUtil.formatDate(record.getDate(),"yyyy-MM-dd").equals(nowDateStr)){
                idIndex=record.getId();
                System.out.println(record.getId());
                VisitorRecord forUpdate= vistorMapper.getById(record.getId());
                forUpdate.setCount(record.getCount()+1);
                vistorMapper.update(forUpdate);
                System.out.println("update----------------------------------");
            }
        }
        if(idIndex==null){
            System.out.println("insert---------------------------------");
            VisitorRecord forInsert=new VisitorRecord();
            forInsert.setCount(1);
            forInsert.setDate(new Date());
            vistorMapper.insert(forInsert);
        }
        session.commit();
    }

    @Test
    public void testCount(){
        SqlSession session = getSession();
        VisitorRecordMapper vistorMapper = session.getMapper(VisitorRecordMapper.class);
        System.out.println(vistorMapper.count());
        System.out.println(vistorMapper.countAllVisitor());
    }
}
