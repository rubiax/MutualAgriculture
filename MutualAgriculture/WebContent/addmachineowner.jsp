<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <link rel="shortcut icon" href="img/icon_web_mini.png" type=""/>
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
    <link rel="stylesheet" href="css/dist/skin/skin-green-light.min.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="css/plugins/datepicker/datepicker3.css">

    <title>Document</title>

</head>
<body style="background-color: #ECF0F5">

<div class="container">

	<section class="content-header">
	      <ol class="breadcrumb">
	        <li><a href="javascript:dashboard()"><i class="fa fa-dashboard"></i> 仪表盘</a></li>
	        <li><a href="machineowner.jsp">农机拥有者</a></li>
	        <li class="active">新增</li>
	      </ol>
	</section>
	<br/>
    <br/>
	
    <div class="box box-success">
        <div class="box-header with-border">
            <h3 class="box-title">Horizontal Form</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form class="form-horizontal">
            <div class="box-body">
                    <div class="form-group">
                        <label for="name" class="col-sm-2 control-label">姓名</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="name" placeholder="Email" type="email">
                        </div>
                    </div>
                <div class="form-group">
                    <label for="sex" class="col-md-2 control-label">性别</label>
                    <div class="radio col-md-10">
                        <label>
                            <input name="sex" id="sex" value="男" checked=""
                                   type="radio">
                            男
                        </label>
                        <label>
                            <input name="sex" id="sex" value="女" type="radio">
                            女
                        </label>
                    </div>
                </div>
                <div class="form-group">
                    <label for="datemask" class="col-md-2 control-label">出生日期</label>
                    <div class="col-md-8">
                        <div class="input-group">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" class="form-control" data-inputmask="'alias': 'yyyy-mm-dd'" data-mask="" id="datemask">
                        </div>
                    </div>

                </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">手机</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="phone" placeholder="Phone" type="text">
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
                <button type="button" class="btn btn-default" onclick="add()">确定</button>
                <button type="button" class="btn btn-default" onclick="returnMachineOwner()">返回</button>
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
    function add() {
        //$(".allInfo").attr("disabled", "disabled");
        var name = $.trim($("#name").val());
        var sex = $("#sex:checked").val();
        var birthday = $.trim($("#datemask").val());
        var phone = $.trim($("#phone").val());
        var address = $.trim($("#address").val());
        alert(name+" "+sex+" "+birthday+" "+phone+" "+address);
        $.post("bMachineOwnerServlet?op=add", {name:name, sex:sex, address:address, phone:phone, birthday:birthday}, function(data) {
        	if(data == 1) {
        		alert("添加成功");
        	} else {
        		alert("添加失败");
        	}
        });
        
        
    }
    function returnMachineOwner() {
        window.location = "bMachineOwnerServlet?op=searchAll";
    }

    function dashboard() {
		parent.location.reload();
    }

</script>

</body>
</html>