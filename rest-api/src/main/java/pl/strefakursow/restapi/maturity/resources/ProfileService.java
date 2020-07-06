package pl.strefakursow.restapi.maturity.resources;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strefakursow.restapi.maturity.swamp.Profile;
import pl.strefakursow.restapi.maturity.util.DataFixtureUtils;

import java.util.List;

@RestController("/api/maturity/profiles")
public class ProfileService {

	@PostMapping
	public List<Profile> getAllProfiles() {
		return DataFixtureUtils.allProfiles();
	}

}
