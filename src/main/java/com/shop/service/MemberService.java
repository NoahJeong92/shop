package com.shop.service;


import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional// 로직 처리시 에러 발생하면 데이터 변경 이전으로 콜백 시켜줌
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {
//UserDetailsService는 데이터베이스에서 회원 정보를 가져오는 역할
    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember !=null){
           throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member =memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }
}
