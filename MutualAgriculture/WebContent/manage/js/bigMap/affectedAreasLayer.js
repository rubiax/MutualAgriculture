/**
 * Created by Jiang on 2016/10/19.
 * 农田受灾区
 */
var countAffected;
var plyaffected=[];
function addAffectAreasLayer(){
    	
	//map.clearOverlays();
	var url = "../pestZoneServlet?op=MapSearchAffectedArea";
	var json;
	
	var myIcon = new BMap.Icon("img/logoMarker/location.png", new BMap.Size(36,36),
	        {anchor:new BMap.Size(18,36),infoWindowAnchor: new BMap.Size(18, 0)} );
	$.post(url,{},function getData(data){
		//alert(data);
		json = JSON.parse(data);
		countAffected = json.length;
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
			plyaffected[j] = new BMap.Polygon(pts);    
	            var area = BMapLib.GeoUtils.getPolygonArea(plyaffected[j]);
	            var mu = area.toFixed(2)*0.0015;

	            	 var label = new BMap.Label("共" + area.toFixed(2) + "平方米, 共有 "+mu+"亩",{offset:new BMap.Size(20,-10)})	
	            	 map.addOverlay(marker);
	            	 marker.setLabel(label);

	            //演示：将面添加到地图上    
	            //map.clearOverlays();
	            map.addOverlay(plyaffected[j]); 
		}
	});
	
	
	
}

/**
 * Created by Jiang on 2017/04/14
 *  隐藏受灾区
 * @returns
 */
function hideAffectedAreasLayer(){
	for(var k=0;k<countAffected;k++){
		map.removeOverlay(plyaffected[k]);
	}
}