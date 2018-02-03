package com.hc.util;

import com.hc.entity.Blog;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hc on 2017/3/24.
 */


/**jsoup解析标签中img标签并填充到Blog中的imgList
 * jsoup解析html
 * @param
 * @param
 * @return
 */
public class BlogUtil {
    public static Blog fillBlogImg(Blog blog, String type){

        List<String> list = new ArrayList<String>();
        String blogInfo=blog.getContent();
        Document doc= Jsoup.parse(blogInfo);
        Elements jpgs=doc.select("img[src$=."+type);
        for (int i = 0; i <jpgs.size() ; i++) {
            Element jpg=jpgs.get(i);
            list.add(jpg.toString());
        }
        blog.setImagesList(list);
        return blog;
    }
}
