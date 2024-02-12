package com.nefer.controller;

import com.nefer.domain.member.Member;
import com.nefer.service.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class FormController {

    private final MemberService memberService;

    @Autowired
    public FormController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String userForm(){
        return "form";
    }

    @PostMapping("/members")
    public String userSubmit(@RequestParam("name") String name) {

        Optional<Member> member = memberService.findByName(name);


        if(member.isPresent()) {
            return "index";

        }

        return "form-no-name";


    }
}
