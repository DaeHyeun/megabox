package com.teamproject.megabox.repository;

import com.teamproject.megabox.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    @Query("select m from Member m where m.name=:name and m.pnumber=:pnumber")
    Member findByNamePnumber(@Param("name") String name, @Param("pnumber") String pnumber);

    @Query("select m from Member m where m.email=:email and m.name=:name")
    Member findByEmailName(@Param("email") String email, @Param("name") String name);
}
