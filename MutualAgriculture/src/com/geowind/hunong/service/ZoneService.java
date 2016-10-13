package com.geowind.hunong.service;

import java.util.List;

import com.geowind.hunong.entities.Zone;

public interface ZoneService {

	public List<Zone> search(int centerId);
}
