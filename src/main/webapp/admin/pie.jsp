<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-2.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var myChart = echarts.init(document.getElementById('main'));
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            var dataStr=year+'年'+month+'月'+day+'日 '+hour+':'+minute+':'+second;
            var arrDate=[];
            //************************************************
            $.ajax({
                url: 'http://www.66super.com/api/blog/count.do',
                type: 'get',
                dataType: 'json',
                async: false,
                success: function (r) {
                    console.log("获取的数据");
                    console.log(r.data);
                    var res=r.data;
                    for(var key in res){
                        var tmp={
                            "name":key,
                            "value":res[key]
                        }
                        console.log(tmp);
                        arrDate.push(tmp)
                    }
                }
            });
            //******************************************************
            option = {
                title : {
                    text: '浏览模块占比',
                    subtext: '统计时间'+dataStr,
                    x:'center'
                },
                tooltip : {
                    trigger: 'item',
                    formatter: "{a} <br/>{b} : {c} ({d}%)"
                }/*,
                legend: {
                    orient: 'vertical',
                    left: 'left',
                    data: ['直接访问','邮件营销','联盟广告','视频广告','搜索引擎']
                }*/,
                series : [
                    {
                        name: '访问数量',
                        type: 'pie',
                        radius : '55%',
                        center: ['50%', '60%'],
                        data:arrDate,
                        itemStyle: {
                            emphasis: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)'
                            }
                        }
                    }
                ]
            };

            myChart.setOption(option);
        })

    </script>
</head>
<body>
    <div id="main" style="width: 600px;height:600px;border: 1px dotted blue"></div>
</body>
</html>