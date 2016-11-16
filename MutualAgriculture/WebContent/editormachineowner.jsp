<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <link rel="shortcut icon" href="img/icon_web_mini.png" type=""/>
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
		       <li class="active">详情</li>
		     </ol>
	</section>
	<br/>
    <br/>
	    
    <div class="box box-info">
        <div class="box-header with-border">
            <h3 class="box-title">Horizontal Form</h3>
        </div>
        <!-- /.box-header -->
        <!-- form start -->
        <form class="form-horizontal">
            <div class="box-body">
                <fieldset disabled="disabled" class="col-md-10 allInfo">
                    <div class="form-group">
                        <label for="username" class="col-md-2 control-label">姓名</label>

                        <div class="col-md-10">
                            <input class="form-control" id="username" type="text" value="${currentMachineOwner.name }">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="sex" class="col-md-2 control-label">性别</label>
                        <div class="radio col-md-10">
                            <label>
								<input name="sex" id="sex"
									value="男" type="radio" ${currentMachineOwner.sex=='男'?'checked':''}> 男
								</label> <label> <input name="sex" id="sex"
									value="女" type="radio" ${currentMachineOwner.sex=='女'?'checked':''}> 女
								</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="datemask" class="col-md-2 control-label">出生日期</label>
                        <div class="col-md-10">
                            <div class="input-group">
                                <div class="input-group-addon">
                                    <i class="fa fa-calendar"></i>
                                </div>
                                <input type="text" class="form-control" data-inputmask="'alias': 'yyyy-mm-dd'" data-mask="" id="datemask" value="${currentMachineOwner.birthday }">
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label for="phone" class="col-sm-2 control-label">手机</label>

                        <div class="col-sm-10">
                            <input class="form-control" id="phone" type="text" id="phone" value="${currentMachineOwner.phone }">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="address" class="col-md-2 control-label">地址</label>

                        <div class="col-md-10">
                            <textarea class="form-control" rows="3" id="address">${currentMachineOwner.address }</textarea>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="center" class="col-sm-2 control-label" >服务中心</label>

                        <div class="col-sm-10">
                            <input disabled="disabled"  class="form-control" id="center" type="text" value="${currentMachineOwner.center.name }">
                        </div>
                    </div>
                </fieldset>
            </div>
            <!-- /.box-body -->
            <div class="box-footer" align="center">
                <button type="button" class="btn btn-default" onclick="editInfo()">修改</button>
                <button type="button" class="btn btn-default" onclick="saveInfo()">保存</button>
                <button type="reset" class="btn btn-default">重置</button>
                <button type="button" class="btn btn-default" onclick="returnMachinerOwner()">返回</button>
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
    function saveInfo() {
        $(".allInfo").attr("disabled", "disabled");
        var username = $.trim($("#username").val());
        var sex = $('input:radio:checked').val();
        var address = $.trim($("#address").val());
        var phone = $.trim($("#phone").val());
        var birthday = $.trim($("#datemask").val());
        alert(username+" "+sex+" "+address+" "+phone+" "+birthday);
        var result= confirm("确认修改？","确认","取消");
        if(result == true) {
        	$.post("bMachineOwnerServlet", {op:"editor", username:username, sex:sex, address:address, phone:phone, birthday:birthday}, function(data) {
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
    function returnMachinerOwner() {
        window.location = "bMachineOwnerServlet?op=searchAll";
    }
    function dashboard() {
		parent.location.reload();
    }

</script>

</body>
</html>