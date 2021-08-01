// **************************
// ******  全局变量定义 ******
// **************************
var qqReg = /^[1-9]\d{4,10}$/;
var wechatReg = /^[a-zA-Z]{1}[-_a-zA-Z0-9]{5,19}$/;
var emailReg = /^([a-zA-Z0-9]+[_|\_|-|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[-|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
var mobileReg = /^1[3|4|5|7|8]\d{9}$/;
var telephoneReg = /^0[1-9]\d{1,2}[-|\-][1-9]\d{6,7}$/;
var urlReg = /^((https|http)?:\/\/)[^\s]+$/;
var chineseReg = /^([\u4E00-\u9FA5])*$/;
var surnameReg = "赵钱孙李周吴郑王冯陈褚卫蒋沈韩杨朱秦尤许何吕施张孔曹严华金魏陶姜戚谢邹喻柏水窦章云苏潘葛奚范彭郎鲁韦昌马苗凤花方俞任袁柳酆鲍史唐费廉岑薛雷贺倪汤滕殷罗毕郝邬安常乐于时傅皮卞齐康伍余元卜顾孟平黄和穆萧尹姚邵堪汪祁毛禹狄米贝明臧计伏成戴谈宋茅庞熊纪舒屈项祝董粱杜阮蓝闵席季麻强贾路娄危江童颜郭梅盛林刁钟徐邱骆高夏蔡田樊胡凌霍虞万支柯咎管卢莫经房裘缪干解应宗宣丁贲邓郁单杭洪包诸左石崔吉钮龚程嵇邢滑裴陆荣翁荀羊於惠甄魏加封芮羿储靳汲邴糜松井段富巫乌焦巴弓牧隗山谷车侯宓蓬全郗班仰秋仲伊宫宁仇栾暴甘钭厉戎祖武符刘姜詹束龙叶幸司韶郜黎蓟薄印宿白怀蒲台从鄂索咸籍赖卓蔺屠蒙池乔阴郁胥能苍双闻莘党翟谭贡劳逄姬申扶堵冉宰郦雍郤璩桑桂濮牛寿通边扈燕冀郏浦尚农温别庄晏柴瞿阎充慕连茹习宦艾鱼容向古易慎戈廖庚终暨居衡步都耿满弘匡国文寇广禄阙东殴殳沃利蔚越夔隆师巩厍聂晁勾敖融冷訾辛阚那简饶空曾毋沙乜养鞠须丰巢关蒯相查后江红游竺权逯盖益桓公万俟司马上官欧阳夏侯诸葛闻人东方赫连皇甫尉迟公羊澹台公冶宗政濮阳淳于仲孙太叔申屠公孙乐正轩辕令狐钟离闾丘长孙慕容鲜于宇文司徒司空亓官司寇仉督子车颛孙端木巫马公西漆雕乐正壤驷公良拓拔夹谷宰父谷粱晋楚闫法汝鄢涂钦段干百里东郭南门呼延妫海羊舌微生岳帅缑亢况後有琴梁丘左丘东门西门商牟佘佴伯赏南宫墨哈谯笪年爱阳佟第五言福付信仝肖";
// **************************
// ******顶部登录表单验证******
// **************************
function loginTopFormCheck() {

  //验证验证码是否输入，输入是否正确
  var topCodeVar = "topCodeVar";
  var topCodeStr = document.getElementById("loginTopValidateCode").value.trim();
  var topCodeUrl = "/source/validate_result_login_top.php?timestamp=" + new Date().getTime();

  if(topCodeStr.length != 4) {
    alert("请输入4个字符的验证码！");
    //document.getElementById("loginTopValidateCode").focus();
    return false;
  } else {
    topCodeReturnStr = topCodeStartXMLHttp(topCodeVar, topCodeStr, topCodeUrl);
    if(topCodeReturnStr == 1) {
      //alert("验证码信息验证通过！");
    } else {
      alert("验证码不正确，请重新输入！提示：不区分大小写，点击验证码图片可换一个验证码！");
      //document.getElementById("registerValidateCode").value = "";
      //document.getElementById("loginTopValidateCode").focus();
      changeImgLoginTop(); //更换验证码
      return false;
    }
  }

  changeImgLoginTop(); //更换验证码

  //验证登录邮箱与登录密码
  var topEmailVar = "topEmailVar";
  var topEmailStr = document.getElementById("loginTopEmail").value;
  var topPasswordVar = "topPasswordVar";
  var topPasswordStr = document.getElementById("loginTopPassword").value.trim();
  var topLoginUrl = "/source/common_check_login_top.php?timestamp=" + new Date().getTime();

  if(!emailReg.test(topEmailStr)) {
    alert("请按邮箱格式正确输入您的登录邮箱地址即注册时的注册邮箱地址！");
    document.getElementById("loginTopPassword").value = "";
    //document.getElementById("loginTopEmail").focus();
    return false;
  }

  if(topPasswordStr.length < 6 || topPasswordStr.length > 18 || !isNaN(topPasswordStr) || topPasswordStr == "请输入登录密码") {
    //验证密码不能为空
    alert("请输入您的登录密码！提示：密码长度至少6位，并且为数字与字符组合。");
    document.getElementById("loginTopPassword").value = "";
    //document.getElementById("loginTopPassword").focus();
    return false;
  } else {
    var topLoginReturnStr = topLoginStartXMLHttp(topEmailVar, topEmailStr, topPasswordVar, topPasswordStr, topLoginUrl);
    if(topLoginReturnStr == 1) {
      //alert("登录信息验证通过，开始体验同学网！");
      //return true;
    } else if(topLoginReturnStr == 2) {
      alert("抱歉，该账号已经失效（已被注销或禁用），目前不可用，无法登录！");
      document.getElementById("loginTopPassword").value = "";
      //document.getElementById("loginTopEmail").focus();
      return false;
    } else if(topLoginReturnStr == 3) {
      //alert("该账号未被激活，请先登录注册邮箱激活该账号后再登录！");
      document.location.href="/login/feedback.php";
      return false;
    } else {
      alert("登录邮箱或登录密码错误，请重新输入！");
      document.getElementById("loginTopPassword").value = "";
      //document.getElementById("loginTopEmail").focus();
      return false;
    }
  }
  //验证成功，禁用提交按钮，避免重复提交
  var loginTopFormBtnText = "<button class='btn' id='loginTopSubmit' name='loginTopSubmit' type='submit' disabled='disabled' style='cursor: wait'>登录</button>";
  document.getElementById("loginTopFormBtn").innerHTML = loginTopFormBtnText;
  //登录信息验证通过，返回真
  return true;
  //登录信息验证通过，提交表单
  //document.getElementById("loginTopForm").submit();

  //建立注册邮箱验证 AJAX 主过程
  function topLoginStartXMLHttp(xmlHttpVarOne, xmlHttpStrOne, xmlHttpVarTwo, xmlHttpStrTwo, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVarOne+"="+xmlHttpStrOne+"&"+xmlHttpVarTwo+"="+xmlHttpStrTwo+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var topLoginReturn = xmlHttp.responseText;
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+topLoginReturn+"");
    return topLoginReturn;
  }

  //建立验证码验证 AJAX 主过程
  function topCodeStartXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var topCodeReturn = xmlHttp.responseText;
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+topCodeReturn+"");
    return topCodeReturn;
  }
}


