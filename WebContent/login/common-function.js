// JavaScript Document
// *******************************
// Create XMLHttp
// *******************************
function createXMLHttp() {
  xmlHttp = false;
  if(window.XMLHttpRequest) { // Mozilla 浏览器
    xmlHttp = new XMLHttpRequest();
    if(xmlHttp.overrideMimeType) {
      // set type accordingly to anticipated content type
      // xmlHttp.overrideMimeType("text/xml");
      xmlHttp.overrideMimeType("text/html");
    }
  } else if(window.ActiveXObject) { // IE 浏览器
    try {
      xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch(e) {
      try {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
      } catch(e) {}
    }
  }
  if(!xmlHttp) {
    alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
    return false;
  }
  return true;
}

// **********************************
//添加数组IndexOf方法 | 字符串Trim()方法
// **********************************
$(document).ready(function() {
//Array.prototype.indexOf
if (!Array.prototype.indexOf) {
  Array.prototype.indexOf = function(elt /*, from*/) {
    var len = this.length >>> 0;

    var from = Number(arguments[1]) || 0;
    from = (from < 0)
         ? Math.ceil(from)
         : Math.floor(from);
    if (from < 0)
      from += len;

    for (; from < len; from++) {
      if (from in this && this[from] === elt)
        return from;
    }
    return -1;
  };
}
//String.prototype.trim
if (!String.prototype.trim) {
  String.prototype.trim = function() {
    return this .replace(/^\s\s*/, "").replace(/\s\s*$/, "");
  }
}
});


// *******************************
// Delete Feed Information
// *******************************
function deleteFeed(userId, msgId, categoryId, feedId, displayNoneFeedId, displayNoneLineId) {
  //alert(userId+" + "+msgId+" + "+categoryId+" + "+feedId+" + "+displayNoneFeedId+" + "+displayNoneLineId);
  confirm("确定要删除吗？", function(){deleteFeedAjax(userId, msgId, categoryId, feedId, displayNoneFeedId, displayNoneLineId);}, function(){return false;});
}

// *****************************************
// Delete Feed Information : Ajax Action
// *****************************************
function deleteFeedAjax(userId, msgId, categoryId, feedId, displayNoneFeedId, displayNoneLineId) {
  //alert(userId+" + "+msgId+" + "+categoryId+" + "+feedId+" + "+displayNoneFeedId+" + "+displayNoneLineId);
  var xmlHttpUrl = "/source/common_submit_feed_delete.php?timestamp=" + new Date().getTime();
  //调用加载函数
  var resultFlagStr = startXMLHttp(userId, msgId, categoryId, feedId, xmlHttpUrl);
  //alert(resultFlagStr);
  if(resultFlagStr == 1) {
    document.getElementById(displayNoneFeedId).setAttribute("style", "display: none;");
    document.getElementById(displayNoneLineId).setAttribute("style", "display: none;");
    return true;
  } else {
    alert("发生未知错误，删除信息失败，请刷新页面后重试！");
    return false;
  }

  //建立验证码验证 AJAX 主过程
  function startXMLHttp(userId, msgId, categoryId, feedId, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = "userId="+userId+"&msgId="+msgId+"&categoryId="+categoryId+"&feedId="+feedId+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"")
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var resultFlag = trimStr(xmlHttp.responseText);
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+resultFlag+"");
    return resultFlag;
  }
}


// *******************************
// Management Collection
// *******************************
function managementCollection(userId, msgId, categoryId, actionFlag, collectionLinkId) {
  //alert(userId+" + "+msgId+" + "+categoryId+" + "+actionFlag+" + "+collectionLinkId);
  if(actionFlag == "addCollection") {
    confirm("确定要收藏吗？", function(){managementCollectionAjax(userId, msgId, categoryId, actionFlag, collectionLinkId);}, function(){return false;});
  } else if(actionFlag == "deleteCollection") {
    confirm("确定要取消收藏吗？", function(){managementCollectionAjax(userId, msgId, categoryId, actionFlag, collectionLinkId);}, function(){return false;});
  } else {
    alert("发生未知错误，操作失败，请刷新页面后重试！");
    return false;
  }
}

// *****************************************
// Management Collection : Ajax Action
// *****************************************
function managementCollectionAjax(userId, msgId, categoryId, actionFlag, collectionLinkId) {
  //alert(userId+" + "+msgId+" + "+categoryId+" + "+actionFlag+" + "+collectionLinkId);
  var xmlHttpUrl = "/source/common_submit_feed_collection.php?timestamp=" + new Date().getTime();
  //调用加载函数
  var resultFlagStr = startXMLHttp(userId, msgId, categoryId, actionFlag, xmlHttpUrl);
  //alert(resultFlagStr);
  if(actionFlag == "addCollection") {
    if(resultFlagStr == 1) {
      alert("收藏成功！");
      var actFlag = "deleteCollection";
      if(categoryId == 0) {
        document.getElementById(collectionLinkId).innerHTML = "<a class=\"span12 btn home-avatar-btn\" href=\"javascript:;\" onClick=\"managementCollection(" + userId + ", " + msgId + ", " + categoryId + ", '" + actFlag + "', '" + collectionLinkId + "'" + ");\"><i class=\"fa fa-star fa-fw\"></i> 取消收藏Ta的个人主页</a>";
	  } else {
        document.getElementById(collectionLinkId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementCollection(" + userId + ", " + msgId + ", " + categoryId + ", '" + actFlag + "', '" + collectionLinkId + "'" + ");\"><i class=\"fa fa-star fa-fw\"></i> 取消收藏</a>";
      }
      return true;
    } else {
      alert("发生未知错误，收藏失败，请刷新页面后重试！");
      return false;
    }
  } else {
    if(resultFlagStr == 1) {
      alert("取消收藏成功！");
      var actFlag = "addCollection";
      if(categoryId == 0) {
        document.getElementById(collectionLinkId).innerHTML = "<a class=\"span12 btn home-avatar-btn\" href=\"javascript:;\" onClick=\"managementCollection(" + userId + ", " + msgId + ", " + categoryId + ", '" + actFlag + "', '" + collectionLinkId + "'" + ");\"><i class=\"fa fa-star fa-fw\"></i> 收藏Ta的个人主页</a>";
	  } else {
        document.getElementById(collectionLinkId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementCollection(" + userId + ", " + msgId + ", " + categoryId + ", '" + actFlag + "', '" + collectionLinkId + "'" + ");\"><i class=\"fa fa-star fa-fw\"></i> 收藏</a>";
      }
      return true;
    } else {
      alert("发生未知错误，取消收藏失败，请刷新页面后重试！");
      return false;
    }
  }

  //建立验证码验证 AJAX 主过程
  function startXMLHttp(userId, msgId, categoryId, actionFlag, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = "userId="+userId+"&msgId="+msgId+"&categoryId="+categoryId+"&actionFlag="+actionFlag+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"")
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var resultFlag = trimStr(xmlHttp.responseText);
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+resultFlag+"");
    return resultFlag;
  }
}

