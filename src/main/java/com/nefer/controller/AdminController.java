package com.nefer.controller;

import com.nefer.domain.member.Member;
import com.nefer.service.member.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    private final MemberService memberService;

    public AdminController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/admin")
    public String home(){
        return "admin-login";
    }

    @PostMapping("/admin")
    public String login(@RequestParam("pw") String password){
        if(password.equals("1q2w3e4r!")) {
            return "admin-modify";
        }
        else{
            return "admin-login";
        }
    }

    @PostMapping("/admin/member/add")
    public String addMember(@RequestParam("memberadd") String name) {
        this.memberService.save(new Member(name));

        return "admin-modify";

    }


    @PostMapping("/admin/member/delete")
    public String deleteMember(@RequestParam("memberdelete") String name) {
        this.memberService.delete(name);

        return "admin-modify";

    }
}
