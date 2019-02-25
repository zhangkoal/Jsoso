//设置cookie
function setCookie(cname, cvalue) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000*365));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    var expires = "expires="+d.toUTCString();
    document.cookie = cname + "=" + cvalue + "; " + expires;
}
//获取cookie
function getCookie(cname) {
    var name = cname + "=";
    var ca = document.cookie.split(';');
    for(var i=0; i<ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') c = c.substring(1);
        if (c.indexOf(name) != -1) return c.substring(name.length, c.length);
    }
    return "";
}
//清除cookie
function clearCookie(name) {
    setCookie(name, "", -1);
}
function checkCookie() {
    var jsoso = getCookie("jsoso");
    if (jsoso != "") {
        //判断登录类型
        var type = getCookie("jsoso_loginType");
        if(type == "QQ") {

            //获取通过UUID获取用户名称头像
            $.ajax({
                type: "GET", //GET
                url: '/getUserInfoByOpenId', //目标地址
                dataType: "json", //数据格式:JSON
                data: {openId:jsoso},
                success: function (data) {
                    var userName = data.data.userName;
                    var url = data.data.imgUrl
                    $("#qq_username").html( "  " + userName);
                    $("#qq_userImg").attr( "src", url);
                    $("#login_id").hide();
                },
                error:function(XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest.status);
                    alert(XMLHttpRequest.readyState);
                    alert(textStatus);
                }
            });
        }
    }
   /* else {
        jsoso = prompt("Please login!", "");
        if (jsoso != "" && jsoso != null) {
            setCookie("jsoso", jsoso, 365);
        }
    }*/

}