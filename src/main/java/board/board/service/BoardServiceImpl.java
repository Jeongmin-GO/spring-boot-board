package board.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.board.dto.BoardDto;
import board.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}
	
	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDto selectBoardDetail(int board_idx, String flag) throws Exception {
		// TODO Auto-generated method stub
		BoardDto board = boardMapper.selectBoardDetail(board_idx);
		if(flag == "detail") {
			boardMapper.updateHitCount(board_idx);
		}
	
		return board;
	}

	@Override
	public void updateBoard(BoardDto board) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.updateBoard(board);
		
	}

	@Override
	public void deleteBoard(int board_idx) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.deleteBoard(board_idx);
	}
}