// **************************
// ******左侧登录表单验证******
// **************************
function loginLeftFormCheck() {

  //验证验证码是否输入，输入是否正确
  var leftCodeVar = "leftCodeVar";
  var leftCodeStr = document.getElementById("loginLeftValidateCode").value.trim();
  var leftCodeUrl = "/source/validate_result_login_left.php?timestamp=" + new Date().getTime();

  if(leftCodeStr.length != 4) {
    alert("请输入4个字符的验证码！");
    //document.getElementById("loginLeftValidateCode").focus();
    return false;
  } else {
    leftCodeReturnStr = leftCodeStartXMLHttp(leftCodeVar, leftCodeStr, leftCodeUrl);
    if(leftCodeReturnStr == 1) {
      //alert("验证码信息验证通过！");
    } else {
      alert("验证码不正确，请重新输入！提示：不区分大小写，点击验证码图片可换一个验证码！");
      //document.getElementById("registerValidateCode").value = "";
      //document.getElementById("loginLeftValidateCode").focus();
      changeImgLoginLeft(); //更换验证码
      return false;
    }
  }

  changeImgLoginLeft(); //更换验证码

  //验证登录邮箱与登录密码
  var leftEmailVar = "leftEmailVar";
  var leftEmailStr = document.getElementById("loginLeftEmail").value;
  var leftPasswordVar = "leftPasswordVar";
  var leftPasswordStr = document.getElementById("loginLeftPassword").value.trim();
  var leftLoginUrl = "/source/common_check_login_left.php?timestamp=" + new Date().getTime();

  if(!emailReg.test(leftEmailStr)) {
    alert("请按邮箱格式正确输入您的登录邮箱地址即注册时的注册邮箱地址！");
    document.getElementById("loginLeftPassword").value = "";
    //document.getElementById("loginLeftEmail").focus();
    return false;
  }

  if(leftPasswordStr.length < 6 || leftPasswordStr.length > 18 || !isNaN(leftPasswordStr) || leftPasswordStr == "请输入登录密码") {
    //验证密码不能为空
    alert("请输入您的登录密码！提示：密码长度至少6位，并且为数字与字符组合。");
    document.getElementById("loginLeftPassword").value = "";
    //document.getElementById("loginLeftPassword").focus();
    return false;
  } else{
    var leftLoginReturnStr = leftLoginStartXMLHttp(leftEmailVar, leftEmailStr, leftPasswordVar, leftPasswordStr, leftLoginUrl);
    if(leftLoginReturnStr == 1) {
      //alert("登录信息验证通过，开始体验同学网！");
      //return true;
    } else if(leftLoginReturnStr == 2) {
      alert("抱歉，该账号已经失效（已被注销或禁用），目前不可用，无法登录！");
      document.getElementById("loginLeftPassword").value = "";
      //document.getElementById("loginLeftEmail").focus();
      return false;
    } else if(leftLoginReturnStr == 3) {
      //alert("该账号未被激活，请先登录注册邮箱激活该账号后再登录！");
      document.location.href="/login/feedback.php";
      return false;
    } else {
      alert("登录邮箱或登录密码错误，请重新输入！");
      document.getElementById("loginLeftPassword").value = "";
      //document.getElementById("loginLeftEmail").focus();
      return false;
    }
  }
  //验证成功，禁用提交按钮，避免重复提交
  var loginLeftFormBtnText = "<button class='btn-large default-color' id='loginLeftSubmit' name='loginLeftSubmit' type='submit' disabled='disabled' style='cursor: wait'>登 录</button>";
  document.getElementById("loginLeftFormBtn").innerHTML = loginLeftFormBtnText;
  //登录信息验证通过，返回真
  return true;
  //登录信息验证通过，提交表单
  //document.getElementById("loginLeftForm").submit();

  //建立注册邮箱验证 AJAX 主过程
  function leftLoginStartXMLHttp(xmlHttpVarOne, xmlHttpStrOne, xmlHttpVarTwo, xmlHttpStrTwo, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVarOne+"="+xmlHttpStrOne+"&"+xmlHttpVarTwo+"="+xmlHttpStrTwo+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var leftLoginReturn = xmlHttp.responseText;
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+leftLoginReturn+"");
    return leftLoginReturn;
  }

  //建立验证码验证 AJAX 主过程
  function leftCodeStartXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");

    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var leftCodeReturn = xmlHttp.responseText;
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+leftCodeReturn+"");
    return leftCodeReturn;
  }
}


