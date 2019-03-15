package kit.personal.ssoserver;

import kit.personal.ssoserver.entity.AppUser;
import kit.personal.ssoserver.entity.AppUserRole;
import kit.personal.ssoserver.repo.AppUserRepository;
import kit.personal.ssoserver.repo.AppUserRoleRepository;
import kit.personal.ssoserver.repo.SubstituicaoRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Controller
public class UserController {
    @Autowired
    AppUserRoleRepository roleRepository;
    @Autowired
    SubstituicaoRoleRepository substituicaoRoleRepository;
    @Autowired
    AppUserRepository appUserRepository;

    @GetMapping("/user/me")
    @ResponseBody
    public Principal user(Principal principal) {
        return principal;
    }

    @GetMapping("/user/role")
    @ResponseBody
    public Set<String> role(Principal principal) {
        OAuth2Authentication authen = (OAuth2Authentication) principal;
        String appId = authen.getOAuth2Request().getClientId();
        String username = principal.getName();
        Set<String> roles = new HashSet<String>();
        /*
        List<SubstituicaoRole> extendRoleList = substituicaoRoleRepository.findAllByAppAndPkFuncNo(appId, funcNo);
         */

        /*

        for (SubstituicaoRole role : extendRoleList){
            roles.add("ROLE_" + role.getApp() + "_" + role.getAppRole());
        }
        */

        List<AppUserRole> originalRoleList = roleRepository.findAllByAppIdAndUsername(appId, username);
        for (AppUserRole role : originalRoleList){
            roles.add("ROLE_" + role.getAppId() + "_" + role.getAppRole());
        }
        return roles;
    }

    @GetMapping("/user/info")
    @ResponseBody
    public AppUser info(Principal principal) {
        AppUser appUser = appUserRepository.findOneByUsername(principal.getName());
        appUser.setPassword(null);
        return appUser;
    }



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String postLogin() {
        // TODO Enable form login with Spring Security (trigger error for now)
        return "redirect:/login-error";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    @RequestMapping("/exit")
    public void exit(HttpServletRequest request, HttpServletResponse response) {
        // TODO token can be revoked here if needed
        new SecurityContextLogoutHandler().logout(request, null, null);
        try {
            //sending back to client app
            response.sendRedirect(request.getHeader("referer"));
            //System.err.println("someone call logout" + request.getHeader("referer"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}