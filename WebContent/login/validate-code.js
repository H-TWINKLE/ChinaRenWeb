// *********************
// ******生成验证码******
// *********************
function changeImg() {
  var imgSrc = document.getElementById('checkCodeImg');
  var src = imgSrc.src;
  imgSrc.src = chgUrl(src);
  // 时间戳
  // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
  function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    urlurl = url.substring(0, 17);
    if ((url.indexOf("?") >= 0)) {
      urlurl = url + "&t=" + timestamp;
    } else {
      urlurl = url + "?t=" + timestamp;
    }
    return urlurl;
  }
}

// *****************************
// ******顶部登录框生成验证码******
// *****************************
function changeImgLoginTop() {
  var imgSrc = document.getElementById('checkCodeImgLoginTop');
  var src = imgSrc.src;
  imgSrc.src = chgUrl(src);
  // 时间戳
  // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
  function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    urlurl = url.substring(0, 17);
    if ((url.indexOf("?") >= 0)) {
      urlurl = url + "&t=" + timestamp;
    } else {
      urlurl = url + "?t=" + timestamp;
    }
    return urlurl;
  }
}

// *****************************
// ******左侧登录框生成验证码******
// *****************************
function changeImgLoginLeft() {
  var imgSrc = document.getElementById('checkCodeImgLoginLeft');
  var src = imgSrc.src;
  imgSrc.src = chgUrl(src);
  // 时间戳
  // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
  function chgUrl(url) {
    var timestamp = (new Date()).valueOf();
    urlurl = url.substring(0, 17);
    if ((url.indexOf("?") >= 0)) {
      urlurl = url + "&t=" + timestamp;
    } else {
      urlurl = url + "?t=" + timestamp;
    }
    return urlurl;
  }
}

