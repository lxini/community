package life.majiang.community.contorller;

import life.majiang.community.dto.AccessTokenDTO;
import life.majiang.community.dto.GithubUserDTO;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {

    @Resource
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.client.redirect.url}")
    private String redirectUrl;

    @Resource
    private UserMapper userMapper;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletResponse response) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_url(redirectUrl);
        accessTokenDTO.setState(state);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        // 调用github API获取accessToken
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        // 调用github API获取用户信息
        GithubUserDTO githubUserDTO = githubProvider.getUser(accessToken);

        if (githubUserDTO != null) {
            // 登录成功，写cookie和session
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(githubUserDTO.getName());
            user.setAccountId(String.valueOf(githubUserDTO.getId()));
            user.setAvatarUrl(githubUserDTO.getAvatar_url());
            user.setBio(githubUserDTO.getBio());
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());

            userMapper.insert(user);
            response.addCookie(new Cookie("token",token));
            return "redirect:/";
        } else {
            // 登录失败，重新登录
            System.out.println("登录失败");
            return "redirect:/";
        }
    }
}
