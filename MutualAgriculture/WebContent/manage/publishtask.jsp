<%@page import="com.geowind.hunong.jpa.Farmland"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>互农综合管理平台 | 发布任务</title>
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
    <link rel="stylesheet" href="css/dist/skin/skin-green-light.min.css">
    <!-- bootstrap datepicker -->
    <link rel="stylesheet" href="css/plugins/datepicker/datepicker3.css">
    

</head>
<body style="background-color: #ECF0F5">

<div class="container">
	<section class="content-header">
	      <ol class="breadcrumb">
	        <li><a href="javascript:dashboard()"><i class="fa fa-dashboard"></i> 仪表盘</a></li>
	        <li class="active">发布任务</li>
	      </ol>
	</section>
	<br/>
    <br/>

    <div class="box box-success">
        <div class="box-header with-border">
            <h3 class="box-title">发布任务</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <div class="form-horizontal">
            <div class="box-body">
           			<div class="form-group" id="musernamediv">
				        <label for="musername" class="col-md-3 control-label">农机手用户名</label>
				        <div class="col-md-5">
				            <input type="text" class="form-control" name="musername" id="musername"/>
				        </div>
				    </div>
					<div class="form-group">
						<label for="mrealname" class="col-md-3 control-label">农机手姓名</label>
						<div class="col-md-5">
							<input class="form-control" id="mrealname" disabled="disabled" type="text" />
						</div>
					</div>
					<div class="form-group">
						<label for="mphone" class="col-md-3 control-label">农机手手机号</label>
						<div class="col-md-5">
							<input class="form-control" id="mphone" disabled="disabled" type="text"/>
						</div>
					</div>
					<div class="form-group" id="machineplatediv">
                        <label for="machineplate" class="col-md-3 control-label">农机牌号</label>
                        <div class="col-md-5">
                            <input class="form-control" name="machineplate" id="machineplate" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="tasktype" class="col-md-3 control-label">任务类型</label>
                        <div class="col-md-5">
                            <input class="form-control" name="tasktype" id="tasktype" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="taskdate" class="col-md-3 control-label">任务时间</label>
                        <div class="col-md-5">
                            <input class="form-control" name="tasktype" id="taskdate" type="text">
                        </div>
                    </div>
					<!-- <div class="form-group">
	                    <label class="col-md-2 control-label">搜索</label>
	                    <div class="col-md-5">
		                      <input class="col-md-5" type="text" id="suggestId" size="20" value="" style="width:150px;">
	    <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>
	                     <a href="FarmlandMap.jsp"  role="button" class="btn btn-primary">选择农田</a>
	                     </div>target="view_window"
	                     
                    </div> -->
                    <div class="form-group">
	                    <label class="col-md-3 control-label"></label>
	                    <div class="col-md-5">
	                    <button data-target="#myModal" role="button" class="btn btn-success" data-toggle="modal" onclick="showModal()">选择农田</button>
	                    </div>
	                    
                    </div>
                    <div class="form-group" id="farmlandIddiv">
                        <label for="farmlandId" class="col-md-3 control-label">农田编号</label>
                        <div class="col-md-5">
                            <input class="form-control" name="farmlandId" disabled="disabled" id="farmlandId" type="text">
                        </div>
                    </div>
                    <div class="form-group" id="zonenamediv">
                        <label for="zonename" class="col-md-3 control-label">区名</label>
                        <div class="col-md-5">
                            <input class="form-control" name="zonename" disabled="disabled" id="zonename" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="croptype" class="col-md-3 control-label">作物类型</label>

                        <div class="col-md-5">
                            <input class="form-control" id="croptype" disabled="disabled"  type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fname" class="col-md-3 control-label">种粮大户姓名</label>
                        <div class="col-md-5">
                            <input class="form-control" id="fname" disabled="disabled" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="fphone" class="col-md-3 control-label">种粮大户手机号</label>
                        <div class="col-md-5">
                            <input class="form-control" id="fphone" disabled="disabled" type="text">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-md-3 control-label">地址</label>

                        <div class="col-md-5">
                            <textarea class="form-control" rows="3" disabled="disabled" id="address" value="${farmlandInfo eq null?'':farmlandInfo.address }"></textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="workload" class="col-md-3 control-label">工作量</label>
                        <div class="col-md-5">
                            <input class="form-control" id="workload" type="text">
                        </div>
                    </div>
                    <div class="form-group">
							<label for="descr" class="col-md-3 control-label">任务描述</label>

							<div class="col-md-5">
								<textarea class="form-control" id="descr" rows="3"></textarea>
							</div>
					</div>
            </div>
            <!-- /.box-body -->
            <div class="box-footer" align="center">
                <button type="button" class="btn btn-success" onclick="taskOK();">确定</button>
                <button type="button" class="btn btn-default" onclick="returnZone()">返回</button>
            </div>
            <!-- /.box-footer -->
        </div>
    </div>


</div>
<jsp:include page="farmlandmap.html"></jsp:include>
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

<!--标记农田信息-->
<script type="text/javascript" src="js/map/clickmark.js"></script>
<script src="js/index.js"></script>
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
    }
    function returnZone() {
        window.location = "zone.html";
    }
    $("#musername").blur(function(){
		var username = $.trim($("#musername").val());
		$.post("../bUserServlet?op=isExistUser", {username:username}, function(data) {
			var obj =  eval(data);
			check("musernamediv", "musername", obj.mark);
			$("#mrealname").val(obj.realname);
			$("#mphone").val(obj.phone);
		},"json");
	});
    $("#machineplate").blur(function(){
		var machineplate = $.trim($("#machineplate").val());
		$.post("../bMachineServlet?op=isExistMachine", {machineplate:machineplate}, function(data) {
			check("machineplatediv", "machineplate", data);
		});
	});

    function taskOK() {
    	var musername = $.trim($("#musername").val());
    	var machineplate = $.trim($("#machineplate").val());
    	var tasktype = $.trim($("#tasktype").val());
    	var taskdate = $.trim($("#taskdate").val());
    	var farmlandId = $.trim($("#farmlandId").val());
    	var workload = $.trim($("#workload").val());
    	var descr = $.trim($("#descr").val());
    	alert(descr);
    	$.post("../taskServlet?op=pulishTask", {musername:musername,machineplate:machineplate,tasktype:tasktype,
    		taskdate:taskdate,farmlandId:farmlandId,workload:workload,descr:descr}, function(data) {
    			if(data == 1) {
    				alert("任务推送成功...")
    			}
    		});
    }

    function dashboard() {
		parent.location.reload();
    }

</script>

</body>
</html>