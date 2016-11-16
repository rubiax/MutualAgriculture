/**
 * Created by Jiang on 2016/10/19.
 * 农田受灾区
 */

function addAffectAreasLayer(){
    	map.clearOverlays();
    	function computeAreaByPolygon(){   	
    	var polygon = new BMap.Polygon([
    	        new BMap.Point(112.593549, 26.908266),
    	        new BMap.Point(112.597034, 26.906108),
    	        new BMap.Point(112.59786, 26.900921),
    	        new BMap.Point(112.592075, 26.903176),
    	        new BMap.Point(112.592722, 26.907622)
    	    ], {strokeColor:"red", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
    	    polygon.enableEditing();
    	    //map.addOverlay(polygon);   //增加多边形
        var ply = new BMap.Polygon(polygon);    
        var area = BMapLib.GeoUtils.getPolygonArea(ply);
        alert("共" + area.toFixed(2) + "平方米"); 
        
        //演示：将面添加到地图上    
        map.addOverlay(ply);  
    }
    var pt = new BMap.Point(112.594267, 26.903756);
    var myIcon = new BMap.Icon("images/danger.png", new BMap.Size(26,26),
        {anchor:new BMap.Size(13,26),infoWindowAnchor: new BMap.Size(13, 0)} );
    var marker = new BMap.Marker(pt,{icon:myIcon, enableDragging: true,
        raiseOnDrag: true});  // 创建标注
    map.addOverlay(marker);              // 将标注添加到地图中
    marker.setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画
    marker.addEventListener("click",function(){
    	alert(polygon);
    	computeAreaByPolygon();
    });

}