/**
 * Created by Jiang on 2016/10/19.
 */
function addZoneLayer(){
    map.clearOverlays();
    var marker = new Array();
    112.5931,26.9034
    
   
   
   
    
   
    
    
  
    var polygon = new BMap.Polygon([
    	new BMap.Point(112.5931,26.9034),
        new BMap.Point(112.59578,26.903118),
        new BMap.Point( 112.59685,26.90348),
        new BMap.Point( 112.5974,26.904215),
        new BMap.Point( 112.59739,26.905155),
        new BMap.Point(112.5958,26.905737),
        new BMap.Point( 112.594185,26.906267),
        new BMap.Point(112.59306,26.905155),
        new BMap.Point(112.59276,26.90473),
        new BMap.Point(  112.59281,26.904245)
    ], {strokeColor:"red", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
    // polygon.enableEditing();
    map.addOverlay(polygon);   //增加多边形
    var data = [{"x":112.594188, "y":26.906267},
    {"x":112.595805, "y":26.905736},
    {"x":112.597386, "y":26.905156},
    {"x":112.597395, "y":26.904214},
    {"x":112.596029, "y":26.904858},
    {"x":112.595778, "y":26.903118},
    {"x":112.593065, "y":26.905156},
    {"x":112.593101, "y":26.9034},
    {"x":112.592813, "y":26.904246},
    {"x": 112.59276,  "y":26.904729},
    {"x":112.595392,"y": 26.904681},
    {"x": 112.595077, "y":26.904197},
    {"x": 112.596281,"y": 26.904278},
    {"x": 112.594206, "y":26.904785},
    {"x":  112.593469,"y": 26.904673},
    {"x":  112.596847, "y":26.903481},
    {"x": 112.595275, "y":26.905389},
    {"x": 112.594305,"y": 26.904375}];
   
    for(var i =0;i<data.length;i++){    	
    	var point = new BMap.Point(data[i].x, data[i].y);
    	marker[i] = new BMap.Marker(point); //创建marker对象
    	map.addOverlay(marker[i]); //在地图中添加marker
    }
    
    
}