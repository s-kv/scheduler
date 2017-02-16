package com.scheduler.domain;

import com.scheduler.controller.BookingController;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"spec_id", "startDate"})})
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
        private Date startDate;
        private Date endDate;
        @Size(max = 140)
        private String note;
        @ManyToOne(fetch = FetchType.EAGER)
	private User client;
        @ManyToOne(fetch = FetchType.EAGER)
	private User spec;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public Date getStartDate() {
            return startDate;
        }

        public String formatStartDate(String format) {
            if (format.isEmpty())
                format = BookingController.DATE_FORMAT;
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            return dateFormat.format(startDate);
        }
        
        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public User getClient() {
            return client;
        }

        public void setClient(User client) {
            this.client = client;
        }

        public User getSpec() {
            return spec;
        }

        public void setSpec(User spec) {
            this.spec = spec;
        }
}
