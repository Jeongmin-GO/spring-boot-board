package board.member.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import board.member.dto.MemberDto;
import board.member.service.MemberService;

//@SessionAttributes 
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
	
	//로그인 창 뿌려주기
	@RequestMapping(value = "/loginpage")
	public String openLogin() throws Exception{
		return "/member/memberLogin";
	}
	
//	######################################미완성
//	@RequestMapping("/doLogin")
//	public String doLogin(Model model, @RequestParam Map<String, Object> param) {
//		Map<String, Object> rs = memberService.checkLoginAvailable(param);
//		String resultCode = (String)rs.get("resultCode");
//		return "redirect";
//	}
	
	//로그인 기능
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String login(MemberDto member,/*HttpSession session,*/ HttpServletRequest req, RedirectAttributes rttr, Model model) throws Exception{
		HttpSession session = req.getSession(); //세션을 가져온다
		MemberDto login = memberService.login(member); 
		System.out.println("################ login : " + login);
		String url = "";
		
		if(login == null) {
			session.setAttribute("member", null); //"
			model.addAttribute("member","");
			rttr.addFlashAttribute("msg", false);
			System.out.println("11111###############login ID : " + member.getUserid()+ "   ############login PW : "+ member.getUserpwd());
			url = "redirect:/loginpage";
		}else {
			session.setAttribute("member", login);
			model.addAttribute("member",login);
			System.out.println("222222###############login ID : " + member.getUserid()+ "   ############login PW : "+ member.getUserpwd());
			url = "redirect:/board/openBoardList.do";
		}
		return url;
	}
	
//	@RequestMapping(value="login", method=RequestMethod.POST)
//	@ResponseBody
//	public ModelAndView login(HttpServletRequest req, MemberDto member) throws Exception {
//		ModelAndView mv = new ModelAndView();
//		
//		String id = req.getParameter("userid");
//		String pwd = req.getParameter("userpwd");
//		
//		MemberDto m = new MemberDto();
//		m = memberService.login(m);
//		
//		if(m == null) {
//			req.setAttribute("msg", "아이디가 없습니다");
//		}
//		
//		if(m!=null && m.getUserid().equals(id) && m.getUserpwd().equals(pwd)) {
//			mv.addObject("msg", "로그인성공");
//			mv.addObject("member", m);
//			System.out.println("로그인 성공");
//			System.out.println("###############login ID : " + m.getUserid()+ "   ############login PW : "+ m.getUserpwd());
//			
//		}else { //로그인 실패
//			mv.addObject("msg", "로그인에 실패했습니다. 아이디 또는 패스워드를 다시 입력해주십시오.");
//			System.out.println("로그인에 실패했습니다. 아이디 또는 패스워드를 다시 입력해주십시오.");
//		}
////		mv.setViewName("redirect:/board/openBoardList.do"); //이동할 주소를 setViewName의 괄호 안에 넣어줌
//		return mv;
	//}
}