// ********************************
// Report Feed Parameters
// ********************************
function reportFeedParameters(userId, msgId, categoryId) {
  //alert(userId+" + "+msgId+" + "+categoryId);
  document.getElementById("reportMsgUserId").value = userId;
  document.getElementById("reportMsgId").value = msgId;
  document.getElementById("reportMsgCategoryId").value = categoryId;
}

// *****************************************
// Report Feed Information : Submit Action
// *****************************************
function reportFeedSubmit() {
  var reportMsgUserId = document.getElementById("reportMsgUserId").value;
  var reportMsgId = document.getElementById("reportMsgId").value;
  var reportMsgCategoryId = document.getElementById("reportMsgCategoryId").value;
  var reportCategoryId = document.getElementById("reportCategoryId").value;
  var reportDescription = document.getElementById("reportDescription").value.trim();
  var reportAccountFlag = document.getElementById("reportAccountFlag").value;
  //校验接收参数
  if(isNaN(reportMsgUserId) || reportMsgUserId < 1) {
    alert("发生未知错误，举报信息失败，请刷新页面后重试！");
    return false;
  }
  if(isNaN(reportMsgId) || reportMsgId < 1) {
    alert("发生未知错误，举报信息失败，请刷新页面后重试！");
    return false;
  }
  if(reportMsgCategoryId < 0 || reportMsgCategoryId > 999) {
    alert("发生未知错误，举报信息失败，请刷新页面后重试！");
    return false;
  }
  if(reportCategoryId < 1 || reportCategoryId > 8) {
    alert("举报类型不能为空，请选择举报类型！");
    return false;
  }
  if(reportDescription.length < 8) {
    alert("举报说明不得少于8个字符，请认真填写举报说明！");
    return false;
  }
  if(reportAccountFlag < 1 || reportAccountFlag > 2) {
    alert("发生未知错误，举报信息失败，请刷新页面后重试！");
    return false;
  }
  //alert(reportMsgUserId+" + "+reportMsgId+" + "+reportMsgCategoryId+" + "+reportCategoryId+" + "+reportDescription+" + "+reportAccountFlag);
  var xmlHttpUrl = "/source/common_submit_feed_report.php?timestamp=" + new Date().getTime();
  //调用加载函数
  var resultFlagStr = startXMLHttp(reportMsgUserId, reportMsgId, reportMsgCategoryId, reportCategoryId, reportDescription, reportAccountFlag, xmlHttpUrl);
  //alert(resultFlagStr);
  if(resultFlagStr == 1) {
    alert("举报信息成功！");
    document.getElementById("reportMsgUserId").value = "";
    document.getElementById("reportMsgId").value = "";
    document.getElementById("reportMsgCategoryId").value = "";
    document.getElementById("reportCategoryId").value = -1;
    document.getElementById("reportDescription").value = "";
    document.getElementById("reportAccountFlag").value = 1;
    $('#modal-report-feed-form').modal('hide');
    return false;
  } else if(resultFlagStr == 2) {
    alert("您已举报过该信息，无需重复举报！");
    document.getElementById("reportMsgUserId").value = "";
    document.getElementById("reportMsgId").value = "";
    document.getElementById("reportMsgCategoryId").value = "";
    document.getElementById("reportCategoryId").value = -1;
    document.getElementById("reportDescription").value = "";
    document.getElementById("reportAccountFlag").value = 1;
    $('#modal-report-feed-form').modal('hide');
    return false;
  } else {
    alert("发生未知错误，举报信息失败，请刷新页面后重试！");
    document.getElementById("reportMsgUserId").value = "";
    document.getElementById("reportMsgId").value = "";
    document.getElementById("reportMsgCategoryId").value = "";
    document.getElementById("reportCategoryId").value = -1;
    document.getElementById("reportDescription").value = "";
    document.getElementById("reportAccountFlag").value = 1;
    $('#modal-report-feed-form').modal('hide');
    return false;
  }

  //建立验证码验证 AJAX 主过程
  function startXMLHttp(userId, msgId, categoryId, reportCategoryId, reportDescription, reportAccountFlag, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = "userId="+userId+"&msgId="+msgId+"&categoryId="+categoryId+"&reportCategoryId="+reportCategoryId+"&reportDescription="+reportDescription+"&reportAccountFlag="+reportAccountFlag+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"")
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var resultFlag = trimStr(xmlHttp.responseText);
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+resultFlag+"");
    return resultFlag;
  }
}



