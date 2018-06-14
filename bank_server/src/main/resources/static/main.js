function rest_main_table_data() {


    $('#datatable').dataTable().fnClearTable();
    $.getJSON('/admin/getusers', function (json) {
        for (var i = 0; i < json.length; i++) {
            var t = $('#datatable').DataTable();
            t.row.add([
                json[i].username,
                json[i].balance,
                "<button class=\"btn btn-primary mytdcss\" id=c" + json[i].username + ">存</button>",
                "<button class=\"btn btn-warning mytdcss\" id=q" + json[i].username + ">取</button>",
                "<button class=\"btn btn-danger  mytdcss\" id=s" + json[i].username + ">删</button>"
            ]).draw();

            $("#c" + json[i].username).delegate('', 'click', function () {
                    var _this = $(this);
                    layer.prompt({
                        formType: 0,
                        value: '0',
                        title: '请输入存款金额：'
                    }, function (value, index, elem) {
                        var money = value;
                        $.get('./admin/deposit?username=' + _this.attr("id").substr(1) + '&info=管理员操作&money=' + money, function () {
                            rest_main_table_data();
                        });
                        layer.close(index);
                    });
                }
            );

            $("#q" + json[i].username).delegate('', 'click', function () {
                var _this = $(this);
                layer.prompt({
                    formType: 0,
                    value: '0',
                    title: '请输入取款金额：'
                }, function (value, index, elem) {
                    var money = value;
                    $.get(encodeURI('./admin/withdraw?username=' + _this.attr("id").substr(1) + '&info=管理员操作&money=' + money), function () {
                        rest_main_table_data();
                    });
                    layer.close(index);
                });
            });

            $("#s" + json[i].username).delegate('', 'click', function () {
                    var _this = $(this);
                    $.get('./admin/delete?username=' + _this.attr("id").substr(1), function () {
                        rest_main_table_data();
                    });
                }
            );

        }
    });
}

function rest_trans_table_data() {
    $('#datatable').dataTable().fnClearTable();
    $.getJSON('/admin/gettranslogs', function (json) {
        for (var i = 0; i < json.length; i++) {
            var t = $('#datatable').DataTable();
            t.row.add([
                json[i].id,
                json[i].type,
                json[i].username,
                json[i].money,
                json[i].transUsername,
                json[i].date,
                json[i].info
            ]).draw();
        }
    });
}

