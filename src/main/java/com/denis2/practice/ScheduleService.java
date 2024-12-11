package com.denis2.practice;

import com.denis2.practice.SwimmingLesson;
import com.denis2.practice.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<SwimmingLesson> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    public Optional<SwimmingLesson> getScheduleById(Long id) {
        return scheduleRepository.findById(id);
    }

    public SwimmingLesson createSchedule(SwimmingLesson schedule) {
        return scheduleRepository.save(schedule);
    }

    public SwimmingLesson updateSchedule(Long id, SwimmingLesson scheduleDetails) {
        SwimmingLesson schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id " + id));

        schedule.setGroupName(scheduleDetails.getGroupName());
        schedule.setLessonNumber(scheduleDetails.getLessonNumber());
        schedule.setDate(scheduleDetails.getDate());
        schedule.setCoach(scheduleDetails.getCoach());
        schedule.setPoolNumber(scheduleDetails.getPoolNumber());

        return scheduleRepository.save(schedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.deleteById(id);
    }
}