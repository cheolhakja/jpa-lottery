package com.nefer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity @Getter
@Setter
public class LotteryTime {

    @Id
    @GeneratedValue @Column(name = "lottery_time_id")
    private Long id;


    private LocalDateTime lastPurchaseTime;
}
