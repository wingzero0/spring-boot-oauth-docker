package kit.personal.ssoserver;

import com.fasterxml.jackson.annotation.JsonView;
import kit.personal.ssoserver.entity.ActingRole;
import kit.personal.ssoserver.entity.AppUser;
import kit.personal.ssoserver.entity.AppUserRole;
import kit.personal.ssoserver.entity.EntityJsonView;
import kit.personal.ssoserver.repo.ActingRoleRepository;
import kit.personal.ssoserver.repo.AppUserRepository;
import kit.personal.ssoserver.repo.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.*;

@Controller
public class AppApiController {
    @Autowired
    AppUserRoleRepository roleRepository;
    @Autowired
    ActingRoleRepository actingRoleRepository;
    @Autowired
    AppUserRepository appUserRepository;


    @GetMapping("/app/fullUserList")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Iterable<AppUserRole> offlineRole(Principal principal) {
        Iterable<AppUserRole> roleList = roleRepository.findAllByAppId(principal.getName());
        return roleList;
    }

    @GetMapping("/app/fullUserListActing")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Iterable<ActingRole> offlineActingRole(Principal principal) {
        Date today = new Date();
        Iterable<ActingRole> roleList = actingRoleRepository.findAllByAppIdAndDate(
                principal.getName(),
                today
        );
        return roleList;
    }

    @GetMapping("/app/fullUserListPaging")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Page<AppUserRole> offlineRolePaging(Principal principal,
                                               @RequestParam(value = "page", required = false, defaultValue = "0") String page,
                                               @RequestParam(value = "limit", required = false, defaultValue = "10") String limit
    ) {
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Sort sort = new Sort(Sort.Direction.DESC, "username");

        Page<AppUserRole> roleList = roleRepository.findAllByAppId(principal.getName(), PageRequest.of(pageNum, limitNum, sort));
        return roleList;
    }

    @PostMapping("/app/addUserRole")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Map<String, Boolean> offlineAddRole(
            Principal principal,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "appRole") String appRole
    ) {
        String appName = principal.getName();
        long recordCount = roleRepository.countByAppIdAndUsernameAndAppRole(appName, username, appRole);
        HashMap<String, Boolean> ret = new HashMap<>();
        if (recordCount <= 0.0){
            AppUserRole role = new AppUserRole();
            role.setAppId(appName)
                    .setAppRole(appRole)
                    .setUsername(username);
            roleRepository.save(role);
            ret.put("ret", true);
        } else {
            ret.put("ret", false);
        }
        return ret;
    }

    @PostMapping("/app/deleteUserRole")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    @Transactional
    public Map<String, Boolean> deleteUserRole(
            Principal principal,
            @RequestParam(value = "username") String username,
            @RequestParam(value = "appRole") String appRole
    ) {
        String appName = principal.getName();
        long recordCount = roleRepository.deleteByAppIdAndUsernameAndAppRole(appName, username, appRole);
        HashMap<String, Boolean> ret = new HashMap<>();
        if (recordCount > 0.0){
            ret.put("ret", true);
        } else {
            ret.put("ret", false);
        }
        return ret;
    }


    @GetMapping("/app/usersWithRole/{appRole}")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    @JsonView(EntityJsonView.PUBLIC_VIEW.class)
    public Iterable<AppUser> offlineUsersOfRole(Principal principal, @PathVariable String appRole) {
        List<AppUserRole> roleList = roleRepository.findAllByAppIdAndAppRole(principal.getName(), appRole);
        Set<String> usernames = new HashSet<>();
        for(AppUserRole role:roleList){
            usernames.add(role.getUsername());
        }
        List<ActingRole> actingRoles = actingRoleRepository.findAllByAppIdAndAppRoleAndDate(principal.getName(), appRole, new Date());
        for (ActingRole actingRole:actingRoles){
            usernames.add(actingRole.getPk().getUsername());
        }
        List<AppUser> gpsList = this.appUserRepository.findAllByUsernameIn(usernames);
        return gpsList;
    }

    @GetMapping("/app/usersEmail")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    @JsonView(EntityJsonView.PUBLIC_VIEW.class)
    public List<AppUser> offlineUsersEmail(Principal principal,
            @RequestParam(value = "username[]") String[] usernameArray
    ) {
        List<String> usernameList = Arrays.asList(usernameArray);
        List<AppUserRole> roleList = this.roleRepository.findAllByAppIdAndUsernameIn(principal.getName(), usernameList);
        Set<String> usernameFiltered = new HashSet<>();
        for(AppUserRole role: roleList){
            usernameFiltered.add(role.getUsername());
        }

        List<ActingRole> actingRoleList = this.actingRoleRepository.findAllByAppIdAndPkUsernameInAndDate(principal.getName(), usernameList, new Date());
        for(ActingRole actingRole: actingRoleList){
            usernameFiltered.add(actingRole.getPk().getUsername());
        }
        List<AppUser> appUserList = this.appUserRepository.findAllByUsernameIn(usernameFiltered);

        return appUserList;
    }

    @GetMapping("/app/allUsersEmail")
    @PreAuthorize("#oauth2.hasScope('internal_trust_app')")
    @ResponseBody
    @JsonView(EntityJsonView.PUBLIC_VIEW.class)
    public List<AppUser> offlineAllUsersEmail(Principal principal,
                                              @RequestParam(value = "username[]") String[] usernameList
    ) {
        List<String> funcNos = Arrays.asList(usernameList);
        List<AppUser> appUserList = this.appUserRepository.findAllByUsernameIn(funcNos);
        return appUserList;
    }
}
