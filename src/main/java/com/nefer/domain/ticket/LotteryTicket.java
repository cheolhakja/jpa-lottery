package com.nefer.domain.ticket;

import com.nefer.domain.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity @Getter
@Setter
public class LotteryTicket {

    @Id @GeneratedValue @Column(name = "lottery_ticket_id")
    private Long id;

    @ElementCollection(fetch = FetchType.LAZY) //아 이게 OneToMany같이 보이지만 아닌 그거구나
    private List<Integer> numbers = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;


    public LotteryTicket(List<Integer> numbers, Member member) {
        this.numbers = numbers;
        this.member = member;
    }

    public LotteryTicket() {
    }
}
