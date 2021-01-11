package board.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import board.member.dto.MemberDto;
import board.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	public MemberService memberService;
	
	//signup페이지 뿌려주기
	@RequestMapping("/signup")
	public String openSignup() throws Exception {
		return "/member/memberSignup";
	}
	
	//db에 회원정보 저장
	@RequestMapping("/signup/insertMember.do")
	public String insertMember(MemberDto member) throws Exception{
//		System.out.println("######################### member : " + member);
		memberService.insertMember(member);
		return "redirect:/board/openBoardList.do"; //회원가입이 완료되면 게시판 목록 조회
	}
	
	//db에서 회원정보 조회는 나중에
	
}
