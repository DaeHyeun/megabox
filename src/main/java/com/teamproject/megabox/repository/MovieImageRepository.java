package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.MovieImg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieImageRepository extends JpaRepository<MovieImg, Long> {
}
