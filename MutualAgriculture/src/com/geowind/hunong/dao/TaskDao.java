package com.geowind.hunong.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Kui on 2016/7/21.
 */
public interface TaskDao {

    public List<Map<String, Object>> selectTaskInfo(String uname, int state);
}
