package kit.personal.ssoclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController{
    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;


    @GetMapping("/")
    @ResponseBody
    public String index() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getPrincipal().getClass().toGenericString() + "--------" + auth.getPrincipal().toString();
    }

    @GetMapping("/userinfo")
    @ResponseBody
    public String userinfo(OAuth2AuthenticationToken authentication) {

        OAuth2AuthorizedClient authorizedClient = this.getAuthorizedClient(authentication);
        String ret = "token:" + authorizedClient.getAccessToken().getTokenValue();
        ret += ", userName:" + authentication.getName();
        ret += ", clientName:" + authorizedClient.getClientRegistration().getClientName();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority authority : auth.getAuthorities()){
            ret += ", authority:" + authority.getAuthority();
        }
        //DefaultOidcUser oidcUser = (DefaultOidcUser) auth.getPrincipal();
        //ret += ", oidcUser" + oidcUser.getFullName();
        return ret;
    }

    @GetMapping("/userias")
    @ResponseBody
    public String userias(OAuth2AuthenticationToken authentication) {
        String ret = "you have role ias";
        return ret;
    }

    @GetMapping("/usergoogle")
    @ResponseBody
    public String usergoogle(OAuth2AuthenticationToken authentication) {
        String ret = "you have role google";
        return ret;
    }

    private OAuth2AuthorizedClient getAuthorizedClient(OAuth2AuthenticationToken authentication) {
        return this.authorizedClientService.loadAuthorizedClient(
                authentication.getAuthorizedClientRegistrationId(), authentication.getName());
    }
}