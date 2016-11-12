<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="shortcut icon" href="img/icon_web_mini.png" type=""/>
<!-- Tell the browser to be responsive to screen width -->
<meta
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no"
	name="viewport">
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
<link rel="stylesheet" href="css/dist/skin/_all-skins.min.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="css/plugins/datepicker/datepicker3.css">

<title>Document</title>

</head>
<body style="background-color: #ECF0F5">

	<div class="container">
	<section class="content-header">
	      <ol class="breadcrumb">
	        <li><a href="javascript:dashboard()"><i class="fa fa-dashboard"></i> 仪表盘</a></li>
	        <li><a href="machiner.jsp">农机手</a></li>
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
						<h3 class="box-title">基本信息</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->

					<div align="center">
						<img src="img/avatar.png" class="img-rounded" width="140px"
							height="140px">
					</div>

					<div class="box-body">
						<div class="form-group">
							<label for="username" class="col-md-3 control-label">用户名</label>

							<div class="col-md-8">
								<input class="form-control" id="username" disabled="disabled"
									type="text" value="${currentMachienr.username}">
							</div>
						</div>
						<div class="form-group">
							<label for="name" class="col-md-3 control-label">姓名</label>

							<div class="col-md-8">
								<input class="form-control" id="name" type="text"
									value="${currentMachienr.realname }">
							</div>
						</div>
						
						
				<!-- 		
						<div class="form-group">
						<label for="inputPassword3" class="col-md-3 control-label">性别</label>
                  <div class="radio">
                    <label>
                      男<input name="optionsRadios" id="optionsRadios1" value="option1" checked="" type="radio">
                    </label>
                  </div>
                  <div class="radio">
                    <label>
                      女<input name="optionsRadios" id="optionsRadios2" value="option2" type="radio">
                    </label>
                  </div>
                </div>
						 -->
						
						
						
						 <div class="form-group">
							<label for="sex" class="col-md-3 control-label">性别</label>
							<div class="radio col-md-6">
								<label>
								<input name="sex" id="sex"
									value="男" type="radio" ${currentMachienr.sex=='男'?'checked':''}> 男
								</label> <label> <input name="sex" id="sex"
									value="女" type="radio" ${currentMachienr.sex=='女'?'checked':''}> 女
								</label>
							</div>
						</div> 

					</div>
					<!-- /.box-body -->

				</div>

			</fieldset>

			<fieldset disabled="disabled" class="col-md-8 allInfo">


				<!-- Horizontal Form -->
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">详细信息</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->

					<div class="box-body">
						<div class="form-group">
							<label for="inputPassword3" class="col-md-2 control-label">出生日期</label>
							<div class="col-md-8">
								<div class="input-group">
									<div class="input-group-addon">
										<i class="fa fa-calendar"></i>
									</div>
									<input type="text" class="form-control"
										data-inputmask="'alias': 'yyyy-mm-dd'" data-mask=""
										id="datemask" value="${currentMachienr.birthday }">
								</div>
							</div>

						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-md-2 control-label">手机号</label>

							<div class="col-md-8">
								<input class="form-control" id="phone" type="text"
									value="${currentMachienr.phone }">
							</div>
						</div>

						<div class="form-group">
							<label for="inputPassword3" class="col-md-2 control-label">地址</label>

							<div class="col-md-8">
								<textarea class="form-control" rows="3">${currentMachienr.address }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="credit" class="col-md-2 control-label">信誉</label>

							<div class="col-md-8">
								<input class="form-control" id="credit" type="text"
									value="${currentMachienr.credit }">
							</div>
						</div>
						<div class="form-group">
							<label for="center" class="col-md-2 control-label">服务中心</label>

							<div class="col-md-8">
								<input class="form-control" id="center" type="text">
							</div>
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
					onclick="returnMachiner()">返回</button>
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

	<script>
		$(function() {
			//Datemask dd/mm/yyyy
			$("#datemask").inputmask("yyyy-mm-dd", {
				"placeholder" : "yyyy-mm-dd"
			});
			//Date picker
			$('#datepicker').datepicker({
				autoclose : true
			});

		});
		function editInfo() {
			$(".allInfo").removeAttr("disabled");
		}
		function saveInfo() {
			$(".allInfo").attr("disabled", "disabled");
		}
		function returnMachiner() {
			window.location = "machiner.jsp";
		}
		function dashboard() {
			parent.location.reload();
	    }
	</script>

</body>
</html>