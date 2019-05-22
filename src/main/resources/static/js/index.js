$('.message a').click(function () {
    $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
});

$('#login').click(function () {
    var userName = $('#userName').val();
    var password = $('#password').val();
    var rememberMe = $('#rememberMe').val();
    if (rememberMe != "true") {
        rememberMe = false;
    }

    $.ajax({
        type: "post", //用Post方式传输
        dataType: "json", //数据格式:JSON
        url: '/subLogin', //目标地址
        data: {userName : userName, password : password, rememberMe : rememberMe},
        success: function (data) {
            if(data.code == -1) {
                alert(data.msg);
            } else {
                setCookie("access_token", data.extData);
                window.location.href= data.url;
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert("服务器响应提交失败，请重试...");
        },
    });
});

$('#emailCode').click(function () {
    if (!jQuery("#emailCode").hasClass('disabled')) {
        jQuery("#emailCode").addClass('disabled');
        var countDownTime = parseInt(10);    //在这里设置时长
        $('#emailCode').removeAttr('onclick');
        $("#emailCode").attr("disabled", "disabled");
        jQuery("#emailCode").val(countDownTime + "秒后重新获取");
        countDown(countDownTime - 1);
    }
    var userName = jQuery("#email").val();
    alert("发送成功！");
    //向后台发送处理数据
    $.ajax({
        type: "post", //用Post方式传输
        dataType: "json", //数据格式:JSON
        url: '/sendMail', //目标地址
        data: {userName: userName},
        success: function (data) {
        }
    });
});


function countDown(countDownTime) {
    var timer = setInterval(function () {
        if (countDownTime > 0) {
            jQuery("#emailCode").val(countDownTime + "秒后重新获取");
            countDownTime--;
        } else {
            clearInterval(timer);
            jQuery("#emailCode").removeClass('disabled');
            jQuery("#emailCode").val('获取邮箱验证码');
            $("#emailCode").attr("onclick", "getEamilCode()");
            $('#emailCode').removeAttr('disabled');
        }
    }, 1000);
}


function hrefFunction(url) {
    window.location.href= url;
}