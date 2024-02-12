package com.nefer.controller;

import com.nefer.LotteryNumberValidator;
import com.nefer.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DropdownController {

    private final MemberService memberService;

    @Autowired
    public DropdownController(MemberService memberService) {
        this.memberService = memberService;
    }


    @GetMapping("/")
    public String showDropdownPage() {
        return "index";
    }

    @PostMapping("/")
    public String handleFormSubmission(
            @RequestParam(value="dropdown1") String dropdown1,
            @RequestParam(value="dropdown2") String dropdown2,
            @RequestParam(value="dropdown3") String dropdown3,
            @RequestParam(value="dropdown4") String dropdown4,
            @RequestParam(value="dropdown5") String dropdown5,
            @RequestParam(value="dropdown6") String dropdown6) {

        List<Integer> result = List.of(dropdown1, dropdown2, dropdown3, dropdown4, dropdown5, dropdown6).stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());



        // Process the selected values from the dropdowns
        ArrayList<Integer> inputLotteryNumbers = new ArrayList<>(result);


        boolean duplicateOk = LotteryNumberValidator.checkDuplicate(inputLotteryNumbers);

        if(duplicateOk) {
            return "done";
        }
        return "redirect:/";


    }
}
