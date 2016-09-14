package com.geowind.hunong.service.impl;

import com.geowind.hunong.dao.TaskDao;
import com.geowind.hunong.dao.impl.TaskDaoImpl;
import com.geowind.hunong.entity.Task;
import com.geowind.hunong.service.TaskService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Kui on 2016/7/21.
 */
public class TaskServiceImpl implements TaskService {

    private TaskDao mapDao;

    public TaskServiceImpl() {
        mapDao = new TaskDaoImpl();
    }

    @Override
    public List<Task> getTaskInfo(String mUname, int state) {
        List<Map<String, Object>> results = mapDao.selectTaskInfo(mUname, state);
        List<Task> list = new ArrayList<>();

        for(Map<String, Object> map : results) {
            Task task = new Task();
            task.setNo((int)map.get("tno"));
            task.setDate(map.get("tdate").toString());
            task.setFzno((int)map.get("fzno"));
            task.setState((int)map.get("state"));
            task.setFarea((double)map.get("farea"));
            task.setFaddr((String)map.get("faddr"));
            task.setLongitude((String)map.get("longitude"));
            task.setLatitude((String)map.get("latitude"));
            task.setWorkLoad((String)map.get("workload"));
            task.setType((String)map.get("ttype"));
            task.setCropType((String)map.get("croptype"));
            task.setMstyle((String)map.get("mstyle"));
            task.setFpic((String)map.get("fpic"));
            task.setNote((String)map.get("note"));
            task.setmUname((String)map.get("muname"));
            list.add(task);
        }
        return  list;
    }



}
