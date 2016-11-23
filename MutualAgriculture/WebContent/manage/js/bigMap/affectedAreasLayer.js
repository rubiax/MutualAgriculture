/**
 * Created by Jiang on 2016/10/19.
 * 农田受灾区
 */

function addAffectAreasLayer(){
    	
			map.clearOverlays();
    	
	        
	      //计算面积，参数为多边形
//	        function computeAreaByPolygon(){
	            var pts = [];
	            var pt1 = new BMap.Point(112.596809, 26.900671);
	            var pt2 = new BMap.Point(112.598013, 26.904054);
	            var pt3 = new BMap.Point(112.59672, 26.907099);
	            var pt4 = new BMap.Point(112.595606, 26.90879);
	            var pt5 = new BMap.Point(112.591815, 26.906712);    
	            var pt6 = new BMap.Point(112.590108, 26.903506);
	            var pt7 = new BMap.Point(112.594366, 26.902556);
	            pts.push(pt1);
	            pts.push(pt2);
	            pts.push(pt3);
	            pts.push(pt4);
	            pts.push(pt5);
	            pts.push(pt6);
	            pts.push(pt7);
	            	
	            var marker = new BMap.Marker(pt1);
	            var ply = new BMap.Polygon(pts);    
	            var area = BMapLib.GeoUtils.getPolygonArea(ply);
	            var mu = area.toFixed(2)*0.0015;
	            ply.addEventListener("click",function(){
	            	 var label = new BMap.Label("共" + area.toFixed(2) + "平方米, 共有 "+mu+"亩",{offset:new BMap.Size(20,-10)})	
	            	 map.addOverlay(marker);
	            	 marker.setLabel(label);
	            });
 
	            //演示：将面添加到地图上    
	            map.clearOverlays();
	            map.addOverlay(ply);  
	    
//			}
}