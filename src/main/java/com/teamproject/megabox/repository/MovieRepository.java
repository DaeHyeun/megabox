package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie , Long> {
}