// *******************************
// Photo Zoom Plus and Minus
// *******************************
function showPhoto(whichphoto) {
  var valueIdS = whichphoto.getAttribute("valueIdS");
  var valueIdL = whichphoto.getAttribute("valueIdL");
  var valuePath = whichphoto.getAttribute("valuePath");
  var valueTitle = htmlEncode(whichphoto.getAttribute("valueTitle"));
  var valueCur = whichphoto.getAttribute("valueCur");
  document.getElementById(valueIdS).setAttribute("style", "display: none;");
  document.getElementById(valueIdL).innerHTML = "<div class=\"span12\"><div class=\"row-fluid\"><div class=\"span12 feed-photo-info\"><a href=\"javascript:;\" onclick=\"shadePhoto(this);\" valueIdS='" + valueIdS + "' valueIdL='" + valueIdL + "'><i class=\"fa fa-arrow-circle-up fa-fw\"></i>收起</a> | <a href='" + valuePath + "' title='" + valueTitle + "' target=\"_blank\"><i class=\"fa fa-arrows-alt fa-fw\"></i>查看原图</a></div></div><div class=\"row-fluid\"><div class=\"span12\"><a href=\"javascript:;\" onclick=\"shadePhoto(this);\" valueIdS='" + valueIdS + "' valueIdL='" + valueIdL + "' style=\"cursor: url('" + valueCur + "'), pointer;\"><img class=\"img-rounded\" src='" + valuePath + "' alt='" + valueTitle + "' /></a></div></div></div>";
}
function shadePhoto(whichphoto) {
  var valueIdS = whichphoto.getAttribute("valueIdS");
  var valueIdL = whichphoto.getAttribute("valueIdL");
  document.getElementById(valueIdS).setAttribute("style", "display: block");
  document.getElementById(valueIdL).innerHTML = "";
}

// *******************************
// Video Start and Stop
// *******************************
function showVideo(whichvideo) {
  var valueIdS = whichvideo.getAttribute("valueIdS");
  var valueIdL = whichvideo.getAttribute("valueIdL");
  var valueUrl = whichvideo.getAttribute("valueUrl");
  var valueSwf = whichvideo.getAttribute("valueSwf");
  var valueTitle = htmlEncode(whichvideo.getAttribute("valueTitle"));
  document.getElementById(valueIdS).setAttribute("style", "display: none;");
  document.getElementById(valueIdL).innerHTML = "<div class=\"span12\"><div class=\"row-fluid\"><div class=\"span12 feed-video-info\"><a href=\"javascript:;\" onclick=\"shadeVideo(this);\" valueIdS='" + valueIdS + "' valueIdL='" + valueIdL + "'><i class=\"fa fa-arrow-circle-up fa-fw\"></i>收起</a> | <a href='" + valueUrl + "' title='" + valueTitle + "' target=\"_blank\" rel=\"external nofollow\"><i class=\"fa fa-hand-o-right fa-fw\"></i>" + valueTitle + "</a></div></div><div class=\"row-fluid\"><div class=\"span12\"><iframe src='" + valueSwf + "' width=\"445\" height=\"400\" frameborder=\"0\" \"allowfullscreen\"></iframe></div></div></div>";
}
function shadeVideo(whichvideo) {
  var valueIdS = whichvideo.getAttribute("valueIdS");
  var valueIdL = whichvideo.getAttribute("valueIdL");
  document.getElementById(valueIdS).setAttribute("style", "display: block");
  document.getElementById(valueIdL).innerHTML = "";
}

// ********************************
// Reply Message Parameters
// ********************************
function replyMsgParameters(whichmsg) {
  var valuePostId = whichmsg.getAttribute("valuePostId");
  var valuePostName = whichmsg.getAttribute("valuePostName");
  var valuePostMsg = whichmsg.getAttribute("valuePostMsg");
  //alert("回复："+valuePostId+" - "+valuePostName+" - "+valuePostMsg+"");
  document.getElementById("rpyUserId").value = valuePostId;
  document.getElementById("rpyUserName").innerHTML = valuePostName;
  document.getElementById("rpyMsg").innerHTML = htmlEncode(valuePostMsg);
}

// *******************************
// Send Message Parameters
// *******************************
function sendMsgParameters(whichmsg) {
  var valueUId = whichmsg.getAttribute("valueUId");
  var valueUName = whichmsg.getAttribute("valueUName");
  //alert("回复："+valueUId+" - "+valueUName+"");
  document.getElementById("sendToUserId").value = valueUId;
  document.getElementById("sendToUserName").innerHTML = valueUName;
}

