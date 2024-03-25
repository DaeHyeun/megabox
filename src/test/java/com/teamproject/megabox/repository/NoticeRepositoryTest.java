package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NoticeRepositoryTest {
    @Autowired
    NoticeRepository noticeRepository;

    @Test
    public void insertData(){
        for (int i = 1; i <= 10; i++) {
            Notice notice = Notice.builder()
                    .title("공지사항 제목"+i)
                    .content("공지사항 내용"+i)
                    .build();
            noticeRepository.save(notice);
        }
    }

}
