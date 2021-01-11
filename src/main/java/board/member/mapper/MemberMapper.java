package board.member.mapper;

import org.apache.ibatis.annotations.Mapper;

import board.member.dto.MemberDto;

@Mapper
public interface MemberMapper {

	public void insertMember(MemberDto member);

}
