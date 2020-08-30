
$(function () {
    // 初始化表格
    $("#table").bootstrapTable({
        url: '/employees/findList',  //获取表格数据
        method: "get",
        toolbar: "#serverToolbar",
        search: "true",//搜索引擎
        cache: true,            //禁用ajax缓存
        striped: true,           //表格显示条纹
        pagination: true,        //在底部显示分页组件
        pageList: [10, 15, 20],       //设置页面可以显示的数据条数
        pageSize: 5,             // 页面数据条数
        pageNumber: 1,            // 首页页码
        sidePagination: 'client', // 设置为客户端分页    server  服务端分页
        uniqueId: "userId",                     //每一行的唯一标识，一般为主键列
        columns: [
            {
                title: "序号",
                formatter: function (value, row, index) {
                    return index + 1;
                },
                align: "center"
            },
            {
                field: 'username',
                title: '姓名',
                align: "center"
            },
            {
                field: 'sex',
                title: '性别',
                formatter:userSex//格式化函数
            },
            {
                field: 'birthday',
                title: '生日',
                align: "center"
            },
            {
                field: 'national',
                title: '国家',
                align: "center"
            },
            {
                field: 'address',
                title: '住址',
                align: "center"
            },
            {
                field: 'mobile',
                title: '联系方式',
                align: "center"
            }, {
                title:'操作',
                align:"center",
                formatter:buttonFun//格式化函数
            }

        ]
    });
    function userSex(value,row,index) {
        return getSex(value);
    }
    function buttonFun(value,row,index) {
        return [
            '<button  type="button" class="btn btn-primary" onclick="updateEmployees('+row.userId+')" data-toggle="modal" data-target="#updateMyModal">修改</button>'+
            '    <button type="button" class="btn btn-primary" onclick="delEmployees('+row.userId+')">删除</button>'
        ];
    }

});
function addRecord() {
    $.ajax({
        type: "post",
        url: "/employees/insert",
        data: JSON.stringify({
            "username": $("#username").val(),
            "sex": $("#sex").val(),
            "birthday": $("#birthday").val(),
            "national": $("#national").val(),
            "address": $("#address").val(),
            "mobile": $("#mobile").val()
            }),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            if (result.state == true) {
                alert("添加成功!")
                window.location.reload();
            } else {
                alert("添加失败!")
            }
        }
    });
}

//删除
function delEmployees(id) {
    var a = confirm("确定删除?");
    if (a) {
        $.ajax({
            url: "/employees/delete",
            type: "post",
            data: {"id": id},
            success: function (result) {
                if (result.state == true) {
                    alert("删除成功!")
                    window.location.reload();
                } else {
                    alert("删除失败!")
                }
            }
        });
    }
}

function  updateEmployees(id) {
    //数据回显
    $.ajax({
        url: "/employees/findById",
        type: "get",
        data: {"id": id},
        success: function (result) {
            if (result.state == true) {
                var data = result.data;
                $("#UuserId").val(data.userId);
                $("#Uusername").val(data.username);
                $("#Ubirthday").val(data.birthday);
                $("#Unational").val(data.national);
                $("#Uaddress").val(data.address);
                $("#Umobile").val(data.mobile);
                $("#Usex").append('<option name="sex" value='+0+' selected>请选择性别</option>')
                for( var i = 1 ;i < 3;i++){
                    if(data.sex == i){
                        $("#Usex").append('<option name="sex" value='+i+' selected>'+getSex(i)+'</option>')
                    }else {
                        $("#Usex").append('<option name="sex" value='+i+'>'+getSex(i)+'</option>')
                    }
                }

            } else {
                alert("程序出错，请联系开发人员!")
            }
        }
    });

}
function getSex(index) {
    var str;
    if(index ==1){
        str = "男";
    }else if(index==2){
        str = "女";
    }
    return str;
}
function uEmployees() {
    $.ajax({
        type: "post",
        url: "/employees/update",
        data: JSON.stringify({
            "userId": $("#UuserId").val(),
            "username": $("#Uusername").val(),
            "sex": $("#Usex").val(),
            "birthday": $("#Ubirthday").val(),
            "national": $("#Unational").val(),
            "address": $("#Uaddress").val(),
            "mobile": $("#Umobile").val()
        }),
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success: function (result) {
            if (result.state == true) {
                alert("添加成功!")
                window.location.reload();
            } else {
                alert("添加失败!")
            }
        }
    });
}