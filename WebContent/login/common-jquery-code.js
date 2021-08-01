// JavaScript Document
//****************************************
// Modify Form Placeholder Attr (IE8 Mode)
//****************************************
!function(a){"function"==typeof define&&define.amd?define(["jquery"],a):a("object"==typeof module&&module.exports?require("jquery"):jQuery)}(function(a){function b(b){var c={},d=/^jQuery\d+$/;return a.each(b.attributes,function(a,b){b.specified&&!d.test(b.name)&&(c[b.name]=b.value)}),c}function c(b,c){var d=this,f=a(this);if(d.value===f.attr(h?"placeholder-x":"placeholder")&&f.hasClass(n.customClass))if(d.value="",f.removeClass(n.customClass),f.data("placeholder-password")){if(f=f.hide().nextAll('input[type="password"]:first').show().attr("id",f.removeAttr("id").data("placeholder-id")),b===!0)return f[0].value=c,c;f.focus()}else d==e()&&d.select()}function d(d){var e,f=this,g=a(this),i=f.id;if(!d||"blur"!==d.type||!g.hasClass(n.customClass))if(""===f.value){if("password"===f.type){if(!g.data("placeholder-textinput")){try{e=g.clone().prop({type:"text"})}catch(j){e=a("<input>").attr(a.extend(b(this),{type:"text"}))}e.removeAttr("name").data({"placeholder-enabled":!0,"placeholder-password":g,"placeholder-id":i}).bind("focus.placeholder",c),g.data({"placeholder-textinput":e,"placeholder-id":i}).before(e)}f.value="",g=g.removeAttr("id").hide().prevAll('input[type="text"]:first').attr("id",g.data("placeholder-id")).show()}else{var k=g.data("placeholder-password");k&&(k[0].value="",g.attr("id",g.data("placeholder-id")).show().nextAll('input[type="password"]:last').hide().removeAttr("id"))}g.addClass(n.customClass),g[0].value=g.attr(h?"placeholder-x":"placeholder")}else g.removeClass(n.customClass)}function e(){try{return document.activeElement}catch(a){}}var f,g,h=!1,i="[object OperaMini]"===Object.prototype.toString.call(window.operamini),j="placeholder"in document.createElement("input")&&!i&&!h,k="placeholder"in document.createElement("textarea")&&!i&&!h,l=a.valHooks,m=a.propHooks,n={};j&&k?(g=a.fn.placeholder=function(){return this},g.input=!0,g.textarea=!0):(g=a.fn.placeholder=function(b){var e={customClass:"placeholder"};return n=a.extend({},e,b),this.filter((j?"textarea":":input")+"["+(h?"placeholder-x":"placeholder")+"]").not("."+n.customClass).not(":radio, :checkbox, [type=hidden]").bind({"focus.placeholder":c,"blur.placeholder":d}).data("placeholder-enabled",!0).trigger("blur.placeholder")},g.input=j,g.textarea=k,f={get:function(b){var c=a(b),d=c.data("placeholder-password");return d?d[0].value:c.data("placeholder-enabled")&&c.hasClass(n.customClass)?"":b.value},set:function(b,f){var g,h,i=a(b);return""!==f&&(g=i.data("placeholder-textinput"),h=i.data("placeholder-password"),g?(c.call(g[0],!0,f)||(b.value=f),g[0].value=f):h&&(c.call(b,!0,f)||(h[0].value=f),b.value=f)),i.data("placeholder-enabled")?(""===f?(b.value=f,b!=e()&&d.call(b)):(i.hasClass(n.customClass)&&c.call(b),b.value=f),i):(b.value=f,i)}},j||(l.input=f,m.value=f),k||(l.textarea=f,m.value=f),a(function(){a(document).delegate("form","submit.placeholder",function(){var b=a("."+n.customClass,this).each(function(){c.call(this,!0,"")});setTimeout(function(){b.each(d)},10)})}),a(window).bind("beforeunload.placeholder",function(){var b=!0;try{"javascript:void(0)"===document.activeElement.toString()&&(b=!1)}catch(c){}b&&a("."+n.customClass).each(function(){this.value=""})}))});
// ---------------------------
$(document).ready(function() {
  $('input, textarea, password').placeholder();
});

