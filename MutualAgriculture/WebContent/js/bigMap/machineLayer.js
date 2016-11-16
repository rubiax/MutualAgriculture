/**
 * Created by Jiang on 2016/10/19.、
 * 农机图层
 */
function addMachineLayer(){
	
		map.clearOverlays();
	    
	    var json;
	    var marker = new Array();
	    var url = "http://localhost:8080/Demo/bMachineOwnerServlet?op=searchAll";
		$.post(url,{},function getData(data){
			 //alert(data);
			 json = JSON.parse(data);
			 
			 for(var i=0;i<json.length;i++)
			 { 	 
				
				 //循环数据 json[i]//获取数据操作 
				 //alert(json[i].langitude+" "+json[i].latitude);
				 addMarker(json[i].address,i);
			 }
		});
		
		
		function addMarker(address,i){
			// 创建地址解析器实例
			var myGeo = new BMap.Geocoder();
			// 将地址解析结果显示在地图上,并调整地图视野
			alert(address);
			myGeo.getPoint(address, function(point){
				if (point){		
					//农作物显示信息
					var content = '<div style="margin:0;line-height:20px;padding:2px;">';+
				 '<img src="weather/1.gif" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
					    '地址：'+json[i].address+'<br/>电话：'+json[i].area+'<br/>年龄：'+json[i].ph+
					  '</div>';
					var myIcon = new BMap.Icon("images/machine.png", new BMap.Size(25,24),
					        {anchor:new BMap.Size(15,24),infoWindowAnchor: new BMap.Size(15, 0)} );	
						//创建检索信息窗口对象
						var searchInfoWindow = null;
						searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
						title  : "农机",      //标题
						width  : 290,             //宽度
						height : 105,              //高度
						panel  : "panel",         //检索结果面板
						enableAutoPan : true,     //自动平移
						searchTypes   :[
						BMAPLIB_TAB_SEARCH,   //周边检索
						BMAPLIB_TAB_TO_HERE,  //到这里去
						//BMAPLIB_TAB_FROM_HERE //从这里出发
						]
						});
						marker= new BMap.Marker(point,{icon:myIcon, enableDragging: true,
				            raiseOnDrag: true}); //创建marker对象
						marker.addEventListener("mouseover", function(e){
						searchInfoWindow.open(marker);
						})
						map.addOverlay(marker); //在地图中添加marker
					}
				else{
					alert("您选择地址没有解析到结果!");
				}
			}, "湖南省");
		}
	}
		
		
//		//农作物显示信息
//		var content = '<div style="margin:0;line-height:20px;padding:2px;">' +
//		    '<img src="weather/1.gif" alt="" style="float:right;zoom:1;overflow:hidden;width:100px;height:100px;margin-left:3px;"/>' +
//		    '地址：农机位置<br/>电话：(010)59928888<br/>简介：衡阳市农机啦啦啦拉拉啦啦啦拉拉' +
//		  '</div>';
//		
//		var myIcon = new BMap.Icon("images/machine.png", new BMap.Size(25,24),
//		        {anchor:new BMap.Size(15,24),infoWindowAnchor: new BMap.Size(15, 0)} );
//		
//		function addMarker(point,i){
//			
//			//创建检索信息窗口对象
//			var searchInfoWindow = null;
//			searchInfoWindow = new BMapLib.SearchInfoWindow(map, content, {
//			title  : "农机",      //标题
//			width  : 290,             //宽度
//			height : 105,              //高度
//			panel  : "panel",         //检索结果面板
//			enableAutoPan : true,     //自动平移
//			searchTypes   :[
//			BMAPLIB_TAB_SEARCH,   //周边检索
//			BMAPLIB_TAB_TO_HERE,  //到这里去
//			//BMAPLIB_TAB_FROM_HERE //从这里出发
//			]
//			});
//			marker[i] = new BMap.Marker(point,{icon:myIcon, enableDragging: true,
//	            raiseOnDrag: true}); //创建marker对象
//			marker[i].addEventListener("mouseover", function(e){
//			searchInfoWindow.open(marker[i]);
//			})
//		
//			map.addOverlay(marker[i]); //在地图中添加marker
//			
//		}
		
