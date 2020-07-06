package pl.strefakursow.restapi.maturity.resources;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.strefakursow.restapi.maturity.Document;
import pl.strefakursow.restapi.maturity.swamp.Command;
import pl.strefakursow.restapi.maturity.util.DataFixtureUtils;

import java.util.List;

@RestController("documentServiceL1")
@RequestMapping("/api/maturity/l1/documents")
public class DocumentService {
	private List<Document> documents =
		DataFixtureUtils.initDocuments();

	@PostMapping
	public ResponseEntity<List<Document>> handle(@RequestBody Command command) {
		if ("get".equals(command.getAction())) {
			return ResponseEntity.ok(documents);
		}
		else if ("add".equals(command.getAction())) {
			documents.add(command.getDocumentToAdd());
			return ResponseEntity.ok().build();
		}
		else if ("delete".equals(command.getAction())) {
			documents.removeIf(document -> document
				.getNumber() == command.getNumberToRemove());
			return ResponseEntity.ok().build();
		}
		else {
			throw new IllegalArgumentException("Unknown action");
		}
	}
}
