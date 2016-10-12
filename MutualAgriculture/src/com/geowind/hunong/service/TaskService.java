package com.geowind.hunong.service;

import com.geowind.hunong.entity.Task;

import java.util.List;

/**
 * Created by Kui on 2016/7/21.
 */
public interface TaskService {

    public List<Task> getTaskInfo(String mUname, int state);
}
