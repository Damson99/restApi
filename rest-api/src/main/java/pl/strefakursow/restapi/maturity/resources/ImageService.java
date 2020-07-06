package pl.strefakursow.restapi.maturity.resources;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maturity/images")
public class ImageService {
	@PostMapping
	public ResponseEntity<Resource> getImage() {
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
			.body(new ClassPathResource("/images/image" + ".png",
				getClass()));
	}
}
