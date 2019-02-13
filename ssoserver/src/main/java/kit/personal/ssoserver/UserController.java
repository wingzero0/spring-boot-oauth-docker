package kit.personal.ssoserver;

import kit.personal.ssoserver.entity.GPS;
import kit.personal.ssoserver.entity.GPSEmail;
import kit.personal.ssoserver.entity.Role;
import kit.personal.ssoserver.entity.SubstituicaoRole;
import kit.personal.ssoserver.repo.GPSRepository;
import kit.personal.ssoserver.repo.RoleRepository;
import kit.personal.ssoserver.repo.SubstituicaoRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    RoleRepository roleRepository;
    @Autowired
    SubstituicaoRoleRepository substituicaoRoleRepository;
    @Autowired
    GPSRepository gpsRepository;

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
        Integer funcNo = Integer.valueOf(principal.getName());
        List<SubstituicaoRole> extendRoleList = substituicaoRoleRepository.findAllByAppAndPkFuncNo(appId, funcNo);

        Set<String> roles = new HashSet<String>();
        for (SubstituicaoRole role : extendRoleList){
            roles.add("ROLE_" + role.getApp() + "_" + role.getRole());
        }

        List<Role> originalRoleList = roleRepository.findAllByAppAndFuncNo(appId, funcNo);
        for (Role role : originalRoleList){
            roles.add("ROLE_" + role.getApp() + "_" + role.getRole());
        }
        return roles;
    }

    @GetMapping("/user/info")
    @ResponseBody
    public GPS info(Principal principal) {
        GPS gps = gpsRepository.findOneByFuncNo(Integer.valueOf(principal.getName()));
        gps.setPasswd(null);
        return gps;
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