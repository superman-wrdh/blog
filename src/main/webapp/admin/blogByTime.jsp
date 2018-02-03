<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>获取超锅博客每个类别的博客数量</title>
    <!-- 引入 echarts.js -->
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-2.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            var myChart = echarts.init(document.getElementById('main'));
            //************************************************
            var myChart = echarts.init(document.getElementById('main'));
            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            var dataStr=year+'年'+month+'月'+day+'日 '+hour+':'+minute+':'+second;
            var title=[];
            var ydata=[];
            $.ajax({
                url: 'http://www.66super.com/api/blog/getByTime.do',
                type: 'get',
                dataType: 'json',
                async: false,
                success: function (r) {
                    console.log("获取的数据");
                    console.log(r);
                    var arr=r.data;
                    console.log(arr);
                    for(i in arr){
                        var tmp=arr[i];
                        title.push(tmp.releaseDateStr)
                        ydata.push(tmp.blogCount)
                    }
                    console.log(dataAxis);
                    console.log(data)

                }
            });
            //******************************************************
            option = {
                title: {
                    text: '超锅博客每个类别博客数量',
                    subtext: '统计时间'+dataStr
                },
                color: ['#3398DB'],
                tooltip : {
                    trigger: 'axis',
                    axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                        type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis : [
                    {
                        type : 'category',
                        data : title,//['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'],
                        axisTick: {
                            alignWithLabel: true
                        }
                    }
                ],
                yAxis : [
                    {
                        type : 'value'
                    }
                ],
                series : [
                    {
                        name:'撰写博客数量',
                        type:'bar',
                        barWidth: '60%',
                        //data: dataShadow
                        data:ydata//[10, 52, 200, 150, 200, 300, 220]
                    }
                ]
            };

            myChart.setOption(option);
        })

    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 1200px;height:800px;"></div>
</body>
</html>