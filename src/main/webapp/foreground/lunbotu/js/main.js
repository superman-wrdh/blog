/* 轮播图 */
var slide = function(){
    var slideWrap = $(".slide"),
        listButtonBox = $('.buttonlist'),
        roll = slideWrap.children(),
        list = roll.children(),
        len = list.length,
        move = list.height(),
        fx = 'easeInSine',
        curIndex = 0,
        timer = null,
        speed = 500,
        auto = 5000;

    var init = function(){
        list.eq(0).css('zIndex' , 2).siblings().hide();
        var s = "";
        for(var i = 0; i < len; i++){
            if(i === curIndex){
                s += '<a  class="active" href="javascript:;"></a>';
            }else{
                s += '<a href="javascript:;"></a>';
            }
        }
        listButtonBox.append($(s));
        listButtonBox.on('mouseenter','a',function(e){
            var index = $(this).index();
            skip(curIndex,index);
        });
        start();

        $('.slide').add(listButtonBox).hover(function(){
            clearInterval(timer);
        },function(){
            start();
        });
       
    }

    var toNext = function(){
      var n = curIndex + 1 == len ? 0 : curIndex + 1;
       skip(curIndex,n);
    }

    var skip = function(pr,ne){
        if(pr == ne) return;
       list.stop(true,true);
       list.eq(pr).css('zIndex',2).stop(true,true).animate({
            opacity : 0
       },speed,fx,function(){
            $(this).css({zIndex : 0 , display : 'none' , opacity : 1});
             list.eq(ne).css('zIndex',2);
       });
       list.eq(ne).css({display : 'block' , zIndex : 1});
       // list.eq(ne).css({display : 'block' , zIndex : 1 , opacity : 0}).stop(true).animate({
       //      opacity : 1
       // },speed,fx,function(){
       //      $(this).css({zIndex : 2});
       // });
       curIndex = ne;
       listButtonBox.children(true).eq(ne).addClass('active').siblings().removeClass('active');
    }

    var start = function(){
        timer = setInterval(function(){
            toNext();
        },auto);
    }

    init();

}

// 20141212 大图
function bannerSlide(){
    var box = $('.ag7-bannerslide .slidebox'),
        buttons = $('.ag7-bannerslide .slidelist'),
        mask = $('.ag7-bannerslide .mask'),
        h1 =  $('.ag7-bannerslide h2'),
        h2 =  $('.ag7-bannerslide h3'),
        list = box.children('li'),
        len = list.length,
        curIndex = 0,
        nextIndex = 1,
        speed = 800,
        timer = null,
        delay = null,
        duration = 3000;
    var setTitle = function(index){
    var li = buttons.find('li').eq(index);
        h1.html(li.data('h1'));
        h2.html(li.data('h2'));
    }
   
    var tab = function(cur,next){
        if(cur === next){
             return;
        };

       list.stop(true,true);
       list.eq(next).css({zIndex : 1, display : 'block' ,opacity : 1}); 
       list.eq(curIndex).animate({opacity : 0} , function(){
        $(this).css({zIndex : 0 , display : 'none'});
        list.eq(next).css('zIndex',2);
       })

        // 小图标遮罩
        mask.stop(true,true).animate({
           // left : next * (buttons.find('li').eq(next).outerWidth(true) + 6) + 32 
           left : buttons.find('li').eq(next).position().left + 2
        },300,'easeOutExpo');
       setTitle(next);
       curIndex = next;
    }

    var autoPlay = function(){
       timer =  setInterval(function(){
            var next = curIndex == len - 1 ?  0 : curIndex + 1;
            tab(curIndex,next);
        },duration);
    }

    var stop = function(){
        clearInterval(timer);
    }


    buttons.on('mouseenter','li',function(){
        var next = $(this).index();
        tab(curIndex,next);

    });

    $('.ag7-bannerslide').hover(function(){
        stop();
    },function(){
        autoPlay();
    });
    list.eq(0).css('zIndex' , 2).siblings().hide();
    setTitle(0);
    autoPlay();
    
}
$(function(){
    bannerSlide();
    kuaixun();
});
