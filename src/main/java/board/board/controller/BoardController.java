package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDto;
import board.board.service.BoardService;


@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
//		System.out.println("##################### list.idx : " + list.get(0).getBoard_idx());
//		System.out.println("##################### list.idx : " + list.get(0).getCreated_datetime());
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception{
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do") //boardWrite.html form의 속성 action에 지정되어있음
	public String insertBoard(BoardDto board) throws Exception{
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int board_idx) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		String flag = "detail";
		BoardDto board = boardService.selectBoardDetail(board_idx, flag);
		mv.addObject("board", board);
		
		return mv;
	}
	
	
	@RequestMapping("/board/openBoardEdit.do")
	public ModelAndView openBoardEdit(@RequestParam int board_idx) throws Exception{
		ModelAndView mv = new ModelAndView("/board/boardEdit");
		String flag = "edit";
		BoardDto board = boardService.selectBoardDetail(board_idx, flag);
		mv.addObject("board", board); 
		
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception{
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int board_idx) throws Exception {
		boardService.deleteBoard(board_idx);
		return "redirect:/board/openBoardList.do";
	}
}
