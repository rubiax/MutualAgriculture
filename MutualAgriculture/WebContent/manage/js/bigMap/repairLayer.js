/**
 *  
 * 维修点层
 */
var markerRepair = new Array();
var countRepair;
function addRepairLayer(){
	
    var json;
   
    var url = "../serviceStaionServlet?op=MapSearchAll";
	$.post(url,{},function getData(data){
		
		if(data==0){
			alert("查询出错");
		}else{
			//alert(data);
			 json = JSON.parse(data);
			 countRepair = json.length;
			 for(var i=0;i<json.length;i++)
			 { 
				 //循环数据 json[i]//获取数据操作 
				 //alert(json[i].langitude+" "+json[i].latitude);
				 var pointa = new BMap.Point(json[i].longitude,json[i].latitude);
				 var label = new BMap.Label("维修点负责人:"+json[i].spname+" 电话:"+json[i].sptel,{offset:new BMap.Size(20,-10)});	
				 addMarker(pointa,i,label);
			 }
		}
		 
	});
	
	
	var myIcon = new BMap.Icon("img/logoMarker/repair.png", new BMap.Size(36,36),
	        {anchor:new BMap.Size(18,36),infoWindowAnchor: new BMap.Size(18, 0)} );
		
	function addMarker(point,i,label){
		
		markerRepair[i] = new BMap.Marker(point,{icon:myIcon, enableDragging: false,
            raiseOnDrag: true}); //创建marker对象
	
		map.addOverlay(markerRepair[i]); //在地图中添加marker
		markerRepair[i].setLabel(label);
	}
	

}

function hideRepairLayer(){
	for(var k =0;k<countRepair;k++){
		map.removeOverlay(markerRepair[k]);
	}
}