<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<link rel="shortcut icon" href="img/icon_web_mini.png" type="" />
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
<link rel="stylesheet" href="css/dist/skin/skin-green-light.min.css">
<!-- bootstrap datepicker -->
<link rel="stylesheet" href="css/plugins/datepicker/datepicker3.css">


<link href="depend/bootstrap3-editable/css/bootstrap-editable.css"
	rel="stylesheet" />
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="depend/bootstrap-table/bootstrap-table.css">
<link rel="stylesheet" href="depend/select2/select2.min.css">

<link rel="stylesheet" href="depend/bootstrap-fileinput-master/css/fileinput.min.css">

<title>Document</title>
<style type="text/css">
#userInfo_left {
	float: left;
	width: 40%;
	height: 320px;
}

#userInfo_right {
	float: right;
	width: 60%;
	height: 320px;
}

.ml10 {
	margin-left: 10px;
}
</style>
</head>
<body style="background-color: #ECF0F5">
	<div class="container" style="width: 100%;">
		<section class="content-header">
			<ol class="breadcrumb">
				<li><a href="javascript:dashboard()"><i
						class="fa fa-dashboard"></i> 仪表盘</a></li>
				<li><a href="farmer.jsp">农田</a></li>
				<li class="active">详情</li>
			</ol>
		</section>
		<br /> <br />

		<!-- Horizontal Form -->
		<div class="box box-success">
			<div class="box-header with-border">
				<h3 class="box-title">农田信息</h3>
			</div>

			<div class="box-body">
				<div id="toolbar" class="btn-group">
					<button type="button" class="btn btn-default" data-toggle="collapse" data-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
						<i class="glyphicon glyphicon-plus"></i>
					</button>
					<button type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-heart"></i>
					</button>
					<button type="button" class="btn btn-default">
						<i class="glyphicon glyphicon-trash"></i>
					</button>
				</div>
				<div id="collapseOne" class="accordion-body collapse">
			      <div class="accordion-inner">
			      		<table class="table table-bordered table-striped">
			      			<tbody>
							<tr>
								<th style="width: 80px"><label>拥有者姓名</label></th>
								<td style="width: 150px">
									<select id="select1" class="js-example-basic-single" style="width: 90%">
										<c:forEach var="item" items="${allFarmer }">
											<option value="${item.username }">${item.realname } ${item.phone } </option>
										</c:forEach>
									</select>
								</td>
								<th style="width: 80px"><label>拥有者手机号</label></th>
								<td style="width: 150px" id="phone">${currentFarmland.user.phone }</td>
								
							</tr>
							<tr>
								<th style="width: 80px"><label>所属分区名</label></th>
								<td style="width: 150px">
									<select id="select2" class="js-example-basic-single" style="width: 90%">
										<c:forEach var="item" items="${allZone }">
											<option value="${item.zoneId } ${item.type }">${item.zonename }</option>
										</c:forEach>
									</select>
								</td>
								<th style="width: 80px"><label>作物类型</label></th>
								<td style="width: 150px" id="type">${currentFarmland.zone.type }</td>
							</tr>
							<tr>
								<td colspan="4">
									<a href="#myModal" role="button" class="btn btn-success" data-toggle="modal" onclick="showModal()">修改地址及经纬度</a>
								</td>
							</tr>
							<tr>
								<th><label>经纬度</label></th>
								<td><a href="#" id="jingweidu"></a></td>
								
								<th><label>地址</label></th>
								<td><a href="#" id="address"></a></td>
							</tr>
							<tr>
								<th><label>面积</label></th>
								<td><a href="#" id="area"></a></td>
								
								<th><label>土壤酸碱度</label></th>
								<td><a href="#" id="ph"></a></td>
							</tr>
							<tr>
								<th><label>氮磷钾含量</label></th>
								<td><a href="#" id="npk"></a></td>
								
								<th><label>流转信息</label></th>
								<td><a href="#" id="transtion"></a></td>
							</tr>
						</tbody>
						</table>
						<label class="control-label">选择图片</label>
						<form id="myform" action="../bMachineServlet?op=uploadImage" method="post" enctype="multipart/form-data">
                        	<input id="uploadImg" name="uploadImg" type="file" class="file" multiple data-show-upload="false" data-show-caption="true" data-allowed-file-extensions='["jpg", "png","gif","jpeg"]'>
						</form>
						<br>
						<button type="button" class="btn btn-success" id="confirmAdd-btn">确定</button>
						<button type="button" class="btn btn-default" id="cancelAdd-btn">取消</button>
			      </div>
			    </div>
				<table id="table" data-toolbar="#toolbar">
					<thead>
						<tr>
							<th data-field="state" data-checkbox="true"></th>
							<th data-field="farmlandId" data-sortable="true">编号</th>
							<th data-field="realname" data-sortable="true">拥有者姓名</th>
							<th data-field="zonename" data-sortable="true">分区名</th>
							<th data-field="jingweidu" data-sortable="true">经纬度</th>
							<th data-field="address" data-sortable="true">地址</th>
							<th data-field="type" data-sortable="true">作物类型</th>
							<th data-field="area" data-sortable="true">面积</th>
							<th data-field="action" data-formatter="actionFormatter" data-events="actionEvents" data-width="65">操作</th>
						</tr>
					</thead>
					<tbody>
                        <c:forEach items="${allFarmland }" var="item">
                        	<tr>
                        		<td data-field="state" data-checkbox="true"></td>
	                        	<td>${item.farmlandId }</td>
	                        	<td>${item.user.realname }</td>
	                        	<td>${item.zone.zonename }</td>
	                            <td>(${item.longitude }, ${item.latitude})</td>
	                            <td>${item.address }</td>
	                            <td>${item.zone.type }</td>
	                            <td>${item.area }</td>
	                            <td data-field="action" data-formatter="actionFormatter" data-events="actionEvents"></td>
                        	</tr>
                       	</c:forEach>
					</tbody>
				</table>

			</div>
			<!-- /.box-body -->

		</div>
	</div>
	<jsp:include page="smallmap.html"></jsp:include>
	<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<script src="js/bootstrap/bootstrap.min.js"></script>
	<!-- date-range-picker -->
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<!-- bootstrap datepicker -->
	<script src="js/plugins/datepicker/bootstrap-datepicker.js"></script>
	<!-- InputMask -->
	<script src="js/plugins/input-mask/jquery.inputmask.js"></script>
	<script src="js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script src="js/plugins/input-mask/jquery.inputmask.extensions.js"></script>
	<script src="depend/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
	<!-- Latest compiled and minified JavaScript -->
	<script src="depend/bootstrap-table/bootstrap-table.min.js"></script>

	<!-- Latest compiled and minified Locales -->
	<script src="depend/bootstrap-table/bootstrap-table-zh-CN.min.js"></script>
	<script src="depend/select2/select2.min.js"></script>
	<script src="depend/bootstrap-fileinput-master/js/fileinput.min.js"></script>
	<script src="depend/bootstrap-fileinput-master/js/zh.js"></script>
	<script>
		function actionFormatter(value, row, index) {
			return [
					'<a class="edit ml10" href="javascript:void(0)" title="编辑">',
					'<i class="glyphicon glyphicon-edit"></i>',
					'</a>',
					'<a class="remove ml10" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}

		window.actionEvents = {
			'click .edit' : function(e, value, row, index) {
				//alert('You click edit icon, row: ' + JSON.stringify(row));
				//console.log(value, row, index);
				var farmlandId = row.farmlandId;
				location.href= "../bFarmlandServlet?op=detail&farmlandId="+farmlandId;
			},
			'click .remove' : function(e, value, row, index) {
				//alert('You click remove icon, row: ' + JSON.stringify(row));
				//console.log(value, row, index);
				var farmlandId = row.farmlandId;
				var result= confirm("确认删除？","确认","取消");
	    		if(result==true){
	    			$.post("../bFarmlandServlet", {op:"delete", farmlandId:farmlandId}, function(data) {
	    	        	if(data == 1) {
	    	        		alert("删除成功");
	    	        		location.href = "../bFarmlandServlet?op=searchAll";
	    	        	} else {
	    	        		alert("删除失败");
	    	        	}
	    	        });
	    		}else{
	    			return;
	    		}
			}
		};
		$(function() {
			$('#table').bootstrapTable({
				pagination : true,
				pageNumber : 1,
				pageSize : 5,
				pageList : [ 5, 10, 20, 50 ],
				search : true,
				height : 380,
				showRefresh : true,
				showToggle : true,
				showColumns : true,
				clickToSelect : true,
				sortName: 'farmlandId',
				sortOrder: 'desc'
			});
			$("#select1").select2();
			//$("#select1").val("${currentMachine.machineowner.ownerId }").trigger("change");
			$("#select1").select2('val',' ');
			$("#select2").select2();
			$("#select2").select2('val',' ');
		});
		
		$("#select1").on("select2:select", function (e) {
			var text = $("#select1").select2('data')[0]['text'];
			var phone = text.split(' ')[1];
			$("#phone").text(phone);
			var username = $("#select1").val();
		});
		$("#select2").on("select2:select", function (e) {
			var value = $("#select2").val();
			var type = value.split(' ')[1];
			var zoneId = value.split(' ')[0];
			$("#type").text(type);
		});

		$('#jingweidu').editable({
			type : 'text',
			placeholder: '(纬度, 经度)',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#address').editable({
			type : 'text',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#area').editable({
			type : 'text',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#ph').editable({
			type : 'text',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#npk').editable({
			type : 'text',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#transtion').editable({
			type : 'text',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		function submitChange() {
			var coordinate = $.trim($("#coordinate").val());
			var _address = $.trim($("#_address").val());
			$("#jingweidu").text(coordinate);
			$("#address").text(_address);
			$('#myModal').modal('hide');
		}
	</script>

</body>
</html>