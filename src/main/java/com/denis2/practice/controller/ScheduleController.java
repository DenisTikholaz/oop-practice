package com.denis2.practice.controller;

import com.denis2.practice.SwimmingLesson;
import com.denis2.practice.dto.SwimmingLessonDTO;
import com.denis2.practice.ScheduleLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ScheduleController {

    @Autowired
    private ScheduleLessonService scheduleLessonService;

    @GetMapping("/schedule/groupedByWeekdays")
    public ResponseEntity<Map<String, List<SwimmingLessonDTO>>> getSchedulesGroupedByWeekdays() {
        Map<String, List<SwimmingLessonDTO>> schedule = scheduleLessonService.getSchedulesGroupedByWeekdays();
        return ResponseEntity.ok(schedule);
    }

    @PostMapping("/schedule")
    public ResponseEntity<SwimmingLessonDTO> addNewLesson(@RequestBody SwimmingLessonDTO lessonDTO) {
        SwimmingLesson lesson = lessonDTO.toEntity();
        SwimmingLesson savedLesson = scheduleLessonService.createSchedule(lesson);
        return ResponseEntity.status(HttpStatus.CREATED).body(SwimmingLessonDTO.fromEntity(savedLesson));
    }
}



