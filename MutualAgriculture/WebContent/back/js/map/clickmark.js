/**
 * Created by Administrator on 2016/10/5.
 */
// 百度地图API功能，初始化地图
var map = new BMap.Map("allmap");
map.enableScrollWheelZoom(true);  //开启鼠标滚轮缩放

var point = new BMap.Point(112.552478,26.923761);
map.centerAndZoom(point,12);

var geoc = new BMap.Geocoder();

//定位跳转到当地地图
var mark;//当前定位标注
var geolocation = new BMap.Geolocation();
geolocation.getCurrentPosition(function(r){
    if(this.getStatus() == BMAP_STATUS_SUCCESS){
        mark = new BMap.Marker(r.point);
        map.addOverlay(mark);
        map.panTo(r.point);

    }
    else {
        alert('failed'+this.getStatus());
    }
},{enableHighAccuracy: true})



//初始化地图组件
// 添加带有定位的导航控件
var navigationControl = new BMap.NavigationControl({
    // 靠左上角位置
    anchor: BMAP_ANCHOR_TOP_RIGHT,
    // LARGE类型
    type: BMAP_NAVIGATION_CONTROL_SMALL,
    // 启用显示定位
    enableGeolocation: true
});
map.addControl(navigationControl);
// 添加定位控件
var geolocationControl = new BMap.GeolocationControl();
geolocationControl.addEventListener("locationSuccess", function(e){
    // 定位成功事件
    var address = '';
    address += e.addressComponent.province;
    address += e.addressComponent.city;
    address += e.addressComponent.district;
    address += e.addressComponent.street;
    address += e.addressComponent.streetNumber;
    alert("当前定位地址为：" + address);
});
geolocationControl.addEventListener("locationError",function(e){
    // 定位失败事件
    alert(e.message);
});
map.addControl(geolocationControl);



/**
 * Created by Administrator on 2016/10/5.
 */
//var mapType1 = new BMap.MapTypeControl({mapTypes: [BMAP_NORMAL_MAP,BMAP_HYBRID_MAP]});
//var mapType2 = new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT});
map.addControl(new BMap.MapTypeControl({anchor: BMAP_ANCHOR_TOP_LEFT}));   //添加地图类型控件
var overView = new BMap.OverviewMapControl();
var overViewOpen = new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_BOTTOM_RIGHT});


/**
 * Created by Jiang on 2016/10/5.
 */
// 百度地图API功能
function G(id) {
    return document.getElementById(id);
}

var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
    {"input" : "suggestId"
        ,"location" : map
    });


ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
    var str = "";
    var _value = e.fromitem.value;
    var value = "";
    if (e.fromitem.index > -1) {
        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }
    str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

    value = "";
    if (e.toitem.index > -1) {
        _value = e.toitem.value;
        value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    }
    str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
    G("searchResultPanel").innerHTML = str;
});

var myValue;
ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
    var _value = e.item.value;
    myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
    G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

    setPlace();
});


function setPlace(){
    //map.clearOverlays();    //清除地图上所有覆盖物
    function myFun(){
        var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
        map.centerAndZoom(pp, 18);
        
        if(mark != undefined) {
        	map.removeOverlay(mark);
        }
        mark = new BMap.Marker(pp);
        map.addOverlay(mark);    //添加标注
    }
    var local = new BMap.LocalSearch(map, { //智能搜索
        onSearchComplete: myFun
    });
    local.search(myValue);
}



/**
 * Created by Jiang on 2016/10/19.
 * 农田信息标注显示
 */
    var json;
    var markera = new Array();
    //url:请求获得农田list数据的url地址
    /**
    * 向数据库发送请求数据
     * url : 表示请求的url地址
     * data:表示后台返回的农田List类型数据
    */
    var markers = new Array();
    /*$.post(url,{},function getData(data){
        json = eval("(" + data + ")");
        for(var i=0;i<json.length;i++)
        {
            //循环数据 json[i]//获取数据操作
            //获得经纬度生成piont点
             var point = new BMap.Point(json[i].longitude,json[i].latitude);
            //添加标注和标签显示农田信息
            markers[i] = addMarker(point,new BMap.Label(json[i].farmlandId +"号田:"+json[i].address,{offset:new BMap.Size(20,-10)}));
            //给农田标注添加点击点击事件，获取农田编号
            markers[i].addEventListener("click", function() {
        		var label = this.getLabel().getTitle();
        		alert(label);
        		var farmlandId = label.split("号田:")[0];
        		alert(farmlandId);
            });
        }
    });

	// 编写自定义函数,创建标注
	function addMarker(point,label){
		var marker = new BMap.Marker(point);
		map.addOverlay(marker);
		marker.setLabel(label);
		return marker;
	}*/
    
    
    
    $.post("../bFarmlandServlet?op=getFarmlands",{},function getData(data){
    	
    	for(var m in data){
    		addMark(data[m]);
    	}
    }, "json");
    
    
    var farmlandId;
    function addMark(data){
    	var me=this;     
    	var label;     
    	var point = new BMap.Point(data.longitude,data.latitude);    //建立测试point点
    	var marker = new BMap.Marker(point);
    	label = new BMap.Label(data.farmlandId, { offset: new BMap.Size(20, 0) }); //创建marker点的标记,这里注意下,因为百度地图可以对label样式做编辑,所以我这里吧重要的id放在了label(然后再隐藏)
    	//label.setStyle(  {   display:"none"         });//对label 样式隐藏   
    	marker.setLabel(label);  //把label设置到maker上  
    	//marker.setTitle(data.address); //这里设置maker的title (鼠标放到marker点上,会出现它的title,所以我这里把name,放到title里)   
    	me.map.addOverlay(marker);  //把maker点添加到 地图上   
    	data["chargerMarker"]=marker;   //这里很重要, 把maker对象放到缓存的data 里面  
    	marker.addEventListener("click", function (e) {      //这里添加maker的监听点击事件,触发自定义div("#info-panel)的展示 
			/*$("#info-panel").toggle(300);//div展开,关闭         
			me.tag=e.target.getLabel().content; //点击maker点后  获取label里面的内容        
			$("#id").html(e.target.getLabel().content); //这里就可以获取到marker的id        
			 */
    		farmlandId = e.target.getLabel().content;
    		
    		
    	});
    };
    
    
    function farmlandOK() {
    	if(farmlandId != undefined) {
    		$('#myModal').modal('hide');
    		$.post("../bFarmlandServlet?op=findFarmland",{farmlandId:farmlandId}, function(data) {
    			$("#zonename").val(data.zone.zonename);
    			$("#croptype").val(data.zone.type);
    			$("#fname").val(data.user.realname);
    			$("#fphone").val(data.user.phone);
    			$("#address").val(data.address);
    		},"json");
    		
    	} else {
    		alert("先选择农田...")
    	}
    }


	


