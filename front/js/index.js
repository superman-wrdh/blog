$(document).ready(function () {
    p = 1
    var host = "http://www.66super.com"

    function load_index_list(page) {
        if (page === null) {
            page = 1
        }
        //获取文章列表
        $.ajax({
            url: host + '/api/blog/list.do',
            type: 'post',
            dataType: 'json',
            contentType: "application/json",
            data: JSON.stringify({
                "page": page
            }),
            async: false,
            success: function (r) {
                var res = r.data.results;
                if (res.length < 10) {
                    console.log('---没有数据了---')
                    $("#load_div").text(" 没 有 数 据 了 ")
                    setTimeout(function () {
                        $("#next_page").remove();
                    }, 2000);
                }
                for (var i = 0; i < res.length; i++) {
                    var img = res[i]['imagesList']
                    if (img.length == 0) {
                        img = "<img src=\"images/t01.jpg\"/>"
                    } else {
                        img = img[0]
                    }
                    var li = "<li>\n" +
                        "<h3 class=\"blogtitle\"><span><a href=\"/jstt/css3/\" title=\"css3\" target=\"_blank\"  class=\"classname\">个人博客</a></span><a href=\"info.html?bid=" + res[i]['id'] + "\" target=\"_blank\" >" + res[i]['title'] + "</a></h3>\n" +
                        "<div class=\"bloginfo\"><span class=\"blogpic\"><a href=\"/jstt/css3/2018-03-26/812.html\" title=\"超锅的博客v5\">" + img + "</a></span>\n" +
                        "<p>" + "<a href='info.html?bid=" + res[i]['id'] + "'" + " target='_blank'>" + res[i]['summary'] + "</a>" + "</p>\n" +
                        "</div>\n" +
                        "<div class=\"autor\"><span class=\"lm f_l\"></span><span class=\"dtime f_l\">" + res[i]['releaseDate'] + "</span><span class=\"viewnum f_l\">浏览数量（<a href=\"/\">+" + res[i]['clickHit'] + "</a>）</span><span class=\"f_r\"><a href=\"info.html?bid=" + res[i]['id'] + "\" class=\"more\">阅读原文>></a></span></div>\n" +
                        "<div class=\"line\"></div>\n" +
                        " </li>"
                    $('#art_list').append(li);
                }
            }
        });
    }

    load_index_list(p)

    // 获取阅读量排行前5的文章
    // http://www.66super.com/api/blog/listByReadTime/1.do
    $.ajax({
        url: host + '/api/blog/listByReadTime/1.do',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (r) {
            var res = r.data.results;
            res = res.slice(0, 5)
            for (var i = 0; i < res.length; i++) {

                var img = res[i]['imagesList']
                if (img.length == 0) {
                    img = "<img src=\"images/t01.jpg\"/>"
                } else {
                    img = img[0]
                }
                var li = "<li><b><a href=\"info.html?bid=" + res[i]['id'] + "\" target=\"_blank\">" + res[i]['title'] + "</a></b>\n" +
                    " <p><i><img src=\"images/t02.jpg\" /></i><a href=\"info.html?bid=" + res[i]['id'] + "\"" + " target=\"_blank\">" + res[i]['summary'].substring(0, 50) + "</a></p>\n" +
                    " </li>"
                $('#hot_list').append(li);
            }
        }
    });

    $("#next_page").click(function () {
        p++
        load_index_list(p)
    })

    $("#bt_search").click(function () {
        mysearch()
    })

    function mysearch() {
        var searchword=$("#searchword").val();
        if(searchword.trim()=="" ||searchword.trim()==""){
            alert("超锅提醒 关键字不能为空！请输入关键字搜索")
            //***************
            // $("#show_win").css("display","block")
            //*******************
            return false;
        }else{
            //ajax请求
            //清空原来的列表
            $("#art_list").children("li")[0].remove()

            //ajax 后台请求 获取数据

            //填充列表

            //若果后台返回列表为空 填充一个结果()

        }
    }

})