/**
 * Created by Jiang on 2016/10/19.
 * 农作物图层详情
 */
var countCrop;
var markerCrop = new Array();
function addCropLayer(){
	

    var json;
    
    var url = "../bFarmlandServlet?op=MapSearchAll";
	$.post(url,{},function getData(data){
		// alert(data);
		 if(data==0){
			 alert("查询出错");
		 }else{
			 json = JSON.parse(data);
			 countCrop = json.length;
			 for(var i=0;i<json.length;i++)
			 { 
				 var pointa = new BMap.Point(json[i].longitude,json[i].latitude);
				//农作物显示信息
					var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
					    '<img src="../'+json[i].picture+'" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
					    '地址：'+json[i].address+
					    '<br/>拥有者：'+json[i].user.realname+
					    '<br>联系电话：'+json[i].user.phone+
					    '<br/>面积：'+json[i].area+
					    '<br>pH:'+json[i].ph+
					    '<br>流转信息：'+json[i].transtion+
					    '<br>产量:'+json[i].production+
					    '<br>NPK:'+json[i].npk+
					    '</div>';
				 addMarker(pointa,i,content);
			 }
		 }
	});
	
	
	var myIcon = new BMap.Icon("img/logoMarker/crop.png", new BMap.Size(36,36),
	        {anchor:new BMap.Size(18,36),infoWindowAnchor: new BMap.Size(18, 0)} );
	function addMarker(point,i,content){
		
		//创建检索信息窗口对象
		var searchInfoWindow = null;
		searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
		title  : "农作物",      //标题
		width  : 290,             //宽度
		height : 200,              //高度
		panel  : "panel",         //检索结果面板
		enableAutoPan : true,     //自动平移
		searchTypes   :[
		BMAPLIB_TAB_SEARCH,   //周边检索
		BMAPLIB_TAB_TO_HERE,  //到这里去
		//BMAPLIB_TAB_FROM_HERE //从这里出发
		]
		});
		markerCrop[i] = new BMap.Marker(point,{icon:myIcon, enableDragging: false,
            raiseOnDrag: true}); //创建marker对象
		markerCrop[i].addEventListener("click", function(e){
		searchInfoWindow.open(markerCrop[i]);
		});
		map.addOverlay(markerCrop[i]); //在地图中添加marker
		
	}
	
    
}

/**
 * Created by Jiang on 2017/04/14
 * 隐藏农作物
 * @returns
 */
function hideCropLayer(){
	alert(countCrop);
	for(var k = 0;k<countCrop;k++){
		map.removeOverlay(markerCrop[k]);
	}
}




