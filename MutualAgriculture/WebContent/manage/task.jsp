<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:useBean id="now" class="java.util.Date" scope="page" />
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
<link rel="stylesheet" href="depend/select2/select2.min.css">

<link rel="stylesheet"
	href="depend/bootstrap-fileinput-master/css/fileinput.min.css">
<title>Document</title>
<style type="text/css">
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
				<li><a href="farmer.jsp">任务</a></li>
				<li class="active">详情</li>
			</ol>
		</section>
		<br /> <br />

		<div>
			<!-- Nav tabs -->
			<ul class="nav nav-tabs" role="tablist">
				<li role="presentation" class="active"><a href="#doingtask"
					aria-controls="doingtask" role="tab" data-toggle="tab">正在进行</a></li>
				<li role="presentation"><a href="#historytask"
					aria-controls="historytask" role="tab" data-toggle="tab">历史任务</a></li>
				<li role="presentation"><a href="#chartanalyze"
					aria-controls="chartanalyze" role="tab" data-toggle="tab">统计分析</a></li>
			</ul>

			<!-- Tab panes -->
			<div class="tab-content">
				<!-- 正在进行 -->
				<div role="tabpanel" class="tab-pane fade in active" id="doingtask">
					<br>
					
					
					<div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingOne">
					      <h4 class="panel-title">
					        <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
					          	任务详情
					        </a>
					      </h4>
					    </div>
					    <div id="collapseOne" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne">
					      <div class="panel-body">
					       		<table id="table" data-toggle="table" data-pagination="true" data-page-size="5">
									<thead>
										<tr>
											<th></th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${tasking }" var="item">
										<tr>
											<td>
												<div class="box">
													<div class="box-header with-border">
														<h3 class="box-title">任务编号：${item.taskId }</h3>
														<div class="box-tools pull-right">
															<button type="button" class="btn btn-box-tool"
																data-widget="collapse" data-toggle="tooltip"
																title="Collapse">
																<i class="fa fa-minus"></i>
															</button>
															<button type="button" class="btn btn-box-tool"
																data-widget="remove" data-toggle="tooltip" title="Remove">
																<i class="fa fa-times"></i>
															</button>
														</div>
													</div>
													<div class="box-body">
														<ul>
															<li><label>农机手信息：</label>${item.user.username }&nbsp;&nbsp;${item.user.realname }&nbsp;&nbsp;${item.user.phone }</li>
															<li><label>农田主信息：</label>${item.farmland.user.username }&nbsp;&nbsp;${item.farmland.user.realname }&nbsp;&nbsp;${item.farmland.user.phone }</li>
															<li><label>分区信息：</label>${item.farmland.zone.zoneId }&nbsp;&nbsp;${item.farmland.zone.zonename }&nbsp;&nbsp;${item.farmland.zone.type }</li>
															<li><label>农田信息：</label>${item.farmland.farmlandId }&nbsp;&nbsp;${item.farmland.address }&nbsp;&nbsp;${item.farmland.area }</li>
															<li><label>农机信息：</label>${item.machine.machineId }&nbsp;&nbsp;${item.machine.type }&nbsp;&nbsp;${item.machine.plate }</li>
															<li><label>任务时间：</label>${item.workdate }</li>
															<li><label>任务描述：</label>${item.descr }</li>
														</ul>
													</div>
													<div class="box-footer">
														[发布时间]${item.publishdate }
														<button type="button" class="btn btn-danger"
															style="float: right">取消任务</button>
														<button onclick="finishTask(${item.taskId })" type="button" class="btn btn-success"
															style="float: right">任务完成</button>
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					       	
					       
					      </div>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingTwo">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo" id="publishTask">
					          	发布任务
					        </a>
					      </h4>
					    </div>
					    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
					      <div class="panel-body">
						        <table class="table table-bordered table-striped">
				      			<tbody>
								<tr>
									<th style="width: 80px"><label>农机手姓名</label></th>
									<td style="width: 150px">
										<select id="select1" class="js-example-basic-single" style="width: 90%">
										</select>
									</td>
									<th style="width: 80px"><label>农机手手机号</label></th>
									<td style="width: 150px" id="machinerphone"></td>
								</tr>
								<tr>
									<td colspan="4">
										<a href="#myModal" role="button" class="btn btn-success" data-toggle="modal" onclick="showModal()">选择农田</a>
									</td>
								</tr>
								<tr>
									<th><label>农田主姓名</label></th>
									<td id="farmername"></td>
									<th><label>农田主手机号</label></th>
									<td id="farmerphone"></td>
								</tr>
								<tr>
									<th><label>所属分区名</label></th>
									<td id="zonename"></td>
									<th><label>作物类型</label></th>
									<td id="zonetype"></td>
								</tr>
								
								<tr>
									<th><label>农田编号</label></th>
									<td id="farmlandId"></td>
									
									<th><label>地址</label></th>
									<td><a href="#" id="address"></a></td>
								</tr>
								<tr>
									<th style="width: 80px"><label>空闲农机</label></th>
									<td style="width: 150px">
										<select id="select2" class="js-example-basic-single" style="width: 90%">
										</select>
									</td>
									<th style="width: 80px"><label>农机类型</label></th>
									<td style="width: 150px" id="machinetype"></td>
								</tr>
								<tr>
									<th><label>任务时间</label></th>
									<td><a href="#" id="workdate"></a></td>
									<th><label>任务描述</label></th>
									<td><a href="#" id="descr"></a></td>
								</tr>
							</tbody>
							</table>
							<button type="button" class="btn btn-success" id="confirmAdd-btn">确定</button>
							<button type="button" class="btn btn-default" id="cancelAdd-btn">取消</button>
					      </div>
					    </div>
					  </div>
					</div>
				</div>
				
				<!-- 历史任务 -->
				<div role="tabpanel" class="tab-pane fade" id="historytask">
					<br>
					<div class="panel-group" id="accordion2" role="tablist" aria-multiselectable="true">
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingOne2">
					      <h4 class="panel-title">
					        <a role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne2" aria-expanded="true" aria-controls="collapseOne2">
					          	任务详情
					        </a>
					      </h4>
					    </div>
					    <div id="collapseOne2" class="panel-collapse collapse in" role="tabpanel" aria-labelledby="headingOne2">
					      <div class="panel-body">
					        <table id="table" data-toggle="table" data-pagination="true" data-page-size="5">
								<thead>
									<tr>
										<th></th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${tasked }" var="item">
										<tr>
											<td>
												<div class="box">
													<div class="box-header with-border">
														<h3 class="box-title" id="taskidInfo">任务编号：${item.taskId }</h3>
														<div class="box-tools pull-right">
															<button type="button" class="btn btn-box-tool"
																data-widget="collapse" data-toggle="tooltip"
																title="Collapse">
																<i class="fa fa-minus"></i>
															</button>
															<button type="button" class="btn btn-box-tool"
																data-widget="remove" data-toggle="tooltip" title="Remove">
																<i class="fa fa-times"></i>
															</button>
														</div>
													</div>
													<div class="box-body">
														<ul>
															<li><label>农机手信息：</label>${item.user.username }&nbsp;&nbsp;${item.user.realname }&nbsp;&nbsp;${item.user.phone }</li>
															<li><label>农田主信息：</label>${item.farmland.user.username }&nbsp;&nbsp;${item.farmland.user.realname }&nbsp;&nbsp;${item.farmland.user.phone }</li>
															<li><label>分区信息：</label>${item.farmland.zone.zoneId }&nbsp;&nbsp;${item.farmland.zone.zonename }&nbsp;&nbsp;${item.farmland.zone.type }</li>
															<li><label>农田信息：</label>${item.farmland.farmlandId }&nbsp;&nbsp;${item.farmland.address }&nbsp;&nbsp;${item.farmland.area }</li>
															<li><label>农机信息：</label>${item.machine.machineId }&nbsp;&nbsp;${item.machine.type }&nbsp;&nbsp;${item.machine.plate }</li>
															<li><label>任务时间：</label>${item.workdate }</li>
															<li><label>任务描述：</label>${item.descr }</li>
														</ul>
													</div>
													<div class="box-footer">
														[发布时间]${item.publishdate }&nbsp;&nbsp;[完成时间]${(item.finishdate eq null || item.finishdate == '') ? '未记录':item.finishdate} 
													</div>
												</div>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
					      </div>
					    </div>
					  </div>
					  <div class="panel panel-default">
					    <div class="panel-heading" role="tab" id="headingTwo2">
					      <h4 class="panel-title">
					        <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion2" href="#collapseTwo2" aria-expanded="false" aria-controls="collapseTwo2">
					          Collapsible Group Item #2
					        </a>
					      </h4>
					    </div>
					    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo2">
					      <div class="panel-body">
					        Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. 3 wolf moon officia aute, non cupidatat skateboard dolor brunch. Food truck quinoa nesciunt laborum eiusmod. Brunch 3 wolf moon tempor, sunt aliqua put a bird on it squid single-origin coffee nulla assumenda shoreditch et. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident. Ad vegan excepteur butcher vice lomo. Leggings occaecat craft beer farm-to-table, raw denim aesthetic synth nesciunt you probably haven't heard of them accusamus labore sustainable VHS.
					      </div>
					    </div>
					  </div>
					</div>
				
				
				
					
				</div>
				<div role="tabpanel" class="tab-pane fade" id="chartanalyze">...</div>
			</div>

		</div>

	</div>
	<jsp:include page="farmlandmap.html"></jsp:include>
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
		$("#select1").on("select2:select", function (e) {
			var text = $("#select1").select2('data')[0]['text'];
			var phone = text.split(' ')[1];
			$("#machinerphone").text(phone);
			var ownerId = $("#select1").val();
		})
		$("#select2").on("select2:select", function (e) {
			var text = $("#select1").select2('data')[0]['text'];
			var type = text.split(' ')[1];
			$("#machinetype").text(type);
			var machineId = $("#select2").val();
		}); 
		
		$('#address').editable({
			type : 'text',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#workdate').editable({
			type : 'text',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$('#descr').editable({
			type : 'textarea',
			validate : function(value) {
				if (value == '') {
					return '不能为空';
				}
			}
		});
		$("#cancelAdd-btn").click(function() {
			$("#select1").select2('val',' ');
			$("#select2").select2('val',' ');
			$("#machinerphone").text('');
			$("#farmername").text('');
			$("#farmerphone").text('');
			$("#zonename").text('');
			$('#zonetype').text('');
			$("#farmlandId").text('');
			$("#machinetype").text('');
	        $("#address").editable('setValue', null).removeClass('editable-unsaved');
			$("#workdate").editable('setValue', null).removeClass('editable-unsaved');
			$("#descr").editable('setValue', null).removeClass('editable-unsaved');
	        $("#collapseTwo").collapse('hide');
		});	
		$("#confirmAdd-btn").click(function() {
			var username = $("#select1").val();
			var machineId = $("#select2").val();
	    	var farmlandId = $.trim($("#farmlandId").text());
			var workdate = $("#workdate").editable('getValue', true);
			var descr = $("#descr").editable('getValue', true);
			alert(username + " " + machineId + " " + farmlandId + " " + workdate + " " + descr);
			if(username == null || machineId == null || farmlandId == null || workdate == null || descr == null
					|| username == '' || machineId == '' || farmlandId == '' || workdate == '' || descr == '') {
				alert("请完善信息");
				return;
			}
			$.post("../taskServlet?op=pulishTask", {username:username,machineId:machineId,farmlandId:farmlandId,
				workdate:workdate,descr:descr}, function(data) {
	    			if(data == 1) {
	    				alert("任务推送成功...")
	    				location.href = "../taskServlet?op=listTask";
	    			} else {
	    				alert("任务推送失败...")
	    			}
	    	});
			$("#select1").select2('val',' ');
			$("#select2").select2('val',' ');
			$("#machinerphone").text('');
			$("#farmername").text('');
			$("#farmerphone").text('');
			$("#zonename").text('');
			$('#zonetype').text('');
			$("#farmlandId").text('');
			$("#machinetype").text('');
	        $("#address").editable('setValue', null).removeClass('editable-unsaved');
			$("#workdate").editable('setValue', null).removeClass('editable-unsaved');
			$("#descr").editable('setValue', null).removeClass('editable-unsaved');
	        $("#collapseTwo").collapse('hide');
	   	});
		$("#publishTask").click(function() {
			$.post("../bUserServlet",{op:"findFreeUser", type:"v_machiner"}, function(data) {
				var machiners = new Array();
				for(var i=0; i<data.length; i++) {
					var obj = {
							"id": data[i].username,
							"text": data[i].realname + " " + data[i].phone
					}
					machiners.push(obj);
					
				}
				$('#select1').select2({
					data: machiners
				})
				$("#select1").select2('val',' ');
			}, "json");
			$.post("../bMachineServlet",{op:"findFreeMachine"}, function(data) {
				var machines = new Array();
				for(var i=0; i<data.length; i++) {
					var obj = {
							"id": data[i].machineId,
							"text": data[i].plate + " " + data[i].type
					}
					machines.push(obj);
					
				}
				$('#select2').select2({
					data: machines
				})
				$("#select2").select2('val',' ');
			}, "json");
		});
		
		function finishTask(obj) {
			var taskId = obj;
			if(taskId==""||taskId==undefined){
				return;
			}else{
				var result= confirm("确认完成","确认","取消");
				if(result==true){
					$.post("../taskServlet", {op:"finishTask", taskId:taskId}, function(data) {
			        	if(data == 1) {
			        		location.href = "../taskServlet?op=listTask";
			        	} else {
			        		alert("操作失败");
			        	}
			        });
				}else{
					return;
				}
			}
		}
			
	</script>

</body>
</html>