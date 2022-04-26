package kr.rimu.rimboard.controller;

import kr.rimu.rimboard.dto.BoardDto;
import kr.rimu.rimboard.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@Controller
public class BoardController {

    private BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardDto> boardDtoList = boardService.getBoardList();
        model.addAttribute("postList", boardDtoList);
        return "board/list";
    }

    @GetMapping
    public String post() {
        return "board/post";
    }

    @PostMapping
    public String write(BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/post/list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit";
    }

    @PutMapping("/edit/{id}")
    public String update( BoardDto boardDto) {
        boardService.savePost(boardDto);
        return "redirect:/post/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deletePost(id);
        return "redirect:/post/list";
    }
}