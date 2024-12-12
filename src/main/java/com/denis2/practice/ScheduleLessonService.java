package com.denis2.practice;

import com.denis2.practice.dto.SwimmingLessonDTO;
import com.denis2.practice.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleLessonService {

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

    public Map<String, List<SwimmingLessonDTO>> getSchedulesGroupedByWeekdays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Map<DayOfWeek, List<SwimmingLessonDTO>> groupedSchedules = scheduleRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        lesson -> {
                            LocalDate date = LocalDate.parse(lesson.getDate(), formatter);
                            return date.getDayOfWeek();
                        },
                        Collectors.mapping(SwimmingLessonDTO::fromEntity, Collectors.toList())
                ));

        Map<String, List<SwimmingLessonDTO>> orderedSchedules = new LinkedHashMap<>();
        orderedSchedules.put("Monday", groupedSchedules.getOrDefault(DayOfWeek.MONDAY, new ArrayList<>()));
        orderedSchedules.put("Tuesday", groupedSchedules.getOrDefault(DayOfWeek.TUESDAY, new ArrayList<>()));
        orderedSchedules.put("Wednesday", groupedSchedules.getOrDefault(DayOfWeek.WEDNESDAY, new ArrayList<>()));
        orderedSchedules.put("Thursday", groupedSchedules.getOrDefault(DayOfWeek.THURSDAY, new ArrayList<>()));
        orderedSchedules.put("Friday", groupedSchedules.getOrDefault(DayOfWeek.FRIDAY, new ArrayList<>()));

        return orderedSchedules;
    }
}