// **************************
// ******主页注册表单验证******
// **************************
function registerFormCheck() {

  //验证验证码是否输入，输入是否正确
  var vCodeVar = "vCodeStr";
  var vCodeStr = document.getElementById("registerValidateCode").value.trim();
  var vCodeUrl = "/source/validate_result_register.php?timestamp=" + new Date().getTime();

  if(vCodeStr.length != 4) {
    alert("请输入4个字符的验证码！");
    //document.getElementById("registerValidateCode").focus();
    return false;
  } else {
    vCodeReturnStr = vCodeStartXMLHttp(vCodeVar, vCodeStr, vCodeUrl);
    if(vCodeReturnStr == 1) {
      //alert("注册信息验证通过，欢迎加入同学网！");
    } else {
      alert("验证码不正确，请重新输入！提示：不区分大小写，点击验证码图片可换一个验证码！");
      //document.getElementById("registerValidateCode").value = "";
      //document.getElementById("registerValidateCode").focus();
      changeImg(); //更换验证码
      return false;
    }
  }

  changeImg(); //更换验证码

  //判断是否开启邀请注册 invitationFlag == 1 开启
  if(invitationFlag == 1) {
    //验证注册邀请码是否输入，输入是否正确
    var iCodeVar = "iCodeStr";
    var iCodeStr = document.getElementById("invitationCode").value.trim();
    var iCodeUrl = "/source/common_check_register_invitation_code.php?timestamp=" + new Date().getTime();

    if(iCodeStr.length != 16) {
      alert("请输入16个字符的有效邀请码！提示：区分大小写！");
      //document.getElementById("invitationCode").value = "";
      //document.getElementById("invitationCode").focus();
      return false;
    } else {
      iCodeReturnStr = iCodeStartXMLHttp(iCodeVar, iCodeStr, iCodeUrl);
      if(iCodeReturnStr == 1) {
        //alert("注册信息验证通过，欢迎加入同学网！");
      } else if(iCodeReturnStr == 2) {
        alert("您输入的邀请码已失效，请重新输入有效的邀请码！提示：区分大小写！");
        document.getElementById("invitationCode").value = "";
        //document.getElementById("invitationCode").focus();
        return false;
      } else {
        alert("您输入的邀请码无效，请重新输入有效的邀请码！提示：区分大小写！");
        document.getElementById("invitationCode").value = "";
        //document.getElementById("invitationCode").focus();
        return false;
      }
    }
  } else {
    //验证注册邀请码是否输入，输入是否正确
    var iCodeVar = "iCodeStr";
    var iCodeStr = document.getElementById("invitationCode").value.trim();
    var iCodeUrl = "/source/common_check_register_invitation_code.php?timestamp=" + new Date().getTime();

    if(iCodeStr.length != 16 && iCodeStr.length != 0) {
      alert("请输入16个字符的有效邀请码或留空！提示：区分大小写！");
      //document.getElementById("invitationCode").value = "";
      //document.getElementById("invitationCode").focus();
      return false;
    } else {
      if(iCodeStr.length == 16) {
        iCodeReturnStr = iCodeStartXMLHttp(iCodeVar, iCodeStr, iCodeUrl);
        if(iCodeReturnStr == 1) {
          //alert("注册信息验证通过，欢迎加入同学网！");
        } else if(iCodeReturnStr == 2) {
          alert("您输入的邀请码已失效，请重新输入有效的邀请码或留空！提示：区分大小写！");
          document.getElementById("invitationCode").value = "";
          //document.getElementById("invitationCode").focus();
          return false;
        } else {
          alert("您输入的邀请码无效，请重新输入有效的邀请码或留空！提示：区分大小写！");
          document.getElementById("invitationCode").value = "";
          //document.getElementById("invitationCode").focus();
          return false;
        }
	  }
    }
  }

  //验证注册邮件
  var emailVar = "emailVar";
  var emailStr = document.getElementById("registerEmail").value;
  var emailUrl = "/source/common_check_register_email.php?timestamp=" + new Date().getTime();

  if(!emailReg.test(emailStr)) {
    alert("请按邮箱格式正确输入有效的邮箱地址！");
    //document.getElementById("registerEmail").focus();
    return false;
  } else {
    var rEmailReturnStr = rEmailStartXMLHttp(emailVar, emailStr, emailUrl);
    if(rEmailReturnStr != 1) {
      //alert("该邮箱还未注册过，请继续注册，欢迎加入同学网！");
    } else if(rEmailReturnStr == 2) {
      alert("该邮箱已在本站注册过，请直接登录或使用未注册过的邮箱进行注册！");
      //document.getElementById("registerEmail").value = "";
      //document.getElementById("confirmRegisterEmail").value = "";
      return false;
    } else if(rEmailReturnStr == 1) {
      //alert("该邮箱已在本站注册过，但处于未激活状态，请使用该邮箱激活账号后即可直接登录本站！");
      document.location.href="/register/feedback.php";
      return false;
    }
  }

  //确认注册邮件
  var confirmEmail = document.getElementById("confirmRegisterEmail").value;
  if(confirmEmail.trim().length == 0 || confirmEmail == "请重复输入邮箱地址") {
    alert("确认邮箱地址为空，请输入确认邮箱地址！");
    //document.getElementById("confirmRegisterEmail").focus();
    return false;
  }
  if(confirmEmail != emailStr) {
    alert("两次输入邮箱地址不同，请重新输入！");
    //document.getElementById("confirmRegisterEmail").focus();
    return false;
  }

  //验证注册密码
  var password = document.getElementById("password").value.trim();
  if(password.length < 6 || password.length > 18 || !isNaN(password) || password == "请输入注册密码")
  {
    alert("请输入6-18位数字与字母组合字符的注册密码作为登录密码！");
    document.getElementById("password").value = "";
    document.getElementById("confirmPassword").value = "";
    //document.getElementById("password").focus();
    return false;
  }

  //确认注册密码
  var confirmPassword = document.getElementById("confirmPassword").value.trim();
  if(confirmPassword.length == 0 || confirmPassword == "请重复输入注册密码") {
    alert("确认密码为空，请输入确认密码！");
    //document.getElementById("confirmPassword").focus();
    return false;
  }
  if(confirmPassword != password)
  {
    alert("两次输入密码不同，请重新输入！");
    document.getElementById("confirmPassword").value = "";
    //document.getElementById("confirmPassword").focus();
    return false;
  }

  //验证网络昵称
  if(document.getElementById("nickname").value.trim().length < 2 || document.getElementById("nickname").value == "请输入您的网络呢称")
  {
    alert("网络昵称不得少于2个字符，请重新输入！");
    //document.getElementById("nickname").focus();
    return false;
  }

  //验证真实姓名
  var strRealName = document.getElementById("realName").value;
  var strFirstName = strRealName.substr(0, 1);
  var sur = surnameReg.search(strFirstName);
  if(strRealName.trim().length < 2 || !strRealName.match(chineseReg) || sur == -1 || strRealName == "请输入您的真实姓名")
  {
    alert("请认真输入真实姓名，注册后不可修改！");
    //document.getElementById("realName").focus();
    return false;
  }

  //验证性别选择
  if(document.getElementById("gender").value < 1 || document.getElementById("gender").value > 2)
  {
    alert("请选择性别！");
    //document.getElementById("gender").focus();
    return false;
  }

  //验证大学选择（大学名称）
  if(document.getElementById("universityName").value.length < 4 || document.getElementById("universityName").value == "选择大学 / 之后不准修改")
  {
    alert("请选择我的大学！");
    //document.getElementById("universityName").focus();
    return false;
  }

  //验证大学选择（大学编码）
  if(document.getElementById("universityId").value < 1)
  {
    alert("请选择我的大学！");
    //document.getElementById("universityName").focus();
    return false;
  }

  //验证院系选择
  if(document.getElementById("departmentId").value < 1)
  {
    alert("请选择所属院系！");
    //document.getElementById("departmentId").focus();
    return false;
  }

  //验证专业名称
  if(document.getElementById("professionName").value.trim().length < 2 || document.getElementById("professionName").value == "请输入所学专业全名")
  {
    alert("专业名称不得少于2个字符，请重新输入！");
    //document.getElementById("professionName").focus();
    return false;
  }

  //验证入学年份选择
  if(document.getElementById("startSchoolYear").value < 1949 || document.getElementById("startSchoolYear").value > 2099)
  {
    alert("请选择入学年份！");
    //document.getElementById("startSchoolYear").focus();
    return false;
  }

  //验证最高学历选择
  if(document.getElementById("degree").value < 1 || document.getElementById("degree").value > 5)
  {
    alert("请选择最高学历！");
    //document.getElementById("degree").focus();
    return false;
  }

  //验证选择生日类别
  if(document.getElementById("birthdayFlag").value < 1 || document.getElementById("birthdayFlag").value > 2)
  {
    alert("请选择生日类别！");
    //document.getElementById("birthdayFlag").focus();
    return false;
  }

  //验证我的生日选择
  if(document.getElementById("birthdayYear").value < 1949 || document.getElementById("birthdayYear").value > 2099)
  {
    alert("请选择出生年！");
    //document.getElementById("birthdayYear").focus();
    return false;
  }
  if(document.getElementById("birthdayMonth").value < 1 || document.getElementById("birthdayMonth").value > 13)
  {
    alert("请选择出生月！");
    //document.getElementById("birthdayMonth").focus();
    return false;
  }
  if(document.getElementById("birthdayDay").value < 1 || document.getElementById("birthdayDay").value > 31)
  {
    alert("请选择出生日！");
    //document.getElementById("birthdayDay").focus();
    return false;
  }

  //验证我的家乡选择
  if(document.getElementById("hometownProvince").value < 1 || document.getElementById("hometownProvince").value > 35)
  {
    alert("请选择家乡所属省份！");
    //document.getElementById("hometownProvince").focus();
    return false;
  }
  if(document.getElementById("hometownCity").value < 1 || document.getElementById("hometownCity").value > 439)
  {
    alert("请选择家乡所属市州！");
    //document.getElementById("hometownCity").focus();
    return false;
  }
  if(document.getElementById("hometownCounty").value < 1 || document.getElementById("hometownCounty").value > 3296)
  {
    alert("请选择家乡所属区县！");
    //document.getElementById("hometownCounty").focus();
    return false;
  }

  //验证现居住地选择
  if(document.getElementById("habitationProvince").value < 1 || document.getElementById("habitationProvince").value > 35)
  {
    alert("请选择现居住地所属省份！");
    //document.getElementById("habitationProvince").focus();
    return false;
  }
  if(document.getElementById("habitationCity").value < 1 || document.getElementById("habitationCity").value > 439)
  {
    alert("请选择现居住地所属市州！");
    //document.getElementById("habitationCity").focus();
    return false;
  }
  if(document.getElementById("habitationCounty").value < 1 || document.getElementById("habitationCounty").value > 3296)
  {
    alert("请选择现居住地所属区县！");
    //document.getElementById("habitationCounty").focus();
    return false;
  }

  //验证是否同意《同学网服务条款》
  if(document.getElementById("serviceTerm").checked != true) {
    alert("抱歉，您只有阅读并同意《同学网服务条款》后才可以完成注册！");
    //document.getElementById("serviceTerm").focus();
    return false;
  }

  //验证成功，禁用提交按钮，避免重复提交
  var registerFormBtnText = "<button class='btn-large default-color' id='registerSubmit' name='registerSubmit' type='submit' disabled='disabled' style='cursor: wait'>立 即 免 费 注 册</button>";
  document.getElementById("registerFormBtn").innerHTML = registerFormBtnText;
  //注册信息验证通过，返回真
  return true;
  //注册信息验证通过，提交表单
  //document.getElementById("registerForm").submit();

  //建立验证码验证 AJAX 主过程
  function vCodeStartXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    /*
    xmlHttp.onreadystatechange = function() {
      if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        var vCodeResult = xmlHttp.responseText;

        if(vCodeResult == 1) {
          alert("注册信息验证通过，欢迎加入同学网！");
          document.getElementById("registerForm").submit();
        } else {
          alert("验证码不正确，请重新输入！提示：不区分大小写，点击验证码图片可换一个验证码！");
        }
      }
    }
    */
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var vCodeReturn = xmlHttp.responseText;
    //alert(""+vCodeReturn+"");
    return vCodeReturn;
  }

  //建立验证码验证 AJAX 主过程
  function iCodeStartXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var iCodeReturn = xmlHttp.responseText;
    //alert(""+vCodeReturn+"");
    return iCodeReturn;
  }

  //建立注册邮箱验证 AJAX 主过程
  function rEmailStartXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    /*
    xmlHttp.onreadystatechange = function() {
      if(xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        var rEmailResult = xmlHttp.responseText;

        if(rEmailResult == 1) {
          //alert("该邮箱还未注册过，请继续注册，欢迎加入同学网！");
          //alert("返回参数为："+rEmailResult+"");
        } else {
          alert("该邮箱已经注册过，请直接登录或重新使用未注册过的邮箱进行注册！");
          document.getElementById("registerEmail").value = "";
          //alert("返回参数为："+rEmailResult+"");
        }
      }
    }
    */
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var rEmailReturn = xmlHttp.responseText;
    //alert(""+rEmailReturn+"");
    return rEmailReturn;
  }
}

