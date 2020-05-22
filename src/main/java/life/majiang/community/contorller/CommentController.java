package life.majiang.community.contorller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import life.majiang.community.dto.CommentDTO;
import life.majiang.community.mapper.CommentMapper;
import life.majiang.community.model.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Api(value = "评论相关接口",tags = "评论接口",description = "评论的相关接口")
@Controller
public class CommentController {

    @Resource
    private CommentMapper commentMapper;

    @ApiOperation(value = "持久化评论",notes = "把评论存到数据库", httpMethod = "POST")
    @RequestMapping(value = "/comment",method = RequestMethod.POST)
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO){
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentDTO,comment);
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setLikeCount(0L);
        comment.setCommentator(1);
        commentMapper.insert(comment);
        return null;
    }
}
