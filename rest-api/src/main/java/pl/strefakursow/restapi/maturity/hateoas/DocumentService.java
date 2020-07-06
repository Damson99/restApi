package pl.strefakursow.restapi.maturity.hateoas;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.strefakursow.restapi.maturity.Document;
import pl.strefakursow.restapi.maturity.util.DataFixtureUtils;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController("documentServiceL3")
@RequestMapping(value = "/api/maturity/l3/documents", produces = "application"
	+ "/vnd.strefakursow.v2+hal+json")
public class DocumentService {
	private List<Document> documents = DataFixtureUtils.initDocuments();

	@GetMapping(produces = "application/vnd.strefakursow.v1+json")
	public List<Document> getAllDocumentsWithoutLinks() {
		return documents;
	}

	@GetMapping
	public Resources<Resource<Document>> getAllDocuments()
	{
		Resources<Resource<Document>> resources = new Resources<>(documents.stream().map(this::mapToResource).collect(Collectors.toList()));
		addDocsLink(resources, "self");
		addFindDocsLink(resources, "docsByTitleAndNumber", null, null);
		return resources;
	}

	@GetMapping("/{number}")
	public Resource<Document> getDocument(
		@PathVariable("number") long number) {
		return documents.stream()
			.filter(document -> number == document.getNumber())
			.findAny().map(this::mapToResource).orElse(null);
	}

	@GetMapping(params = {"title", "number"})
	public ResponseEntity<Resources<Resource<Document>>> getDocumentsByTitleAndNumber(@RequestParam("title") String title, @RequestParam("number") Long number)
	{
		Resources<Resource<Document>> resources = new Resources<>(documents.stream().filter(document ->
				title.equals(document.getTitle()) && number == document.getNumber())
				.map(this::mapToResource).collect(Collectors.toList()));

		addDocsLink(resources, "allDocs");
		addFindDocsLink(resources, "self", title, number);

		return ResponseEntity.ok().header("Cache-Control", "max-age" + "=3600").body(resources);
	}

	@GetMapping(produces = "text/vnd.strefakursow.v1+plain")
	public String getAllTitles() {
		return documents.stream().map(Document::getTitle)
			.reduce((acc, curr) -> String.join(",", acc, curr))
			.orElse("");
	}

	@PostMapping
	public ResponseEntity<?> addDocumentV2(@RequestBody Document document) {
		documents.add(document);
		return ResponseEntity.created(URI.create(
			mapToResource(document).getLink("self").getHref()))
			.build();
	}

	@PostMapping(produces = "application/vnd.strefakursow.v1+json")
	@ResponseStatus(HttpStatus.CREATED)
	public void addDocument(@RequestBody Document document) {
		documents.add(document);
	}

	@DeleteMapping("/{number}")
	public ResponseEntity<?> removeDocument(@PathVariable("number") long number)
	{
		boolean anyElementRemoved = documents.removeIf(document -> document.getNumber() == number);
		if (anyElementRemoved) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

	private Resource<Document> mapToResource(Document document) {
		Resource<Document> documentResource = new Resource<>(document);
		documentResource.add(linkTo(methodOn(DocumentService.class)
			.getDocument(document.getNumber())).withSelfRel());
		return documentResource;
	}

	private void addFindDocsLink(Resources<Resource<Document>> resources,
				     String rel, String title, Long number) {
		resources.add(linkTo(methodOn(DocumentService.class)
			.getDocumentsByTitleAndNumber(title, number))
			.withRel(rel));
	}

	private void addDocsLink(Resources<Resource<Document>> resources,
				 String rel) {
		resources.add(linkTo(DocumentService.class).withRel(rel));
	}

}
