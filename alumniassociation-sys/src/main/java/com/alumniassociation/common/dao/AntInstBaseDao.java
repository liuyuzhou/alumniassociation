package com.alumniassociation.common.dao;

import java.sql.Date;
import java.sql.Timestamp;

public interface AntInstBaseDao {

	String getSysStrTimestamp();
	
	String getSysStrDatestamp();
	
	String getSysStrDate();
	
	Date getSysDate();
	
	Timestamp getSysTimestamp();
	
	long getSequence(String name); 
	
}

