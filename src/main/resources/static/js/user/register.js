layui.use('layer', function(){
    $("#register").click(function () {
    var user = $("#user_register").val();
    var name = $("#name_register").val();
    var pwd = $("#password_register").val();
    var confrim = $("#confrim_password_register").val();
    var question = $("#question_register").val();
    var answer = $("#answer_register").val();
    var layer = layui.layer;

    if (user.replace(/(^s*)|(s*$)/g, "").length == 0) {
        layer.msg("用户名不能为空！");
        return;
    }

    if (user.length < 6 || user.length > 16) {
        layer.msg("用户名长度6-16位！");
        return;
    }

    if (name.replace(/(^s*)|(s*$)/g, "").length == 0) {
        layer.msg("名字不能为空！");
        return;
    }

    if (name.length < 2 || name.length > 6) {
        layer.msg("名字长度2-6位！");

        return;
    }

    if (pwd.replace(/(^s*)|(s*$)/g, "").length == 0) {
        layer.msg("密码不能为空！");
        return;
    }

    if (pwd.length < 6 || pwd.length > 16) {
        layer.msg("密码长度6-16位！");
        return;
    }

    if (confrim != pwd) {
        layer.msg("密码和确认密码不同!");
        return;
    }

    if (answer.replace(/(^s*)|(s*$)/g, "").length == 0) {
        layer.msg("答案不能为空！");
        return;
    }

    if (answer.length < 2 || answer.length > 16) {
        layer.msg("答案长度2-16位！");
        return;
    }

    var data = {username : user, password : pwd, name : name, question : question, answer : answer};

    $.ajax({
        type : "POST",
        url : "/user/register",
        contentType : "application/json",
        data : JSON.stringify(data),
        dataType : "JSON",
        success : function (data) {
            var code = data['code'];
            if (code != 0) {
                layer.msg(data['msg']);
            } else {
                window.location.href = "/user/login";
            }
        }
    })

})
})