/**
 * Created by Jiang on 2016/10/19
 * 提问显示图层.
 */
function addQuestionLayer(){
   	map.clearOverlays();
    var json;
    var marker = new Array();
    var url = "http://localhost:8080/Demo/FarmerServlet";
	$.post(url,{},function getData(data){
		 alert(data);
		 json = JSON.parse(data);
		 for(var i=0;i<json.length;i++)
		 { 
			 //循环数据 json[i]//获取数据操作 
			 //alert(json[i].langitude+" "+json[i].latitude);
			 var pointa = new BMap.Point(json[i].longitude,json[i].latitude);
			 addMarker(pointa,i);
		 }
	});
	
	var myIcon = new BMap.Icon("images/question.png", new BMap.Size(25,24),
		        {anchor:new BMap.Size(15,24),infoWindowAnchor: new BMap.Size(15, 0)} );
	 // 编写自定义函数,创建标注
    function addMarker(point,i){
        marker[i] = new BMap.Marker(point,{icon:myIcon, enableDragging: true,
            raiseOnDrag: true});
        map.addOverlay(marker[i]);
        marker[i].setAnimation(BMAP_ANIMATION_BOUNCE); //跳动的动画

        marker[i].addEventListener("mouseover", function(){
    	var opts = {
            width : 350,     // 信息窗口宽度
            height: 150,     // 信息窗口高度
            title : "提问信息"  // 信息窗口标题
        }
        var infoWindow = new BMap.InfoWindow("名字："+json[i].name+"地址： "+
        		json[i].address+'<img src="weather/0.gif" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>', opts);  // 创建信息窗口对象
        marker[i].openInfoWindow(infoWindow, marker[i].getPosition());      // 打开信息窗口
    });
        marker[i].addEventListener("mouseout",function(){
        	marker[i].closeInfoWindow();
        });
    }
}