package com.nefer.service.member;

import com.nefer.domain.member.Member;
import com.nefer.domain.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void save(Member member) {
        memberRepository.save(member);
    }

    @Transactional
    public List<Member> findAll() {
        return memberRepository.findAll();
    }
}
