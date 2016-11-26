<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
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
    
    <link href="css/LXXUploadPic.css" rel="stylesheet" type="text/css"> 
    
    <title>Document</title>

</head>
<body style="background-color: #ECF0F5">

<div class="container">
	<section class="content-header">
	      <ol class="breadcrumb">
	        <li><a href="javascript:dashboard()"><i class="fa fa-dashboard"></i> 仪表盘</a></li>
	        <li><a href="farmland.jsp">农田管理</a></li>
	        <li class="active">新增</li>
	      </ol>
	</section>
	<br/>
    <br/>
    <div class="box box-success">
        <div class="box-header with-border">
            <h3 class="box-title">新增农田</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <div class="form-horizontal">
            <div class="box-body">
                    <div class="form-group" id="usernamediv">
                        <label for="username" class="col-sm-2 control-label">拥有者用户名</label>
                        <div class="col-sm-5">
                            <input class="form-control" name="username" id="username" type="text">
                        </div>
                    </div>
                    <div class="form-group" id="zonenamediv">
                        <label for="zonename" class="col-sm-2 control-label">区名</label>
                        <div class="col-sm-5">
                            <input class="form-control" name="zonename" id="zonename" type="text">
                        </div>
                    </div>
                    <div class="form-group">
	                    <label class="col-md-2 control-label"></label>
	                    <div class="col-md-5">
	                    <button data-target="#myModal" role="button" class="btn btn-success" data-toggle="modal" onclick="showModal()">加载地址及经纬度</button>
	                    </div>
	                    
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-md-2 control-label">详细地址</label>
                        <div class="col-md-5">
                            <textarea class="form-control" rows="3" id="address" disabled="disabled"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="lal" class="col-sm-2 control-label">经纬度</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="lal" type="text" disabled="disabled">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="area" class="col-sm-2 control-label">农田面积</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="area" type="text">
                        </div>
                    </div>
                    <!-- <div class="form-group">
                        <label for="type" class="col-sm-2 control-label">作物类型</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="type" placeholder="type" type="text">
                        </div>
                    </div> -->
                    <!-- <div class="form-group">
                        <label for="status" class="col-md-2 control-label">农田状态</label>
						<div class="col-sm-5">
                            <input class="form-control" id="status" type="text">
                        </div>
                    </div> -->
                    <div class="form-group">
                        <label for="ph" class="col-md-2 control-label">pH</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="ph" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="npk" class="col-md-2 control-label">NPK</label>
						<div class="col-sm-5">
                            <input class="form-control" id="npk" type="text">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="transtion" class="col-md-2 control-label">流转信息</label>
                        <div class="col-md-5">
                            <textarea class="form-control" rows="3" id="transtion"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                    <form id="myform" action="../bFarmlandServlet?op=uploadImage" method="post" enctype="multipart/form-data">
                        <label for="pic" class="col-md-2 control-label">图片</label>
                        <div class="col-md-5">
	                        <div id="LXXUploadPic" LXXCol="3" LXXRow="1" LXXWidth="100" LXXHeight="100"></div>
                        </div>
                    </form>
                    </div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer" align="center">
                <button type="button" class="btn btn-success" onclick="saveInfo()">确定</button>
                <button type="button" class="btn btn-default" onclick="returnFarmland()">返回</button>
            </div>
            <!-- /.box-footer -->
        </div>
    </div>


</div>

<jsp:include page="smallmap.html"></jsp:include>
<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
<!-- date-range-picker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
<!-- bootstrap datepicker -->
<script src="js/plugins/datepicker/bootstrap-datepicker.js"></script>
<!-- InputMask -->
<script src="js/plugins/input-mask/jquery.inputmask.js"></script>
<script src="js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
<script src="js/plugins/input-mask/jquery.inputmask.extensions.js"></script>
<script type="text/javascript" src="js/index.js"></script>

<script src="js/LXXUploadPic.js"></script>
<!-- 
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=mcc568Fn4O4pF5ldXtFOs8ILbQGPG1jl"></script>
<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>

<script type="text/javascript" src="js/map/mapInit.js"></script>
<script type="text/javascript" src="js/map/search.js"></script>
<script type="text/javascript" src="js/map/location.js"></script>
<script type="text/javascript" src="js/map/mapType.js"></script> -->

<script type="text/javascript">
	function saveInfo() {
		var username = $.trim($("#username").val());
        var zonename = $.trim($("#zonename").val());
        var lal = $.trim($("#lal").val());
        var area = $.trim($("#area").val());
        var ph = $.trim($("#ph").val());
        var npk = $.trim($("#npk").val());
        var address = $.trim($("#address").val());
        var transtion = $.trim($("#transtion").val());
        $.post("../bFarmlandServlet?op=add", {username:username,zonename:zonename,lal:lal,
        	area:area,ph:ph,npk:npk,address:address,transtion:transtion}, function(data) {
        	if(data == 1) {
        		alert("添加成功");
        		$("#myform").submit();
        	} else {
        		alert("添加失败");
        	}
        });
	}
	function returnFarmland() {
        window.location = "../bFarmlandServlet?op=searchAll";
    }
	$("#username").blur(function(){
		var username = $.trim($("#username").val());
		$.post("../bUserServlet?op=isExistUser", {username:username}, function(data) {
			var obj =  eval(data);
			check("usernamediv", "username", obj.mark);
		}, "json");
	});
	$("#zonename").blur(function(){
		var zonename = $.trim($("#zonename").val());
		$.post("../bZoneServlet?op=isExistZone", {zonename:zonename}, function(data) {
			var obj =  eval(data);
			check("zonenamediv", "zonename", obj.mark);
		}, "json");
	});
	function submitChange() {
		var coordinate = $.trim($("#coordinate").val());
		var _address = $.trim($("#_address").val());
		$("#lal").val(coordinate);
		$("#address").val(_address);
		$('#myModal').modal('hide');
	}

	function dashboard() {
		parent.location.reload();
    }
	
</script>
</body>
</html>