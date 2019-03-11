var showRecordEle = '<div class="layui-row" id="!@#$%"></div>';
var message = '<span><img src="" width="50px" height="50px" /></span><span class="message">!@#$%</span><hr class="layui-bg-red">';
var comment = '<hr class="layui-bg-blue" /><textarea name="desc" id="*&^%" class="layui-textarea"></textarea><button class="layui-btn layui-btn-sm" style="float: right; margin-left:5px;" onclick="showComment(&^%$)">查看评论</button><button class="layui-btn layui-btn-sm" style="float: right;" onclick="addComment(&^%!);">评论</button>\n';

layui.use('layedit', function(){
    var layedit = layui.layedit;
    //编辑器图片上传接口
    layedit.set({
        uploadImage: {
            url: "/uditImage"
        }
    })
    //建立编辑器
    var udit = layedit.build('record', {
        height: 100
    });


    $("#submit").on('click', function () {
        var text = layedit.getText(udit); //获取编辑器纯文本内容
        var content = layedit.getContent(udit);

        $.ajax({
            type : "POST",
            url : "/record/add",
            data : {content : content, comment : false},
            dataType : "JSON",
            success : function (data) {
                var code = data['code'];
                if (code == 30001) {
                    layer.confirm(data['msg'], {
                        btn: ['去注册', '去登录']
                        }, function(index, layero){
                            window.open("/user/register");
                            layer.close(index);
                        }, function(index){
                            window.open("/user/login");
                            layer.close(index);
                    });
                } else if (code != 0) {
                    layer.msg(data['msg']);
                } else {
                    window.location.reload();
                }
            }
        })
    })
});

layui.use('laypage', function(){
    $.ajax({
        type: "GET",
        url: "/record",
        dataType: "JSON",
        success: function (data) {
            var total = data['data']['total'];
            var list = data['data']['list'];
            appendRecord(list);

            var laypage = layui.laypage;
            laypage.render({
                elem: 'page'
                , count: total
                , jump: function (obj, first) {
                    if(!first){
                        showRecord(obj.curr, obj.limit);
                    }
                }
            })
        }
    })
});

function showRecord(curr, limit) {
    $("#showRecord").empty();
    $.ajax({
        type: "GET",
        data: {start: curr, size: limit},
        url: "/record",
        dataType: "JSON",
        success: function (data) {
            var list = data['data']['list'];
            appendRecord(list);
        }
    })
}

function appendRecord(list) {
    for (var i = 0; i < list.length; i++) {
        var record = list[i];
        console.log(record['createImage'])
        var msg = record['createName'] + "发布于"  + formateDate(record['createDate']);
        $("#showRecord").append(showRecordEle.replace("!@#$%", record['id']));
        $("#" + record['id']).append(message.replace("!@#$%", msg)).append(record['content']).append(comment.replace("*&^%", "comment" + record['id']).replace("&^%!", record['id']).replace("&^%$", record['id']));
        $("#" + record['id'] + " span img").attr("src", record['createImage']);
    }
}

function formateDate(dateTime) {
    var date = new Date(dateTime);
    return date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日 " + date.getHours() + ":" + date.getMinutes();
}

function addComment(id) {
    var commentId = "comment" + id;
    var content = $("#" + commentId).val();
    if (content.replace(/(^s*)|(s*$)/g, "").length == 0) {
        layer.msg("评论内容不能为空！");
        return;
    }

    $.ajax({
        type : "POST",
        url : "/record/add",
        data : {content : content, comment : true, pid : id},
        dataType : "JSON",
        success : function (data) {
            var code = data['code'];
            if (code == 30001) {
                layer.confirm(data['msg'], {
                    btn: ['去注册', '去登录']
                }, function(index, layero){
                    window.open("/user/register");
                    layer.close(index);
                }, function(index){
                    window.open("/user/login");
                    layer.close(index);
                });
            } else if (code != 0) {
                layer.msg(data['msg']);
            } else {
                layer.confirm("评论成功！", {
                    btn: ['查看评论', '关闭窗口']
                }, function(index, layero){
                    layer.open({
                        title: "查看评论",
                        type: 2,
                        content: "/record/comment?id=" + id,
                        area: ['70%', '70%']
                    });
                    layer.close(index);
                }, function(index){
                    layer.close(index);
                });
            }
        }
    })

}

function showComment(id) {
    layer.open({
        title: "查看评论",
        type: 2,
        content: "/record/comment?id=" + id,
        area: ['70%', '70%']
    });
}