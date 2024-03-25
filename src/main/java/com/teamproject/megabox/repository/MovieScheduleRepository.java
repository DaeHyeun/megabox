package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.MovieSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieScheduleRepository extends JpaRepository<MovieSchedule, Long> {
}
