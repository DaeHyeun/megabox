package com.teamproject.megabox.service;

//이메일 보내는 거 담당

import com.teamproject.megabox.dto.MailDTO;
import com.teamproject.megabox.entity.Member;
import com.teamproject.megabox.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendEmailService {

    //멤버 레포지토리가 필요한 이유 : 임시비밀번호로 member table update 해주어야 하기 때문에
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;
    private static final String FROM_ADDRESS = "megabox@megabox.com";


    public MailDTO createMailAndChangePassword(String email, String name){
        //임시 비밀번호 생성하는 메소드 호출
        String tempPass = getTempPassword();
        MailDTO dto = new MailDTO();
        dto.setAddress(email);
        dto.setTitle(name+"님의 Megabox 임시 비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. Megabox 임시비밀번호 안내 관련 이메일 입니다." + "[" + name + "]" +"님의 임시 비밀번호는 "
                + tempPass + " 입니다.");
        //임시비밀번호로 db에 회원 정보 update하는 메소드
        updatePassword(tempPass, email);
        return dto;
    }

    //임시 비밀번호 생성하는 메소드
    public String getTempPassword(){
        char[] charSet = new char[]{ '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
        //임시 비밀번호 저장될 변수
        String str = "";
        //10자리의 임시 비밀번호
        for (int i = 0; i < 10; i++) {
            //idx에는 0 ~ charSet의 길이에 해당하는 랜덤한 수를 가진다.
            int idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }

    //email에 해당하는 회원찾아서 비밀번호 재설정해주는 메소드
    public void updatePassword(String str, String email){
        //임시 비밀번호 인코딩 (암호화)
        String password = passwordEncoder.encode(str);
        //email로 회원 엔티티 찾기
        Member findMember = memberRepository.findByEmail(email);
        //찾은 회원의 엔티티 비밀번호 update
        findMember.setPassword(password);
        //db에 업데이트
        memberRepository.save(findMember);
    }

    //STMT mailSend
    public void mailSend(MailDTO mailDTO){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDTO.getAddress());                //받는 사람 주소
        //보내는 사람 주소
        //설정하지 않으면 디폴트 값인 application.properties에 있는 메일 주소로 보내짐
        message.setFrom(SendEmailService.FROM_ADDRESS);
        //메일 제목
        message.setSubject(mailDTO.getTitle());
        //메일 내용
        message.setText(mailDTO.getMessage());

        mailSender.send(message);
    }
}
