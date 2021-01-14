package board.member.service;

import java.util.List;
import java.util.Map;

import board.member.dto.MemberDto;

public interface MemberService {

	public void insertMember(MemberDto member) throws Exception;
	
	// 아이디 중복 체크
	public int idChk(MemberDto member) throws Exception;
	//로그인
	public MemberDto login(MemberDto member) throws Exception;


//	public Map<String, Object> checkLoginAvailable(Map<String, Object> param);

	//?
	//public MemberDto findByUserIdAndPwd(String userid, String userpwd);
}