// *********************************
// ******根据生日类型查询生日年份******
// *********************************
function myBDYSelectStartXMLHttp() {
  //验证选择生日类别
  if(document.getElementById("birthdayFlag").value < 1 || document.getElementById("birthdayFlag").value > 2)
  {
    if(navigator.appName == "Microsoft Internet Explorer" && (navigator.appVersion.indexOf("MSIE 8.") > 0 || navigator.appVersion.indexOf("MSIE 7.") > 0 || navigator.appVersion.indexOf("MSIE 6.") > 0)) {
      //alert (navigator.appName);
      //alert (navigator.appVersion);
      var myBDYSelectText = "<select class='register-select-three' id='birthdayYear' name='birthdayYear'><option value='-1' selected>年</option></select>";
      var myBDMSelectText = "<select class='register-select-three' id='birthdayMonth' name='birthdayMonth'><option value='-1' selected>月</option></select>";
      var myBDDSelectText = "<select class='register-select-three' id='birthdayDay' name='birthdayDay'><option value='-1' selected>日</option></select>";
    } else {
      var myBDYSelectText = "<select class='span4 input-block-level' id='birthdayYear' name='birthdayYear'><option value='-1' selected>年</option></select>";
      var myBDMSelectText = "<select class='span4 input-block-level' id='birthdayMonth' name='birthdayMonth'><option value='-1' selected>月</option></select>";
      var myBDDSelectText = "<select class='span4 input-block-level' id='birthdayDay' name='birthdayDay'><option value='-1' selected>日</option></select>";
    }
    document.getElementById("birthdayYearSel").innerHTML = myBDYSelectText;
    document.getElementById("birthdayMonthSel").innerHTML = myBDMSelectText;
    document.getElementById("birthdayDaySel").innerHTML = myBDDSelectText;
    return false;
  }
  //判断是否已选择生日类别
  var xmlHttpStrOne = "birthdayFlag";
  var xmlHttpVarOne = document.getElementById("birthdayFlag").value;
  //alert(xmlHttpVar);
  var xmlHttpUrl = "/source/common_calendar_convert.php?timestamp=" + new Date().getTime();
  //建立xmlHttp 对象
  var returnCreateXMLHttp = createXMLHttp();
  //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
  if(returnCreateXMLHttp) {
    //alert("创建 XMLHTTP 成功");
  } else {
    alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
    return false;
  }
  var send_string = ""+xmlHttpStrOne+"="+xmlHttpVarOne+"&timestampCheck=" + new Date().getTime();
  var send_url = xmlHttpUrl;
  send_string = encodeURI(send_string);
  //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
  xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
  xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlHttp.setRequestHeader("Charset", "utf-8");
  xmlHttp.setRequestHeader("Cache-control", "no-cache");
  xmlHttp.setRequestHeader("Content-length", send_string.length);
  xmlHttp.setRequestHeader("Connection", "close");
  xmlHttp.send(send_string); //发送
  myBDYSelectShow();
}
// ******展现查询生日年份结果******
function myBDYSelectShow() {
  if(navigator.appName == "Microsoft Internet Explorer" && (navigator.appVersion.indexOf("MSIE 8.") > 0 || navigator.appVersion.indexOf("MSIE 7.") > 0 || navigator.appVersion.indexOf("MSIE 6.") > 0)) {
    var myBDMSelectText = "<select class='register-select-three' id='birthdayMonth' name='birthdayMonth'><option value='-1' selected>月</option></select>";
    var myBDDSelectText = "<select class='register-select-three' id='birthdayDay' name='birthdayDay'><option value='-1' selected>日</option></select>";
  } else {
    var myBDMSelectText = "<select class='span4 input-block-level' id='birthdayMonth' name='birthdayMonth'><option value='-1' selected>月</option></select>";
    var myBDDSelectText = "<select class='span4 input-block-level' id='birthdayDay' name='birthdayDay'><option value='-1' selected>日</option></select>";
  }
  document.getElementById("birthdayYearSel").innerHTML = xmlHttp.responseText;
  document.getElementById("birthdayMonthSel").innerHTML = myBDMSelectText;
  document.getElementById("birthdayDaySel").innerHTML = myBDDSelectText;
}

