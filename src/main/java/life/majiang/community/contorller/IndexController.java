package life.majiang.community.contorller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import life.majiang.community.dto.PaginationDTO;

import life.majiang.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;


@Controller
@Api(value = "首页接口",description = "首页相关接口",tags = "首页相关接口")
public class IndexController {

    @Resource
    private QuestionService questionService;

    @GetMapping("/")
    @ApiOperation(value = "首页页面信息获取",notes = "获取首页所需的各种信息")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size) {
        PaginationDTO paginationDTO = questionService.list(page, size);
        model.addAttribute("pagination", paginationDTO);
        return "index";
    }
}
