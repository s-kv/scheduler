package com.scheduler.service;

import com.scheduler.domain.Booking;
import com.scheduler.domain.User;
import com.scheduler.persistence.BookingDao;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javafx.collections.transformation.SortedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class BookingServiceImpl implements BookingService{
        @Autowired
        private BookingDao bookingDao;

        @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
        @Override
	public void addBooking(Booking booking) {
		bookingDao.create(booking);
	}    
        
        @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
        @Override
        public TreeMap<String, String> getAllowTimesPerDay(User spec)
        {
            TreeMap<String, String> dateMap = new TreeMap();
            
            Calendar c = new GregorianCalendar();
            c.set(Calendar.HOUR_OF_DAY, 0);
            c.set(Calendar.MINUTE, 0);
            c.set(Calendar.SECOND, 0);
            c.set(Calendar.MILLISECOND, 0);

            Date startDate = c.getTime();
            
            Calendar cEnd = new GregorianCalendar();
            cEnd.set(Calendar.HOUR_OF_DAY, 0);
            cEnd.set(Calendar.MINUTE, 0);
            cEnd.set(Calendar.SECOND, 0);
            cEnd.set(Calendar.MILLISECOND, 0);
            cEnd.add(Calendar.DAY_OF_YEAR, 14);
            
            Date endDate = cEnd.getTime();
            
            List<Date> allowDates = new ArrayList();
            List<Date> reservedDates = bookingDao.getReservedDates(spec, startDate, endDate);
            
            Calendar duration = new GregorianCalendar();
            duration.setTime(spec.getDuration());
            
            Calendar begTime = new GregorianCalendar();
            begTime.setTime(spec.getBegTime());

            Calendar endTime = new GregorianCalendar();
            endTime.setTime(spec.getEndTime());
            
            for(Calendar curTime = new GregorianCalendar(); c.before(cEnd); c.add(Calendar.HOUR_OF_DAY, duration.get(Calendar.HOUR_OF_DAY)), c.add(Calendar.MINUTE, duration.get(Calendar.MINUTE)))
            {
                if((c.get(Calendar.HOUR_OF_DAY) > begTime.get(Calendar.HOUR_OF_DAY) 
                        || c.get(Calendar.HOUR_OF_DAY) == begTime.get(Calendar.HOUR_OF_DAY) 
                            && c.get(Calendar.MINUTE) >= begTime.get(Calendar.MINUTE))
                    &&(c.get(Calendar.HOUR_OF_DAY) < endTime.get(Calendar.HOUR_OF_DAY)
                        || c.get(Calendar.HOUR_OF_DAY) == endTime.get(Calendar.HOUR_OF_DAY) 
                            && c.get(Calendar.MINUTE) < endTime.get(Calendar.MINUTE))
                    && c.after(curTime)
                    && !reservedDates.contains(c.getTime())
                    && (!spec.getMon() || c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY)
                    && (!spec.getTue() || c.get(Calendar.DAY_OF_WEEK) != Calendar.TUESDAY)
                    && (!spec.getWed() || c.get(Calendar.DAY_OF_WEEK) != Calendar.WEDNESDAY)
                    && (!spec.getThu() || c.get(Calendar.DAY_OF_WEEK) != Calendar.THURSDAY)    
                    && (!spec.getFri() || c.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY)
                    && (!spec.getSat() || c.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY)
                    && (!spec.getSun() || c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY)
                )
                    allowDates.add(c.getTime());
            }
            
            SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            for(Date date : allowDates)
            {
                c.setTime(date);
                c.set(Calendar.HOUR_OF_DAY, 0);
                c.set(Calendar.MINUTE, 0);
                c.set(Calendar.SECOND, 0);
                
                String allowTimes = dateMap.get(dateFormat.format(c.getTime()));
                
                if (allowTimes == null){
                    dateMap.put(dateFormat.format(c.getTime()), "'" + timeFormat.format(date) + "'");
                } else {
                    dateMap.put(dateFormat.format(c.getTime()), allowTimes + ", '" + timeFormat.format(date) + "'");
                }
            }

            return dateMap;
        }
}
