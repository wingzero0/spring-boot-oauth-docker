package kit.personal.ssoserver;

import kit.personal.ssoserver.entity.AppUser;
import kit.personal.ssoserver.entity.AppUserRole;
import kit.personal.ssoserver.entity.GPSEmail;
import kit.personal.ssoserver.repo.AppUserRepository;
import kit.personal.ssoserver.repo.AppUserRoleRepository;
import kit.personal.ssoserver.repo.SubstituicaoRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
public class AppApiController {
    @Autowired
    AppUserRoleRepository roleRepository;
    @Autowired
    SubstituicaoRoleRepository substituicaoRoleRepository;
    @Autowired
    AppUserRepository appUserRepository;


    @GetMapping("/app/fullUserList")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Iterable<AppUserRole> offlineRole(Principal principal) {
        Iterable<AppUserRole> roleList = roleRepository.findAllByAppId(principal.getName());
        // TODO search user by substituicaoRoleRepository too;
        // TODO add a new return type which combine role and substituicao role
        return roleList;
    }
    /*
    @GetMapping("/app/fullUserListPaging")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Page<AppUserRole> offlineRolePaging(Principal principal,
                                               @RequestParam(value = "page", required = false, defaultValue = "0") String page,
                                               @RequestParam(value = "limit", required = false, defaultValue = "10") String limit
    ) {
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Sort sort = new Sort(Sort.Direction.DESC, "funcNo");

        Page<AppUserRole> roleList = roleRepository.findAllByApp(principal.getName(), PageRequest.of(pageNum, limitNum, sort));
        return roleList;
    }

    @GetMapping("/app/addUserRole")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Map<String, Object> offlineRole(
            Principal principal,
            @RequestParam(value = "funcNo") Integer funcNo,
            @RequestParam(value = "role") String roleName
    ) {
        String appName = principal.getName();
        long recordCount = roleRepository.countByAppAndFuncNoAndRole(appName, funcNo, roleName.toUpperCase());
        HashMap<String, Object> ret = new HashMap<>();
        if (recordCount <= 0.0){
            AppUserRole role = new AppUserRole();
            role.setAppId(appName)
                    .setRole(roleName.toUpperCase())
                    .setUsername(funcNo);
            roleRepository.save(role);
            ret.put("ret", new Long(1));
        } else {
            ret.put("ret", new Long(0));
        }
        return ret;
    }

    @GetMapping("/app/deleteUserRole")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    @Transactional
    public Map<String, Object> deleteUserRole(
            Principal principal,
            @RequestParam(value = "funcNo") Integer funcNo,
            @RequestParam(value = "role") String roleName
    ) {
        String appName = principal.getName();
        long recordCount = roleRepository.deleteByAppAndFuncNoAndRole(appName, funcNo, roleName.toUpperCase());
        HashMap<String, Object> ret = new HashMap<>();
        ret.put("ret", new Long(recordCount));
        return ret;
    }


    @GetMapping("/app/usersWithRole/{roleName}")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Iterable<AppUser> offlineUsersOfRole(Principal principal, @PathVariable String roleName) {
        Iterable<AppUserRole> roleList = roleRepository.findAllByAppAndRole(principal.getName(), roleName);
        Set<Integer> funcNos = new HashSet<>();
        for(AppUserRole role:roleList){
            funcNos.add(role.getUsername());
        }
        Iterable<SubstituicaoRole> substituicaoRoleList = substituicaoRoleRepository.findAllByAppAndRole(principal.getName(), roleName);
        for (SubstituicaoRole substituicaoRole:substituicaoRoleList){
            funcNos.add(substituicaoRole.getPk().getFuncNo());
        }
        List<AppUser> gpsList = this.appUserRepository.findAllByFuncNoIn(funcNos);
        for (AppUser gps: gpsList){
            gps.setPassword(null); // TODO use json serializer instead of set null every time;
        }
        return gpsList;
    }
    */

    @GetMapping("/app/userEmail/{username}")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public GPSEmail offlineUserEmail(@PathVariable String username) {
        AppUser appUser = appUserRepository.findOneByUsername(username);
        if (appUser != null){
            return GPSEmail.extractEmail(appUser);
        }
        return null;
    }
    /*
    @GetMapping("/app/userListEmail/{funcNoList}")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public List<GPSEmail> offlineUserListEmail(Principal principal, @PathVariable String funcNoList) {
        // funcNoList separated by "-"
        List<String> funcNos = Arrays.asList(funcNoList.split("-"));
        List<Integer> funcNosInteger = new ArrayList<>();
        for (String funcNo : funcNos){
            funcNosInteger.add(Integer.valueOf(funcNo));
        }
        List<AppUser> gpsList = this.appUserRepository.findAllByFuncNoIn(funcNosInteger);
        return GPSEmail.extractEmail(gpsList);
    }

    @GetMapping("/app/userFuncNo/{email}")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public String offlineUsername(Principal principal, @PathVariable String email) {
        AppUser gps = this.appUserRepository.findOneByEmail(email);
        if (gps != null){
            return gps.getUsername();
        }
        return null;
    }
    */
}
