package kit.personal.ssoserver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import kit.personal.ssoserver.entity.AppUser;
import kit.personal.ssoserver.repo.AppUserRepository;
import kit.personal.ssoserver.repo.RoleRepository;
import kit.personal.ssoserver.repo.SubstituicaoRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private AppUserRepository userRepository;
    private RoleRepository roleRepository;
    private SubstituicaoRoleRepository substituicaoRoleRepository;

    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String staffNo) throws UsernameNotFoundException {
			Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			return new User("john", encoder.encode("123"), grantedAuthorities);
		}
/*
    @Override
    @Transactional(readOnly = true)
    public User loadUserByUsername(String staffNo) throws UsernameNotFoundException {
        GPS gps = userRepository.findOneByFuncNo(Integer.valueOf(staffNo));

        if (gps == null){
            throw new UsernameNotFoundException("Staff no not found");
        }

        List<Role> roleList = roleRepository.findAllByFuncNo(Integer.valueOf(staffNo));
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
//        for (Role role : roleList){
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getApp() + "_" + role.getRole()));
//        }
//
//        List<SubstituicaoRole> extendRoleList = substituicaoRoleRepository.findAllByPkFuncNo(Integer.valueOf(staffNo));
//        for (SubstituicaoRole role : extendRoleList){
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getApp() + "_" + role.getRole()));
//        }
//
//        if (grantedAuthorities.size() == 0){
//            throw new UsernameNotFoundException("Role not found");
//        }
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        String password = new String(gps.getPasswd());
        gps.setPasswd(null);
        return new GPSUser(gps.getFuncNo().toString(), password, grantedAuthorities, gps);
    }
*/
    @Autowired
    public void setUserRepository(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public UserDetailsServiceImpl setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
        return this;
    }

    @Autowired
    public UserDetailsServiceImpl setSubstituicaoRoleRepository(SubstituicaoRoleRepository substituicaoRoleRepository) {
        this.substituicaoRoleRepository = substituicaoRoleRepository;
        return this;
    }
}
