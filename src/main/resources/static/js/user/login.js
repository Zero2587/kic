layui.use('layer', function(){
    $("#login").click(function () {
    var user = $("#user_login").val();
    var pwd = $("#password_login").val();
    var layer = layui.layer;

    if (user.replace(/(^s*)|(s*$)/g, "").length == 0) {
        layer.msg("用户名不能为空！");
        return;
    }

    if (pwd.replace(/(^s*)|(s*$)/g, "").length == 0) {
        layer.msg("密码不能为空！");
        return;
    }

    $.ajax({
        type : "POST",
        url : "/user/login",
        data : {user : user, pwd : pwd},
        dataType : "JSON",
        success : function (data) {
            var code = data['code'];
            if (code != 0) {
                layer.msg(data['msg']);
            } else {
                window.location.href = "/index";
            }
        }
    })
})
})
