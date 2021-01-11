package board.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import board.member.dto.MemberDto;
import board.member.service.MemberService;

@Controller
public class MemberController {

	@Autowired
	public MemberService memberService;

	// signup페이지 뿌려주기
	@RequestMapping("/signup")
	public String openSignup() throws Exception {
		return "/member/memberSignup";
	}

	// db에 회원정보 저장
	@RequestMapping(value = "/signup/insertMember.do" , method = RequestMethod.POST)
	public String insertMember(MemberDto member) throws Exception {
		//String userId = member.getUserid();
		//String userPwd = member.getUserpwd();
		String check = member.getPermission();
		String url = null;
		
		if(check.equals("1")) {
			memberService.insertMember(member);
			url = "redirect:/board/openBoardList.do";
		} else if(check.equals("0")) {
			url = "redirect:/signup";
		}
		
		return url; // 회원가입이 완료되면 게시판 목록 조회
	}

	// 아이디 조회
	@RequestMapping(value = "/idcheck.do", method = RequestMethod.POST)
	@ResponseBody
	public int idChk(MemberDto member) throws Exception {
		int result = memberService.idChk(member);
		return result;
	}
	
}
