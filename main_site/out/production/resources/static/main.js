function rest_main_table_data() {
    $('#datatable').dataTable().fnClearTable();


    $.getJSON('./getflights', function (json) {
        for (var i = 0; i < json.length; i++) {
            var t = $('#datatable').DataTable();
            t.row.add([
                json[i].flightProvider,
                json[i].id,
                json[i].day,
                json[i].departure,
                json[i].destination,
                json[i].arrival,
                json[i].go_off,
                json[i].prize+"元",
                json[i].remaining,
                "<td class=\"center\" style=\"width: 30px;padding: 0px 0px 0px 0px;vertical-align: inherit;\">" +
                " <button  data-toggle=\"dropdown\" flightProvider=" + json[i].flightProvider + " fid=" + json[i].id
                + " id=" + json[i].flightProvider + json[i].id + " class=\"btn" +
                " btn-primary dropdown-toggle\">下订单</button>\n" +
                "</td>"
            ]).draw();
            $("#" + json[i].flightProvider + json[i].id).delegate('', 'click', function () {
                var _this = $(this);
                layer.prompt({
                    formType: 0,
                    value: '请输入订票人姓名',
                    title: '请输入订票人姓名：'
                }, function (value, index, elem) {
                    var uid = value;
                    $.get('./orderflight?id=' + _this.attr("fid")+
                        '&flightProvider='+_this.attr("flightProvider")
                        +'&uid='+uid, function () {
                        rest_main_table_data();
                    });
                    layer.msg('下单成功', {
                        offset: 't',
                        anim: 0
                    });
                    layer.close(index);

                });

                }
            );
        }
    });
}

$.fn.dataTable.ext.search.push(
    function (settings, data, dataIndex) {
        var min = parseInt($('#min').val(), 10);
        var max = parseInt($('#max').val(), 10);
        var age = parseInt(data[7], 10); // use data for the age column
        var begin = Date.parse($('#begin').val());
        var end = Date.parse($('#end').val());
        var date = Date.parse(data[2]);
        var company = $('#company').val()

        if (
            (
                (isNaN(min) && isNaN(max)) ||
                (isNaN(min) && age <= max) ||
                (min <= age && isNaN(max)) ||
                (min <= age && age <= max)
            )
            &&
            (
                (isNaN(begin) && isNaN(end)) ||
                (isNaN(begin) && date <= end) ||
                (begin <= date && isNaN(end)) ||
                (begin <= date && date <= end)
            )
            &&
            (
                company === "" || (company === data[0])
            )
        ) {
            return true;
        }
        return false;
    }
);



// $(document).ready(function() {
//     var table = $('#datatable').DataTable();
//
//     // Event listener to the two range filtering inputs to redraw on input
//
// } );
