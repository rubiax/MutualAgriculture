
package com.geowind.hunong.dao.impl;

import com.geowind.hunong.dao.LibraryDao;
import com.geowind.hunong.util.DBHelper;

import java.util.List;
import java.util.Map;


/**
 * Created by Kui on 2016/7/23.
 */
public class LibraryDaoImpl implements LibraryDao {

    @Override
    public List<Map<String, Object>> selectTitle(int category) {
        String sql = "select * from article where category=?";
        return DBHelper.doQuery(sql, category);
    }
}