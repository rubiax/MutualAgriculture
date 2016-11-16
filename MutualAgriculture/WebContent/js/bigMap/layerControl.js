/**
 * Created by J on 2016/10/19.
 * 图层选择下拉列表功能实现
 */
// 定义一个控件类,即function
function LayerControl(){
    // 默认停靠位置和偏移量
    this.defaultAnchor = BMAP_ANCHOR_TOP_RIGHT;
    this.defaultOffset = new BMap.Size(50, 50);
}

// 通过JavaScript的prototype属性继承于BMap.Control
LayerControl.prototype = new BMap.Control();

// 自定义控件必须实现自己的initialize方法,并且将控件的DOM元素返回
// 在本方法中创建个select元素作为控件的容器,并将其添加到地图容器中
LayerControl.prototype.initialize = function(map){
    // 创建DOM元素
    var select = document.createElement("select");

    select.setAttribute("onchange","changeLayer()");
    select.setAttribute("id","sid");

    var blank=document.createElement('option');
    blank.text = "重置";

    var weather=document.createElement('option');
    weather.text = "天气";
    //weather.setAttribute("value","weather");

    var crop=document.createElement('option');
    crop.text = "农作物";
    //crop.setAttribute("value","crop");

    var zone=document.createElement('option');
    zone.text = "分区";

    var pest=document.createElement('option');
    pest.text = "虫害";

    var disease=document.createElement('option');
    disease.text = "病害";

    var machine=document.createElement('option');
    machine.text = "农机";

    var question=document.createElement('option');
    question.text = "提问";

    var task=document.createElement('option');
    task.text = "任务";

    var farmer=document.createElement('option');
    farmer.text = "农民";

    var areas=document.createElement('option');
    areas.text = "受灾区";
    
    var fuel=document.createElement('option');
    fuel.text = "加油点";
    
    var repair=document.createElement('option');
    repair.text = "维修点";
    
    var serviceCenter=document.createElement('option');
    serviceCenter.text = "服务中心点";

    select.add(blank,null);
    select.add(weather,null);
    select.add(crop,null);
    select.add(zone,null);
    select.add(pest,null);
    select.add(disease,null);
    select.add(machine,null);
    select.add(question,null);
    select.add(task,null);
    select.add(farmer,null);
    select.add(areas,null);
    select.add(fuel,null);
    select.add(repair,null);
    select.add(serviceCenter,null);


    // // 添加文字说明
    // div.appendChild(document.createTextNode("放大2级"));
    // // 设置样式
    // div.style.cursor = "pointer";
    // div.style.border = "1px solid gray";
    // div.style.backgroundColor = "white";
    // 绑定事件,点击一次放大两级


    // 添加DOM元素到地图中
    map.getContainer().appendChild(select);
    // 将DOM元素返回
    return select;
}
// 创建控件
var LayerControl = new LayerControl();
// 添加到地图当中
map.addControl(LayerControl);

/*
    改变图层显示状态
 */
function changeLayer(){
    var obj = document.getElementById("sid");
    var grade = obj.options[obj.selectedIndex].value;
    alert(grade + "A"+obj.selectedIndex );
    var n = obj.selectedIndex;
    switch (n)
    {
        case 0:
            map.clearOverlays();
            break;
        case 1:
            addWeatherLayer();
            break;
        case 2:
            addCropLayer();
            break;
        case 3:
            addZoneLayer();
            break;
        case 4:
            addPestLayer();
            break;
        case 5:
            addDiseaseLayer();
            break;
        case 6:
            addMachineLayer();
            break;
        case 7:
            addQuestionLayer();
            break;
        case 8:
            addTaskLayer();
            break;
        case 9:
            addFarmerLayer();
            break;
        case 10:
            addAffectAreasLayer();
            break;
        case 11:
        	addFuelLayer();
        	break;
        case 12:
        	addRepairLayer();
        	break;
        case 13:
        	addCenterLayer();
        	break;
        default:
        	break;
    }
}
