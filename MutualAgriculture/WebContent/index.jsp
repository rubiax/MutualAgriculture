<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>互农综合管理平台</title>
  <link rel="shortcut icon" href="img/icon_web_mini.png" type=""/>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="css/bootstrap/bootstrap.min.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.5.0/css/font-awesome.min.css">
  <!-- Ionicons -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/ionicons/2.0.1/css/ionicons.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="css/dist/AdminLTE.min.css">
  <link rel="stylesheet" href="css/dist/skin/_all-skins.min.css">

  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body class="hold-transition skin-green-light sidebar-mini ">
<div class="wrapper">

  <!-- Main Header -->
  <header class="main-header">

    <!-- Logo -->
    <a href="index.jsp" class="logo">
      <!-- mini logo for sidebar mini 50x50 pixels -->
      <span class="logo-mini"><b><img alt="" height="42px" src="img/icon_web.png"></b></span>
      <!-- logo for regular state and mobile devices -->
      <span class="logo-lg"><b>互农</b><small>综合管理平台</small></span>
    </a>

    <!-- Header Navbar -->
    <nav class="navbar navbar-static-top" role="navigation">
      <!-- Sidebar toggle button-->
      <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
        <span class="sr-only">Toggle navigation</span>
      </a>
      <!-- Navbar Right Menu -->
      <div class="navbar-custom-menu">
        <ul class="nav navbar-nav">
          <!-- Messages: style can be found in dropdown.less-->
          <li class="dropdown messages-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-envelope-o"></i>
              <span class="label label-success">4</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 4 messages</li>
              <li>
                <!-- inner menu: contains the messages -->
                <ul class="menu">
                  <li><!-- start message -->
                    <a href="#">
                      <div class="pull-left">
                        <!-- User Image -->
                        <img src="img/user2-160x160.jpg" class="img-circle" alt="User Image">
                      </div>
                      <!-- Message title and timestamp -->
                      <h4>
                        Support Team
                        <small><i class="fa fa-clock-o"></i> 5 mins</small>
                      </h4>
                      <!-- The message -->
                      <p>Why not buy a new awesome theme?</p>
                    </a>
                  </li>
                  <!-- end message -->
                </ul>
                <!-- /.menu -->
              </li>
              <li class="footer"><a href="#">See All Messages</a></li>
            </ul>
          </li>
          <!-- /.messages-menu -->

          <!-- Notifications Menu -->
          <li class="dropdown notifications-menu">
            <!-- Menu toggle button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-bell-o"></i>
              <span class="label label-warning">10</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 10 notifications</li>
              <li>
                <!-- Inner Menu: contains the notifications -->
                <ul class="menu">
                  <li><!-- start notification -->
                    <a href="#">
                      <i class="fa fa-users text-aqua"></i> 5 new members joined today
                    </a>
                  </li>
                  <!-- end notification -->
                </ul>
              </li>
              <li class="footer"><a href="#">View all</a></li>
            </ul>
          </li>
          <!-- Tasks Menu -->
          <li class="dropdown tasks-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <i class="fa fa-flag-o"></i>
              <span class="label label-danger">9</span>
            </a>
            <ul class="dropdown-menu">
              <li class="header">You have 9 tasks</li>
              <li>
                <!-- Inner menu: contains the tasks -->
                <ul class="menu">
                  <li><!-- Task item -->
                    <a href="#">
                      <!-- Task title and progress text -->
                      <h3>
                        Design some buttons
                        <small class="pull-right">20%</small>
                      </h3>
                      <!-- The progress bar -->
                      <div class="progress xs">
                        <!-- Change the css width attribute to simulate progress -->
                        <div class="progress-bar progress-bar-aqua" style="width: 20%" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100">
                          <span class="sr-only">20% Complete</span>
                        </div>
                      </div>
                    </a>
                  </li>
                  <!-- end task item -->
                </ul>
              </li>
              <li class="footer">
                <a href="#">View all tasks</a>
              </li>
            </ul>
          </li>
          <!-- User Account Menu -->
          <li class="dropdown user user-menu">
            <!-- Menu Toggle Button -->
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
              <!-- The user image in the navbar-->
              <img src="img/user2-160x160.jpg" class="user-image" alt="User Image">
              <!-- hidden-xs hides the username on small devices so only the image appears. -->
              <span class="hidden-xs">Alexander Pierce</span>
            </a>
            <ul class="dropdown-menu">
              <!-- The user image in the menu -->
              <li class="user-header">
                <img src="img/user2-160x160.jpg" class="img-circle" alt="User Image">

                <p>
                  Alexander Pierce - Web Developer
                  <small>Member since Nov. 2012</small>
                </p>
              </li>
              <!-- Menu Body -->
              
              <!-- Menu Footer-->
              <li class="user-footer">
                <div class="pull-left">
                  <a href="#" class="btn btn-default btn-flat">设置</a>
                </div>
                <div class="pull-right">
                  <a href="#" class="btn btn-default btn-flat">退出登录</a>
                </div>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </nav>
  </header>
  <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="img/user2-160x160.jpg" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
        </div>
      </div>

      <!-- search form (Optional) -->
      <form action="#" method="get" class="sidebar-form">
        <div class="input-group">
          <input type="text" name="q" class="form-control" placeholder="搜索...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i>
                </button>
              </span>
        </div>
      </form>
      <!-- /.search form -->

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu">
        <li class="header">管理栏目</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="treeview active">
          <a href="#"><i class="fa fa-users"></i> <span>人员管理</span>
            <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
          </a>
          <ul class="treeview-menu">
            <li><a href="javascript:showpage(this,'bUserServlet?op=searchAll&type=v_farmer')"><i class="fa fa-circle-o"></i> 种粮大户</a></li>
            <li><a href="javascript:showpage(this,'bUserServlet?op=searchAll&type=v_machiner')"><i class="fa fa-circle-o"></i> 农机手</a></li>
            <li><a href="javascript:showpage(this,'bMachineOwnerServlet?op=searchAll')"><i class="fa fa-circle-o"></i> 农机拥有者</a></li>
          </ul>
        </li>
        <li><a href="javascript:showpage(this,'bZoneServlet?op=searchAll')"><i class="fa fa-th"></i> <span>分区管理</span></a></li>
        <li><a href="javascript:showpage(this,'bFarmlandServlet?op=searchAll')"><i class="fa fa-th-large"></i> <span>农田管理</span></a></li>
        <li><a href="javascript:showpage(this,'bMachineServlet?op=searchAll')"><i class="fa fa-truck"></i> <span>农机管理</span></a></li>
        <li><a href="#"><i class="fa fa-tasks"></i> <span>任务管理</span>
          <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
        </a>
          <ul class="treeview-menu">
            <li><a href="javascript:showpage(this,'publishtask.jsp')"><i class="fa fa-circle-o"></i> 发布任务</a></li>
            <li><a href="javascript:showpage(this,'tasking.jsp')"><i class="fa fa-circle-o"></i> 正在进行</a></li>
            <li><a href="javascript:showpage(this,'tasked.jsp')"><i class="fa fa-circle-o"></i> 历史任务</a></li>
          </ul>
        </li>
         <li><a href="#"><i class="fa fa-user-md"></i> <span>咨询中心</span>
          <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
        </a>
          <ul class="treeview-menu">
            <li><a href="javascript:showpage(this,'consult.html')"><i class="fa fa-circle-o"></i> 待解决</a></li>
            <li><a href="javascript:showpage(this,'consulted.html')"><i class="fa fa-circle-o"></i> 已解决</a></li>
          </ul>
        </li>
        <li><a href="#"><i class="fa fa-book"></i> <span>文库中心</span></a></li>
       	<li><a href="#"><i class="fa fa-medkit"></i> <span>病虫防治</span>
          <span class="pull-right-container">
              <i class="fa fa-angle-left pull-right"></i>
            </span>
        </a>
          <ul class="treeview-menu">
            <li><a href="javascript:showpage(this,'publishtask.jsp')"><i class="fa fa-circle-o"></i> 病虫图鉴</a></li>
            <li><a href="javascript:showpage(this,'tasking.jsp')"><i class="fa fa-circle-o"></i> 自动识别</a></li>
            <li><a href="javascript:showpage(this,'tasked.jsp')"><i class="fa fa-circle-o"></i> 人工识别</a></li>
          </ul>
        </li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <!-- <section class="content-header">

    <!-- Main content -->
    <section class="content" >
        <!-- Your Page Content Here -->
        <iframe id="rightMain" src="calendar.html" scrolling="no" onload="changeFrameHeight()" frameborder="0" width="100%" height="500px" allowtransparency="true"></iframe>
		
    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <!-- <div class="pull-right hidden-xs">
      Anything you want
    </div> -->
    <strong>Copyright &copy; 2016 <a href="http://www.geowind.cn" style="color: green;" target="_Blank">geowind</a>.</strong> All rights reserved.
    <!-- Default to the left -->
  </footer>

</div>
<!-- ./wrapper -->


<!-- jQuery 2.2.3 -->
<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
<!-- Bootstrap 3.3.6 -->
<script src="js/bootstrap/bootstrap.min.js"></script>
<!-- AdminLTE App -->
<script src="js/dist/app.min.js"></script>
<script type="text/javascript">
	function showpage(obj,page){
	    $("#rightMain").attr("src",page);
	}
	
	function changeFrameHeight() {
		var mainheight = $("#rightMain").contents().find("body").height() + 30;
	    if(mainheight < 500){
	    	mainheight = 500;
		}
	    $("#rightMain").height(mainheight);
	} 

	window.setInterval("changeFrameHeight()", 200);
</script>
</body>
</html>
