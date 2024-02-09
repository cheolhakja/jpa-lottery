package com.nefer.domain.ticket;

import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LotteryTicketRepository {

    private final EntityManager em;


    public LotteryTicketRepository(EntityManager em) {
        this.em = em;
    }

    public LotteryTicket add(LotteryTicket lotteryTicket) {
        return null;
    }

    public List<LotteryTicket> findAll() {
        return new ArrayList<>();
    }
}
