 ## 按月份获取博客数量 /api/blog/getByTime.do
      传参 空
      输出 json
      {
        "data": [
          {
            "blogCount": 1,
            "releaseDateStr": "2016年12月",
            "imagesList": []
          },
          {
            "blogCount": 3,
            "releaseDateStr": "2016年11月",
            "imagesList": []
          },
          {
            "blogCount": 4,
            "releaseDateStr": "2016年10月",
            "imagesList": []
          },
          {
            "blogCount": 2,
            "releaseDateStr": "2016年09月",
            "imagesList": []
          },
          {
            "blogCount": 10,
            "releaseDateStr": "2016年08月",
            "imagesList": []
          },
          {
            "blogCount": 3,
            "releaseDateStr": "2016年07月",
            "imagesList": []
          }
        ]
      }
      
   
### 获取不同类别博客数量
 
    API地址:      /api/blog/getAllType.do
    传参 空
    输出 json
    {
      "data": [
        {
          "id": 1,
          "typeName": "JavaSE",
          "blogCount": 2
        },
        {
          "id": 2,
          "typeName": "MySQL",
          "blogCount": 3
        },
        {
          "id": 3,
          "typeName": "Tomcat",
          "blogCount": 0
        },
        {
          "id": 20,
          "typeName": "Spring",
          "blogCount": 0
        },
        {
          "id": 19,
          "typeName": "Struts2",
          "blogCount": 1
        },
        {
          "id": 11,
          "typeName": "hibernate",
          "blogCount": 0
        },
        {
          "id": 12,
          "typeName": "maven",
          "blogCount": 0
        },
        {
          "id": 4,
          "typeName": "IT之路",
          "blogCount": 3
        },
        {
          "id": 13,
          "typeName": "Linux",
          "blogCount": 7
        },
        {
          "id": 14,
          "typeName": "算法",
          "blogCount": 4
        },
        {
          "id": 16,
          "typeName": "阿里云实战（Linux版）",
          "blogCount": 0
        },
        {
          "id": 18,
          "typeName": "SpringMVC",
          "blogCount": 3
        },
        {
          "id": 17,
          "typeName": "生活",
          "blogCount": 0
        },
        {
          "id": 15,
          "typeName": "HTML/CSS/JS",
          "blogCount": 0
        },
        {
          "id": 21,
          "typeName": "AngularJS",
          "blogCount": 0
        }
      ]
    }
    
    
    
##  获取博客列表 带分页 
    API地址:      /api/blog/list.do
    传参 {
          "page":"1"                       //第几页
          "releaseDateStr":"2016年12月",  //根据月份查 
          "blogType":"1"                 //根据类别查
       }
       
    空json查所有
    
    输出结果 json
    {
      "data": {
        "page": 1,
        "pageSize": 10,
        "start": 0,
        "resultSize": 10,
        "results": [
          {
            "id": 102,
            "title": "Ubuntu Linux 14.04设置ssh允许root远程登录",
            "summary": "ubuntu14.04默认是不能以ssh远程登录的，这是为了防止root用户权限过大引起的不安全操作，通常我们以非root用户登录当需要权限的时候执行sudo或者直接su切换到root用户，这样有时候不怎么方便，当我们修改ssh的配置文件时候，就可以以root用户登录sudo vim /etc/ssh/ssh",
            "releaseDate": "2016-12-26 10:51:13",
            "clickHit": 102,
            "replyHit": 1,
            "content": "<p>ubuntu14.04默认是不能以ssh远程登录的，这是为了防止root用户权限过大引起的不安全操作，通常我们以非root用户登录</p><p>当需要权限的时候执行sudo或者直接su切换到root用户，这样有时候不怎么方便，当我们修改ssh的配置文件时候，就可以以</p><p>root用户登录</p><p><br/></p><p>sudo vim /etc/ssh/sshd_config</p><p><br/></p><p>找到 PermitRootLogin without-password</p><p>修改箭头中部分</p><p><img src=\"/static/userImages/20161226/1482720636724035376.png\" title=\"1482720636724035376.png\" alt=\"1.png\"/></p><p><br/></p><p>改成 PermitRootLogin yes</p><p><br/></p><p><img src=\"/static/userImages/20161226/1482720644837065542.png\" title=\"1482720644837065542.png\" alt=\"2.png\"/></p><p>重启ssh服务就可以了</p><p><br/></p><p>sudo service ssh restart</p><p><br/></p>",
            "blogType": {
              "id": 13,
              "typeName": "Linux",
              "orderNo": 7
            },
            "keyWord": "Linux root",
            "imagesList": []
          },
          {
           
          } 
          ,
          {
           
          }
        ]
      }
    }
 
