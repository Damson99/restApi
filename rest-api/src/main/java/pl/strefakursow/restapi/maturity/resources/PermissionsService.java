package pl.strefakursow.restapi.maturity.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maturity/permissions")
public class PermissionsService {
	@PostMapping
	public void managePermissions() {
	}
}
