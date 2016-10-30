<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.geowind.hunong.entities.Farmland"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	Farmland farmland = (Farmland)request.getSession().getAttribute("currentFarmland");
	if(farmland != null) {
		String images = farmland.getPicture();
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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    
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

		<!-- <form class="form-horizontal"> -->
		<form id="myform" method="post" class="form-horizontal">

			<fieldset disabled="disabled" class="col-md-4 allInfo">


				<!-- Horizontal Form -->
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">农田图片</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->

					<div class="box-body">
						<div align="center">
							<!-- <img src="../../../../HN_upload/1477313496129_2237.JPG" class="img-rounded" width="220px" height="auto">
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

				</div>

			</fieldset>

			<fieldset disabled="disabled"  class="col-md-8 allInfo">
			
			
				<!-- Horizontal Form -->
				<div class="box box-info">
					<div class="box-header with-border">
						<h3 class="box-title">详细信息</h3>
					</div>
					<!-- /.box-header -->
					<!-- form start -->

					<div class="box-body">
						
						<!-- <div class="form-group">
							<label for="username" class="col-md-2 control-label">用户名</label>
							<div class="col-md-8">
								<input class="form-control" id="username" disabled="disabled" type="text" value="${currentFarmland.user.username}">
							</div>
						</div> -->
						
						
						<div class="form-group" id="usernamediv">
					        <label class="col-md-2 control-label">用户名</label>
					        <div class="col-md-8">
					            <input type="text" class="form-control" name="username" id="username"
					                 value="${currentFarmland.user.username }"/>
					        </div>
					    </div>

						<div class="form-group">
							<label for="realname" class="col-md-2 control-label">姓名</label>
							<div class="col-md-8">
								<input class="form-control" id="realname" disabled="disabled" type="text" value="${currentFarmland.user.realname}">
							</div>
						</div>
						<div class="form-group" id="zonenamediv">
							<label for="zonename" class="col-md-2 control-label">区名</label>
							<div class="col-md-8">
								<input class="form-control" id="zonename" type="text" value="${currentFarmland.zone.zonename}">
							</div>
						</div>
						
						<div class="form-group">
	                    <label class="col-md-2 control-label"></label>
	                    <div class="col-md-5">
	                    <!-- <button data-target="#myModal2" role="button" class="btn btn-primary" data-toggle="modal" >加载地址及经纬度</button>
	                     -->
	                     <a href="#myModal" role="button" class="btn btn-primary"
		data-toggle="modal" onclick="showModal()">修改地址及经纬度</a>
	                     </div>
                    	</div>
		                <div class="form-group">
							<label for="address" class="col-md-2 control-label">详细地址</label>
							<div class="col-md-8">
								<textarea class="form-control" id="address" rows="3" disabled="disabled">${currentFarmland.address }</textarea>
							</div>
						</div>
	                    <div class="form-group">
							<label for="lal" class="col-md-2 control-label">经纬度</label>
							<div class="col-md-8">
								<input class="form-control" id="lal" type="text" disabled="disabled" value="(${currentFarmland.longitude },${currentFarmland.latitude})">
							</div>
						</div>
						
						<div class="form-group">
							<label for="type" class="col-md-2 control-label">作物类型</label>
							<div class="col-md-8">
								<input class="form-control" id="type" type="text" disabled="disabled" value="${currentFarmland.zone.type}">
							</div>
						</div>
						
						<div class="form-group">
							<label for="area" class="col-md-2 control-label">农田面积</label>
							<div class="col-md-8">
								<input class="form-control" id="area" type="text" value="${currentFarmland.area}">
							</div>
						</div>
						<div class="form-group">
							<label for="ph" class="col-md-2 control-label">pH</label>
							<div class="col-md-8">
								<input class="form-control" id="ph" type="text" value="${currentFarmland.ph }">
							</div>
						</div>
						<div class="form-group">
							<label for="npk" class="col-md-2 control-label">NPK</label>
							<div class="col-md-8">
								<input class="form-control" id="npk" type="text" value="${currentFarmland.npk}">
							</div>
						</div>
						<%-- <div class="form-group">
							<label for="inputPassword3" class="col-md-2 control-label">农田状态</label>
							<div class="col-md-8">
								<input class="form-control" id="phone" type="text" value="${currentFarmland}">
							</div>
						</div> --%>
						
						<div class="form-group">
							<label for="center" class="col-md-2 control-label">流转信息</label>

							<div class="col-md-8">
								<textarea class="form-control" id="transtion" rows="3">${currentFarmland.transtion }</textarea>
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
					onclick="returnFarmland()">返回</button>
			</div>
		</form>

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
	<script>

		function editInfo() {
			$(".allInfo").removeAttr("disabled");
		}
		function saveInfo() {
			$(".allInfo").attr("disabled", "disabled");
			var username = $.trim($("#username").val());
	        var zonename = $.trim($("#zonename").val());
	        var lal = $.trim($("#lal").val());
	        var area = $.trim($("#area").val());
	        var ph = $.trim($("#ph").val());
	        var npk = $.trim($("#npk").val());
	        var address = $.trim($("#address").val());
	        var transtion = $.trim($("#transtion").val());
	        var result= confirm("确认修改？","确认","取消");
	        if(result == true) {
	        	$.post("../bFarmlandServlet", {op:"editor", username:username,zonename:zonename,
	        		lal:lal,area:area,ph:ph,npk:npk,address:address,transtion:transtion}, function(data) {
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
		function returnFarmland() {
			window.location = "../bFarmlandServlet?op=searchAll";
		}
		
		$("#username").blur(function(){
			var username = $.trim($("#username").val());
			$.post("../bUserServlet?op=isExistUser", {username:username}, function(data) {
				var obj =  eval(data);
				check("usernamediv", "username", obj.mark);
				$("#realname").val(obj.realname);
			}, "json");
		});
		$("#zonename").blur(function(){
			var zonename = $.trim($("#zonename").val());
			$.post("../bZoneServlet?op=isExistZone", {zonename:zonename}, function(data) {
				var obj =  eval(data);
				check("zonenamediv", "zonename", obj.mark);
				$("#type").val(obj.type);
			}, "json");
		});
		function submitChange() {
			var coordinate = $.trim($("#coordinate").val());
			var _address = $.trim($("#_address").val());
			$("#lal").val(coordinate);
			$("#address").val(_address);
			$('#myModal').modal('hide');
		}
		
	</script>
	

</body>
</html>