## 搜索
        API地址:      /api/blog/search.do
        传参 无
            {
             "page":"1",
             "keyWord":"linux"	
            }
        输出 json
        {
          "data": {
            "page": 1,
            "pageSize": 10,
            "start": 0,
            "resultSize": 1,
            "results": [
              {
                "id": 102,
                "title": "Ubuntu <b><font color='red'>Linux</font></b> 14.04设置ssh允许root远程登录",
                "content": "ubuntu14.04&#40664;&#35748;&#26159;&#19981;&#33021;&#20197;ssh&#36828;&#31243;&#30331;&#24405;&#30340;&#65292;&#36825;&#26159;&#20026;&#20102;&#38450;&#27490;root&#29992;&#25143;&#26435;&#38480;&#3680",
                "releaseDateStr": "2017-03-04",
                "imagesList": []
              }
            ]
          }
        }    
    
## 根据id查询文章详情
        API地址:      /api/blog/get/{id}.do
        传参 无
        输出 json
        {
          "id": 102,
          "title": "Ubuntu Linux 14.04设置ssh允许root远程登录",
          "summary": "ubuntu14.04默认是不能以ssh远程登录的，这是为了防止root用户权限过大引起的不安全操作，通常我们以非root用户登录当需要权限的时候执行sudo或者直接su切换到root用户，这样有时候不怎么方便，当我们修改ssh的配置文件时候，就可以以root用户登录sudo vim /etc/ssh/ssh",
          "releaseDate": "2016-12-26 10:51:13",
          "clickHit": 102,
          "replyHit": 1,
          "content": "<p>ubuntu14.04默认是不能以ssh远程登录的，这是为了防止root用户权限过大引起的不安全操作，通常我们以非root用户登录</p><p>当需要权限的时候执行sudo或者直接su切换到root用户，这样有时候不怎么方便，当我们修改ssh的配置文件时候，就可以以</p><p>root用户登录</p><p><br/></p><p>sudo vim /etc/ssh/sshd_config</p><p><br/></p><p>找到 PermitRootLogin without-password</p><p>修改箭头中部分</p><p><img src=\"/static/userImages/20161226/1482720636724035376.png\" title=\"1482720636724035376.png\" alt=\"1.png\"/></p><p><br/></p><p>改成 PermitRootLogin yes</p><p><br/></p><p><img src=\"/static/userImages/20161226/1482720644837065542.png\" title=\"1482720644837065542.png\" alt=\"2.png\"/></p><p>重启ssh服务就可以了</p><p><br/></p><p>sudo service ssh restart</p><p><br/></p>",
          "blogType": {
            "id": 13,
            "typeName": "Linux",
            "orderNo": 7
          },
          "keyWord": "Linux root",
          "imagesList": []
        }
## 获取轮播图接口
        API地址:      /api/blog/banner.do
        传参 无
        输出 json
        {
          "data": [
            "http://www.66super.com/banner/1.png",
            "http://www.66super.com/banner/2.png",
            "http://www.66super.com/banner/3.png",
            "http://www.66super.com/banner/4.png",
            "http://www.66super.com/banner/5.png"
          ]
        }  
 
    
## 按照阅读量

      API地址:      /api/blog//listByReadTime/{page}.do
      传参 无
      输出 json
      {
         文章list
      }
    