// *********************************
// ******根据生日年份查询生日月份******
// *********************************
function myBDMSelectStartXMLHttp() {
  //判断是否已选择生日类别
  if(document.getElementById("birthdayYear").value < 1891 || document.getElementById("birthdayYear").value > 2100)
  {
    if(navigator.appName == "Microsoft Internet Explorer" && (navigator.appVersion.indexOf("MSIE 8.") > 0 || navigator.appVersion.indexOf("MSIE 7.") > 0 || navigator.appVersion.indexOf("MSIE 6.") > 0)) {
      var myBDMSelectText = "<select class='register-select-three' id='birthdayMonth' name='birthdayMonth'><option value='-1' selected>月</option></select>";
      var myBDDSelectText = "<select class='register-select-three' id='birthdayDay' name='birthdayDay'><option value='-1' selected>日</option></select>";
    } else {
      var myBDMSelectText = "<select class='span4 input-block-level' id='birthdayMonth' name='birthdayMonth'><option value='-1' selected>月</option></select>";
      var myBDDSelectText = "<select class='span4 input-block-level' id='birthdayDay' name='birthdayDay'><option value='-1' selected>日</option></select>";
    }
    document.getElementById("birthdayMonthSel").innerHTML = myBDMSelectText;
    document.getElementById("birthdayDaySel").innerHTML = myBDDSelectText;
    return false;
  }
  var xmlHttpStrOne = "birthdayFlag";
  var xmlHttpVarOne = document.getElementById("birthdayFlag").value;
  var xmlHttpStrTwo = "birthdayYear";
  var xmlHttpVarTwo = document.getElementById("birthdayYear").value;
  //alert(xmlHttpVar);
  var xmlHttpUrl = "/source/common_calendar_convert.php?timestamp=" + new Date().getTime();
  //建立xmlHttp 对象
  var returnCreateXMLHttp = createXMLHttp();
  //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
  if(returnCreateXMLHttp) {
    //alert("创建 XMLHTTP 成功");
  } else {
    alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
    return false;
  }
  var send_string = ""+xmlHttpStrOne+"="+xmlHttpVarOne+"&"+xmlHttpStrTwo+"="+xmlHttpVarTwo+"&timestampCheck=" + new Date().getTime();
  var send_url = xmlHttpUrl;
  send_string = encodeURI(send_string);
  //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
  xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
  xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlHttp.setRequestHeader("Charset", "utf-8");
  xmlHttp.setRequestHeader("Cache-control", "no-cache");
  xmlHttp.setRequestHeader("Content-length", send_string.length);
  xmlHttp.setRequestHeader("Connection", "close");
  xmlHttp.send(send_string); //发送
  myBDMSelectShow();
}
// ******展现查询生日月份结果******
function myBDMSelectShow() {
  if(navigator.appName == "Microsoft Internet Explorer" && (navigator.appVersion.indexOf("MSIE 8.") > 0 || navigator.appVersion.indexOf("MSIE 7.") > 0 || navigator.appVersion.indexOf("MSIE 6.") > 0)) {
    var myBDDSelectText = "<select class='register-select-three' id='birthdayDay' name='birthdayDay'><option value='-1' selected>日</option></select>";
  } else {
    var myBDDSelectText = "<select class='span4 input-block-level' id='birthdayDay' name='birthdayDay'><option value='-1' selected>日</option></select>";
  }
  document.getElementById("birthdayMonthSel").innerHTML = xmlHttp.responseText;
  document.getElementById("birthdayDaySel").innerHTML = myBDDSelectText;
}

// *********************************
// ******根据生日年月查询生日日期******
// *********************************
function myBDDSelectStartXMLHttp() {
  var xmlHttpStrOne = "birthdayFlag";
  var xmlHttpVarOne = document.getElementById("birthdayFlag").value;
  var xmlHttpStrTwo = "birthdayYear";
  var xmlHttpVarTwo = document.getElementById("birthdayYear").value;
  var xmlHttpStrThree = "birthdayMonth";
  var xmlHttpVarThree = document.getElementById("birthdayMonth").value;
  //alert(xmlHttpVar);
  var xmlHttpUrl = "/source/common_calendar_convert.php?timestamp=" + new Date().getTime();
  //建立xmlHttp 对象
  var returnCreateXMLHttp = createXMLHttp();
  //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
  if(returnCreateXMLHttp) {
    //alert("创建 XMLHTTP 成功");
  } else {
    alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
    return false;
  }
  var send_string = ""+xmlHttpStrOne+"="+xmlHttpVarOne+"&"+xmlHttpStrTwo+"="+xmlHttpVarTwo+"&"+xmlHttpStrThree+"="+xmlHttpVarThree+"&timestampCheck=" + new Date().getTime();
  var send_url = xmlHttpUrl;
  send_string = encodeURI(send_string);
  //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
  xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
  xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xmlHttp.setRequestHeader("Charset", "utf-8");
  xmlHttp.setRequestHeader("Cache-control", "no-cache");
  xmlHttp.setRequestHeader("Content-length", send_string.length);
  xmlHttp.setRequestHeader("Connection", "close");
  xmlHttp.send(send_string); //发送
  myBDDSelectShow();
}
// ******展现查询生日日期结果******
function myBDDSelectShow() {
  document.getElementById("birthdayDaySel").innerHTML = xmlHttp.responseText;
}




// ************************************
// ******检查提交激活邮箱地址是否有效******
// ************************************
function sendActEmailCheck() {

  //验证激活账号邮箱
  var emailVar = "emailVar";
  var emailStr = document.getElementById("registerEmail").value;
  var emailUrl = "/source/common_check_email_send_activation.php?timestamp=" + new Date().getTime();

  if(!emailReg.test(emailStr)) {
    alert("请按邮箱格式正确输入有效的邮箱地址！");
    //document.getElementById("registerEmail").focus();
    return false;
  } else {
    var rEmailReturnStr = rEmailStartXMLHttp(emailVar, emailStr, emailUrl);
    if(rEmailReturnStr == 1) {
      //alert("该邮箱已经注册过，但未被激活，符合重发激活账号邮件条件！");
      //验证成功，禁用提交按钮，避免重复提交
      var sendActEmailBtnText = "<a class='btn send-email-submit' type='submit' style='cursor: wait'>正在发送电子邮件中...</a>";
      document.getElementById("sendActEmailBtn").innerHTML = sendActEmailBtnText;
      return true;
    } else if(rEmailReturnStr == 2) {
      alert("该邮箱账号已经成功激活，请直接登录，如果忘记了登录密码，请使用找回密码功能！");
      //document.getElementById("registerEmail").value = "";
      //document.getElementById("registerEmail").focus();
      return false;
    } else {
      alert("该邮箱账号还未在本站注册过，邮箱账号无效！");
      //document.getElementById("registerEmail").value = "";
      //document.getElementById("registerEmail").focus();
      return false;
    }
  }

  //建立注册邮箱验证 AJAX 主过程
  function rEmailStartXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var rEmailReturn = xmlHttp.responseText;
    //alert(""+rEmailReturn+"");
    return rEmailReturn;
  }
}




