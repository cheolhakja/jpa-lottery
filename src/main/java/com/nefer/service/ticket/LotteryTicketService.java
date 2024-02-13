package com.nefer.service.ticket;

import com.nefer.domain.member.Candidate;
import com.nefer.domain.member.Member;
import com.nefer.domain.member.MemberRepository;
import com.nefer.domain.ticket.LotteryTicket;
import com.nefer.domain.ticket.LotteryTicketRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        //1등횟수와 2등횟수를 계산해서 db에 반영하는 메서드
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



    @Transactional
    public void convertAFewSecondPlaceToWinning() {
        //5번이상 2등을 하면 1등 1번으로 바꿔줌

        List<Member> members = memberRepository.findAll();

        for (Member member : members) {
            Integer numOfWinning = member.getNumOfWinning();
            Integer numOfSecondPlace = member.getNumOfSecondPlace();

            //원래 1등 횟수 + 2등을 5번한횟수
            member.setNumOfWinning(numOfWinning + Math.divideExact(numOfSecondPlace, 5));

            //2등 5번한횟수의 나머지
            member.setNumOfSecondPlace(numOfSecondPlace % 5);

        }

    }

    @Transactional
    public List<Candidate> getCandidate() {
        //1등 횟수를 세서 1등 후보 리스트 반환
        List<Member> members = memberRepository.findAll();

        List<Candidate> result = new ArrayList<>();

        for (Member member : members) {
            Integer numOfWinning = member.getNumOfWinning();

            for (int i = 0; i < numOfWinning; i++) {
                result.add(new Candidate(member.getId(), member.getName()));
            }


        }

        return result;

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
