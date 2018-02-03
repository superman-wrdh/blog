
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery-2.1.1.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts-wordcloud.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            function  loaDdata() {
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
                        text: '不同分类阅读数量',
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
            }
            function loadData1() {
                var myChart = echarts.init(document.getElementById('main1'));
                var date = new Date();
                var year = date.getFullYear();
                var month = date.getMonth()+1;
                var day = date.getDate();
                var hour = date.getHours();
                var minute = date.getMinutes();
                var second = date.getSeconds();
                var dataStr=year+'年'+month+'月'+day+'日 '+hour+':'+minute+':'+second;
                var JosnList = [];
                //************************************************
                $.ajax({
                    url: 'http://www.66super.com/api/blog/searchWordCount.do',
                    type: 'get',
                    dataType: 'json',
                    async: false,
                    success: function (r) {
                        console.log("获取的数据");
                        console.log(r);
                        var arr=r.data;
                        console.log(arr)
                        for(var i in arr){
                            var tmp=arr[i];
                            JosnList.push({
                                "name":tmp.word,
                                "value":tmp.count
                            })
                        }

                    }
                });
                //******************************************************

                option = {
                    title: {
                        text: '热词搜索排行',
                        subtext: '统计时间'+dataStr,
                        link: 'https://www.baidu.com/s?wd=' + encodeURIComponent('ECharts'),
                        x: 'center',
                        textStyle: {
                            fontSize: 23
                        }

                    },
                    backgroundColor: '#FFFFF',
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
                        sizeRange: [26, 96],
                        //textRotation: [0, 45, 90, -45],
                        rotationRange: [-45, 90],
                        //shape: 'circle',
                        textPadding: 0,
                        autoSize: {
                            enable: true,
                            minSize: 26
                        },
                        textStyle: {
                            normal: {
                                color: function() {
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
                        }
                    }]
                };

                option.series[0].data = JosnList;

                myChart.setOption(option);
                myChart.on('click', function (params) {
                    //alert((params.name));
                    window.open('https://www.baidu.com/s?wd=' + encodeURIComponent(params.name));

                });
            }
            loaDdata();
            loadData1();
            $("#flush").click(function () {
                loaDdata()
            })

            $("#flush1").click(function () {
                loadData1();
            })
        })

    </script>

</head>
<body>
<div id="main" style="width: 600px;height:600px;border: 1px dotted blue;float: left"></div>
<div id="main1" style="width: 600px;height:600px;border: 1px dotted blue;float: left"></div>
<div id="flush" style="width:600px;height:50px ;font-size: 18px;border: 1px solid blue;text-align: center;background: #e0ecff;line-height: 2;float: left">点击刷新</div>
<div id="flush1" style="width:600px;height:50px ;font-size: 18px;border: 1px solid blue;text-align: center;background: #e0ecff;line-height: 2;float: left">点击刷新</div>
</body>

</html>