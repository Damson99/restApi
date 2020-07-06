package pl.strefakursow.restapi.maturity.swamp;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strefakursow.restapi.maturity.Document;
import pl.strefakursow.restapi.maturity.util.DataFixtureUtils;

import java.util.List;

@RestController
@RequestMapping("/api/maturity/l0/service")
public class AppService {
	private List<Document> documents = initDocuments();

	@PostMapping
	public ResponseEntity<Object> handleCommand(@RequestBody Command command) {
		if ("getAllDocuments".equals(command.getAction())) {
			return ResponseEntity.ok(documents);
		}
		else if ("addDocument".equals(command.getAction())) {
			documents.add(command.getDocumentToAdd());
			return ResponseEntity.ok().build();
		}
		else if ("deleteDocument".equals(command.getAction())) {
			documents.removeIf(document -> document
				.getNumber() == command.getNumberToRemove());
			return ResponseEntity.ok().build();
		}
		else if ("getAllProfiles".equals(command.getAction())) {
			return ResponseEntity.ok(allProfiles());
		}
		else if ("downloadFile".equals(command.getAction())) {
			return ResponseEntity.ok()
				.contentType(MediaType.IMAGE_PNG)
				.body(new ClassPathResource("/images/image" +
					".png", getClass()));
		}
		else if ("managePermissions".equals(command.getAction())) {
			// TODO: change permissions
			return ResponseEntity.ok().build();
		}
		else {
			throw new IllegalArgumentException("Unknown action");
		}
	}

	private List<Profile> allProfiles() {
		return DataFixtureUtils.allProfiles();
	}

	private List<Document> initDocuments() {
		return DataFixtureUtils.initDocuments();
	}
}
