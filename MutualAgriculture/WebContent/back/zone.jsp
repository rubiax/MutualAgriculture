<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="now" class="java.util.Date" scope="page"/>
<!doctype html>
<html lang="zh-CN">
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
    <link rel="stylesheet" href="css/plugins/datatables/jquery.dataTables.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="css/dist/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins
         folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="css/dist/skin/_all-skins.min.css">

    <title>Document</title>
</head>
<body class="hold-transition skin-blue sidebar-mini" style="background-color: #ECF0F5">

<div class="container">



    <div class="box">
        <div class="box-header">
            <h3 class="box-title">Data Table With Full Features</h3>
            <div class="container">
                <a class="btn btn-default" href="addzone.jsp">
                    <i class="fa fa-edit"></i>&nbsp;新增
                </a>
                <a class="btn btn-default" href="javascript:del()">
                    <i class="fa fa-times"></i>&nbsp;删除
                </a>
                <a class="btn btn-default" href="javascript:detail()">
                    <i class="fa fa-area-chart"></i>&nbsp;详情
                </a>
                <a class="btn btn-default">
                    <i class="fa fa-check-square-o"></i>&nbsp;全选
                </a>
                <a class="btn btn-default">
                    <i class="fa fa-square-o"></i>&nbsp;取消
                </a>
                <a class="btn btn-default">
                    <i class="fa fa-save"></i>&nbsp;导出
                </a>
            </div>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
            <div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                <div class="row">
                    <div class="col-sm-12">
                        <table id="example" class="table table-bordered table-striped dataTable" role="grid"
                               aria-describedby="example1_info">
                            <thead>
                            <tr>
                                <th rowspan="1" colspan="1">分区名</th>
                            <th rowspan="1" colspan="1">面积</th>
                            <th rowspan="1" colspan="1">作物类型</th>
                            <th rowspan="1" colspan="1">地址</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${allZone }" var="item">
                        	<tr role="row" class="odd">
                        	<td class=""><label hidden="hidden">${item.zoneId }</label>${item.zonename }</td>
                            <td class="sorting_1">${item.area }</td>
                            <td>${item.type }</td>
                            <td>${item.address }</td>
                        	</tr>
                        </c:forEach>

                            </tbody>
                            <tfoot>
                            <tr>
                                 <th rowspan="1" colspan="1">分区名</th>
                            <th rowspan="1" colspan="1">面积</th>
                            <th rowspan="1" colspan="1">作物类型</th>
                            <th rowspan="1" colspan="1">地址</th>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>

            </div>
        </div>
        <!-- /.box-body -->
    </div>


</div>


<!-- jQuery 2.2.3 -->
<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="js/bootstrap/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="js/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="js/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="js/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="js/plugins/fastclick/fastclick.min.js"></script>
<!-- AdminLTE App -->
<script src="js/dist/app.min.js"></script>
<!-- AdminLTE for demo purposes -->
<script src="js/dist/demo.js"></script>
<!-- page script -->
<script>

	var zoneId;
	
    $(function () {
        var table = $('#example').DataTable();

        $('#example tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
                zoneId = '';
            }
            else {
                table.$('tr.selected').removeClass('selected');
                zoneId = '';
                $(this).addClass('selected');
                //alert(table.$('tr.selected td:first').html());
                zoneId = $('.selected td:first label').text();
            }
        } );

        $('#button').click( function () {
            table.row('.selected').remove().draw( false );
        } );
        
    });
    
    function detail() {
    	if(zoneId == '' || zoneId == undefined) {
    		return;
    	}
    	var uri= "../bZoneServlet?op=detail&zoneId="+zoneId;
    	location.href = uri;
    }
    
    
    function del() {
    	if(zoneId==" "||zoneId==undefined){
    		return;
    	}else{
    		var result= confirm("确认删除？","确认","取消");
    		if(result==true){
    			$.post("../bZoneServlet", {op:"delete", zoneId:zoneId}, function(data) {
    	        	if(data == 1) {
    	        		alert("删除成功");
    	        		location.href = "zone.jsp";
    	        	} else {
    	        		alert("删除失败");
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
  