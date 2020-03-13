package kit.personal.ssomanagement.controller;

import kit.personal.ssoentity.entity.App;
import kit.personal.ssoentity.repo.AppRepository;
import kit.personal.ssomanagement.controller.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/api")
public class AppApiController {
	@Autowired
	AppRepository appRepository;
	private static Logger LOG = LoggerFactory.getLogger(AppApiController.class);

	@GetMapping( value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Page<App> getAppList(
			@RequestParam(value = "pageNumber", required = false, defaultValue = "0") String page,
			@RequestParam(value = "pageSize", required = false, defaultValue = "10") String limit
	){
		int pageNum = Integer.valueOf(page);
		int limitNum = Integer.valueOf(limit);
		Sort sort = Sort.by(Sort.Direction.DESC, "clientId");

		Page<App> roleList = appRepository.findAllBy(PageRequest.of(pageNum, limitNum, sort));
		return roleList;
	}

	@GetMapping( value = "/app/{clientId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public App getApp(
			@PathVariable(value = "clientId") String clientId
	){
		App app = appRepository.findById(clientId).orElseThrow(()-> new ResourceNotFoundException("client id not found"));
		return app;
	}

	@PostMapping( value = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public App createAppList(
			@RequestBody App app
	){
		//TODO need to skip password if if is not updated
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		app.setClientSecret(encoder.encode(app.getClientSecret()));
		appRepository.save(app);
		return app;
	}
}
