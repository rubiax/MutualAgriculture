/**
 * 
 */

//计算面积
var pointArray = new Array();
var pts = [];
var i=0;
function showInfo(e){
        // alert("经纬度为："+e.point.lng+", "+e.point.lat);
        	//document.getElementById("show").setAttribute("value",e.point.lng+", "+e.point.lat);
        //计算面积，参数为多边形
//        function computeAreaByPolygon(){
           
            pointArray[i] = new BMap.Point(e.point.lng, e.point.lat);
            
            pts.push(pointArray[i]);
            
            var marker = new BMap.Marker(pointArray[i]);
            map.addOverlay(marker);
            i++;          
           
}
map.addEventListener("rightclick",showInfo);

function addComputerAreaResult(){
	 var ply = new BMap.Polygon(pts);    
     var area = BMapLib.GeoUtils.getPolygonArea(ply);
     var mu = area.toFixed(2)*0.0015;
//     ply.addEventListener("click",function(){
//     	 var label = new BMap.Label("共" + area.toFixed(2) + "平方米, 共有 "+mu+"亩",{offset:new BMap.Size(20,-10)})	
//     	
//     	 marker.setLabel(label);
//     });
     alert("共" + area.toFixed(2) + "平方米, 共有 "+mu+"亩");

     //演示：将面添加到地图上    
    // map.clearOverlays();
     map.addOverlay(ply);  
}
