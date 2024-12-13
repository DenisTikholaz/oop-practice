package com.denis2.practice.controller;

import com.denis2.practice.dto.SwimmingLessonDTO;
import com.denis2.practice.ScheduleLessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleLessonService scheduleService;

    @GetMapping("/groupedByWeekdays")
    public ResponseEntity<Map<String, List<SwimmingLessonDTO>>> getScheduleGroupedByWeekdays() {
        Map<String, List<SwimmingLessonDTO>> groupedSchedule = scheduleService.getSchedulesGroupedByWeekdays();
        return ResponseEntity.ok(groupedSchedule);
    }
}
