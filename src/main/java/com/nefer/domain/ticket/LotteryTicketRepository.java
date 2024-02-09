package com.nefer.domain.ticket;

import com.nefer.domain.member.Member;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LotteryTicketRepository {

    private final EntityManager em;

    @Autowired
    public LotteryTicketRepository(EntityManager em) {
        this.em = em;
    }

    public LotteryTicket save(LotteryTicket lotteryTicket) {
        em.persist(lotteryTicket);
        return lotteryTicket;
    }

    public List<LotteryTicket> findAll() {
        return em.createQuery("select l from LotteryTicket l", LotteryTicket.class)
                .getResultList();
    }

    /*
    public List<LotteryTicket> findByMemberName(String memberName) {
        return em.createQuery("SELECT l FROM LotteryTicket WHERE l.member.name = :memberName", LotteryTicket.class)
                .setParameter("memberName", memberName).getResultList();
    }

     */
}
