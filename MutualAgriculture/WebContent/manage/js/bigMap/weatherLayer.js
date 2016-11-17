/**
 * Created by Jiang on 2016/10/19.
 * 天气标注图层
 */
// var marker2 = new BMap.Marker();
var weatherDetail;
function addWeatherLayer(){
    //map.clearOverlays();
    var pt = new BMap.Point(112.594267, 26.903756);
    var myIcon = new BMap.Icon("weather/a_0.gif", new BMap.Size(70,65),
    {anchor:new BMap.Size(35,65),infoWindowAnchor: new BMap.Size(35, 0)} );
    var marker2 = new BMap.Marker(pt,{icon:myIcon, enableDragging: true,
    raiseOnDrag: true});  // 创建标注
    map.addOverlay(marker2);              // 将标注添加到地图中

    marker2.addEventListener("click", function(){
    	 
    	//alert("您点击了标注");
    
    	 var url="http://localhost:8080/Demo/WeatherServlet";
    	 //var weatherDetail;
    	 $.post(url,{},function getWeather(data){
    		 if(data!=null){
    			 //var k="dsdsds";
    			 weatherDetail = data;
    			 var infoWindow = new BMap.InfoWindow("weatherDeatil"+weatherDetail, opts);  // 创建信息窗口对象
    			 map.openInfoWindow(infoWindow, pt);      // 打开信息窗口
    			 alert(data);
    		 }else{
    			 weatherDetail = "get Weather Error";
    			 alert(data+"q");
    		 }
    	 });
        var opts = {
            width : 250,     // 信息窗口宽度
            height: 100,     // 信息窗口高度
            title : "天气预报"  // 信息窗口标题
        }
//        var infoWindow = new BMap.InfoWindow("weatherDeatil"+weatherDetail, opts);  // 创建信息窗口对象
//        map.openInfoWindow(infoWindow, pt);      // 打开信息窗口
    });
    
    

}


