<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>互农综合管理平台 | 农田信息</title>
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
    <link rel="stylesheet" href="css/dist/skin/_all-skins.min.css">
    
    <link rel="stylesheet" href="css/table.css">
    <title>Document</title>
</head>
<body class="hold-transition skin-green sidebar-mini" style="background-color: #ECF0F5">
<div class="container">
    <div class="box">
        <div class="box-header">
            <h3 class="box-title">农田信息</h3>
        </div>
        <!-- /.box-header -->
        <div class="box-body">
        	<div class="container" style="margin-bottom: 10px;">
                <a class="btn btn-default" href="addfarmland.jsp">
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
            <div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap">
                <div class="row">
                    <div class="col-sm-12">
                        <table id="example" class="table dataTable table-bordered" role="grid"
                               aria-describedby="example1_info">
                            <thead>
                            <tr>
                                <th rowspan="1" colspan="1">农田编号</th>
                                <th rowspan="1" colspan="1">拥有者用户名</th>
	                            <th rowspan="1" colspan="1">拥有者姓名</th>
	                            <th rowspan="1" colspan="1">经纬度</th>
	                            <th rowspan="1" colspan="1">地址</th>
	                            <th rowspan="1" colspan="1">农田面积</th>
	                            <th rowspan="1" colspan="1">作物种类</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${allFarmland }" var="item">
	                        	<tr role="row" class="odd include">
		                        	<td>${item.farmlandId }</td>
		                        	<td>${item.user.username }</td>
		                        	<td>${item.user.realname }</td>
		                            <td>(${item.longitude },${item.latitude})</td>
		                            <td>${item.address }</td>
		                            <td>${item.area }</td>
		                            <td>${item.zone.type }</td>
	                        	</tr>
                        	</c:forEach>

                            </tbody>
                            <tfoot>
                            <tr>
	                            <th rowspan="1" colspan="1">农田编号</th>
	                            <th rowspan="1" colspan="1">拥有者用户名</th>
	                            <th rowspan="1" colspan="1">拥有者姓名</th>
	                            <th rowspan="1" colspan="1">经纬度</th>
	                            <th rowspan="1" colspan="1">地址</th>
	                            <th rowspan="1" colspan="1">农田面积</th>
	                            <th rowspan="1" colspan="1">作物种类</th>
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

	var farmlandId;	

    $(function () {
        var table = $('#example').DataTable({
    	"oLanguage": {
                "oAria": {
                    "sSortAscending": " - click/return to sort ascending",
                    "sSortDescending": " - click/return to sort descending"
                },
                "sLengthMenu": "显示 _MENU_ 记录",
                "sZeroRecords": "对不起，查询不到任何相关数据",
                "sEmptyTable": "未有相关数据",
                "sLoadingRecords": "正在加载数据-请等待...",
                "sInfo": "当前显示 _START_ 到 _END_ 条，共 _TOTAL_ 条记录。",
                "sInfoEmpty": "当前显示0到0条，共0条记录",
                "sInfoFiltered": "（数据库中共为 _MAX_ 条记录）",
                "sProcessing": "正在加载数据...",
                "sSearch": "模糊查询：",
                "sUrl": "",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": " 上一页 ",
                    "sNext": " 下一页 ",
                    "sLast": " 尾页 "
                },
                //多语言配置
                // set the initial value
                "fnCreatedRow": function(nRow, aData, iDataIndex) {
                    $('td:eq(0)', nRow).html("<span class='row-details row-details-close' data_id='" + aData[1] + "'></span>&nbsp;" + aData[0]);
                }
          },
          "columnDefs": [
               { "width": "20%", "targets": 4 }
          ]
    });

        $('#example tbody').on( 'click', 'tr', function () {
            if ( $(this).hasClass('selected') ) {
                $(this).removeClass('selected');
                farmlandId = '';
            }
            else {
                $('tr.selected').removeClass('selected');
                farmlandId = '';
                $(this).addClass('selected');
                farmlandId = $('.selected td:first').text();
            }
        } );

        $('#button').click( function () {
            table.row('.selected').remove().draw( false );
        } );
        
    });
    
    function del() {
    	if(farmlandId==""||farmlandId==undefined){
    		return;
    	}else{
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
    }
    
    function detail() {
    	if(farmlandId== ""||farmlandId==undefined){
    		return;
    	}else{
    		var uri= "../bFarmlandServlet?op=detail&farmlandId="+farmlandId;
        	location.href = uri;
    	}
    	
    }
</script>
</body>
</html>
  