// *******************************
// Send Message Submit
// *******************************
function sendMessageSubmit() {
  //接收发布信息值
  var xmlHttpVar = "feedFlag";
  var xmlHttpStr = "sendMessage";
  var xmlHttpVarOne = "sendMsgContent";
  var xmlHttpStrOne = document.getElementById("sendMsgContent").value.trim();
  //处理 CheckBox 单选框
  //var sendMsgSecretFlag = document.getElementById("sendMsgSecretFlag").checked;
  //alert(""+sendMsgSecretFlag+"");
  var xmlHttpVarTwo = "sendMsgSecretId";
  if(!document.getElementById("sendMsgSecretFlag").checked) {
    var xmlHttpStrTwo = 1;
  } else {
    var xmlHttpStrTwo = 2;
  }
  //alert(""+xmlHttpStrTwo+"");
  var xmlHttpVarThree = "sendToUserId";
  var xmlHttpStrThree = document.getElementById("sendToUserId").value;
  var sendToUserName = document.getElementById("sendToUserName").innerHTML;
  var xmlHttpUrl = "/source/common_submit_publish_message.php?timestamp=" + new Date().getTime();

  if(xmlHttpStrOne.length < 8) {
    alert("消息内容不得少于8个字符！");
    return false;
  } else {
    var sendMsgReturnStr = startXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpVarOne, xmlHttpStrOne, xmlHttpVarTwo, xmlHttpStrTwo, xmlHttpVarThree, xmlHttpStrThree, xmlHttpUrl);
    //alert(sendMsgReturnStr);
    if(sendMsgReturnStr == 1) {
      alert("给 【"+sendToUserName+"】 发送消息成功！");
      document.getElementById("sendMsgContent").value = "";
      document.getElementById("sendMsgSecretFlag").checked = false;
      document.getElementById("sendToUserId").value = "";
      document.getElementById("sendToUserName").innerHTML = "";
      document.getElementById("sendMsgLeft").innerHTML = 512;
      $('#modal-send-message-form').modal('hide');
      return false;
    } else if(sendMsgReturnStr == 2) {
      alert("你已将 【"+sendToUserName+"】 加入黑名单，移出黑名单后才可给 【"+sendToUserName+"】 发送消息！");
      document.getElementById("sendMsgContent").value = "";
      document.getElementById("sendMsgSecretFlag").checked = false;
      document.getElementById("sendToUserId").value = "";
      document.getElementById("sendToUserName").innerHTML = "";
      document.getElementById("sendMsgLeft").innerHTML = 512;
      $('#modal-send-message-form').modal('hide');
      return false;
    } else if(sendMsgReturnStr == 3) {
      alert("由于 【"+sendToUserName+"】 进行了免打扰设置，你暂时无权给 【"+sendToUserName+"】 发送消息！");
      document.getElementById("sendMsgContent").value = "";
      document.getElementById("sendMsgSecretFlag").checked = false;
      document.getElementById("sendToUserId").value = "";
      document.getElementById("sendToUserName").innerHTML = "";
      document.getElementById("sendMsgLeft").innerHTML = 512;
      $('#modal-send-message-form').modal('hide');
      return false;
    } else {
      alert("发生未知错误，给 【"+sendToUserName+"】 发送消息失败，请稍后重试！");
      document.getElementById("sendMsgContent").value = "";
      document.getElementById("sendMsgSecretFlag").checked = false;
      document.getElementById("sendToUserId").value = "";
      document.getElementById("sendToUserName").innerHTML = "";
      document.getElementById("sendMsgLeft").innerHTML = 512;
      $('#modal-send-message-form').modal('hide');
      return false;
	}
  }

  //建立验证码验证 AJAX 主过程
  function startXMLHttp(xmlHttpVar, xmlHttpStr, xmlHttpVarOne, xmlHttpStrOne, xmlHttpVarTwo, xmlHttpStrTwo, xmlHttpVarThree, xmlHttpStrThree, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = ""+xmlHttpVar+"="+xmlHttpStr+"&"+xmlHttpVarOne+"="+xmlHttpStrOne+"&"+xmlHttpVarTwo+"="+xmlHttpStrTwo+"&"+xmlHttpVarThree+"="+xmlHttpStrThree+"&timestampCheck=" + new Date().getTime();
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
    var sendMsgReturn = xmlHttp.responseText;
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+sendMsgReturn+"");
    return sendMsgReturn;
    //alert("调用 AJAX 函数结束");
  }
}


// *******************************
// Management User Relation
// *******************************
function managementUserRel(whichRel) {
  var cardId = whichRel.getAttribute("valueCId");
  var userId = whichRel.getAttribute("valueUId");
  var userName = whichRel.getAttribute("valueUName");
  var actFlag = whichRel.getAttribute("valueFlag");
  //alert(userId + userName + cardId + actFlag);
  if(actFlag == "addFriend" || actFlag == "acceptRequest") { //加为好友|接受请求
    managementUserRelAjax(cardId, userId, userName, actFlag);
  } else {
    managementUserRelConfirm(cardId, userId, userName, actFlag);
  }
}