// *******************************
// Fix Top Menu
// *******************************
(function(a){a.isScrollToFixed=function(b){return !!a(b).data("ScrollToFixed")};a.ScrollToFixed=function(d,h){var k=this;k.$el=a(d);k.el=d;k.$el.data("ScrollToFixed",k);var c=false;var F=k.$el;var G;var D;var p;var C=0;var q=0;var i=-1;var e=-1;var t=null;var y;var f;function u(){F.trigger("preUnfixed.ScrollToFixed");j();F.trigger("unfixed.ScrollToFixed");e=-1;C=F.offset().top;q=F.offset().left;if(k.options.offsets){q+=(F.offset().left-F.position().left)}if(i==-1){i=q}G=F.css("position");c=true;if(k.options.bottom!=-1){F.trigger("preFixed.ScrollToFixed");w();F.trigger("fixed.ScrollToFixed")}}function m(){var H=k.options.limit;if(!H){return 0}if(typeof(H)==="function"){return H.apply(F)}return H}function o(){return G==="fixed"}function x(){return G==="absolute"}function g(){return !(o()||x())}function w(){if(!o()){t.css({display:F.css("display"),width:F.outerWidth(true),height:F.outerHeight(true),"float":F.css("float")});cssOptions={position:"fixed",top:k.options.bottom==-1?s():"",bottom:k.options.bottom==-1?"":k.options.bottom,"margin-left":"0px"};if(!k.options.dontSetWidth){cssOptions.width=F.width()}F.css(cssOptions);F.addClass("scroll-to-fixed-fixed");if(k.options.className){F.addClass(k.options.className)}G="fixed"}}function b(){var I=m();var H=q;if(k.options.removeOffsets){H=0;I=I-C}cssOptions={position:"absolute",top:I,left:H,"margin-left":"0px",bottom:""};if(!k.options.dontSetWidth){cssOptions.width=F.width()}F.css(cssOptions);G="absolute"}function j(){if(!g()){e=-1;t.css("display","none");F.css({width:"",position:D,left:"",top:p.top,"margin-left":""});F.removeClass("scroll-to-fixed-fixed");if(k.options.className){F.removeClass(k.options.className)}G=null}}function v(H){if(H!=e){F.css("left",q-H);e=H}}function s(){var H=k.options.marginTop;if(!H){return 0}if(typeof(H)==="function"){return H.apply(F)}return H}function z(){if(!a.isScrollToFixed(F)){return}var J=c;if(!c){u()}var H=a(window).scrollLeft();var K=a(window).scrollTop();var I=m();if(k.options.minWidth&&a(window).width()<k.options.minWidth){if(!g()||!J){n();F.trigger("preUnfixed.ScrollToFixed");j();F.trigger("unfixed.ScrollToFixed")}}else{if(k.options.bottom==-1){if(I>0&&K>=I-s()){if(!x()||!J){n();F.trigger("preAbsolute.ScrollToFixed");b();F.trigger("unfixed.ScrollToFixed")}}else{if(K>=C-s()){if(!o()||!J){n();F.trigger("preFixed.ScrollToFixed");w();e=-1;F.trigger("fixed.ScrollToFixed")}v(H)}else{if(!g()||!J){n();F.trigger("preUnfixed.ScrollToFixed");j();F.trigger("unfixed.ScrollToFixed")}}}}else{if(I>0){if(K+a(window).height()-F.outerHeight(true)>=I-(s()||-l())){if(o()){n();F.trigger("preUnfixed.ScrollToFixed");if(D==="absolute"){b()}else{j()}F.trigger("unfixed.ScrollToFixed")}}else{if(!o()){n();F.trigger("preFixed.ScrollToFixed");w()}v(H);F.trigger("fixed.ScrollToFixed")}}else{v(H)}}}}function l(){if(!k.options.bottom){return 0}return k.options.bottom}function n(){var H=F.css("position");if(H=="absolute"){F.trigger("postAbsolute.ScrollToFixed")}else{if(H=="fixed"){F.trigger("postFixed.ScrollToFixed")}else{F.trigger("postUnfixed.ScrollToFixed")}}}var B=function(H){if(F.is(":visible")){c=false;z()}};var E=function(H){z()};var A=function(){var I=document.body;if(document.createElement&&I&&I.appendChild&&I.removeChild){var K=document.createElement("div");if(!K.getBoundingClientRect){return null}K.innerHTML="x";K.style.cssText="position:fixed;top:100px;";I.appendChild(K);var L=I.style.height,M=I.scrollTop;I.style.height="3000px";I.scrollTop=500;var H=K.getBoundingClientRect().top;I.style.height=L;var J=(H===100);I.removeChild(K);I.scrollTop=M;return J}return null};var r=function(H){H=H||window.event;if(H.preventDefault){H.preventDefault()}H.returnValue=false};k.init=function(){k.options=a.extend({},a.ScrollToFixed.defaultOptions,h);k.$el.css("z-index",k.options.zIndex);t=a("<div />");G=F.css("position");D=F.css("position");p=a.extend({},F.offset());if(g()){k.$el.after(t)}a(window).bind("resize.ScrollToFixed",B);a(window).bind("scroll.ScrollToFixed",E);if(k.options.preFixed){F.bind("preFixed.ScrollToFixed",k.options.preFixed)}if(k.options.postFixed){F.bind("postFixed.ScrollToFixed",k.options.postFixed)}if(k.options.preUnfixed){F.bind("preUnfixed.ScrollToFixed",k.options.preUnfixed)}if(k.options.postUnfixed){F.bind("postUnfixed.ScrollToFixed",k.options.postUnfixed)}if(k.options.preAbsolute){F.bind("preAbsolute.ScrollToFixed",k.options.preAbsolute)}if(k.options.postAbsolute){F.bind("postAbsolute.ScrollToFixed",k.options.postAbsolute)}if(k.options.fixed){F.bind("fixed.ScrollToFixed",k.options.fixed)}if(k.options.unfixed){F.bind("unfixed.ScrollToFixed",k.options.unfixed)}if(k.options.spacerClass){t.addClass(k.options.spacerClass)}F.bind("resize.ScrollToFixed",function(){t.height(F.height())});F.bind("scroll.ScrollToFixed",function(){F.trigger("preUnfixed.ScrollToFixed");j();F.trigger("unfixed.ScrollToFixed");z()});F.bind("detach.ScrollToFixed",function(H){r(H);F.trigger("preUnfixed.ScrollToFixed");j();F.trigger("unfixed.ScrollToFixed");a(window).unbind("resize.ScrollToFixed",B);a(window).unbind("scroll.ScrollToFixed",E);F.unbind(".ScrollToFixed");k.$el.removeData("ScrollToFixed")});B()};k.init()};a.ScrollToFixed.defaultOptions={marginTop:0,limit:0,bottom:-1,zIndex:1000};a.fn.scrollToFixed=function(b){return this.each(function(){(new a.ScrollToFixed(this,b))})}})(jQuery);
// ---------------------------
$(document).ready(function() {
  $('.navbar-fixed-top').scrollToFixed();
  //$('.footer').scrollToFixed( { bottom: 0, limit: $('.footer').offset().top } );
});

