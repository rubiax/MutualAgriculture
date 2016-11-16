<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <!--<meta name="viewport" content="initial-scale=1.0,use-scalable=no">-->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">

    <style type="text/css">
        body,html,#allmap{width: 100%;height: 95%;overflow: hidden;margin: 0;font-family: "微软雅黑";}
    </style>
        <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=mcc568Fn4O4pF5ldXtFOs8ILbQGPG1jl"></script>
         <!--<script src="http://api.map.baidu.com/components?ak=mcc568Fn4O4pF5ldXtFOs8ILbQGPG1jl&v=1.0"></script>-->
        <script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>        
        <script type="text/javascript" src="http://api.map.baidu.com/library/GeoUtils/1.2/src/GeoUtils_min.js">   </script>
        <link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />
    <title>地图使用测试</title>
</head>
<body>

    <div>
        经纬度：<input type="text" name="showGeography" id="show" value="">
        搜索:<input type="text" id="suggestId" size="20" value="百度" style="width:150px;">
        <div id="searchResultPanel" style="border:1px solid #C0C0C0;width:150px;height:auto; display:none;"></div>

        <input type="button" onclick="add_control()" name="add" value="add">
        <input type="button" onclick="delete_control()" name="delete" value="delete">
    </div>

     <div id="allmap"></div>
	
	<!-- 初始化地图界面的四个js 文件 -->
    <script type="text/javascript" src="js/bigMap/mapInit.js"></script>
    <script type="text/javascript" src="js/bigMap/search.js"></script>
    <script type="text/javascript" src="js/bigMap/location.js"></script>
    <script type="text/javascript" src="js/bigMap/mapType.js"></script>
	
	<!-- 操作显示各个功能层的js 文件 -->
    <script type="text/javascript" src="js/bigMap/weatherLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/pestLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/cropLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/diseaseLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/farmerLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/machineLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/questionLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/taskLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/zoneLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/affectedAreasLayer.js"></script>
    <script type="text/javascript" src="js/bigMap/zoneLayer.js" ></script>
    <script type="text/javascript" src="js/bigMap/fuelLayer.js" ></script>
	<script type="text/javascript" src="js/bigMap/repairLayer.js" ></script>
	<script type="text/javascript" src="js/bigMap/centerLayer.js" ></script>
	
	<!-- 统筹控制地图各个功能层块的调度显示js文件 -->
    <script type="text/javascript" src="js/bigMap/layerControl.js"></script>

	<script src="js/plugins/jQuery/jquery-2.2.3.min.js"></script>
	<!-- date-range-picker -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.11.2/moment.min.js"></script>
	<!-- bootstrap datepicker -->
	<script src="js/plugins/datepicker/bootstrap-datepicker.js"></script>
	<!-- InputMask -->
	<script src="js/plugins/input-mask/jquery.inputmask.js"></script>
	<script src="js/plugins/input-mask/jquery.inputmask.date.extensions.js"></script>
	<script src="js/plugins/input-mask/jquery.inputmask.extensions.js"></script>

</body>
</html>


