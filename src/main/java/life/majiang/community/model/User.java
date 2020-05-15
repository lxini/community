package life.majiang.community.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private String token;
    private String avatarUrl;
    private String bio;
    private Long gmtCreate;
    private Long gmtModified;
}
