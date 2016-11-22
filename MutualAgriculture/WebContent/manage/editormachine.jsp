<%@page import="com.geowind.hunong.jpa.Machine"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Machine machine = (Machine)request.getSession().getAttribute("currentMachine");
	if(machine != null) {
		String images = machine.getPicture();
		if(images != null) {
			String[] pic = images.split(",");
			for(int i=0; i<pic.length; i++) {
				pic[i] = "../../../"+pic[i];
				System.out.println(pic[i]);
			}
			List<String> picList = Arrays.asList(pic);
			pageContext.setAttribute("picList", picList);
		}
	}
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
<link rel="shortcut icon" href="img/icon_web_mini.png" type=""/>
<!-- Bootstrap 3.3.6 -->
<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
<!-- Font Awesome -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
<!-- Ionicons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
<!-- DataTables -->
<link rel="stylesheet"
	href="css/plugins/datatables/dataTables.bootstrap.css">
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
		        <li><a href="machine.jsp">农机管理</a></li>
		        <li class="active">详情</li>
		      </ol>
		</section>
		<br/>
	    <br/>
	    
		<form class="form-horizontal">
			<fieldset disabled="disabled" class="col-md-4 allInfo">
				<!-- Horizontal Form -->
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">农机图片</h3>
					</div>
				</div>
					<!-- /.box-header -->
					<!-- form start -->

					<div class="box box-body">
						<div align="center">
							<!-- <img src="img/photo1.png" class="img-rounded" width="220px" height="auto">
							<img src="img/photo2.png" class="img-rounded" width="220px" height="auto">
							<img src="img/photo4.jpg" class="img-rounded" width="220px" height="auto"> -->
							<c:forEach items="${picList}" var="item"> 
								<div class="row">
									<img src="${item}"  class="img-rounded" width="220px" height="auto">
									<hr>
									<div style="width:10px; height:30px"></div>
								</div>
							</c:forEach>
						</div>
					</div>

					<!-- /.box-body -->

				

			</fieldset>

			<fieldset disabled="disabled" class="col-md-8 allInfo">


				<!-- Horizontal Form -->
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">详细信息</h3>
					</div>
				</div>
					<!-- /.box-header -->
					<!-- form start -->

					<div class="box box-body">
						<div class="form-group" id="phonediv">
                        <label for="phone" class="col-sm-2 control-label">拥有者手机号</label>
                        <div class="col-sm-5">
                            <input class="form-control" id="phone" type="text" value="${currentMachine.machineowner.phone }">
                        </div>
	                    </div>
	                    <div class="form-group">
	                        <label for="ownername" class="col-sm-2 control-label">拥有者姓名</label>
	                        <div class="col-sm-5">
	                            <input class="form-control" id="ownername" type="text" value="${currentMachine.machineowner.name }">
	                        </div>
	                    </div>
						<div class="form-group">
							<label for="plate" class="col-md-2 control-label">机牌号</label>
							<div class="col-md-8">
								<input class="form-control" id="plate" type="text" value="${currentMachine.plate }">
							</div>
						</div>
						<div class="form-group">
							<label for="type" class="col-md-2 control-label">农机类型</label>
							<div class="col-md-8">
								<input class="form-control" id="type" type="text" value="${currentMachine.plate }">
							</div>
						</div>
						<div class="form-group">
							<label for="brand" class="col-md-2 control-label">农机品牌</label>
							<div class="col-md-8">
								<input class="form-control" id="brand" type="text" value="${currentMachine.brand }">
							</div>
						</div>
						<div class="form-group">
							<label for="horsepower" class="col-md-2 control-label">马力</label>
							<div class="col-md-8">
								<input class="form-control" id="horsepower" type="text" value="${currentMachine.horsepower }">
							</div>
						</div>
						<%-- <div class="form-group">
							<label for="" class="col-md-2 control-label">农机状态</label>
							<div class="col-md-8">
								<textarea class="form-control" rows="3">${}</textarea>
							</div>
						</div> --%>
						<div class="form-group">
							<label for="overdate" class="col-md-2 control-label">报废时间</label>

							<div class="col-md-8">
								<textarea class="form-control" id="overdate" rows="3"><fmt:formatDate value="${currentMachine.overdate }" pattern="yyyy-MM-dd"/></textarea>
							</div>
						</div>
					
					<!-- /.box-body -->

				</div>

			</fieldset>
			<div align="center">
				<button type="button" class="btn btn-default" onclick="editInfo()">修改</button>
				<button type="button" class="btn btn-default" onclick="saveInfo()">保存</button>
				<button type="reset" class="btn btn-default">重置</button>
				<button type="button" class="btn btn-default"
					onclick="returnMachine()">返回</button>
			</div>
		</form>


	</div>
	<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- date-range-picker -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<!-- bootstrap datepicker -->
	<script src="js/plugins/datepicker/bootstrap-datepicker.js"></script>
	<!-- InputMask -->
	<script src="js/plugins/input-mask/jquery.inputmask.js"></script>
	<script src="js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script src="js/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	
	<script type="text/javascript" src="js/index.js"></script>

	<script>

		function editInfo() {
			$(".allInfo").removeAttr("disabled");
		}
		function saveInfo() {
			$(".allInfo").attr("disabled", "disabled");
			var ownername = $.trim($("#ownername").val());
		    var plate = $.trim($("#plate").val());
		    var type = $.trim($("#type").val());
		    var brand = $.trim($("#brand").val());
		    var horsepower = $.trim($("#horsepower").val());
		    var overdate = $.trim($("#overdate").val());
		    var result= confirm("确认修改？","确认","取消");
	        if(result == true) {
	        	$.post("../bMachineServlet", {op:"editor", ownername:ownername, plate:plate, type:type,
	    	    	brand:brand, horsepower:horsepower, overdate:overdate}, function(data) {
	            	if(data == 1) {
	            		alert("修改成功");
	            	} else {
	            		alert("修改失败");
	            	}
	            });
	        } else {
	        	return;
	        }
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

		function dashboard() {
			parent.location.reload();
	    }
	</script>

</body>
</html>