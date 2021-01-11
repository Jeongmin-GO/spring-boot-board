package board.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.member.dto.MemberDto;
import board.member.mapper.MemberMapper;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired(required=true)
	private MemberMapper memberMapper;
	
	@Override
	public void insertMember(MemberDto member) throws Exception{
		memberMapper.insertMember(member);
	}
	
	@Override
	public int idChk(MemberDto member) throws Exception{
		return memberMapper.idChk(member);
	}
}
