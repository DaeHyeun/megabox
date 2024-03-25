package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MovieRepository extends JpaRepository<Movie , Long> {
    @Query("select m, i from Movie m " +
            "left outer join MovieImg i on i.movie = m where i.repImgYn = 'Y'")
    Page<Object[]> getListPage(Pageable pageable);
}
