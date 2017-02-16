package com.scheduler.service;

import com.scheduler.domain.Booking;
import com.scheduler.domain.User;
import java.util.Set;
import java.util.TreeMap;

public interface BookingService {
    public void addBooking(Booking booking);
    public TreeMap<String, String> getAllowTimesPerDay(User spec);
}
