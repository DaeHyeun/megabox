package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
