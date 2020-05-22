package life.majiang.community.contorller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;

@Controller
@Api(value = "问题接口",tags = "问题相关接口")
public class QuestionController {

    @Resource
    private QuestionService questionService;

    @GetMapping("/question/{id}")
    @ApiOperation(value = "累加阅读数",notes = "访问一次问题阅读数加一",httpMethod = "GET")
    public String question(@PathVariable(name = "id")Long id, Model model){
        QuestionDTO questionDTO = questionService.getById(id);
        // 累加阅读数
        questionService.incView(id);
        model.addAttribute("question",questionDTO);
        return "question";
    }
}
