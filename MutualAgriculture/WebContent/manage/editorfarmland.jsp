<%@page import="java.util.List"%>
<%@page import="java.util.Arrays"%>
<%@page import="com.geowind.hunong.jpa.Farmland"%>
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
<link rel="stylesheet"
	href="depend/select2/select2.min.css">

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
						class="fa fa-dashboard"></i>仪表盘</a></li>
				<li><a href="machine.jsp">农田</a></li>
				<li class="active">详情</li>
			</ol>
		</section>
		<br /> <br />

		<form class="form-horizontal">

			<!-- Horizontal Form -->
			<div class="box box-success">
				<div class="box-header with-border">
					<h3 class="box-title">基本信息</h3>
				</div>

				<div class="box-body">

					<table class="table table-bordered">
						<tbody>
							<tr>
								<td style="width: 150px" rowspan="6">

									<div id="carousel-example-generic" class="carousel slide"
										data-ride="carousel">
										<!-- Indicators -->
										<ol class="carousel-indicators">
											<li data-target="#carousel-example-generic" data-slide-to="0"
												class="active"></li>
											<li data-target="#carousel-example-generic" data-slide-to="1"></li>
											<li data-target="#carousel-example-generic" data-slide-to="2"></li>
										</ol>

										<!-- Wrapper for slides -->
										<div class="carousel-inner" role="listbox">
										
										<c:forEach items="${picList}" var="item"> 
											<div class="item">
												<img src="${item}" alt="无图片" width="100%"
													height="180">

											</div>
										</c:forEach>
										
										</div>

										<!-- Controls -->
										<a class="left carousel-control"
											href="#carousel-example-generic" role="button"
											data-slide="prev"> <span
											class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
											<span class="sr-only">Previous</span>
										</a> <a class="right carousel-control"
											href="#carousel-example-generic" role="button"
											data-slide="next"> <span
											class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
											<span class="sr-only">Next</span>
										</a>
									</div>
								</td>
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
								<th style="width: 80px"><label>农田编号</label></th>
								<td style="width: 150px">${currentFarmland.farmlandId }</td>
								<td colspan="2">
									<a href="#myModal" role="button" class="btn btn-success" data-toggle="modal" onclick="showModal()">修改地址及经纬度</a>
								</td>
							</tr>
							<tr>
								<th><label>经纬度</label></th>
								<td><a href="#" id="jingweidu">(${currentFarmland.latitude }, ${currentFarmland.longitude })</a></td>
								
								<th><label>地址</label></th>
								<td><a href="#" id="address">${currentFarmland.address }</a></td>
							</tr>
							<tr>
								<th><label>面积</label></th>
								<td><a href="#" id="area">${currentFarmland.area }</a></td>
								
								<th><label>土壤酸碱度</label></th>
								<td><a href="#" id="ph">${currentFarmland.ph }</a></td>
							</tr>
							<tr>
								<th><label>氮磷钾含量</label></th>
								<td><a href="#" id="npk">${currentFarmland.npk }</a></td>
								
								<th><label>流转信息</label></th>
								<td><a href="#" id="transtion">${currentFarmland.transtion }</a></td>
							</tr>
						</tbody>
					</table>

				</div>
				<!-- /.box-body -->

			</div>

		</form>
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
	<script>
		function actionFormatter(value, row, index) {
			return [
					'<a class="edit ml10" href="javascript:void(0)" title="编辑">',
					'<i class="glyphicon glyphicon-edit"></i>',
					'</a>',
					'<a class="remove ml10" href="javascript:void(0)" title="删除">',
					'<i class="glyphicon glyphicon-remove"></i>', '</a>' ]
					.join('');
		}

		window.actionEvents = {
			'click .edit' : function(e, value, row, index) {
				alert('You click edit icon, row: ' + JSON.stringify(row));
				console.log(value, row, index);
			},
			'click .remove' : function(e, value, row, index) {
				alert('You click remove icon, row: ' + JSON.stringify(row));
				console.log(value, row, index);
			}
		};
		$(function() {
			$("#select1").select2();
			$("#select1").val("${currentFarmland.user.username }").trigger("change");
			$("#select2").select2();
			$("#select2").val("${currentFarmland.zone.zoneId } ${currentFarmland.zone.type}").trigger("change");
			$(".carousel-inner .item:first").addClass("active");
		});
		function editInfo() {
			$(".allInfo").removeAttr("disabled");
		}
		function saveInfo() {
			$(".allInfo").attr("disabled", "disabled");
		}
		function returnFarmer() {
			window.location = "farmer.jsp";
		}
		function dashboard() {
			parent.location.reload();
		}
		$("#select1").on("select2:select", function (e) {
			var text = $("#select1").select2('data')[0]['text'];
			var phone = text.split(' ')[1];
			$("#phone").text(phone);
			var username = $("#select1").val();
			$.post('../bFarmlandServlet', {
				op : 'editeOne',
				pk : '${currentFarmland.farmlandId }',
				item : "username",
				value : username
			});
		});
		$("#select2").on("select2:select", function (e) {
			var value = $("#select2").val();
			var type = value.split(' ')[1];
			var zoneId = value.split(' ')[0];
			$("#type").text(type);
			$.post('../bFarmlandServlet', {
				op : 'editeOne',
				pk : '${currentFarmland.farmlandId }',
				item : "zoneId",
				value : zoneId
			});
		});

		$('#jingweidu').editable({
			type : 'text',
			placeholder: '(纬度, 经度)',
			url : function(params) {
				return $.post('../bFarmlandServlet', {
					op : 'editeOne',
					pk : '${currentFarmland.farmlandId }',
					item : "jingweidu",
					value : params.value
				});
			},
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#address').editable({
			type : 'text',
			url : function(params) {
				return $.post('../bFarmlandServlet', {
					op : 'editeOne',
					pk : '${currentFarmland.farmlandId }',
					item : "address",
					value : params.value
				});
			},
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#area').editable({
			type : 'text',
			url : function(params) {
				return $.post('../bFarmlandServlet', {
					op : 'editeOne',
					pk : '${currentFarmland.farmlandId }',
					item : "area",
					value : params.value
				});
			},
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#ph').editable({
			type : 'text',
			url : function(params) {
				return $.post('../bFarmlandServlet', {
					op : 'editeOne',
					pk : '${currentFarmland.farmlandId }',
					item : "ph",
					value : params.value
				});
			},
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#npk').editable({
			type : 'text',
			url : function(params) {
				return $.post('../bFarmlandServlet', {
					op : 'editeOne',
					pk : '${currentFarmland.farmlandId }',
					item : "npk",
					value : params.value
				});
			},
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#transtion').editable({
			type : 'text',
			url : function(params) {
				return $.post('../bFarmlandServlet', {
					op : 'editeOne',
					pk : '${currentFarmland.farmlandId }',
					item : "transtion",
					value : params.value
				});
			},
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
			$.post('../bFarmlandServlet', {
				op : 'editeOne',
				pk : '${currentFarmland.farmlandId }',
				item : "jingweidu",
				value : coordinate
			});
			$.post('../bFarmlandServlet', {
				op : 'editeOne',
				pk : '${currentFarmland.farmlandId }',
				item : "address",
				value : _address
			});
		}

	</script>

</body>
</html>