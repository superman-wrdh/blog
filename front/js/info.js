$(document).ready(function () {
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

    var bid = GetQueryString('bid')
    console.log(bid)
    $.ajax({
        url: 'http://www.66super.com/api/blog/get/' + bid + '.do',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (r) {
            $('title').append(r['title'])
            $('#b_content').append(r['content'])
            $('#news_title').append(r['title'])
            $('#b_date').append(r['releaseDate'])
            $('#b_click').append(r['clickHit'])
            var kwords = r['keyWord']
            $('#tags').append("关键词  " + kwords)
        }
    });

    // /api/blog/next_and_previous/102.do
    $.ajax({
        url: 'http://www.66super.com/api/blog/next_and_previous/' + bid + '.do',
        type: 'get',
        dataType: 'json',
        async: false,
        success: function (r) {
            var next = null
            var last = null
            if ("next" in r) {
                next = r['next']
            }
            if("last" in r){
                last = r['last']
            }
            if (last !== null) {
                $("#last_blog").text(last['title'])
                $("#last_blog").attr("href", "/info.html?bid=" + last['id']);
            }

            if (next !== null) {
                $("#next_blog").text(next['title'])
                $("#next_blog").attr("href", "/info.html?bid=" + last['id']);
            }


        }
    });



    function rnd(n, m) {
        var random = Math.floor(Math.random() * (m - n + 1) + n);
        return random;
    }

    $.ajax({
        url: 'http://www.66super.com/api/blog/list.do',
        type: 'post',
        dataType: 'json',
        contentType: "application/json",
        data: JSON.stringify({
            "page": rnd(1, 3)
        }),
        success: function (r) {
            var res = r.data.results.slice(0, 6)
            console.log(res)
            //"<li><a href="/" title="云南之行――丽江古镇玉龙雪山">云南之行――丽江古镇玉龙雪山</a></li>"
            for(var i = 0;i<res.length;i++){
                var li = "<li><a href='info.html?bid="+res[i]['id']+"' title='文章'>"+res[i]['title']+"</a></li>"
                $("#about_blog").append(li)
            }

        }
    });

})