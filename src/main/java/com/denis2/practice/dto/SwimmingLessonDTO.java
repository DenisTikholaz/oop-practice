package com.denis2.practice.dto;

import com.denis2.practice.SwimmingLesson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;

public class SwimmingLessonDTO {

    private Long id;

    @NotNull(message = "Group name cannot be null")
    @Size(min = 2, max = 100, message = "Group name must be between 2 and 100 characters")
    private String groupName;

    @Min(value = 1, message = "Lesson number must be at least 1")
    private int lessonNumber;

    @NotNull(message = "Date cannot be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Date must follow the format YYYY-MM-DD")
    private String date;

    @NotNull(message = "Coach name cannot be null")
    @Size(min = 2, max = 100, message = "Coach name must be between 2 and 100 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Coach name must contain only letters and spaces")
    private String coach;

    @Min(value = 1, message = "Pool number must be at least 1")
    private int poolNumber;

    public SwimmingLessonDTO() {
    }

    public SwimmingLessonDTO(Long id, String groupName, int lessonNumber, String date, String coach, int poolNumber) {
        this.id = id;
        this.groupName = groupName;
        this.lessonNumber = lessonNumber;
        this.date = date;
        this.coach = coach;
        this.poolNumber = poolNumber;
    }

    public static SwimmingLessonDTO fromEntity(SwimmingLesson lesson) {
        return new SwimmingLessonDTO(
                lesson.getId(),
                lesson.getGroupName(),
                lesson.getLessonNumber(),
                lesson.getDate(),
                lesson.getCoach(),
                lesson.getPoolNumber()
        );
    }

    public SwimmingLesson toEntity() {
        return new SwimmingLesson(groupName, lessonNumber, date, coach, poolNumber);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(Integer lessonNumber) {
        this.lessonNumber = lessonNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public Integer getPoolNumber() {
        return poolNumber;
    }

    public void setPoolNumber(Integer poolNumber) {
        this.poolNumber = poolNumber;
    }
    @AssertTrue(message = "Month must be between 01 and 12")
    @JsonIgnore
    public boolean isMonthValid() {
        if (date != null) { String month = date.substring(5, 7);
            int monthInt = Integer.parseInt(month);
            return monthInt >= 1 && monthInt <= 12;
        }
        return true;
    }
    @AssertTrue(message = "Day must be between 01 and 31")
    @JsonIgnore
    public boolean isDayValid() {
        if (date != null) {
            String day = date.substring(8, 10);
            int dayInt = Integer.parseInt(day);
            return dayInt >= 1 && dayInt <= 31;
        }
        return true;
    }
}
