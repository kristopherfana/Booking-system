package net.javaguides.springboot.repository;

import net.javaguides.springboot.model.Schedule;
import net.javaguides.springboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAll();
    List<Schedule> findByBarber(User barber);
    Optional<Schedule> findById(Long Id);
}
