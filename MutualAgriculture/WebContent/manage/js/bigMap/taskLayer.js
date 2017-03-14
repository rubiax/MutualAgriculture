/**
 * Created by Jiang on 2016/10/19.
 */
function addTaskLayer(){
	map.clearOverlays();
    
    var json;
    var marker = new Array();
    var url = "../taskServlet?op=MapSearchAll";
	$.post(url,{},function getData(data){
		 alert(data);
		 json = JSON.parse(data);
		 for(var i=0;i<json.length;i++)
		 { 
			 //循环数据 json[i]//获取数据操作 
			 //alert(json[i].langitude+" "+json[i].latitude);
			 var pointa = new BMap.Point(json[i].farmland.longitude,json[i].farmland.latitude);
			//农作物显示信息
				var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
							    '<img src="../'+json[i].uploadPic+'" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
							    '农田地址：'+json[i].farmland.address+
							    '<br/>农民姓名：'+json[i].farmland.user.realname+
							    '<br/>联系电话：'+json[i].farmland.user.phone+
							    '<br/>任务类型:'+json[i].type+
							    '<br/>农机手姓名:'+json[i].machine.machineowner.name+
							    '<br/>农机手电话：'+json[i].machine.machineowner.phone+
							    '</div>';
			 
			 addMarker(pointa,i,content);
		 }
	});
	
	
	
	var myIcon = new BMap.Icon("img/logoMarker/task.png", new BMap.Size(30,30),
	        {anchor:new BMap.Size(15,30),infoWindowAnchor: new BMap.Size(15, 0)} );
	
	function addMarker(point,i,content){		
		//创建检索信息窗口对象
		var searchInfoWindow = null;
		searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
		title  : "任务",      //标题
		width  : 290,             //宽度
		height : 150,              //高度
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
		marker[i].addEventListener("click", function(e){
		searchInfoWindow.open(marker[i]);
		})
	
		map.addOverlay(marker[i]); //在地图中添加marker
		
	}
	

}