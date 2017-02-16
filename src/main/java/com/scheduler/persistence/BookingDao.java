package com.scheduler.persistence;

import com.scheduler.domain.Booking;
import com.scheduler.domain.User;
import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

public interface BookingDao extends AbstractDao<Booking> {
	public List<Date> getReservedDates(User spec, Date startDate, Date endDate);
}
