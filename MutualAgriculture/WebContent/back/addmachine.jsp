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
    
    <link href="css/LXXUploadPic.css" rel="stylesheet" type="text/css"> 
    

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
        <div class="form-horizontal">
            <div class="box-body">
            		<div class="form-group" id="phonediv">
                        <label for="phone" class="col-sm-2 control-label">拥有者手机号</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="phone" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="ownername" class="col-sm-2 control-label">拥有者姓名</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="ownername" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="plate" class="col-sm-2 control-label">机牌号</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="plate" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">农机类型</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="type"  type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="brand" class="col-sm-2 control-label">农机品牌</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="brand"  type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="horsepower" class="col-sm-2 control-label">马力</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="horsepower"  type="text">
                        </div>
                    </div>
                    <!-- <div class="form-group">
                        <label for="address" class="col-md-2 control-label">农机状态</label>
						<div class="col-sm-5">
                            <input class="form-control" id="type" placeholder="type" type="text">
                        </div>
                    </div> -->
                    <div class="form-group">
                        <label for="overdate" class="col-md-2 control-label">报废时间</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="overdate" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <form id="myform" action="../bMachineServlet?op=uploadImage" method="post" enctype="multipart/form-data">
                        <label for="pic" class="col-md-2 control-label">图片</label>
                        <div class="col-md-5">
	                        <div id="LXXUploadPic" LXXCol="3" LXXRow="1" LXXWidth="100" LXXHeight="100"></div>
                        </div>
                    </form>
                    </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer" align="center">
                <button type="button" class="btn btn-default" onclick="saveInfo()">确定</button>
                <button type="button" class="btn btn-default" onclick="returnMachine()">返回</button>
            </div>
            <!-- /.box-footer -->
        </div>
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

<script src="js/LXXUploadPic.js"></script>

<script type="text/javascript" src="js/index.js"></script>

<script type="text/javascript">
	function saveInfo() {
		var ownername = $.trim($("#ownername").val());
	    var plate = $.trim($("#plate").val());
	    var type = $.trim($("#type").val());
	    var brand = $.trim($("#brand").val());
	    var horsepower = $.trim($("#horsepower").val());
	    var overdate = $.trim($("#overdate").val());
	    $.post("../bMachineServlet?op=add", {ownername:ownername, plate:plate, type:type,
	    	brand:brand, horsepower:horsepower, overdate:overdate}, function(data) {
	    	if(data == 1) {
	    		alert("添加成功");
	    		$("#myform").submit();
	    	} else {
	    		alert("添加失败");
	    	}
	    });
	}
	function returnMachine() {
	    window.location = "../bMachineServlet?op=searchAll";
	}
	$("#phone").blur(function(){
		var phone = $.trim($("#phone").val());
		if(phone == "") {
			return;
		}
		$.post("../bMachineOwnerServlet?op=isExistMachineownerByPhone", {phone:phone}, function(data) {
			var obj =  eval(data);
			check("phonediv", "phone", obj.mark);
			$("#ownername").val(obj.phone);
		}, "json");
	});
</script>

</body>
</html>