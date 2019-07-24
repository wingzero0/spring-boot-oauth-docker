package kit.personal.ssoserver.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

//@FrameworkEndpoint
@Controller
@SessionAttributes("authorizationRequest")
public class AuthorizeController {
    private static Logger LOG = LoggerFactory.getLogger(AuthorizeController.class);

    @RequestMapping("/oauth/confirm_access")
    public String getAccessConfirmation(Map<String, Object> model, HttpServletRequest request)
            throws Exception {
        LOG.debug("dumping confirm_access model key:value");
        model.forEach((key, value) -> LOG.debug(key + ":" + value.toString()));

        LOG.debug("dumping confirm_access HttpServletRequest ParameterMap key:value");
        Map<String,String[]> requestMap = request.getParameterMap();
        requestMap.forEach((key, value) -> LOG.debug(key + ":" + value.toString()));

        LOG.debug("dumping confirm_access HttpServletRequest AttributeNames key:value");
        Enumeration<String> requestAttributeNames =  request.getAttributeNames();
        while(requestAttributeNames.hasMoreElements()){
            String attrName = requestAttributeNames.nextElement();
            LOG.debug(attrName + ":" + request.getAttribute(attrName).toString());
        }

        AuthorizationRequest authorizationRequest = (AuthorizationRequest) model.get("authorizationRequest");
        String clientId = authorizationRequest.getClientId();
        model.put("clientId", clientId);

        @SuppressWarnings("unchecked")
        Map<String, Object> scopes = (Map<String, Object>) (model.containsKey("scopes") ?
                model.get("scopes") : request.getAttribute("scopes"));

        LOG.debug("dumping scopes key:value");
        scopes.forEach((key, value) -> LOG.debug(key + ":" + value.toString()));
        model.put("scopesRefine", scopes);
        return "approval";
    }
}
