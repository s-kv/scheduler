package com.scheduler.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	//@Size(min = 10, max = 10, message = "Телефонный номер должен содержать 10 цифр.")
	@Pattern(regexp = "\\d{10}", message = "Телефонный номер должен состоять из 10 цифр.")
	private String phone;
	@Size(min = 6, max = 32, message = "Пароль должен быть не менее 6 символов.")
	private String password;
        @Column (name = "email", unique = true, nullable = false)
        @Email
	private String email;
	@Size(min = 3, max = 50, message = "Имя должно быть от 3 до 50 символов.")
	private String fullName;
	private String avatar;
	@Size(max = 140)
	private String description;
	private String grantedAuthority;
        
        private Boolean allowBooking;
        
        @DateTimeFormat(pattern = "HH:mm")
        @Temporal(TemporalType.TIME)
        private Date begTime;
        @DateTimeFormat(pattern = "HH:mm")
        @Temporal(TemporalType.TIME)
        private Date endTime;
        @DateTimeFormat(pattern = "HH:mm")
        @Temporal(TemporalType.TIME)
        private Date duration;
        
        private Boolean mon;
        private Boolean tue;
        private Boolean wed;
        private Boolean thu;
        private Boolean fri;
        private Boolean sat;
        private Boolean sun;

        @OneToMany(fetch = FetchType.LAZY, mappedBy = "spec")
        @OrderBy("startDate DESC")
        private Collection<Booking> bookings = new ArrayList<>();
        @OneToMany(fetch = FetchType.LAZY, mappedBy = "client")
        @OrderBy("startDate DESC")
        private Collection<Booking> bookedByMe = new ArrayList<>();
        
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<User> usersIFollow = new ArrayList<>();
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "usersIFollow")
	private Collection<User> followers = new ArrayList<>();
        
        public User copyEditFields(User user) {
            setPhone(user.getPhone());
            setFullName(user.getFullName());
            setDescription(user.getDescription());
            setAllowBooking(user.getAllowBooking());
            setBegTime(user.getBegTime());
            setEndTime(user.getEndTime());
            setDuration(user.getDuration());
            setMon(user.getMon());
            setTue(user.getTue());
            setWed(user.getWed());
            setThu(user.getThu());
            setFri(user.getFri());
            setSat(user.getSat());
            setSun(user.getSun());
            if(user.getAvatar() != null)
                setAvatar(user.getAvatar());
            
            return this;
        }        
        
        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

        public Collection<User> getUsersIFollow() {
            return usersIFollow;
        }

        public void setUsersIFollow(Collection<User> usersIFollow) {
            this.usersIFollow = usersIFollow;
        }

        public Collection<User> getFollowers() {
            return followers;
        }

        public void setFollowers(Collection<User> followers) {
            this.followers = followers;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getGrantedAuthority() {
            return grantedAuthority;
        }

        public void setGrantedAuthority(String grantedAuthority) {
            this.grantedAuthority = grantedAuthority;
        }
 
        public Date getBegTime() {
            return begTime;
        }

        public void setBegTime(Date begTime) {
            this.begTime = begTime;
        }

        public Date getEndTime() {
            return endTime;
        }

        public void setEndTime(Date endTime) {
            this.endTime = endTime;
        }

        public Date getDuration() {
            return duration;
        }

        public void setDuration(Date duration) {
            this.duration = duration;
        }

        public Collection<Booking> getBookings() {
            return bookings;
        }

        public void setBookings(Collection<Booking> bookings) {
            this.bookings = bookings;
        }

        public Collection<Booking> getBookedByMe() {
            return bookedByMe;
        }

        public void setBookedByMe(Collection<Booking> bookedByMe) {
            this.bookedByMe = bookedByMe;
        }

        public Boolean getMon() {
            return mon;
        }

        public void setMon(Boolean mon) {
            this.mon = mon;
        }

        public Boolean getTue() {
            return tue;
        }

        public void setTue(Boolean tue) {
            this.tue = tue;
        }

        public Boolean getWed() {
            return wed;
        }

        public void setWed(Boolean wed) {
            this.wed = wed;
        }

        public Boolean getThu() {
            return thu;
        }

        public void setThu(Boolean thu) {
            this.thu = thu;
        }

        public Boolean getFri() {
            return fri;
        }

        public void setFri(Boolean fri) {
            this.fri = fri;
        }

        public Boolean getSat() {
            return sat;
        }

        public void setSat(Boolean sat) {
            this.sat = sat;
        }

        public Boolean getSun() {
            return sun;
        }

        public void setSun(Boolean sun) {
            this.sun = sun;
        }

        public Boolean getAllowBooking() {
            return allowBooking;
        }

        public void setAllowBooking(Boolean allowBooking) {
            this.allowBooking = allowBooking;
        }
}

