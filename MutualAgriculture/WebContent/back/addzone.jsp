<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>AdminLTE 2 | General Form Elements</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
    <!-- DataTables -->
    <link rel="stylesheet" href="css/plugins/datatables/dataTables.bootstrap.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="css/dist/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="css/dist/skin/_all-skins.min.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="css/plugins/datepicker/datepicker3.css">

    <title>Document</title>

</head>
<body style="background-color: #ECF0F5">

<div class="container">


    <div class="box box-info">
        <div class="box-header with-border">
            <h3 class="box-title">Horizontal Form</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form class="form-horizontal">
            <div class="box-body">
                    <div class="form-group">
                        <label for="zonename" class="col-sm-2 control-label">分区名</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="zonename" placeholder="name" type="email">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="area" class="col-sm-2 control-label">面积</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="area" placeholder="area" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">作物类型</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="type" placeholder="type" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-md-2 control-label">地址</label>

                        <div class="col-md-10">
                            <textarea class="form-control" rows="3" placeholder="Enter ..." id="address"></textarea>
                        </div>
                    </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer" align="center">
                <button type="button" class="btn btn-default" onclick="saveInfo()">确定</button>
                <button type="button" class="btn btn-default" onclick="returnZone()">返回</button>
            </div>
            <!-- /.box-footer -->
        </form>
    </div>


</div>
<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- date-range-picker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<!-- bootstrap datepicker -->
<script src="js/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- InputMask -->
<script src="js/plugins/input-mask/jquery.inputmask.js"></script>
<script src="js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="js/plugins/input-mask/jquery.inputmask.extensions.js"></script>

<script>
    $(function () {
        //Datemask dd/mm/yyyy
        $("#datemask").inputmask("yyyy-mm-dd", {"placeholder": "yyyy-mm-dd"});
        //Date picker
        $('#datepicker').datepicker({
            autoclose: true
        });

    });
    function editInfo() {
        $(".allInfo").removeAttr("disabled");
    }
    function saveInfo() {
        $(".allInfo").attr("disabled", "disabled");
        var zonename = $.trim($("#zonename").val());
        var area = $.trim($("#area").val());
        var type = $.trim($("#type").val());
        var address = $.trim($("#address").val());
        $.post("../bZoneServlet", {op:"editor", zonename:zonename, area:area, type:type, address:address}, function(data) {
        	if(data == 1) {
        		alert("添加成功");
        	} else {
        		alert("添加失败");
        	}
        });
    }
    function returnZone() {
        window.location = "zone.jsp";
    }

 

</script>

</body>
</html>