/**
 * 加油点层
 */
var countFuel;
var markerFuel = new Array();
function addFuelLayer() {
	var json;

	var url = "../gasStationServlet?op=MapSearchAll";
	$.post(url, {},
			function getData(data) {
				if (data == 0) {
					alert("查询出错");
				} else {
					// alert(data);
					json = JSON.parse(data);
					countFuel = json.length;
					for (var i = 0; i < json.length; i++) {
						// 循环数据 json[i]//获取数据操作
						// alert(json[i].langitude+" "+json[i].latitude);
						var pointa = new BMap.Point(json[i].longitude,
								json[i].latitude);
						addMarker(pointa, i);
					}
				}

			});

	var myIcon = new BMap.Icon("img/logoMarker/fuel.png",
			new BMap.Size(36, 36), {
				anchor : new BMap.Size(18, 36),
				infoWindowAnchor : new BMap.Size(18, 0)
			});

	function addMarker(point, i) {
		markerFuel[i] = new BMap.Marker(point, {
			icon : myIcon,
			enableDragging : false,
			raiseOnDrag : true
		}); // 创建marker对象
		// marker[i].addEventListener("mouseover", function(e){
		// searchInfoWindow.open(marker[i]);
		// })

		map.addOverlay(markerFuel[i]); // 在地图中添加marker
		markerFuel[i].setLabel(new BMap.Label("加油点", {
			offset : new BMap.Size(20, -10)
		}));
	}

}

/**
 * Created by Jiang on 2017/04/14 隐藏加油点
 * 
 * @returns
 */
function hideFuelLayer() {
	for (var k = 0; k < countFuel; k++) {
		map.removeOverlay(markerFuel[k]);
	}
}