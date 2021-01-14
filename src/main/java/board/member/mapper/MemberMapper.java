package board.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import board.member.dto.MemberDto;

@Mapper
public interface MemberMapper {

	// 회원정보저장
	public void insertMember(MemberDto member) throws Exception;
	// 아이디 중복체크
	public int idChk(MemberDto member) throws Exception;
	//로그인
	public MemberDto login(MemberDto member) throws Exception;
	
	//public MemberDto findByUserIdAndPwd(String userid, String userpwd);
}
