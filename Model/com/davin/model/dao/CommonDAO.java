package com.davin.model.dao;

import java.util.List;

import com.davin.model.entity.Departure;

public interface CommonDAO {

	List<Departure> listDeparture(String sidx, String sord, String search);

	List<Departure> listDepartureLimitOffset(int limit, int offset,
			String sidx, String sord, String search);

}
