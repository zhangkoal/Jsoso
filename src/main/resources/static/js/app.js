$(function () {
    $('#formCheckId').bootstrapValidator({
        live: 'disabled',//验证时机，enabled是内容有变化就验证（默认），disabled和submitted是提交再验证
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            email: {
                validators: {
                    notEmpty: {
                        message: '邮箱地址不能为空'
                    },
                    emailAddress: {
                        message: '邮箱地址格式有误'
                    },
                }
            },
            password:{
                validators: {
                    notEmpty: {
                        message: '文本框必须输入'
                    },
                    notEmpty: {//检测非空,radio也可用
                        message: '文本框必须输入'
                    },
                    stringLength: {//检测长度
                        min: 6,
                        max: 30,
                        message: '长度必须在6-30之间'
                    },
                    identical: {//与指定控件内容比较是否相同，比如两次密码不一致
                        field: 'confirmPassword',//指定控件name
                        message: '输入的内容不一致'
                    },
                }
            },
            confirmPassword:{
                validators: {
                    identical: {//与指定控件内容比较是否相同，比如两次密码不一致
                        field: 'password',//指定控件name
                        message: '输入的内容不一致'
                    },
                }
            }
        }
    });
});
$('#submitBtn').click(function () {
    $('#formCheckId').bootstrapValidator('validate')
    if($('#formCheckId').data('bootstrapValidator').isValid()) {
        var userName = jQuery("#email").val();
        var password = jQuery("#password").val();
        var emailCode = jQuery("#emailCodeInput").val();
        $.ajax({
            type: "post", //用Post方式传输
            dataType: "json", //数据格式:JSON
            url: '/userRegist', //目标地址
            data: {userName : userName, password:password, emailCode:emailCode},
            success: function (data) {
                alert(data.msg);
            }
        });
    } else {
        alert('验证失败！');
    }
});
function getEamilCode(){
    var flag = $('#formCheckId').data("bootstrapValidator").validateField('email');
    if(flag.isValidField('email')) {
        if(!jQuery("#emailCode").hasClass('disabled')){
            jQuery("#emailCode").addClass('disabled');
            var countDownTime=parseInt(10);    //在这里设置时长
            jQuery("#emailCode").val(countDownTime+"秒后重新获取");
            countDown(countDownTime - 1);
        }
        var userName = jQuery("#email").val();
        alert("发送成功！");
        //向后台发送处理数据
        $.ajax({
            type: "post", //用Post方式传输
            dataType: "json", //数据格式:JSON
            url: '/sendMail', //目标地址
            data: {userName : userName},
            success: function (data) {
            }
        });
    }
}
function countDown(countDownTime){
    var timer=setInterval(function(){
        if(countDownTime>0){
            jQuery("#emailCode").val(countDownTime+"秒后重新获取");
            countDownTime--;
        }else{
            clearInterval(timer);
            jQuery("#emailCode").removeClass('disabled');
            jQuery("#emailCode").val('获取邮箱验证码')
        }
    },1000);
}