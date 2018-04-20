function checkUserSession() {
    var cookie = getCookie("jsoso");
    if(cookie != '' && cookie != undefined) {
        $.ajax({
            type: "post", //用Post方式传输
            dataType: "json", //数据格式:JSON
            url: 'checkUser', //目标地址
            data: {cookieValue:cookie},
            async:false,
            success: function (data) {
                return data;
            }
        });
    }
}
function getUserSessionTime() {
    var cookie = getCookie("jsoso");
    $.ajax({
        type: "post", //用Post方式传输
        dataType: "json", //数据格式:JSON
        url: 'getUserSession', //目标地址
        data: {cookieValue:cookie},
        async:false,
        success: function (data) {
            return data;
        }
    });
}