// *******************************
// Back To Top Function
// *******************************
var scrolltotop = {
  setting:{
    //startline:1800, //起始行
    startline:100, //起始行
    scrollto:0, //滚动到指定位置
    scrollduration:400, //滚动过渡时间
    fadeduration:[500,100] //淡出淡现消失
  },
  //controlHTML:'<img src="/images/default/images-main/topback.gif" style="width:42px; height:42px; border:0;" />', //返回顶部按钮
  controlHTML:"<span style='color:#3b5998;'><i class='fa fa-chevron-circle-up fa-4x'></i></span>", //返回顶部按钮
  //controlattrs:{offsetx:20,offsety:160},//返回按钮固定位置
  controlattrs:{offsetx:28,offsety:45},//返回按钮固定位置
  anchorkeyword:"#top",
  state:{
    isvisible:false,
    shouldvisible:false
  },scrollup:function(){
    if(!this.cssfixedsupport){
      this.$control.css({opacity:0});
    }
    var dest=isNaN(this.setting.scrollto)?this.setting.scrollto:parseInt(this.setting.scrollto);
    if(typeof dest=="string"&&jQuery("#"+dest).length==1){
      dest=jQuery("#"+dest).offset().top;
    }else{
      dest=0;
    }
    this.$body.animate({scrollTop:dest},this.setting.scrollduration);
  },keepfixed:function(){
    var $window=jQuery(window);
    var controlx=$window.scrollLeft()+$window.width()-this.$control.width()-this.controlattrs.offsetx;
    var controly=$window.scrollTop()+$window.height()-this.$control.height()-this.controlattrs.offsety;
    this.$control.css({left:controlx+"px",top:controly+"px"});
  },togglecontrol:function(){
    var scrolltop=jQuery(window).scrollTop();
    if(!this.cssfixedsupport){
      this.keepfixed();
    }
    this.state.shouldvisible=(scrolltop>=this.setting.startline)?true:false;
    if(this.state.shouldvisible&&!this.state.isvisible){
      this.$control.stop().animate({opacity:1},this.setting.fadeduration[0]);
      this.state.isvisible=true;
    }else{
      if(this.state.shouldvisible==false&&this.state.isvisible){
        this.$control.stop().animate({opacity:0},this.setting.fadeduration[1]);
        this.state.isvisible=false;
      }
    }
  },init:function(){
    jQuery(document).ready(function($){
      var mainobj=scrolltotop;
      var iebrws=document.all;
      mainobj.cssfixedsupport=!iebrws||iebrws&&document.compatMode=="CSS1Compat"&&window.XMLHttpRequest;
      mainobj.$body=(window.opera)?(document.compatMode=="CSS1Compat"?$("html"):$("body")):$("html,body");
      mainobj.$control=$('<div id="topcontrol" data-toggle="tooltip" data-placement="top">'+mainobj.controlHTML+"</div>").css({position:mainobj.cssfixedsupport?"fixed":"absolute",bottom:mainobj.controlattrs.offsety,right:mainobj.controlattrs.offsetx,opacity:0,cursor:"pointer"}).attr({title:"返回顶部"}).click(function(){mainobj.scrollup();return false;}).appendTo("body");if(document.all&&!window.XMLHttpRequest&&mainobj.$control.text()!=""){mainobj.$control.css({width:mainobj.$control.width()});}mainobj.togglecontrol();
      $('a[href="'+mainobj.anchorkeyword+'"]').click(function(){mainobj.scrollup();return false;});
      $(window).bind("scroll resize",function(e){mainobj.togglecontrol();});
      $('#topcontrol').tooltip('hide');
    });
  }
};
scrolltotop.init();


