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
            var myChart = echarts.init(document.getElementById('main'));
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
                    link: 'https://www.baidu.com/s?wd=' + encodeURIComponent('ECharts'),
                    x: 'center',
                    textStyle: {
                        fontSize: 23
                    }

                },
                backgroundColor: '#bbeef7',
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
        })
    </script>
</head>
<body>
<div id="main" style="width: 600px;height:600px;"></div>

</body>
</html>