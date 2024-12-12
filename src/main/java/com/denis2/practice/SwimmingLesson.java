package com.denis2.practice;

public class SwimmingLesson {
    private String groupName;
    private int lessonNumber;
    private String date;
    private String coach;
    private int poolNumber;

    public SwimmingLesson(String groupName, int lessonNumber, String date, String coach, int poolNumber) {
        this.groupName = groupName;
        this.lessonNumber = lessonNumber;
        this.date = date;
        this.coach = coach;
        this.poolNumber = poolNumber;

    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    public void setLessonNumber(int lessonNumber) {
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

    public int getPoolNumber() {
        return poolNumber;
    }

    public void setPoolNumber(int poolNumber) {
        this.poolNumber = poolNumber;
    }
}
