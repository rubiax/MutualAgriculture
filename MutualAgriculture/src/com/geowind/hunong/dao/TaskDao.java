package com.geowind.hunong.dao;

import java.util.List;
import java.util.Map;

import com.geowind.hunong.entities.Task;

/**
 * Created by Kui on 2016/7/21.
 */
public interface TaskDao {

	public List<Map<String, Object>> getTaskInfo(int centerId, int isFinished);
}
