/**
 * Created by Administrator on 2016/10/5.
 */
// 百度地图API功能
var map = new BMap.Map("allmap");

map.enableScrollWheelZoom(true);  //开启鼠标滚轮缩放

var point = new BMap.Point(112.552478,26.923761);
map.centerAndZoom(point,14);

//定位跳转到当地地图
var geolocation = new BMap.Geolocation();
geolocation.getCurrentPosition(function(r){
    if(this.getStatus() == BMAP_STATUS_SUCCESS){
        var mk = new BMap.Marker(r.point);
        map.addOverlay(mk);
        map.panTo(r.point);

    }
    else {
        alert('failed'+this.getStatus());
    }
},{enableHighAccuracy: true})

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
map.addEventListener("click",showInfo);

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



