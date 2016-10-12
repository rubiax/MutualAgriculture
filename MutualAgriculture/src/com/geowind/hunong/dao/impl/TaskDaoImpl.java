package com.geowind.hunong.dao.impl;

import com.geowind.hunong.dao.TaskDao;
import com.geowind.hunong.util.DBHelper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Kui on 2016/7/21.
 */
public class TaskDaoImpl implements TaskDao {
    @Override
    public List<Map<String, Object>> selectTaskInfo(String uname, int state) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String date = sdf.format(new Date());
        String sql = "";
        String tempSql = "SELECT Utype FROM user WHERE Uname = ?";
        Map<String, Object> typeMap = DBHelper.doQueryOne(tempSql, uname);
        switch ((int)typeMap.get("utype")){
            case 0:  //种粮大户
                sql = "select Tno, Tdate, State, MUname, farmland.FZno, Farea, Faddr, longitude, latitude, WorkLoad, Ttype, CropType, Mstyle, Fpic, note " +
                        "from farmland join farmlandzone on farmland.FZno = farmlandzone.FZno " +
                        "join task on farmland.Fno = task.Fno " +
                        "join machine on task.Mno = machine.Mno " +
                        "where farmland.Fno in (select Fno from task where FUname=? and State=?) ";
                break;
            case 1:  //农机主
                sql = "select Tno, Tdate, State, farmland.FZno, Farea, Faddr, longitude, latitude, WorkLoad, Ttype, CropType, Mstyle, Fpic, note " +
                        "from farmland join farmlandzone on farmland.FZno = farmlandzone.FZno " +
                        "join task on farmland.Fno = task.Fno " +
                        "join machine on task.Mno = machine.Mno " +
                        "where farmland.Fno in (select Fno from task where MUname=? and State=?) ";
                break;
        }


        return DBHelper.doQuery(sql, uname, state);

    }

}
