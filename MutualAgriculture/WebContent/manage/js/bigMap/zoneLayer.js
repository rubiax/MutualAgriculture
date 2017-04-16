/**
 * Created by Jiang on 2016/10/19.
 */
var areaNum=[];
var areaMu=[];
var plyzone=[];
var countZone;
function addZoneLayer(zone){
    
    var url = "../bZoneServlet?op=MapSearchZonePoint";
	var json;
	
	var myIcon = new BMap.Icon("img/logoMarker/location2.png", new BMap.Size(30,30),
	        {anchor:new BMap.Size(15,30),infoWindowAnchor: new BMap.Size(15, 0)} );
	$.post(url,{},function getData(data){
		json = JSON.parse(data);
		countZone = json.length;

		for(var j=0;j<json.length;j++){
		
			var pts =[];
			//绘出边界点
			for(var i=0;i<json[j].pointList.length-1;i++){
				
				var pt = new BMap.Point(json[j].pointList[i].x,json[j].pointList[i].y);
				pts.push(pt);
			}
			var pt1 = new BMap.Point(json[j].pointList[0].x,json[j].pointList[0].y)
			var marker = new BMap.Marker(pt1,{icon:myIcon, enableDragging: false,
	            raiseOnDrag: true});
			   plyzone[j] = new BMap.Polygon(pts);    
	            var area = BMapLib.GeoUtils.getPolygonArea(plyzone[j]);
	            var mu = area.toFixed(2)*0.0015;

	            	 //var label = new BMap.Label("",{offset:new BMap.Size(20,-10)});	
	            	 areaNum[j]=area.toFixed(2);
	            	 areaMu[j]=mu;
	            	 //map.addOverlay(marker);
	            	 //marker.setLabel(label);

	            //演示：将面添加到地图上    
	            //map.clearOverlays();
	            map.addOverlay(plyzone[j]); 
		}
		
		if(zone==0){
			$("#area").text("总面积： "+"共" + areaNum[0] + "平方米, 共有 "+areaMu[0]+"亩");
			var point = new BMap.Point(json[0].pointList[0].x,json[0].pointList[0].y);
			map.centerAndZoom(point,16);
		}else if(zone==1){
			$("#area").text("总面积： "+"共" + areaNum[1] + "平方米, 共有 "+areaMu[1]+"亩");
			var point = new BMap.Point(json[1].pointList[0].x,json[1].pointList[0].y);
			map.centerAndZoom(point,16);
		}
	});
	
    getTaskNum(zone);
    
}


/**
 * 获得分区任务数量
 * @returns
 */
function getTaskNum(zone){
	
	var url = "../taskServlet?op=getTaskNum";
	var json;
	
	var myIcon = new BMap.Icon("img/logoMarker/location2.png", new BMap.Size(30,30),
	        {anchor:new BMap.Size(15,30),infoWindowAnchor: new BMap.Size(15, 0)} );
	$.post(url,{zone:zone},function getData(data){
		$("#taskNum").text("任务数量："+ data);
	});
	
}




/**
 * Created by Jiang on 2017/04/14
 * 隐藏分区
 * @returns
 */
function hideZoneLayer(){
	for(var k =0;k<countZone;k++){
		map.removeOverlay(plyzone[k]);
	}
}

