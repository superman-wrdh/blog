function login(){
			 var m=(new Date().getMonth()+1);
                var d=new Date().getDate();
                var mm=new Date().getMinutes();
                var mstring=""+m;
                var dstring=""+d;
                var mmstring=""+mm;
                if(m<10){
                    mstring="0"+m;
                }if(d<10){
                    dstring="0"+d;
                }if(mm<10){
                    mmstring="0"+mm;
                }
                var ymd=new Date().getFullYear()+mstring+""+dstring+mmstring
                var saltstring ="superman";//后期从数据库读取
                ymd=ymd+saltstring;
                var sign = MD5(ymd);
                console.log(sign);


			var userName=$("#name").val();
			var password=$("#pwd").val();
			var varificae=$("#varifi").val();

			if(userName.trim()=="" ||password.trim()=="" ||userName==null ||password==null||varificae==null || varificae==""){
				alert("超锅提醒！密码或用户名或验证码不能为空");
				return false;
			}else{
				/*if(varificae!=sign){
					//alert("验证码输入错误");
					return true;
				}
				else{*/
					$("form")[0].submit();
				//}

			}
			
		}