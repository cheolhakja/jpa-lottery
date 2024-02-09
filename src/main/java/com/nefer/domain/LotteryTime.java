package com.nefer.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class LotteryTime {

    @Id
    @GeneratedValue @Column(name = "lottery_time_id")
    private Long id;


    private LocalDateTime lastPurchaseTime;
}