// *******************************
// Total And Forbidden Text Length
// *******************************
// ********* Record Form *********
$(function() {
  $("#recordContent").keyup(function() {
    var $max = 512; //定义最多输入数字
    var $length = $("#recordContent").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#recordContent").val().substring(0, $max);
      document.getElementById("recordContent").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#pubRecordLeft").text($max - $length);
    }
  })
});
// ******* Question Form *********
$(function() {
  $("#questionContent").keyup(function() {
    var $max = 512; //定义最多输入数字
    var $length = $("#questionContent").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#questionContent").val().substring(0, $max);
      document.getElementById("questionContent").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#pubQuestionLeft").text($max - $length);
    }
  })
});
// ********* Topic Form **********
$(function() {
  $("#topicContent").keyup(function() {
    var $max = 512; //定义最多输入数字
    var $length = $("#topicContent").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#topicContent").val().substring(0, $max);
      document.getElementById("topicContent").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#pubTopicLeft").text($max - $length);
    }
  })
});
// ********* Diary Form **********
$(function() {
  $("#diaryContent").keyup(function() {
    var $max = 1024; //定义最多输入数字
    var $length = $("#diaryContent").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#diaryContent").val().substring(0, $max);
      document.getElementById("diaryContent").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#pubDiaryLeft").text($max - $length);
    }
  })
});
// ********* Photo Form **********
$(function() {
  $("#photoDesc").keyup(function() {
    var $max = 512; //定义最多输入数字
    var $length = $("#photoDesc").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#photoDesc").val().substring(0, $max);
      document.getElementById("photoDesc").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#pubPhotoLeft").text($max - $length);
    }
  })
});
// ********* Video Form **********
$(function() {
  $("#videoDesc").keyup(function() {
    var $max = 512; //定义最多输入数字
    var $length = $("#videoDesc").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#videoDesc").val().substring(0, $max);
      document.getElementById("videoDesc").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#pubVideoLeft").text($max - $length);
    }
  })
});
// ********* Publish Message Form *********
$(function() {
  $("#pubMsgContent").keyup(function() {
    var $max = 512; //定义最多输入数字
    var $length = $("#pubMsgContent").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#pubMsgContent").val().substring(0, $max);
      document.getElementById("pubMsgContent").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#pubMsgLeft").text($max - $length);
    }
  })
});
// ********* Reply Message Form *********
$(function() {
  $("#rpyMsgContent").keyup(function() {
    var $max = 512; //定义最多输入数字
    var $length = $("#rpyMsgContent").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#rpyMsgContent").val().substring(0, $max);
      document.getElementById("rpyMsgContent").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#rpyMsgLeft").text($max - $length);
    }
  })
});
// ********* Send Message Form *********
$(function() {
  $("#sendMsgContent").keyup(function() {
    var $max = 512; //定义最多输入数字
    var $length = $("#sendMsgContent").val().length; //取得用户输入的字符的长度
    if ($length > $max) { //判断是否超过最长数字
      var $newStr = $("#sendMsgContent").val().substring(0, $max);
      document.getElementById("sendMsgContent").innerHTML = $newStr;
    } else { //没有超过就修改剩余字数
      $("#sendMsgLeft").text($max - $length);
    }
  })
});


// *******************************
// Feed Video Icon Style Hover
// *******************************
function feedVideoIcon() {
  $(".feed-video-icon").hover(function() {
    $(this).addClass("feed-video-icon-hover")
  },function() {
    $(this).removeClass("feed-video-icon-hover")
  })
}







