package com.shop.farmers.boundedContext.member.service;

import com.shop.farmers.boundedContext.member.entity.Member;
import com.shop.farmers.boundedContext.member.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){
        Member findMember = memberRepository.findByEmail(member.getEmail());

        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Transactional
    // 일반 회원가입
    public Member login(String email, String password) {
        return login("Farmers", email, password);
    }

    private Member login(String providerTypeCode, String email, String password)  throws UsernameNotFoundException {
        Member findMember = memberRepository.findByEmail(email);

        if(findMember == null){
            throw new UsernameNotFoundException(email);
        }

        Member member = Member
                .builder()
                .providerTypeCode(providerTypeCode)
                .username(email)
                .password(password)
                .build();

        return memberRepository.save(member);
    }

    // 소셜 로그인 시 실행되는 함수
    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String email) {
        Member opMember = memberRepository.findByEmail(email);

        if (opMember != null)
            throw new IllegalStateException("이미 가입된 회원입니다.");

        // 소셜 로그인를 통한 가입 시 비밀번호는 없다.
        return login(providerTypeCode, email, ""); // 최초 로그인 시 딱 한번 실행
    }
}
