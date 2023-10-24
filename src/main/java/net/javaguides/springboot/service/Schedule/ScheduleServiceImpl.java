package net.javaguides.springboot.service.Schedule;

import net.javaguides.springboot.model.Schedule;
import net.javaguides.springboot.repository.ScheduleRepository;
import net.javaguides.springboot.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService{

    private ScheduleRepository scheduleRepository;
    private UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository){
        super();
        this.scheduleRepository=scheduleRepository;
        this.userRepository=userRepository;
    }
    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();

    }

    @Override
    public List<Schedule> findByBarber(String email) {
        List<Schedule> scheduleListByBarber= scheduleRepository.findByBarber(userRepository.findByEmail(email));
        for(Schedule schedule:scheduleListByBarber){
            System.out.println(schedule);
        }
        return scheduleListByBarber;
    }
}
