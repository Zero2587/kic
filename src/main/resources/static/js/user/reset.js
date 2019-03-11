layui.use('layer', function() {
    $("#reset").click(function () {
        var user = $("#user_reset").val();
        var question = $("#question_reset").val();
        var answer = $("#answer_reset").val();
        var pwd = $("#password_reset").val();
        var layer = layui.layer;

        if (user.replace(/(^s*)|(s*$)/g, "").length == 0) {
            layer.msg("用户名不能为空！");
            return;
        }

        if (user.length < 6 || user.length > 16) {
            layer.msg("用户名长度6-16位！");
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

        if (pwd.replace(/(^s*)|(s*$)/g, "").length == 0) {
            layer.msg("密码不能为空！");
            return;
        }

        if (pwd.length < 6 || pwd.length > 16) {
            layer.msg("密码长度6-16位！");
            return;
        }

        $.ajax({
            type: "POST",
            url: "/user/reset",
            data: {username: user, question: question, answer: answer, password: pwd},
            dataType: "JSON",
            success: function (data) {
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