// ****************************************
// ******检查提交重置密码邮箱地址是否有效******
// ****************************************
function sendResetEmailCheck() {

  //验证重置密码邮箱
  var emailVar = "emailVar";
  var emailStr = document.getElementById("registerEmail").value;
  var emailUrl = "/source/common_check_email_send_resetpass.php?timestamp=" + new Date().getTime();

  if(!emailReg.test(emailStr)) {
    alert("请按邮箱格式正确输入有效的邮箱地址！");
    //document.getElementById("registerEmail").focus();
    return false;
  } else {
    var rEmailReturnStr = rEmailStartXMLHttp(emailVar, emailStr, emailUrl);
    if(rEmailReturnStr == 1) {
      alert("该邮箱账号未被激活，请先登录注册邮箱激活该账号后再做此操作！");
      //document.location.href="/activation/sendemail.php";
      return false;
    } else if(rEmailReturnStr == 2) {
      //alert("该邮箱账号为有效账号，可以进行重置密码操作！");
      //验证成功，禁用提交按钮，避免重复提交
      var sendResetEmailBtnText = "<a class='btn send-email-submit' type='submit' style='cursor: wait'>正在发送电子邮件中...</a>";
      document.getElementById("sendResetEmailBtn").innerHTML = sendResetEmailBtnText;
      return true;
    } else if(rEmailReturnStr == 3) {
      alert("该邮箱账号已经失效，不可进行重置密码操作！");
      //document.getElementById("registerEmail").value = "";
      //document.getElementById("registerEmail").focus();
      return false;
    } else {
      alert("该邮箱账号还未在本站注册过，邮箱账号无效！");
      //document.getElementById("registerEmail").value = "";
      //document.getElementById("registerEmail").focus();
      return false;
    }
  }

  //建立注册邮箱验证 AJAX 主过程
  function rEmailStartXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var rEmailReturn = xmlHttp.responseText;
    //alert(""+rEmailReturn+"");
    return rEmailReturn;
  }
}




// *******************************
// ******检查提交新密码是否有效******
// *******************************
function resetPasswordCheck() {
  //验证账号新密码
  var newPassword = document.getElementById("newPassword").value.trim();
  if(newPassword.length < 6 || newPassword.length > 18 || !isNaN(newPassword) || newPassword == "请输入新密码")
  {
    alert("请输入6-18位数字与字母组合字符的新密码！");
    document.getElementById("newPassword").value = "";
    document.getElementById("confirmNewPassword").value = "";
    //document.getElementById("newPassword").focus();
    return false;
  }
  //确认账号新密码
  var confirmNewPassword = document.getElementById("confirmNewPassword").value.trim();
  if(confirmNewPassword.length == 0 || confirmNewPassword == "请确认新密码") {
    alert("确认密码为空，请输入确认密码！");
    //document.getElementById("confirmNewPassword").focus();
    return false;
  }
  if(confirmNewPassword != newPassword)
  {
    alert("两次输入密码不同，请重新输入！");
    document.getElementById("confirmNewPassword").value = "";
    //document.getElementById("confirmNewPassword").focus();
    return false;
  }
  //验证成功，禁用提交按钮，避免重复提交
  var resetPasswordFormBtnText = "<a class='btn reset-password-submit' type='submit' style='cursor: wait'>重置密码中...</a>";
  document.getElementById("resetPasswordFormBtn").innerHTML = resetPasswordFormBtnText;
  //验证通过，返回真
  return true;
}


// ************************************
// ******检查更改密码提交信息是否正确******
// ************************************
function changePasswordCheck() {

  //验证账号原密码
  var oldPassVar = "oldPassVar";
  var oldPassStr = document.getElementById("oldPassword").value.trim();
  var oldPassUrl = "/source/common_check_change_password.php?timestamp=" + new Date().getTime();

  if(oldPassStr.length < 6 || oldPassStr.length > 18 || !isNaN(oldPassStr) || oldPassStr == "请输入原密码")
  {
    alert("请输入6-18位数字与字母组合字符的原密码！");
    document.getElementById("oldPassword").value = "";
    document.getElementById("newPassword").value = "";
    document.getElementById("confirmNewPassword").value = "";
    //document.getElementById("oldPassword").focus();
    return false;
  } else {
    var oldPassReturnStr = oldPassStartXMLHttp(oldPassVar, oldPassStr, oldPassUrl);
    if(oldPassReturnStr == 1) {
      //alert("原密码验证通过，进行更改密码操作！");
      //return true;
    } else {
      alert("原密码不正确，请重新输入！");
      document.getElementById("oldPassword").value = "";
      document.getElementById("newPassword").value = "";
      document.getElementById("confirmNewPassword").value = "";
      //document.getElementById("oldPassword").focus();
      return false;
    }
  }

  //验证账号新密码
  var newPassword = document.getElementById("newPassword").value.trim();
  if(newPassword.length < 6 || newPassword.length > 18 || !isNaN(newPassword) || newPassword == "请输入新密码")
  {
    alert("请输入6-18位数字与字母组合字符的新密码！");
    document.getElementById("newPassword").value = "";
    document.getElementById("confirmNewPassword").value = "";
    //document.getElementById("newPassword").focus();
    return false;
  }

  //确认账号新密码
  var confirmNewPassword = document.getElementById("confirmNewPassword").value.trim();
  if(confirmNewPassword.length == 0 || confirmNewPassword == "请确认新密码") {
    alert("确认密码为空，请输入确认密码！");
    //document.getElementById("confirmNewPassword").focus();
    return false;
  }
  if(confirmNewPassword != newPassword)
  {
    alert("确认密码不正确，请重新输入！");
    document.getElementById("confirmNewPassword").value = "";
    //document.getElementById("confirmNewPassword").focus();
    return false;
  }

  //验证成功，禁用提交按钮，避免重复提交
  var changePasswordFormBtnText = "<a class='btn reset-password-submit' type='submit' style='cursor: wait'>更改密码中...</a>";
  document.getElementById("changePasswordFormBtn").innerHTML = changePasswordFormBtnText;
  //验证通过，返回真
  return true;

  //建立注册邮箱验证 AJAX 主过程
  function oldPassStartXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"");
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var oldPassReturn = xmlHttp.responseText;
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+oldPassReturn+"");
    return oldPassReturn;
  }
}


