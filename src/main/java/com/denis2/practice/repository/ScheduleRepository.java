package com.denis2.practice.repository;

import com.denis2.practice.SwimmingLesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleRepository extends JpaRepository<SwimmingLesson, Long> {

}
