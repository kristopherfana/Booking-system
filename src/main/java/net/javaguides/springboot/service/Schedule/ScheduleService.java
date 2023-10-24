package net.javaguides.springboot.service.Schedule;

import net.javaguides.springboot.model.Schedule;
import net.javaguides.springboot.model.User;

import java.util.List;

public interface ScheduleService {
    List<Schedule> findAll();
    List<Schedule> findByBarber(String email);
}