// *********************************
// ******检查提交反馈意见是否有效******
// *********************************
function ideaFeedbackCheck() {
  //反馈主题验证
  if(document.getElementById("feedbackSubject").value.trim().length < 10) {
    alert("反馈主题至少10个字符！");
    //document.getElementById("feedbackSubject").focus();
    return false;
  }
  //反馈类型验证
  if(document.getElementById("feedbackType").value < 1) {
    alert("请选择反馈类型！");
    //document.getElementById("feedbackType").focus();
    return false;
  }
  //反馈内容验证
  var feedbackContentLen = document.getElementById("feedbackContent").value.trim().length;
  if(feedbackContentLen < 20) {
    alert("反馈内容至少20个字符，请详细输入反馈内容！");
    //document.getElementById("feedbackContent").focus();
    return false;
  } else if(feedbackContentLen > 512) {
    alert("反馈内容最多512个字符，您输入的字符数过多！");
    //document.getElementById("feedbackContent").focus();
    return false;
  }
  //联系邮箱地址验证
  var contactEmailStr = document.getElementById("contactEmail").value;
  if(!emailReg.test(contactEmailStr)) {
    alert("请按邮箱格式正确输入有效的邮箱地址！");
    //document.getElementById("contactEmail").focus();
    return false;
  }
  //定义公用变量
  var feedbackOptStr = "好的意见建议有礼物送哦 {选填}";
  //联系QQ号码验证
  var qqNumberStr = document.getElementById("qqNumber").value;
  if(qqNumberStr == feedbackOptStr) {
    //值为空，验证通过
  } else if(qqNumberStr != "" && !qqReg.test(qqNumberStr)) {
    alert("QQ号码为5-11位纯数字，请正确填写QQ号码或留空！");
    //document.getElementById("qqNumber").focus();
    return false;
  }
  //联系手机号码验证
  var mobileNumberStr = document.getElementById("mobileNumber").value;
  if(mobileNumberStr == feedbackOptStr) {
    //值为空，验证通过
  } else if(mobileNumberStr != "" && !mobileReg.test(mobileNumberStr)) {
    alert("不是正确的11位手机号码，请正确填写手机号码或留空！");
    //document.getElementById("mobileNumber").focus();
    return false;
  }
  //联系电话号码验证
  var telephoneNumberStr = document.getElementById("telephoneNumber").value;
  if(telephoneNumberStr == feedbackOptStr) {
    //值为空，验证通过
  } else if(telephoneNumberStr != "" && !telephoneReg.test(telephoneNumberStr)) {
    alert("电话号码为 {区号+电话号码，如：010-81818888} 字符串，请正确填写电话号码或留空！");
    //document.getElementById("telephoneNumber").focus();
    return false;
  }
  //验证通过，返回真
  return true;
}

// *********************************
// ******检查提交举报信息是否有效******
// *********************************
function reportCheck() {
  //举报链接地址验证
  if(document.getElementById("reportLink").value.indexOf('www.chinatongxue.com') < 0) {
    alert("请输入正确的链接地址，只限输入本站链接地址！");
    //document.getElementById("reportLink").focus();
    return false;
  }
  //举报说明信息验证
  var reportContentLen = document.getElementById("reportContent").value.trim().length;
  if(reportContentLen < 20) {
    alert("举报说明至少20个字符，请详细输入举报原因！");
    //document.getElementById("reportContent").focus();
    return false;
  } else if(reportContentLen > 140) {
    alert("举报说明最多140个字符，您输入的字符数过多！");
    //document.getElementById("reportContent").focus();
    return false;
  }
  //定义公用变量
  var reportOptStr = "请留下联系方式以便快速回复 {选填}";
  //联系QQ号码验证
  var qqNumberStr = document.getElementById("qqNumber").value;
  if(qqNumberStr == reportOptStr) {
    //值为空，验证通过
  } else if(qqNumberStr != "" && !qqReg.test(qqNumberStr)) {
    alert("QQ号码为5-11位纯数字，请正确填写QQ号码或留空！");
    //document.getElementById("qqNumber").focus();
    return false;
  }
  //联系手机号码验证
  var mobileNumberStr = document.getElementById("mobileNumber").value;
  if(mobileNumberStr == reportOptStr) {
    //值为空，验证通过
  } else if(mobileNumberStr != "" && !mobileReg.test(mobileNumberStr)) {
    alert("不是正确的11位手机号码，请正确填写手机号码或留空！");
    //document.getElementById("mobileNumber").focus();
    return false;
  }
  //联系电话号码验证
  var telephoneNumberStr = document.getElementById("telephoneNumber").value;
  if(telephoneNumberStr == reportOptStr) {
    //值为空，验证通过
  } else if(telephoneNumberStr != "" && !telephoneReg.test(telephoneNumberStr)) {
    alert("电话号码为 {区号+电话号码，如：010-81818888} 字符串，请正确填写电话号码或留空！");
    //document.getElementById("telephoneNumber").focus();
    return false;
  }
  //验证通过，返回真
  return true;
}




