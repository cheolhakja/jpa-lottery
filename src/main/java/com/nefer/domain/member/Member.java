package com.nefer.domain.member;

import com.nefer.domain.LotteryTime;
import jakarta.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String studentId;

    @OneToOne
    @JoinColumn(name = "lottery_time_id")
    private LotteryTime lotteryTime;

}
