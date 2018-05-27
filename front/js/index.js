$(document).ready(function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    var type_id = GetQueryString('type_id')
    var releaseDateStr = GetQueryString('releaseDateStr')
    var d_tmp = decodeURI(releaseDateStr)
    p = 1
    var host = "http://www.66super.com"

    function load_index_list(data, url, op_type) {
        if (data.page === null) {
            data.page = 1
        }
        //获取文章列表
        $.ajax({
            url: url,
            type: 'post',
            dataType: 'json',
            contentType: "application/json;charset=UTF-8",
            data: JSON.stringify(data),
            async: false,
            scriptCharset: 'utf-8',
            success: function (r) {

                var res = r.data.results;
                if (op_type == 'search') {
                    if (res.length > 0) {
                        $("#art_list").children("li").remove()
                        $('#art_list').append(" <li style='text-align: center;font-size: 19px'>关键词 " +
                            "<em style='color: red'>" + data.keyWord + "</em>" + " 共搜索到 " + "<em style='color: red'>" + res.length + "</em>" + " 条记录</li>");
                    } else {
                        $("#art_list").children("li").remove()
                        $('#art_list').append("<li style='text-align: center;font-size: 19px'>亲 关键词 " + "<em style='color:red;'>"
                            + data.keyWord + "</em>" + " 没有搜索到结果，请换个关键词试试</li>");
                    }

                }
                if (res.length < 10) {
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
                    if (res[i]['summary'] === undefined) {
                        res[i]['summary'] = res[i]['content']
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

    load_index_url = host + '/api/blog/list.do'
    load_index_list({
        "page": p
    }, load_index_url)

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
                var li = "<li><b><a href=\"info.html?bid=" + res[i]['id'] + "\" target=\"_blank\">" + res[i]['title'] +"("+res[i]['clickHit']+")"+ "</a></b>\n" +
                    " <p><i><img src=\"images/t02.jpg\" /></i><a href=\"info.html?bid=" + res[i]['id'] + "\"" + " target=\"_blank\">" + res[i]['summary'].substring(0, 50) + "</a></p>\n" +
                    " </li>"
                $('#hot_list').append(li);
            }
        }
    });

    $("#next_page").click(function () {
        p++
        load_index_list({
            "page": p
        }, load_index_url)
    })

    $("#bt_search").click(function () {
        mysearch()
    })

    function mysearch() {
        var searchword = $("#searchword").val();
        if (searchword.trim() == "" || searchword.trim() == "") {
            alert("超锅提醒 关键字不能为空！请输入关键字搜索")
            //***************
            // $("#show_win").css("display","block")
            //*******************
            return false;
        } else {
            //清空原来的列表
            $("#art_list").children("li").remove()

            //ajax 后台请求 获取数据
            p = 1
            search_url = host + '/api/blog/search.do'
            load_index_list({
                "page": p,
                "keyWord": searchword
            }, search_url, 'search')
        }
    }

    function worldcloud() {
        var myChart = echarts.init(document.getElementById('main'));
        option = {
            title: {
                text: '',
                link: 'https://www.baidu.com/s?wd=' + encodeURIComponent('ECharts'),
                x: 'center',
                textStyle: {
                    fontSize: 23
                }

            },
            backgroundColor: '#FFFFFF',
            tooltip: {
                show: true
            },
            toolbox: {
                feature: {
                    saveAsImage: {
                        iconStyle: {
                            normal: {
                                color: '#FFFFFF'
                            }
                        }
                    }
                }
            },
            series: [{
                name: '该词汇搜索次数',
                type: 'wordCloud',
                //size: ['9%', '99%'],
                sizeRange: [16, 56],
                //textRotation: [0, 45, 90, -45],
                rotationRange: [-45, 90],
                //shape: 'circle',
                textPadding: 0,
                autoSize: {
                    enable: true,
                    minSize: 16
                },
                textStyle: {
                    normal: {
                        color: function () {
                            return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                        }
                    },
                    emphasis: {
                        shadowBlur: 50,
                        shadowColor: '#FF0000'
                    }
                }/*,
                    data: [{
                        name: "Jayfee",
                        value: 666
                    }, {
                        name: "Nancy",
                        value: 520
                    }]*/
            }]
        };

        var JosnList = [];
        //************************************************
        $.ajax({
            url: host + '/api/blog/searchWordCount.do',
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (r) {

                var arr = r.data;

                for (var i in arr) {
                    var tmp = arr[i];
                    JosnList.push({
                        "name": tmp.word,
                        "value": tmp.count
                    })
                }

            }
        });
        //******************************************************

        option.series[0].data = JosnList;

        myChart.setOption(option);
        myChart.on('click', function (params) {
            //alert((params.name));
            window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));

        });
    }

    worldcloud()
    $("#hot_title").click(function () {
        worldcloud()
    })

    function RenderBlogType() {
        //************************************************
        $.ajax({
            url: host + '/api/blog/getAllType.do',
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (r) {

                var arr = r.data;

                for (var i in arr) {
                    var tmp = arr[i];
                    var li = "<li style='height: 22px'><b><a href='index.html?type_id=" + tmp['id'] + "'" + "target='_self'>" + tmp['typeName'] + "(" + tmp['blogCount'] + ")" + "</a></b>"
                    $("#type_list").append(li)

                }

            }
        });
        //******************************************************
    }

    RenderBlogType()

    if (type_id !== null) {
        //清空原来的列表
        $("#art_list").children("li").remove()

        //ajax 后台请求 获取数据
        p = 1
        search_url = host + '/api/blog/list.do'
        load_index_list({
            "page": p,
            "blogType": type_id
        }, search_url)
    }

    function RenderBlogDate() {
        //************************************************
        $.ajax({
            url: host + '/api/blog/getByTime.do',
            type: 'get',
            dataType: 'json',
            async: false,
            success: function (r) {


                var arr = r.data;

                for (var i in arr) {
                    var tmp = arr[i];
                    date = encodeURI(tmp['releaseDateStr'])
                    //var li = "<li style='height: 22px'><b><a href='index.html?releaseDateStr=" + date + "'" + "target='_self'>" + tmp['releaseDateStr'] + "<em>(" + tmp['blogCount'] + ")</em></em>" + "</a></b>"
                    var li = "<li style='height: 22px'><b><a href='javascript:void(0)' target='_self'>" + tmp['releaseDateStr'] + "<em>(" + tmp['blogCount'] + ")</em></em>" + "</a></b>"
                    $("#date_list").append(li)

                }

            }
        });
        //******************************************************
    }

    RenderBlogDate()

    // if (releaseDateStr !== null) {
    //
    //
    // }

    $("#date_list>li").click(function () {
        var x = $("#date_list>li>b>a").text()
        var date_str = x.substring(0, x.indexOf('('))
        //清空原来的列表
        $("#art_list").children("li").remove()
        p = 1
        search_url = host + '/api/blog/list.do'
        load_index_list({
            "page": "1",
            "releaseDateStr": date_str
        }, search_url)
    })


})