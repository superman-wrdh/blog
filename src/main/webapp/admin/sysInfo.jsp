<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/echarts-liquidfill.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {


            function getNowTime(){
                var date = new Date();
                var year = date.getFullYear();
                var month = date.getMonth()+1;
                var day = date.getDate();
                var hour = date.getHours();
                var minute = date.getMinutes();
                var second = date.getSeconds();
                var dataStr=year+'年'+month+'月'+day+'日 '+hour+':'+minute+':'+second;
                return dataStr
            }
            function getPercent() {
                var date = new Date();
                var percent=0;
                $.ajax({
                    url: 'http://www.66super.com/api/blog/systemInfo.do',
                    type: 'get',
                    dataType: 'json',
                    async: false,
                    success: function (r) {
                        console.log("获取的数据");
                        console.log(r.data);
                        var res=r.data;
                        percent=res.memPercent;
                        console.log(percent);
                        $("#java_str").text(res.javaVersion);
                        $("#os_str").text(res.os);
                        $("#mem_total").text(res.totalMemory);
                        $("#mem_use").text(res.usedMemory);
                    }
                });
                return percent;
            }
            var myChart = echarts.init(document.getElementById('main'));
            var option = {title: {
                text: '服务器内存监测',
                subtext: '获取数据时间',
                left: 'right'
                },
                series: [{
                    type: 'liquidFill',
                    radius: '80%',
                    data: [getPercent()/100,getPercent()*0.9/100],
                    label: {
                        normal: {
                            textStyle: {
                                color: 'red',
                                insideColor: 'yellow',
                                fontSize: 50
                            }
                        }
                    }
                }]
            };
            option.title.subtext='获取数据时间'+getNowTime();
            option.series[0].data[0].value = getPercent();
            myChart.setOption(option);

           setInterval(function () {
               var p=getPercent();
                option.title.subtext='获取数据时间'+getNowTime();
                option.series[0].data[0] = p/100;
                option.series[0].data[1] = p*0.9/100;
                myChart.setOption(option, true);
            },10000);


        })
     </script>
</head>
<body>
<div id="main" style="width: 600px;height:400px;float: left"></div>
<div id="os" style="width: 300px;height:300px;float: left;margin-top: 100px">
    <table border="1px">
        <table style="border: 1px solid #ddd">
            <tr>
                <td>内存消耗:</td>
                <td>
                    <span id="mem_use"></span> / <span id="mem_total"></span>
                </td>
            </tr>
            <tr>
                <td style="width:120px;">操作系统:</td>
                <td><span id="os_str"></span></td>
            </tr>
            <tr>
                <td style="width:120px;">JDK版本:</td>
                <td><span id="java_str"></span></td>
            </tr>
        </table>

    </table>
</div>
</body>
</html>