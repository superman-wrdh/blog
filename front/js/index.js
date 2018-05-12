$(document).ready(function () {
    $.ajax({
        url: 'http://www.66super.com/api/blog/list.do',
        type: 'post',
        dataType: 'json',
        contentType : "application/json",
        data:JSON.stringify({

        }),
        async: false,
        success: function (r) {
            var res = r.data.results;
            for(var i = 0;i<res.length;i++){
                console.log(i)
                var img = res[i]['imagesList']
                if(img.length == 0){
                    img = "<img src=\"images/t01.jpg\"/>"
                }else {
                    img = img[0]
                }
                var li = "<li>\n" +
                    "<h3 class=\"blogtitle\"><span><a href=\"/jstt/css3/\" title=\"css3\" target=\"_blank\"  class=\"classname\">个人博客</a></span><a href=\"info.html?bid="+res[i]['id']+"\" target=\"_blank\" >"+res[i]['title']+"</a></h3>\n" +
                    "<div class=\"bloginfo\"><span class=\"blogpic\"><a href=\"/jstt/css3/2018-03-26/812.html\" title=\"帝国cms 首页或者列表无图，不使用默认图片的方法\">"+img+"</a></span>\n" +
                    "<p>"+res[i]['summary']+"</p>\n" +
                    "</div>\n" +
                    "<div class=\"autor\"><span class=\"lm f_l\"></span><span class=\"dtime f_l\">"+res[i]['releaseDate']+"</span><span class=\"viewnum f_l\">浏览数量（<a href=\"/\">+"+res[i]['clickHit']+"</a>）</span><span class=\"f_r\"><a href=\"info.html?bid="+res[i]['id']+"\" class=\"more\">阅读原文>></a></span></div>\n" +
                    "<div class=\"line\"></div>\n" +
                    " </li>"
                $('#art_list').append(li);
            }

        }
    });
})