package board.board.service;

import java.util.List;

import board.board.dto.BoardDto;

public interface BoardService {
	List<BoardDto> selectBoardList() throws Exception;
	void insertBoard(BoardDto board) throws Exception;
	BoardDto selectBoardDetail(int board_idx, String flag) throws Exception;
	void updateBoard(BoardDto board) throws Exception;
	void deleteBoard(int board_idx) throws Exception;
}
