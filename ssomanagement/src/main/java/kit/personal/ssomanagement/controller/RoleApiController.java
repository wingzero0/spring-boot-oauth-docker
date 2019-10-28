package kit.personal.ssomanagement.controller;

import kit.personal.ssomanagement.entity.AppUserRole;
import kit.personal.ssomanagement.repo.AppUserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.*;

@Controller
@RequestMapping(value = "/api/role")
public class RoleApiController {
	@Autowired
	AppUserRoleRepository roleRepository;

	@RequestMapping( value = "/", method = {RequestMethod.POST, RequestMethod.PUT}, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AppUserRole createOrUpdate(
			@ModelAttribute AppUserRole appUserRole
	){
		Date now = new Date();
		String modifier = "test";
		appUserRole.setLastModifiedDate(now).setLastModifiedBy(modifier);

		roleRepository.save(appUserRole);
		return appUserRole;
	}

	@GetMapping( value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public AppUserRole read(
			@RequestParam(value = "id") BigInteger id
	){
		Optional<AppUserRole> optional = roleRepository.findById(id);
		return optional.orElse(null);
	}

	@DeleteMapping( value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Map<String, Boolean> delete(
			@RequestParam(value = "id") BigInteger id
	){
		roleRepository.deleteById(id);

		Map<String, Boolean> ret = new HashMap<>();
		ret.put("ret", true);
		return ret;
	}
}
