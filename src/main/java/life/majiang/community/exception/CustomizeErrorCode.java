package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {

    QUESTION_NOT_FOUND(2001,"你查找的问题不存在，换个问题看看？"),
    TARGET_PARENT_NOT_FOUND(2002,"未选中任何问题或评论进行回复。"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试。"),
    SYS_ERROR(2004,"服务冒烟了，请稍后再试一下下！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或不存在。"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了。");

    private Integer code;
    private String message;

    CustomizeErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public Integer getCode() {
        return code;
    }
}
