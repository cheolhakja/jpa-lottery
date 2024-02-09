package com.nefer.domain.ticket;

import com.nefer.domain.member.Member;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class LotteryTicket {

    @Id @GeneratedValue @Column(name = "lottery_ticket_id")
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Integer> numbers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
