/**
 * Created by Jiang on 2016/10/19.
 */
function addZoneLayer(){
    map.clearOverlays();
    var polygon = new BMap.Polygon([
        new BMap.Point(112.593549, 26.908266),
        new BMap.Point(112.597034, 26.906108),
        new BMap.Point(112.59786, 26.900921),
        new BMap.Point(112.592075, 26.903176),
        new BMap.Point(112.592722, 26.907622)
    ], {strokeColor:"green", strokeWeight:2, strokeOpacity:0.5});  //创建多边形

    // polygon.enableEditing();
    map.addOverlay(polygon);   //增加多边形
}