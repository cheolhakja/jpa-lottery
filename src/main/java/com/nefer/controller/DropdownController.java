package com.nefer.controller;

import com.nefer.LotteryNumberValidator;
import com.nefer.domain.member.Member;
import com.nefer.domain.ticket.LotteryTicket;
import com.nefer.service.member.MemberService;
import com.nefer.service.ticket.LotteryTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class DropdownController {

    private final MemberService memberService;
    private final LotteryTicketService lotteryTicketService;

    @Autowired
    public DropdownController(MemberService memberService, LotteryTicketService lotteryTicketService) {
        this.memberService = memberService;
        this.lotteryTicketService = lotteryTicketService;
    }


    @GetMapping("/")
    public String showDropdownPage() {
        return "index";
    }

    @PostMapping("/")
    public String handleFormSubmission(@RequestParam("name") String name,
            @RequestParam(value="dropdown1") String dropdown1,
            @RequestParam(value="dropdown2") String dropdown2,
            @RequestParam(value="dropdown3") String dropdown3,
            @RequestParam(value="dropdown4") String dropdown4,
            @RequestParam(value="dropdown5") String dropdown5,
            @RequestParam(value="dropdown6") String dropdown6) {

        List<Integer> result = List.of(dropdown1, dropdown2, dropdown3, dropdown4, dropdown5, dropdown6).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());


        Optional<Member> member = memberService.findByName(name);
        if(!member.isPresent()) {
            return "redirect:/";
        }



        // Process the selected values from the dropdowns
        ArrayList<Integer> inputLotteryNumbers = new ArrayList<>(result);


        boolean duplicateOk = LotteryNumberValidator.checkDuplicate(inputLotteryNumbers);

        if(duplicateOk) {
            lotteryTicketService.save(new LotteryTicket(inputLotteryNumbers, member.get()));
            return "done";
        }
        return "redirect:/";


    }
}
