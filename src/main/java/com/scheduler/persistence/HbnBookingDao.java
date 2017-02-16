package com.scheduler.persistence;

import com.scheduler.domain.Booking;
import com.scheduler.domain.User;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class HbnBookingDao extends AbstractHbnDao<Booking> implements BookingDao {
        @Override
	public List<Date> getReservedDates(User spec, Date startDate, Date endDate) {
            Criteria criteria = getSession().createCriteria(Booking.class);
            
            criteria.add(Restrictions.eq("spec", spec)).
                    add(Restrictions.gt("startDate", startDate)).
                    add(Restrictions.or(
                            Restrictions.lt("endDate", endDate),
                            Restrictions.isNull("endDate")
                        )
                    );
            
            criteria.setProjection(Projections.property("startDate"));
            
            @SuppressWarnings("unchecked")
            List<Timestamp> timestamps = criteria.list();
            List<Date> dates = new ArrayList<>();
            
            for(Timestamp elem : timestamps)
            {
                dates.add(elem);
            }
            
            return dates;
                    
            /*Query query = getSession().createQuery("select startDate "
                    + "                             from booking "
                    + "                             where spec = :spec "
                    + "                             and startDate > :startDate"
                    + "                             and endDate < :endDate");

            query.setLong("spec", specId);
            query.setDate("startDate", startDate);
            query.setDate("endDate", endDate);
            @SuppressWarnings("unchecked")
            List<Date> dates = query.list();
            return dates;*/
	}    
}
