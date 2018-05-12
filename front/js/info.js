$(document).ready(function () {
    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null)return  unescape(r[2]); return null;
    }

    var bid = GetQueryString('bid')
    console.log(bid)
    $.ajax({
        url: 'http://www.66super.com/api/blog/get/'+bid+'.do',
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
            $('#tags').append("关键词  "+kwords)
        }
    });

})