package life.majiang.community.contorller;

import life.majiang.community.dto.PaginationDTO;

import life.majiang.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;


@Controller
public class IndexController {



    @Resource
    private QuestionService questionService;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO paginationDTO = questionService.list(page, size);
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }
}