// *****************************************
// Management User Relation : Confirm Action
// *****************************************
function managementUserRelConfirm(cardId, userId, userName, actFlag) {
  if(actFlag == "addFriend") { //加为好友
    //confirm('abc', function(){alert('点击确定按钮！'); return true;}, function(){alert('点击取消按钮！'); return false;});
    confirm("确定加 【" + userName + "】 为好友吗？", function(){managementUserRelAjax(cardId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "deleteFriend") { //删除好友
    confirm("确定删除你与 【" + userName + "】 的好友关系吗？", function(){managementUserRelAjax(cardId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "cancelRequest") { //取消请求
    confirm("确定取消你向 【" + userName + "】 发送的加好友请求吗？", function(){managementUserRelAjax(cardId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "acceptRequest") { //接受请求
    confirm("确定接受 【" + userName + "】 向你发送的加好友请求吗？", function(){managementUserRelAjax(cardId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "ignoreRequest") { //忽略请求
    confirm("确定忽略 【" + userName + "】 向你发送的加好友请求吗？", function(){managementUserRelAjax(cardId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "addBlacklist") { //加入黑名单
    confirm("确定将 【" + userName + "】 加入黑名单吗？", function(){managementUserRelAjax(cardId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "removeBlacklist") { //移出黑名单
    confirm("确定将 【" + userName + "】 移出黑名单吗？", function(){managementUserRelAjax(cardId, userId, userName, actFlag);}, function(){return false;});
  } else { //异常情况
    return false;
  }
}

// *****************************************
// Management User Relation : Ajax Action
// *****************************************
function managementUserRelAjax(cardId, userId, userName, actFlag) {
  //alert(userId + actFlag);
  //定义 xmlHttp 值
  var xmlHttpUrl = "/source/common_submit_management_user_rel.php?timestamp=" + new Date().getTime();
  //调用加载函数
  var resultFlagStr = startXMLHttp(userId, actFlag, xmlHttpUrl);
  //alert(resultFlagStr);
  if(resultFlagStr == 1) {
    //showNextUserRel
    if(actFlag == "addFriend") { //加为好友
      addFriend();
      return true;
    } else if(actFlag == "deleteFriend") { //删除好友
      deleteFriend();
      return true;
    } else if(actFlag == "cancelRequest") { //取消请求
      cancelRequest();
      return true;
    } else if(actFlag == "acceptRequest") { //接受请求
      acceptRequest();
      return true;
    } else if(actFlag == "ignoreRequest") { //忽略请求
      ignoreRequest();
      return true;
    } else if(actFlag == "addBlacklist") { //加入黑名单
      addBlacklist();
      return true;
    } else if(actFlag == "removeBlacklist") { //移出黑名单
      removeBlacklist();
      return true;
    } else { //异常情况
      return false;
    }
  } else if(resultFlagStr == 2) {
    acceptRequest();
    return true;
  } else {
    return false;
  }

  //建立验证码验证 AJAX 主过程
  function startXMLHttp(userId, actFlag, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = "userId="+userId+"&actFlag="+actFlag+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"")
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var resultFlag = trimStr(xmlHttp.responseText);
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+resultFlag+"");
    return resultFlag;
  }

  //addFriend
  function addFriend() {
    document.getElementById(cardId).innerHTML = "<div class=\"row-fluid text-left card-info-desc-m\">你已向Ta发送了加好友请求...</div><div class=\"row-fluid text-right\"><a href=\"javascript:;\" onClick=\"managementUserRel(this);\" valueCId='" + cardId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"cancelRequest\" class=\"btn btn-success btn-small\"><i class=\"fa fa-times fa-fw\"></i> 取消请求</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"btn btn-success btn-small\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a></div>";
  }
  //deleteFriend
  function deleteFriend() {
    document.getElementById(cardId).innerHTML = "<div class=\"row-fluid text-left card-info-desc-m\">Ta还不是你的好友...</div><div class=\"row-fluid text-right\"><a href=\"javascript:;\" onClick=\"managementUserRel(this);\" valueCId='" + cardId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addFriend\" class=\"btn btn-success btn-small\"><i class=\"fa fa-user-plus fa-fw\"></i> 加为好友</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"btn btn-success btn-small\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a></div>";
  }
  //cancelRequest
  function cancelRequest() {
    document.getElementById(cardId).innerHTML = "<div class=\"row-fluid text-left card-info-desc-m\">Ta还不是你的好友...</div><div class=\"row-fluid text-right\"><a href=\"javascript:;\" onClick=\"managementUserRel(this);\" valueCId='" + cardId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addFriend\" class=\"btn btn-success btn-small\"><i class=\"fa fa-user-plus fa-fw\"></i> 加为好友</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"btn btn-success btn-small\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a></div>";
  }
  //acceptRequest
  function acceptRequest() {
    document.getElementById(cardId).innerHTML = "<div class=\"row-fluid text-left card-info-desc-m\">Ta是你的好友哦...</div><div class=\"row-fluid text-right\"><a href=\"javascript:;\" onClick=\"managementUserRel(this);\" valueCId='" + cardId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"deleteFriend\" class=\"btn btn-success btn-small\"><i class=\"fa fa-user-times fa-fw\"></i> 删除好友</a> <a href=\"javascript:;\" onClick=\"managementUserRel(this);\" valueCId='" + cardId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addBlacklist\" class=\"btn btn-success btn-small\"><i class=\"fa fa-ban fa-fw\"></i> 加入黑名单</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"btn btn-success btn-small\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a></div>";
  }
  //ignoreRequest
  function ignoreRequest() {
    document.getElementById(cardId).innerHTML = "<div class=\"row-fluid text-left card-info-desc-m\">Ta还不是你的好友...</div><div class=\"row-fluid text-right\"><a href=\"javascript:;\" onClick=\"managementUserRel(this);\" valueCId='" + cardId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addFriend\" class=\"btn btn-success btn-small\"><i class=\"fa fa-user-plus fa-fw\"></i> 加为好友</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"btn btn-success btn-small\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a></div>";
  }
  //addBlacklist
  function addBlacklist() {
    document.getElementById(cardId).innerHTML = "<div class=\"row-fluid text-left card-info-desc-m\">你已将Ta加入了黑名单...</div><div class=\"row-fluid text-right\"><a href=\"javascript:;\" onClick=\"managementUserRel(this);\" valueCId='" + cardId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"removeBlacklist\" class=\"btn btn-success btn-small\"><i class=\"fa fa-ban fa-fw\"></i> 移出黑名单</a></div>";
  }
  //addBlacklist
  function removeBlacklist() {
    document.getElementById(cardId).innerHTML = "<div class=\"row-fluid text-left card-info-desc-m\">Ta还不是你的好友...</div><div class=\"row-fluid text-right\"><a href=\"javascript:;\" onClick=\"managementUserRel(this);\" valueCId='" + cardId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addFriend\" class=\"btn btn-success btn-small\"><i class=\"fa fa-user-plus fa-fw\"></i> 加为好友</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"btn btn-success btn-small\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a></div>";
  }
}



// *******************************
// Management User Relation Page
// *******************************
function managementUserRelPage(whichRel) {
  var pageId = whichRel.getAttribute("valuePId");
  var userId = whichRel.getAttribute("valueUId");
  var userName = whichRel.getAttribute("valueUName");
  var actFlag = whichRel.getAttribute("valueFlag");
  //alert(userId + userName + pageId + actFlag);
  if(actFlag == "addFriend" || actFlag == "acceptRequest") { //加为好友|接受请求
    managementUserRelPageAjax(pageId, userId, userName, actFlag);
  } else {
    managementUserRelPageConfirm(pageId, userId, userName, actFlag);
  }
}

// **********************************************
// Management User Relation : Confirm Action Page
// **********************************************
function managementUserRelPageConfirm(pageId, userId, userName, actFlag) {
  if(actFlag == "addFriend") { //加为好友
    //confirm('abc', function(){alert('点击确定按钮！'); return true;}, function(){alert('点击取消按钮！'); return false;});
    confirm("确定加 【" + userName + "】 为好友吗？", function(){managementUserRelPageAjax(pageId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "deleteFriend") { //删除好友
    confirm("确定删除你与 【" + userName + "】 的好友关系吗？", function(){managementUserRelPageAjax(pageId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "cancelRequest") { //取消请求
    confirm("确定取消你向 【" + userName + "】 发送的加好友请求吗？", function(){managementUserRelPageAjax(pageId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "acceptRequest") { //接受请求
    confirm("确定接受 【" + userName + "】 向你发送的加好友请求吗？", function(){managementUserRelPageAjax(pageId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "ignoreRequest") { //忽略请求
    confirm("确定忽略 【" + userName + "】 向你发送的加好友请求吗？", function(){managementUserRelPageAjax(pageId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "addBlacklist") { //加入黑名单
    confirm("确定将 【" + userName + "】 加入黑名单吗？", function(){managementUserRelPageAjax(pageId, userId, userName, actFlag);}, function(){return false;});
  } else if(actFlag == "removeBlacklist") { //移出黑名单
    confirm("确定将 【" + userName + "】 移出黑名单吗？", function(){managementUserRelPageAjax(pageId, userId, userName, actFlag);}, function(){return false;});
  } else { //异常情况
    return false;
  }
}

// *******************************************
// Management User Relation : Ajax Action Page
// *******************************************
function managementUserRelPageAjax(pageId, userId, userName, actFlag) {
  //alert(userId + actFlag);
  //定义 xmlHttp 值
  var xmlHttpUrl = "/source/common_submit_management_user_rel.php?timestamp=" + new Date().getTime();
  //调用加载函数
  var resultFlagStr = startXMLHttp(userId, actFlag, xmlHttpUrl);
  //alert(resultFlagStr);
  if(resultFlagStr == 1) {
    //showNextUserRel
    if(actFlag == "addFriend") { //加为好友
      addFriend();
      return true;
    } else if(actFlag == "deleteFriend") { //删除好友
      deleteFriend();
      return true;
    } else if(actFlag == "cancelRequest") { //取消请求
      cancelRequest();
      return true;
    } else if(actFlag == "acceptRequest") { //接受请求
      acceptRequest();
      return true;
    } else if(actFlag == "ignoreRequest") { //忽略请求
      ignoreRequest();
      return true;
    } else if(actFlag == "addBlacklist") { //加入黑名单
      addBlacklist();
      return true;
    } else if(actFlag == "removeBlacklist") { //移出黑名单
      removeBlacklist();
      return true;
    } else { //异常情况
      return false;
    }
  } else if(resultFlagStr == 2) {
    acceptRequest();
    return true;
  } else {
    return false;
  }

  //建立验证码验证 AJAX 主过程
  function startXMLHttp(userId, actFlag, xmlHttpUrl) {
    var returnCreateXMLHttp = createXMLHttp(); //建立xmlHttp 对象
    //alert("创建 XMLHTTP 返回参数为："+returnCreateXMLHttp+"");
    if(returnCreateXMLHttp) {
      //alert("创建 XMLHTTP 成功");
    } else {
      alert("创建 XMLHTTP 失败，请换用其它浏览器试试，给您带来不便我们深表歉意！");
      return false;
    }
    var send_string = "userId="+userId+"&actFlag="+actFlag+"&timestampCheck=" + new Date().getTime();
    var send_url = xmlHttpUrl;
    send_string = encodeURI(send_string);
    //alert("Send 参数为："+send_string+"\n调用 Url 为："+send_url+"")
    xmlHttp.open("POST", send_url, false); //传送方式 读取的页面 异步与否
    xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
    xmlHttp.setRequestHeader("Charset", "utf-8");
    xmlHttp.setRequestHeader("Cache-control", "no-cache");
    xmlHttp.setRequestHeader("Content-length", send_string.length);
    xmlHttp.setRequestHeader("Connection", "close");
    xmlHttp.send(send_string); //发送
    var resultFlag = trimStr(xmlHttp.responseText);
    //alert("调用 AJAX 函数结束");
    //alert("返回值为："+resultFlag+"");
    return resultFlag;
  }

  //addFriend
  function addFriend() {
    document.getElementById(pageId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementUserRelPage(this);\" valuePId='" + pageId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"cancelRequest\" class=\"span6 btn btn-success home-avatar-btn\"><i class=\"fa fa-times fa-fw\"></i> 取消请求</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"span6 pull-right btn btn-success home-avatar-btn\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a>";
  }
  //deleteFriend
  function deleteFriend() {
    document.getElementById(pageId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementUserRelPage(this);\" valuePId='" + pageId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addFriend\" class=\"span6 btn btn-success home-avatar-btn\"><i class=\"fa fa-user-plus fa-fw\"></i> 加为好友</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"span6 pull-right btn btn-success home-avatar-btn\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a>";
  }
  //cancelRequest
  function cancelRequest() {
    document.getElementById(pageId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementUserRelPage(this);\" valuePId='" + pageId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addFriend\" class=\"span6 btn btn-success home-avatar-btn\"><i class=\"fa fa-user-plus fa-fw\"></i> 加为好友</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"span6 pull-right btn btn-success home-avatar-btn\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a>";
  }
  //acceptRequest
  function acceptRequest() {
    document.getElementById(pageId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementUserRelPage(this);\" valuePId='" + pageId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"deleteFriend\" class=\"span6 btn btn-success home-avatar-btn\"><i class=\"fa fa-user-times fa-fw\"></i> 删除好友</a> <a href=\"javascript:;\" onClick=\"managementUserRelPage(this);\" valuePId='" + pageId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addBlacklist\" class=\"span6 pull-right btn btn-success home-avatar-btn\"><i class=\"fa fa-ban fa-fw\"></i> 加入黑名单</a>";
  }
  //ignoreRequest
  function ignoreRequest() {
    document.getElementById(pageId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementUserRelPage(this);\" valuePId='" + pageId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addFriend\" class=\"span6 btn btn-success home-avatar-btn\"><i class=\"fa fa-user-plus fa-fw\"></i> 加为好友</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"span6 pull-right btn btn-success home-avatar-btn\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a>";
  }
  //addBlacklist
  function addBlacklist() {
    document.getElementById(pageId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementUserRelPage(this);\" valuePId='" + pageId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"removeBlacklist\" class=\"span12 btn btn-success home-avatar-btn\"><i class=\"fa fa-ban fa-fw\"></i> 移出黑名单</a>";
  }
  //addBlacklist
  function removeBlacklist() {
    document.getElementById(pageId).innerHTML = "<a href=\"javascript:;\" onClick=\"managementUserRelPage(this);\" valuePId='" + pageId + "' valueUId='" + userId + "' valueUName='" + userName + "' valueFlag=\"addFriend\" class=\"span6 btn btn-success home-avatar-btn\"><i class=\"fa fa-user-plus fa-fw\"></i> 加为好友</a> <a href=\"#modal-send-message-form\" role=\"button\" data-toggle=\"modal\" onClick=\"sendMsgParameters(this);\" valueUId='" + userId + "' valueUName='" + userName + "' class=\"span6 pull-right btn btn-success home-avatar-btn\"><i class=\"fa fa-envelope fa-fw\"></i> 发送消息</a>";
  }
}

// ************************************
// Unicode Encode And Decode Function
// ************************************
//Unicode Encode
function unicodeEncode(sourceStr) {
  var unicodeStr = '';
  for (var i = 0; i < sourceStr.length; i++) {
    unicodeStr += '&#' + sourceStr.charCodeAt(i) + ';';
  }
  return unicodeStr;
}
//Unicode Decode
function unicodeDecode(unicodeStr) {
  var unicodeArr = unicodeStr.match(/&#(\d+);/g);
  //alert(unicodeArr + unicodeArr.length);
  var sourceStr = '';
  for (var i = 0; i < unicodeArr.length; i++) {
    sourceStr += String.fromCharCode(unicodeArr[i].replace(/[&#;]/g, ''));
  }
  return sourceStr;
}

// *******************************
// JS Encode And Decode Html Code
// *******************************
function htmlEncode(str) {
  str = str.replace(/<br \/>/g,"");
  return str.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
}
/*
//去掉HTML标签
function removeHtmlTab(str) {
 return str.replace(/<[^<>]+?>/g,''); //删除所有HTML标签
}
//普通字符转换成转意符
function html2Escape(str) {
  return str.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
}
//转意符换成普通字符
function escape2Html(str) {
  var arrEntities={'lt':'<','gt':'>','nbsp':' ','amp':'&','quot':'"'};
  return str.replace(/&(lt|gt|nbsp|amp|quot);/ig,function(all,t){return arrEntities[t];});
}
//&nbsp;转成空格
function nbsp2Space(str) {
  var arrEntities = {'nbsp' : ' '};
  return str.replace(/&(nbsp);/ig, function(all, t){return arrEntities[t]})
}
//空格转成&nbsp;
function space2Nbsp(str) {
  return str.replace(/\s/ig, '&nbsp;');
}
//回车转为br标签
function return2Br(str) {
  return str.replace(/\r?\n/g,"<br />");
}
//去除开头结尾换行,并将连续3次以上换行转换成2次换行
function trimBr(str) {
  str=str.replace(/((\s|&nbsp;)*\r?\n){3,}/g,"\r\n\r\n"); //限制最多2次换行
  str=str.replace(/^((\s|&nbsp;)*\r?\n)+/g,''); //清除开头换行
  str=str.replace(/((\s|&nbsp;)*\r?\n)+$/g,''); //清除结尾换行
  return str;
}
//将多个连续空格合并成一个空格
function mergeSpace(str) {
  str=str.replace(/(\s|&nbsp;)+/g,' ');
  return str;
}
*/


// *******************************
// Alert And Confirm Style
// *******************************
// Alert
window.alert = function(txt) {
  var shield = document.createElement("DIV");
  shield.id = "shield";
  shield.style.position = "absolute";
  shield.style.left = "0px";
  shield.style.top = "0px";
  shield.style.width = document.body.scrollWidth+"px";
  shield.style.height = document.body.scrollHeight+"px";
  shield.style.background = "#efefef";
  shield.style.textAlign = "center";
  shield.style.zIndex = "10000";
  shield.style.filter = "alpha(opacity=0)";
  var alertFram = document.createElement("DIV");
  alertFram.id="alertFram";
  alertFram.style.position = "absolute";
  alertFram.style.left = ($(window).width() - 520) / 2 + $(window).scrollLeft() + "px";
  alertFram.style.top = ($(window).height() - 112) / 2 + $(window).scrollTop() + "px";
  alertFram.style.marginLeft = "0";
  alertFram.style.marginTop = "0";
  alertFram.style.width = "520px";
  alertFram.style.height = "60px";
  alertFram.style.background = "#cccccc";
  alertFram.style.textAlign = "center";
  alertFram.style.lineHeight = "60px";
  alertFram.style.zIndex = "10001";
  strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%;\">\n";
  strHtml += "<li style=\"background:#3b5997;text-align:left;padding-left:20px;color:#ffffff;font-size:13px;font-weight:bold;height:24px;line-height:24px;border:1px solid #666666;\">【系统提示】</li>\n";
  strHtml += "<li style=\"background:#ffffff;text-align:center;font-size:12px;height:60px;line-height:60px;border-left:1px solid #666666;border-right:1px solid #666666;\">"+txt+"</li>\n";
  strHtml += "<li style=\"background:#3b5997;text-align:center;font-weight:bold;height:28px;line-height:25px; border:1px solid #666666;\"><input style=\"font-size:12px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;\" type=\"button\" value=\"确 定\" onClick=\"doOk();\" /></li>\n";
  strHtml += "</ul>\n";
  alertFram.innerHTML = strHtml;
  document.body.appendChild(alertFram);
  document.body.appendChild(shield);
  var c = 0;
  this.doAlpha = function(){
    if (c++ > 20){clearInterval(ad);return 0;}
    shield.style.filter = "alpha(opacity="+c+");";
  }
  var ad = setInterval("doAlpha()",5);
  this.doOk = function(){
    alertFram.style.display = "none";
    shield.style.display = "none";
  }
  alertFram.focus();
  document.body.onselectstart = function(){return false;};
};

// Confirm
window.confirm = function(txt, confirmFun, cancelFun) {
  var shield = document.createElement("DIV");
  shield.id = "shield";
  shield.style.position = "absolute";
  shield.style.left = "0px";
  shield.style.top = "0px";
  shield.style.width = document.body.scrollWidth+"px";
  shield.style.height = document.body.scrollHeight+"px";
  shield.style.background = "#efefef";
  shield.style.textAlign = "center";
  shield.style.zIndex = "10000";
  shield.style.filter = "alpha(opacity=0)";
  var confirmFram = document.createElement("DIV");
  confirmFram.id="confirmFram";
  confirmFram.style.position = "absolute";
  confirmFram.style.left = ($(window).width() - 520) / 2 + $(window).scrollLeft() + "px";
  confirmFram.style.top = ($(window).height() - 112) / 2 + $(window).scrollTop() + "px";
  confirmFram.style.marginLeft = "0";
  confirmFram.style.marginTop = "0";
  confirmFram.style.width = "520px";
  confirmFram.style.height = "60px";
  confirmFram.style.background = "#cccccc";
  confirmFram.style.textAlign = "center";
  confirmFram.style.lineHeight = "60px";
  confirmFram.style.zIndex = "10001";
  strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%;\">\n";
  strHtml += "<li style=\"background:#3b5997;text-align:left;padding-left:20px;color:#ffffff;font-size:13px;font-weight:bold;height:24px;line-height:24px;border:1px solid #666666;\">【系统提示】</li>\n";
  strHtml += "<li style=\"background:#ffffff;text-align:center;font-size:12px;height:60px;line-height:60px;border-left:1px solid #666666;border-right:1px solid #666666;\">"+txt+"</li>\n";
  strHtml += "<li style=\"background:#3b5997;text-align:center;font-weight:bold;height:28px;line-height:25px; border:1px solid #666666;\"><input style=\"font-size:12px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;\" id=\"confirmOk\" type=\"button\" value=\"确 定\" onClick=\"doConfirm();\" /><span style=\"padding-left:20px;\"></span><input style=\"font-size:12px;-webkit-border-radius:3px;-moz-border-radius:3px;border-radius:3px;\" id=\"confirmNo\" type=\"button\" value=\"取 消\" onClick=\"doCancel();\" /></li>\n";
  strHtml += "</ul>\n";
  confirmFram.innerHTML = strHtml;
  document.body.appendChild(confirmFram);
  document.body.appendChild(shield);
  var c = 0;
  this.doAlpha = function(){
    if (c++ > 20){clearInterval(ad);return 0;}
    shield.style.filter = "alpha(opacity="+c+");";
  }
  var ad = setInterval("doAlpha()",5);
  this.doConfirm = function(){
    confirmFram.style.display = "none";
    shield.style.display = "none";
    if(typeof confirmFun == 'function') confirmFun();
    return true;
  }
  this.doCancel = function(){
    confirmFram.style.display = "none";
    shield.style.display = "none";
    if(typeof cancelFun == 'function') cancelFun();
    return false;
  }
  confirmFram.focus();
  document.body.onselectstart = function(){return false;};
};




// *******************************
// Forbidden Backspace Event
// *******************************
document.onkeydown = check;
function check(e) {
  var code;
  if (!e) var e = window.event;
  if (e.keyCode) code = e.keyCode;
  else if (e.which) code = e.which;
  if (((event.keyCode == 8) && //BackSpace
      ((event.srcElement.type != "text" &&
        event.srcElement.type != "textarea" &&
        event.srcElement.type != "password") ||
        event.srcElement.readOnly == true)) ||
      ((event.ctrlKey) &&
      ((event.keyCode == 78) || (event.keyCode == 82))) /* || (event.keyCode == 116) */ ) { //CtrlN,CtrlR,F5
    event.keyCode = 0;
    event.returnValue = false;
  }
  return true;
}

// ********************************
// Forbidden Mouse Right Menu Event
// ********************************
document.oncontextmenu = function (event) {
  if (window.event) {
    event = window.event;
  }
  try {
    var the = event.srcElement;
    if (!((the.tagName == "INPUT" && the.type.toLowerCase() == "text") || the.tagName == "TEXTAREA")) {
      return false;
    }
    return true;
  }
  catch (e) {
    return false; 
  } 
};

// *******************************
// Forbidden Ctrl + Mousewheel
// *******************************
window.onmousewheel = document.onmousewheel = function(evt) {
  evt = evt || window.event;
  if (evt.ctrlKey) {
    if (evt.preventDefault) {
      evt.preventDefault();
    } else {
      evt.returnValue = false;
    }
  }
};
if (document.addEventListener) { 
  document.addEventListener("DOMMouseScroll", document.onmousewheel, false); 
}

// *******************************
// Copy Text Function
// *******************************
function copyText(obj) {
  var rng = document.body.createTextRange();
  rng.moveToElementText(obj);
  rng.scrollIntoView();
  rng.select();
  rng.execCommand("Copy");
  rng.collapse(false);
  alert("复制成功，可粘贴使用。");
}

// *******************************
// Trim Text Function
// *******************************
function trimStr(str) {
  //alert("  9 9 9 ".length);
  //alert(trimStr("  9 9 9   ").length);
  return str.replace(/(^\s*)|(\s*$)/g, "");
}