// ************************************
// ******检查个人档案更新信息是否有效******
// ************************************
function profileFormCheck() {

  //验证用户账号（注册邮箱）
  var emailStr = document.getElementById("registerEmail").value;
  if(!emailReg.test(emailStr)) {
    alert("请按邮箱格式正确输入有效的邮箱地址！");
    //document.getElementById("registerEmail").focus();
    return false;
  }

  //验证网络昵称
  if(document.getElementById("nickname").value.trim().length < 2)
  {
    alert("网络昵称不得少于2个字符，请重新输入！");
    //document.getElementById("nickname").focus();
    return false;
  }

  //验证真实姓名
  var strRealName = document.getElementById("realName").value;
  var strFirstName = strRealName.substr(0, 1);
  var sur = surnameReg.search(strFirstName);
  if(strRealName.trim().length < 2 || !strRealName.match(chineseReg) || sur == -1)
  {
    alert("请认真输入真实姓名，姓名只能由两个以上上汉字组成！");
    //document.getElementById("realName").focus();
    return false;
  }

  //验证性别选择
  if(document.getElementById("gender").value < 1 || document.getElementById("gender").value > 2)
  {
    alert("请选择性别！");
    //document.getElementById("gender").focus();
    return false;
  }

  //验证选择生日类别
  if(document.getElementById("birthdayFlag").value < 1 || document.getElementById("birthdayFlag").value > 2)
  {
    alert("请选择生日类别！");
    //document.getElementById("birthdayFlag").focus();
    return false;
  }

  //验证我的生日选择
  if(document.getElementById("birthdayYear").value < 1949 || document.getElementById("birthdayYear").value > 2099)
  {
    alert("请选择出生年！");
    //document.getElementById("birthdayYear").focus();
    return false;
  }
  if(document.getElementById("birthdayMonth").value < 1 || document.getElementById("birthdayMonth").value > 13)
  {
    alert("请选择出生月！");
    //document.getElementById("birthdayMonth").focus();
    return false;
  }
  if(document.getElementById("birthdayDay").value < 1 || document.getElementById("birthdayDay").value > 31)
  {
    alert("请选择出生日！");
    //document.getElementById("birthdayDay").focus();
    return false;
  }

  //验证我的家乡选择
  if(document.getElementById("hometownProvince").value < 1 || document.getElementById("hometownProvince").value > 35)
  {
    alert("请选择家乡所属省份！");
    //document.getElementById("hometownProvince").focus();
    return false;
  }
  if(document.getElementById("hometownCity").value < 1 || document.getElementById("hometownCity").value > 439)
  {
    alert("请选择家乡所属市州！");
    //document.getElementById("hometownCity").focus();
    return false;
  }
  if(document.getElementById("hometownCounty").value < 1 || document.getElementById("hometownCounty").value > 3296)
  {
    alert("请选择家乡所属区县！");
    //document.getElementById("hometownCounty").focus();
    return false;
  }

  //验证现居住地选择
  if(document.getElementById("habitationProvince").value < 1 || document.getElementById("habitationProvince").value > 35)
  {
    alert("请选择现居住地所属省份！");
    //document.getElementById("habitationProvince").focus();
    return false;
  }
  if(document.getElementById("habitationCity").value < 1 || document.getElementById("habitationCity").value > 439)
  {
    alert("请选择现居住地所属市州！");
    //document.getElementById("habitationCity").focus();
    return false;
  }
  if(document.getElementById("habitationCounty").value < 1 || document.getElementById("habitationCounty").value > 3296)
  {
    alert("请选择现居住地所属区县！");
    //document.getElementById("habitationCounty").focus();
    return false;
  }

  //验证院系选择
  if(document.getElementById("departmentId").value < 1)
  {
    alert("请选择所属院系！");
    //document.getElementById("departmentId").focus();
    return false;
  }

  //验证专业名称
  if(document.getElementById("professionName").value.trim().length < 2)
  {
    alert("专业名称不得少于2个字符，请重新输入！");
    //document.getElementById("professionName").focus();
    return false;
  }

  //验证入学年份选择
  if(document.getElementById("startSchoolYear").value < 1949 || document.getElementById("startSchoolYear").value > 2099)
  {
    alert("请选择入学年份！");
    //document.getElementById("startSchoolYear").focus();
    return false;
  }

  //验证最高学历选择
  if(document.getElementById("degree").value < 1 || document.getElementById("degree").value > 5)
  {
    alert("请选择最高学历！");
    //document.getElementById("degree").focus();
    return false;
  }

  //验证用户个人信息（兴趣与爱好）
  if(document.getElementById("perInterests").value.length > 256)
  {
    alert("“兴趣与爱好”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perInterests").focus();
    return false;
  }

  //验证用户个人信息（喜欢的书籍）
  if(document.getElementById("perBooks").value.length > 256)
  {
    alert("“喜欢的书籍”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perBooks").focus();
    return false;
  }

  //验证用户个人信息（喜欢的电影）
  if(document.getElementById("perMovies").value.length > 256)
  {
    alert("“喜欢的电影”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perMovies").focus();
    return false;
  }

  //验证用户个人信息（喜欢的电视剧）
  if(document.getElementById("perTvSeries").value.length > 256)
  {
    alert("“喜欢的电视剧”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perTvSeries").focus();
    return false;
  }

  //验证用户个人信息（喜欢的音乐）
  if(document.getElementById("perMusic").value.length > 256)
  {
    alert("“喜欢的音乐”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perMusic").focus();
    return false;
  }

  //验证用户个人信息（喜欢的游戏）
  if(document.getElementById("perGames").value.length > 256)
  {
    alert("“喜欢的游戏”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perGames").focus();
    return false;
  }

  //验证用户个人信息（喜欢的动漫）
  if(document.getElementById("perAnimations").value.length > 256)
  {
    alert("“喜欢的动漫”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perAnimations").focus();
    return false;
  }

  //验证用户个人信息（喜欢的运动）
  if(document.getElementById("perSports").value.length > 256)
  {
    alert("“喜欢的运动”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perSports").focus();
    return false;
  }

  //验证用户个人信息（崇拜的偶像）
  if(document.getElementById("perIdols").value.length > 256)
  {
    alert("“崇拜的偶像”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perIdols").focus();
    return false;
  }

  //验证用户个人信息（我的座右铭）
  if(document.getElementById("perMottos").value.length > 256)
  {
    alert("“我的座右铭”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perMottos").focus();
    return false;
  }

  //验证用户个人信息（最近的愿望）
  if(document.getElementById("perWishes").value.length > 256)
  {
    alert("“最近的愿望”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perWishes").focus();
    return false;
  }

  //验证用户个人信息（自我简介绍）
  if(document.getElementById("perIntroduction").value.length > 256)
  {
    alert("“自我简介绍”信息不能超过256个字符，请重新修改！");
    //document.getElementById("perIntroduction").focus();
    return false;
  }

  //验证用户联系信息（手机号码）
  var mobileNumber = document.getElementById("mobNumber").value;
  if(mobileNumber != "" && !mobileReg.test(mobileNumber)) {
    alert("不是正确的11位手机号码，请正确填写手机号码或留空！");
    //document.getElementById("mobNumber").focus();
    return false;
  }

  //验证用户联系信息（固定电话）
  var telephoneNumber = document.getElementById("telNumber").value;
  if(telephoneNumber != "" && !telephoneReg.test(telephoneNumber)) {
    alert("电话号码为 {区号+电话号码，如：010-81818888} 字符串，请正确填写电话号码或留空！");
    //document.getElementById("telNumber").focus();
    return false;
  }

  //验证用户联系信息（QQ 号码）
  var qqNumber = document.getElementById("qqNumber").value;
  if(qqNumber != "" && !qqReg.test(qqNumber)) {
    alert("QQ号码为5-11位纯数字，请正确填写QQ号码或留空！");
    //document.getElementById("qqNumber").focus();
    return false;
  }

  //验证用户联系信息（微信号码）
  var wechatNumber = document.getElementById("wechatNumber").value;
  if(wechatNumber != "" && !wechatReg.test(wechatNumber)) {
    alert("微信号码由6到20个字母、数字、下划线、减号字符组成，请正确填写或留空！");
    //document.getElementById("wechatNumber").focus();
    return false;
  }

  //验证用户联系信息（MSN 账号）
  var msnAccountStr = document.getElementById("msnAccount").value;
  if(msnAccountStr != "" && !emailReg.test(msnAccountStr)) {
    alert("请按正确格式输入有效的MSN账号地址或留空！");
    //document.getElementById("msnAccount").focus();
    return false;
  }

  //验证用户联系信息（电子邮箱）
  var emailAccountStr = document.getElementById("emailAccount").value;
  if(emailAccountStr != "" && !emailReg.test(emailAccountStr)) {
    alert("请按正确格式输入有效的电子邮箱地址或留空！");
    //document.getElementById("emailAccount").focus();
    return false;
  }

  //验证用户联系信息（个人网址）
  var personalUrlStr = document.getElementById("personalUrl").value.trim();
  if(personalUrlStr != "" && !urlReg.test(personalUrlStr))
  {
    alert("您输入的“个人网址”格式不符合要求，请输入正确格式的常规网址或留空！");
    //document.getElementById("personalUrl").focus();
    return false;
  }
  if(personalUrlStr.length > 64)
  {
    alert("“个人网址”信息不能超过64个字符，请重新修改或留空！");
    //document.getElementById("personalUrl").focus();
    return false;
  }

  //验证用户联系信息（联系地址）
  if(document.getElementById("addressInfo").value.length > 128)
  {
    alert("“联系地址”信息不能超过128个字符，请重新修改或留空！");
    //document.getElementById("addressInfo").focus();
    return false;
  }
  //验证成功，禁用提交按钮，避免重复提交
  var profileFormBtnText = "<a class='input-block-level btn btn-success' type='submit' style='cursor: wait'>保存更改中...</a>";
  document.getElementById("profileFormBtn1").innerHTML = profileFormBtnText;
  document.getElementById("profileFormBtn2").innerHTML = profileFormBtnText;
  document.getElementById("profileFormBtn3").innerHTML = profileFormBtnText;
  document.getElementById("profileFormBtn4").innerHTML = profileFormBtnText;
  //档案设置信息验证通过，返回真
  return true;
  //档案设置信息验证通过，提交表单
  //document.getElementById("profileForm").submit();
}












