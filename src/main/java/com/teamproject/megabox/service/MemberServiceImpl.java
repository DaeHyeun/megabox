package com.teamproject.megabox.service;

import com.teamproject.megabox.dto.MemberDTO;
import com.teamproject.megabox.entity.Member;
import com.teamproject.megabox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService, UserDetailsService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    //회원가입 메소드
    @Override
    public Member saveMember(MemberDTO dto) {
        Member member = dtoToEntity(dto);
        member.setPassword(passwordEncoder.encode(dto.getPassword()));
        return memberRepository.save(member);
    }

    //회원 아이디 중복확인 메소드
    @Override
    public String validateMember(MemberDTO dto) {
        Member findMember = memberRepository.findByEmail(dto.getEmail());
        if (findMember!=null){
            return "ok"; //이미 가입된 이메일일 때
        }else {
            return "fail";  //없는 이메일일 때
        }
    }

    //스프링 시큐리티를 이용한 로그인을 위한 UserDetails를 리턴하는 메소드
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(username);
        if (member ==null) {
            throw new UsernameNotFoundException(username);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
