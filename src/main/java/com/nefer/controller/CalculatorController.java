package com.nefer.controller;

import com.nefer.domain.member.Candidate;
import com.nefer.service.ticket.LotteryTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class CalculatorController {

    private final LotteryTicketService lotteryTicketService;

    @Autowired
    public CalculatorController(LotteryTicketService lotteryTicketService) {
        this.lotteryTicketService = lotteryTicketService;
    }


    @PostMapping("/admin/calculate/home")
    public String home() {
        return "calculate";
    }

    @PostMapping("/admin/calculate/action")
    public String inAction() {

        //1등, 2등 계산
        this.lotteryTicketService.calculateWinnerAndUpdateMembers(new ArrayList<>(List.of(1,2,3,4,5,6)));
        this.lotteryTicketService.convertAFewSecondPlaceToWinning();
        List<Candidate> candidateList = this.lotteryTicketService.getCandidate();

        //1등 후보중 최종 1등 선정
        Random random = new Random();
        int indexOfWinner = random.nextInt(candidateList.size());
        Candidate winner = candidateList.get(indexOfWinner);

        //출력
        System.out.println(winner);



        return "calculate-done";
    }

}
