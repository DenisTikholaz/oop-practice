package com.denis2.practice.controller;

import com.denis2.practice.dto.SwimmingLessonDTO;
import com.denis2.practice.ScheduleLessonService;
import com.denis2.practice.SwimmingLesson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    @Autowired
    private ScheduleLessonService scheduleService;

    @GetMapping
    public List<SwimmingLessonDTO> getAllLessons() {
        return scheduleService.getAllSchedules().stream()
                .map(SwimmingLessonDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SwimmingLessonDTO> getLessonById(@PathVariable Long id) {
        return scheduleService.getScheduleById(id)
                .map(SwimmingLessonDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SwimmingLessonDTO> createLesson(@Valid @RequestBody SwimmingLessonDTO lessonDTO) {
        SwimmingLesson savedLesson = scheduleService.createSchedule(lessonDTO.toEntity());
        return ResponseEntity.ok(SwimmingLessonDTO.fromEntity(savedLesson));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SwimmingLessonDTO> updateLesson(@PathVariable Long id, @Valid @RequestBody SwimmingLessonDTO lessonDTO) {
        try {
            SwimmingLesson updatedLesson = scheduleService.updateSchedule(id, lessonDTO.toEntity());
            return ResponseEntity.ok(SwimmingLessonDTO.fromEntity(updatedLesson));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
}
