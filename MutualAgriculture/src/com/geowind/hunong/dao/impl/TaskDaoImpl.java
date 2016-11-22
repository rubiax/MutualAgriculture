package com.geowind.hunong.dao.impl;

import com.geowind.hunong.dao.TaskDao;
import com.geowind.hunong.jpa.Task;
import com.geowind.hunong.util.DBHelper;

import java.util.List;
import java.util.Map;

/**
 * Created by Kui on 2016/7/21.
 */
public class TaskDaoImpl implements TaskDao {

	@Override
	public List<Map<String, Object>> getTaskInfo(int centerId, int isFinished) {
		String sql = "select * from task where centerId = ? and finished=?";
		return DBHelper.doQuery(sql, centerId, isFinished);
	}

}
