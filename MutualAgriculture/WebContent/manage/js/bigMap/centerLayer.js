/**
 * 服务中心点层
 */

function addCenterLayer(){
map.clearOverlays();
    
    var json;
    var marker = new Array();
    var url = "http://localhost:8080/Demo/FarmerServlet";
	$.post(url,{},function getData(data){
		 alert(data);
		 json = JSON.parse(data);
		 for(var i=0;i<json.length;i++)
		 { 
			 //循环数据 json[i]//获取数据操作 
			 //alert(json[i].langitude+" "+json[i].latitude);
			 var pointa = new BMap.Point(json[i].longitude,json[i].latitude);
			 addMarker(pointa,i);
		 }
	});
	
	//农作物显示信息
	var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
	    '<img src="weather/0.gif" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
	    '地址：南华大学<br/>电话：(010)59928888<br/>简介：衡阳市南华大学啦啦啦拉拉啦啦啦拉拉' +
	  '</div>';
	var myIcon = new BMap.Icon("images/center.png", new BMap.Size(25,24),
	        {anchor:new BMap.Size(15,24),infoWindowAnchor: new BMap.Size(15, 0)} );
	function addMarker(point,i){
		
		//创建检索信息窗口对象
		var searchInfoWindow = null;
		searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
		title  : "服务中心",      //标题
		width  : 290,             //宽度
		height : 105,              //高度
		panel  : "panel",         //检索结果面板
		enableAutoPan : true,     //自动平移
		searchTypes   :[
		BMAPLIB_TAB_SEARCH,   //周边检索
		BMAPLIB_TAB_TO_HERE,  //到这里去
		//BMAPLIB_TAB_FROM_HERE //从这里出发
		]
		});
		marker[i] = new BMap.Marker(point,{icon:myIcon, enableDragging: true,
            raiseOnDrag: true}); //创建marker对象
		marker[i].addEventListener("mouseover", function(e){
			searchInfoWindow.open(marker[i]);
		});
		
		//鼠标移开关闭信息窗
//		marker[i].addEventListener("mouseout",function(e){
//			searchInfoWindow.close();
//		});
		
		map.addOverlay(marker[i]); //在地图中添加marker
		
	}

}