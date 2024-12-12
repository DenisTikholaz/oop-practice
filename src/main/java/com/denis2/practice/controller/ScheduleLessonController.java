package com.denis2.practice.controller;

import com.denis2.practice.dto.SwimmingLessonDTO;
import com.denis2.practice.ScheduleLessonService;
import com.denis2.practice.SwimmingLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleLessonController {

    @Autowired
    private ScheduleLessonService scheduleService;

    @GetMapping
    public List<SwimmingLessonDTO> getAllSchedules() {
        return scheduleService.getAllSchedules().stream()
                .map(SwimmingLessonDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SwimmingLessonDTO> getScheduleById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id)
                .map(SwimmingLessonDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SwimmingLessonDTO> createSchedule(@Valid @RequestBody SwimmingLessonDTO scheduleDTO) {
        SwimmingLesson savedSchedule = scheduleService.createSchedule(scheduleDTO.toEntity());
        return ResponseEntity.ok(SwimmingLessonDTO.fromEntity(savedSchedule));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SwimmingLessonDTO> updateSchedule(@PathVariable Long id, @Valid @RequestBody SwimmingLessonDTO scheduleDTO) {
        try {
            SwimmingLesson updatedSchedule = scheduleService.updateSchedule(id, scheduleDTO.toEntity());
            return ResponseEntity.ok(SwimmingLessonDTO.fromEntity(updatedSchedule));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/groupedByWeekdays")
    public ResponseEntity<Map<String, List<SwimmingLessonDTO>>> getSchedulesGroupedByWeekdays() {
        Map<String, List<SwimmingLessonDTO>> groupedSchedules = scheduleService.getSchedulesGroupedByWeekdays();
        return ResponseEntity.ok(groupedSchedules);
    }
}


