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

            var date = new Date();
            var year = date.getFullYear();
            var month = date.getMonth()+1;
            var day = date.getDate();
            var hour = date.getHours();
            var minute = date.getMinutes();
            var second = date.getSeconds();
            var dataStr=year+'年'+month+'月'+day+'日 '+hour+':'+minute+':'+second;

            var dataAxis=[];
            var data=[];
             //************************************************
              $.ajax({
                url: 'http://www.66super.com/api/blog/getEachBlogClick.do',
                type: 'get',
                dataType: 'json',
                async: false,
                success: function (r) {
                    console.log("获取的数据");
                    console.log(r);
                    var arr=r.data;
                    console.log("--------------------");
                    console.log(arr);

                    for(i in arr){
                        var tmp=arr[i];
                        dataAxis.push(tmp.title)
                        data.push(tmp.clickHit)
                    }
                    console.log(dataAxis);
                    console.log(data)

                 }
              });
            //******************************************************
            //讲y轴的最大值设为元素中最大的数
            var max=0
            for(var i=0;i<data.length;i++){
                if(data[i]>max){
                    max=data[i];
                }
            }
           //**********************************************************

            var dataShadow = [];
            var yMax = max+80;
            for (var i = 0; i < data.length; i++) {
                dataShadow.push(yMax);
            }

            option = {
                title: {
                    text: '超锅博客每个类别博客数量www.66super.com',
                    subtext: '统计时间'+dataStr
                },
                xAxis: {
                    data: dataAxis,
                    axisLabel: {
                        inside: true,
                        textStyle: {
                            color: '#f00'
                        }
                    },
                    axisTick: {
                        show: false
                    },
                    axisLine: {
                        show: false
                    },
                    z: 10
                },
                yAxis: {
                    axisLine: {
                        show: false
                    },
                    axisTick: {
                        show: false
                    },
                    axisLabel: {
                        textStyle: {
                            color: '#999'
                        }
                    }
                },
                dataZoom: [
                    {
                        type: 'inside'
                    }
                ],
                series: [
                    { // For shadow
                        type: 'bar',
                        itemStyle: {
                            normal: {color: 'rgba(0,0,0,0.05)'}
                        },
                        barGap:'-100%',
                        barCategoryGap:'40%',
                        data: dataShadow,
                        animation: false
                    },
                    {
                        type: 'bar',
                        itemStyle: {
                            normal: {
                                color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#83bff6'},
                                            {offset: 0.5, color: '#188df0'},
                                            {offset: 1, color: '#188df0'}
                                        ]
                                )
                            },
                            emphasis: {
                                color: new echarts.graphic.LinearGradient(
                                        0, 0, 0, 1,
                                        [
                                            {offset: 0, color: '#2378f7'},
                                            {offset: 0.7, color: '#2378f7'},
                                            {offset: 1, color: '#83bff6'}
                                        ]
                                )
                            }
                        },
                        data: data
                    }
                ]
            };

            // Enable data zoom when user click bar.
            var zoomSize = 2; //
            myChart.on('click', function (params) {
                console.log(dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)]);
                myChart.dispatchAction({
                    type: 'dataZoom',
                    startValue: dataAxis[Math.max(params.dataIndex - zoomSize / 2, 0)],
                    endValue: dataAxis[Math.min(params.dataIndex + zoomSize / 2, data.length - 1)]
                });
            });

            myChart.setOption(option);


        })

    </script>
</head>
<body>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 100%;height:800px;"></div>
</body>
</html>