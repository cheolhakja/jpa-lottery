package com.nefer;

import com.nefer.domain.member.Member;
import com.nefer.domain.member.MemberRepository;
import com.nefer.domain.ticket.LotteryTicket;
import com.nefer.service.member.MemberService;
import com.nefer.service.ticket.LotteryTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class JpalotteryApplication implements CommandLineRunner {

	private final LotteryTicketService lotteryTicketService;
	private final MemberService memberService;

	@Autowired
	public JpalotteryApplication(LotteryTicketService lotteryTicketService, MemberService memberService) {
		this.lotteryTicketService = lotteryTicketService;
		this.memberService = memberService;
	}

	public static void main(String[] args) {
		SpringApplication.run(JpalotteryApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Member member = new Member("kevin");

		memberService.save(member);

		LotteryTicket ticket_1 = new LotteryTicket(new ArrayList<>(List.of(1,2,3,4,5, 6)), member);
		LotteryTicket ticket_2 = new LotteryTicket(new ArrayList<>(List.of(1,2,3,4,5, 8)), member);
		LotteryTicket ticket_3 = new LotteryTicket(new ArrayList<>(List.of(1,2,10, 9, 8, 7)), member);
		LotteryTicket ticket_4 = new LotteryTicket(new ArrayList<>(List.of(1,2,3,4,5,10)), member);

		//티켓을 db에 저장
		this.lotteryTicketService.save(ticket_1);
		this.lotteryTicketService.save(ticket_2);
		this.lotteryTicketService.save(ticket_3);
		this.lotteryTicketService.save(ticket_4);

		//티켓이 몇등인지 계산해보자
		this.lotteryTicketService.calculateWinnerAndUpdateMembers(new ArrayList<>(List.of(1,2,3,4,5,6)));

	}
}
