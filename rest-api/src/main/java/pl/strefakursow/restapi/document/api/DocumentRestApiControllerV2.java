package pl.strefakursow.restapi.document.api;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.strefakursow.restapi.document.Document;
import pl.strefakursow.restapi.document.service.DocumentAppService;

import java.net.URI;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/documents", produces = {ApiVersion.V2_HAL_JSON,
	MediaType.ALL_VALUE})
public class DocumentRestApiControllerV2 {
	private static final String REL_SELF = "self";
	private static final String REL_DOCS_BY_TITLE = "docsByTitle";
	private static final String REL_ALL_DOCS = "allDocs";
	private DocumentAppService documentService;

	public DocumentRestApiControllerV2(DocumentAppService documentService) {
		this.documentService = documentService;
	}

	@GetMapping(params = "title")
	public Resources<Resource<Document>> findDocumentsByTitle(
		@RequestParam("title") String title) {
		Resources<Resource<Document>> resources = new Resources<>(
			documentService.findDocumentsByTitle(title)
				.map(this::resource)
				.collect(Collectors.toList()));
		addFindDocsLink(resources, REL_SELF, title);
		addDocsLink(resources, REL_ALL_DOCS);
		return resources;
	}

	@DeleteMapping("/{id}")
	public void deleteDocument(@PathVariable("id") long number) {
		documentService.deleteDocument(number);
	}

	@PatchMapping("/{number}")
	public void updateDocument(@PathVariable long number,
				   @RequestBody Document newPartialDocument) {
		documentService.updateDocument(number, newPartialDocument);
	}

	@PutMapping("/{number}")
	public void replaceDocument(@PathVariable long number,
				    @RequestBody Document newDocument) {
		documentService.replaceDocument(number, newDocument);
	}

	@GetMapping
	public Resources<Resource<Document>> getDocuments() {
		Resources<Resource<Document>> resources = new Resources<>(
			documentService.getDocuments().map(this::resource)
				.collect(Collectors.toList()));
		addDocsLink(resources, REL_SELF);
		addFindDocsLink(resources, REL_DOCS_BY_TITLE, null);
		return resources;
	}

	@GetMapping(value = "/{number}/title", produces =
		ApiVersion.V2_TEXT_PLAIN)
	public ResponseEntity<String> getTitleOfDocument(
		@PathVariable long number) {
		return documentService.getTitleOfDocument(number).map(this::ok)
			.orElse(notFound());
	}

	@GetMapping("/{number}")
	public ResponseEntity<Resource<Document>> getDocument(
		@PathVariable long number) {
		return documentService.getDocument(number).map(this::resource)
			.map(this::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> addDocument(@RequestBody Document document) {
		Document addedDocument = documentService.addDocument(document);
		return ResponseEntity.created(URI.create(
			resource(addedDocument).getLink(REL_SELF).getHref()))
			.build();
	}

	@PostMapping(value = "/{documentNumber}/tags", consumes =
		MediaType.TEXT_PLAIN_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void addTag(@PathVariable long documentNumber,
			   @RequestBody String tag) {
		documentService.addTag(documentNumber, tag);
	}

	private ResponseEntity<String> notFound() {
		return ResponseEntity.notFound().build();
	}

	private <T> ResponseEntity<T> ok(T body) {
		return ResponseEntity.ok().body(body);
	}

	private Resource<Document> resource(Document document) {
		Resource<Document> documentResource = new Resource<>(document);
		documentResource.add(linkTo(
			methodOn(DocumentRestApiControllerV2.class)
				.getDocument(document.getNumber()))
			.withSelfRel());
		return documentResource;
	}

	private void addFindDocsLink(Resources<Resource<Document>> resources,
				     String rel, String title) {
		resources.add(linkTo(methodOn(DocumentRestApiControllerV2.class)
			.findDocumentsByTitle(title)).withRel(rel));
	}

	private void addDocsLink(Resources<Resource<Document>> resources,
				 String rel) {
		resources.add(linkTo(DocumentRestApiControllerV2.class)
			.withRel(rel));
	}
}
