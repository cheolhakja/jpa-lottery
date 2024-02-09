package com.nefer.service.ticket;

import com.nefer.domain.member.Member;
import com.nefer.domain.member.MemberRepository;
import com.nefer.domain.ticket.LotteryTicket;
import com.nefer.domain.ticket.LotteryTicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LotteryTicketService {

    private final LotteryTicketRepository lotteryTicketRepository;
    private final MemberRepository memberRepository;

    @Autowired
    public LotteryTicketService(LotteryTicketRepository lotteryTicketRepository, MemberRepository memberRepository) {
        this.lotteryTicketRepository = lotteryTicketRepository;
        this.memberRepository = memberRepository;
    }

    @Transactional
    public void save(LotteryTicket lotteryTicket) {
        lotteryTicketRepository.save(lotteryTicket);
    }

    @Transactional
    public void calculateWinnerAndUpdateMembers (List<Integer> winningNumbers) {
        List<LotteryTicket> allTickets = lotteryTicketRepository.findAll();


        for (LotteryTicket ticket : allTickets) {
            List<Integer> ticketNumbers = ticket.getNumbers();

            Integer numberOfMatching = this.getNumberOfMatching(winningNumbers, ticketNumbers);


            if(numberOfMatching.equals(6)) {
                //Member의 numOfWinning을 +1 시키는 코드
                Member member = ticket.getMember();
                Integer numOfWinning = member.getNumOfWinning();
                numOfWinning++;
                member.setNumOfWinning(numOfWinning);

            }
            else if(numberOfMatching.equals(5)) {
                //Member의 numOfSecondPlace를 +1 시키는 코드
                Member member = ticket.getMember();
                Integer numOfSecondPlace = member.getNumOfSecondPlace();
                numOfSecondPlace++;
                member.setNumOfSecondPlace(numOfSecondPlace);
            }
            else{
                continue;
            }

        }


    }

    private Integer getNumberOfMatching(List<Integer> winningNumbers, List<Integer> ticketNumbers) {
        Collections.sort(winningNumbers);
        Collections.sort(ticketNumbers);

        Integer result = 0;

        for (int i = 0; i < 6; i++) {
            if(winningNumbers.get(i).equals(ticketNumbers.get(i))) {
                result++;
            }
        }

        return result;
    }
}