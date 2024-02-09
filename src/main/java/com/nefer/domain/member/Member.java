package com.nefer.domain.member;

import com.nefer.domain.LotteryTime;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter
@Setter
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

    private Integer numOfWinning;
    private Integer numOfSecondPlace;

    public Member(String name, String studentId, LotteryTime lotteryTime, Integer numOfWinning, Integer numOfSecondPlace) {
        this.name = name;
        this.studentId = studentId;
        this.lotteryTime = lotteryTime;
        this.numOfWinning = numOfWinning;
        this.numOfSecondPlace = numOfSecondPlace;
    }

    public Member(String name) {
        this(name, null, null, 0, 0);
    }

    public Member() {
    }
}
