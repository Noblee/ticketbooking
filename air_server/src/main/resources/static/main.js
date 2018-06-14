function rest_main_table_data() {
    $('#datatable').dataTable().fnClearTable();


    $.getJSON('./getFlights', function (json) {
        for (var i = 0; i < json.length; i++) {
            var t = $('#datatable').DataTable();
            t.row.add([
                json[i].id,
                json[i].day,
                json[i].departure,
                json[i].destination,
                json[i].arrival,
                json[i].go_off,
                json[i].prize,
                json[i].remaining,
                "  <td class=\"center\" style=\"width: 30px;padding: 0px 0px 0px 0px;vertical-align:" +
                " inherit;\">\n" +
                "                                                              <button data-toggle=\"dropdown\" id="+ json[i].id  +" class=\"btn" +
                " btn-danger dropdown-toggle\">删除</button>\n" +
                "                                        </td>"
            ]).draw();

            $("#" + json[i].id).delegate('', 'click', function () {
                    var _this = $(this);
                    $.get('./admin/deleteflight?id=' + _this.attr("id"), function () {
                        rest_main_table_data();
                    });
                }
            );
        }
    });
}
function rest_trans_table_data() {
    $('#datatable').dataTable().fnClearTable();
    $.getJSON('./admin/getorders', function (json) {
        for (var i = 0; i < json.length; i++) {
            var t = $('#datatable').DataTable();
            t.row.add([
                json[i].id,
                json[i].flightId,
                json[i].payBank==null?"未付款":json[i].payBank,
                json[i].uid
            ]).draw();
        }
    });
}
function rest_bank_table_data() {
    $('#datatable').dataTable().fnClearTable();
    $.getJSON('./getAllbanks', function (json) {
        for (var i = 0; i < json.length; i++) {
            var t = $('#datatable').DataTable();
            t.row.add([
                json[i].name,
                json[i].username,
                "<td class=\"center\" style=\"width: 30px;padding: 0px 0px 0px 0px;vertical-align: inherit;\">" +
                " <button  data-toggle=\"dropdown\" "
                + " id="  + json[i].name + " class=\"btn" +
                " btn-primary dropdown-toggle\">取消支持</button>\n" +
                "</td>"
            ]).draw();
        }
    });
}
$.get('/myname', function (json) {
    $('#myname').text(json);
});
