package board.member.service;

import java.util.List;

import board.member.dto.MemberDto;

public interface MemberService {

	public void insertMember(MemberDto member) throws Exception;
	
	// 아이디 중복 체크
	public int idChk(MemberDto member) throws Exception;
}
