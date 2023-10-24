package net.javaguides.springboot.web.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDate;

public class BookingDto {
    private String clientEmail;
    private String barberEmail;
    private Long serviceId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private LocalDate date;
    @Temporal(TemporalType.TIME)
    @DateTimeFormat(pattern = "HH-mm")
    private String time;

    public BookingDto() {
    }

    public BookingDto(String clientEmail, String barberEmail, Long serviceId, LocalDate date, String time) {
        this.clientEmail = clientEmail;
        this.barberEmail = barberEmail;
        this.serviceId = serviceId;
        this.date = date;
        this.time = time;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getBarberEmail() {
        return barberEmail;
    }

    public void setBarberEmail(String barberEmail) {
        this.barberEmail = barberEmail;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
