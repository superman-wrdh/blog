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
                url: 'http://www.66super.com/api/blog/getAllType.do',
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
                        title.push(tmp.typeName)
                        ydata.push(tmp.blogCount)
                    }
                    console.log(dataAxis);
                    console.log(data)

                }
            });
            //******************************************************
            option = {
                title: {
                    text: '超锅博客每个类别博客数量www.66super.com',
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
                        data : title,
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
                        name:'该分类下博客数量',
                        type:'bar',
                        barWidth: '60%',
                        data:ydata
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