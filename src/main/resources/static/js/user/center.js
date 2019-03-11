layui.use('upload', function(){
    var upload = layui.upload;

    //执行实例
    upload.render({
        elem: '#uploadImage' //绑定元素
        ,url: '/user/image' //上传接口
        ,done: function(res){
            var code = res['code'];
            if (code != 0) {
                layer.msg(res['msg']);
            } else {
                window.location.reload()
            }
        }
        ,error: function(){
            //请求异常回调
        }
    });
});

var showRecordEle = '<div class="layui-row" id="!@#$%"></div>';
var message = '<i class="fa fa-user-circle-o fa-lg"></i><span class="message">!@#$%</span><div style="float: right;"><a href="javascript:delRecord(*&^%$);"><i class="layui-icon layui-icon-close" style="font-size: 20px;"></i></a></div><hr class="layui-bg-red">';

layui.use('laypage', function(){
    $.ajax({
        type: "GET",
        url: "/record/user",
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
})

function showRecord(curr, limit) {
    $("#showRecord").empty();
    $.ajax({
        type: "GET",
        data: {start: curr, size: limit},
        url: "/record/user",
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
        var msg = "发布于"  + formateDate(record['createDate']);
        $("fieldset").append(showRecordEle.replace("!@#$%", record['id']));
        $("#" + record['id']).append(message.replace("!@#$%", msg).replace("*&^%$", record['id'])).append(record['content']);
    }
}

function formateDate(dateTime) {
    var date = new Date(dateTime);
    return date.getFullYear() + "年" + (date.getMonth() + 1) + "月" + date.getDate() + "日 " + date.getHours() + ":" + date.getMinutes();
}

function delRecord(id) {
    layer.confirm('确定删除吗？', {
            btn: ['确定', '取消']
        }, function (index, layero) {
            $.ajax({
                type: "DELETE",
                url: "/record/del?id=" + id,
                dataType: "JSON",
                success: function (data) {
                    var code = data['code'];
                    if (code == 0) {
                        window.location.reload();
                    }
                }
            })
        }
    );
}