package kit.personal.ssoserver;

import kit.personal.ssoserver.entity.GPS;
import kit.personal.ssoserver.entity.GPSEmail;
import kit.personal.ssoserver.entity.Role;
import kit.personal.ssoserver.entity.SubstituicaoRole;
import kit.personal.ssoserver.repo.GPSRepository;
import kit.personal.ssoserver.repo.RoleRepository;
import kit.personal.ssoserver.repo.SubstituicaoRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.*;

@Controller
public class AppApiController {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    SubstituicaoRoleRepository substituicaoRoleRepository;
    @Autowired
    GPSRepository gpsRepository;


    @GetMapping("/app/fullUserList")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Iterable<Role> offlineRole(Principal principal) {
        Iterable<Role> roleList = roleRepository.findAllByApp(principal.getName());
        // TODO search user by substituicaoRoleRepository too;
        // TODO add a new return type which combine role and substituicao role
        return roleList;
    }

    @GetMapping("/app/fullUserListPaging")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Page<Role> offlineRolePaging(Principal principal,
                                            @RequestParam(value = "page", required = false, defaultValue = "0") String page,
                                            @RequestParam(value = "limit", required = false, defaultValue = "10") String limit
    ) {
        int pageNum = Integer.valueOf(page);
        int limitNum = Integer.valueOf(limit);
        Sort sort = new Sort(Sort.Direction.DESC, "funcNo");

        Page<Role> roleList = roleRepository.findAllByApp(principal.getName(), PageRequest.of(pageNum, limitNum, sort));
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
            Role role = new Role();
            role.setApp(appName)
                    .setRole(roleName.toUpperCase())
                    .setFuncNo(funcNo);
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
    public Iterable<GPS> offlineUsersOfRole(Principal principal, @PathVariable String roleName) {
        Iterable<Role> roleList = roleRepository.findAllByAppAndRole(principal.getName(), roleName);
        Set<Integer> funcNos = new HashSet<>();
        for(Role role:roleList){
            funcNos.add(role.getFuncNo());
        }
        Iterable<SubstituicaoRole> substituicaoRoleList = substituicaoRoleRepository.findAllByAppAndRole(principal.getName(), roleName);
        for (SubstituicaoRole substituicaoRole:substituicaoRoleList){
            funcNos.add(substituicaoRole.getPk().getFuncNo());
        }
        List<GPS> gpsList = this.gpsRepository.findAllByFuncNoIn(funcNos);
        for (GPS gps: gpsList){
            gps.setPasswd(null); // TODO use json serializer instead of set null every time;
        }
        return gpsList;
    }

    @GetMapping("/app/userEmail/{funcNo}")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public GPSEmail offlineUserEmail(Principal principal, @PathVariable String funcNo) {
        GPS gps = this.gpsRepository.findOneByFuncNo(Integer.valueOf(funcNo));
        if (gps != null){
            return GPSEmail.extractEmail(gps);
        }
        return null;
    }

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
        List<GPS> gpsList = this.gpsRepository.findAllByFuncNoIn(funcNosInteger);
        return GPSEmail.extractEmail(gpsList);
    }

    @GetMapping("/app/userFuncNo/{email}")
    @PreAuthorize("#oauth2.hasScope('full_user_list')")
    @ResponseBody
    public Integer offlineUserFuncNo(Principal principal, @PathVariable String email) {
        GPS gps = this.gpsRepository.findOneByEmail(email);
        if (gps != null){
            return gps.getFuncNo();
        }
        return null;
    }
}
