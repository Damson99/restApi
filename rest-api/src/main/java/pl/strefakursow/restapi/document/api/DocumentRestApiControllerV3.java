package pl.strefakursow.restapi.document.api;

import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.hateoas.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.strefakursow.restapi.document.Document;
import pl.strefakursow.restapi.document.api.dto.AddDocumentDto;
import pl.strefakursow.restapi.document.api.dto.DocumentDto;
import pl.strefakursow.restapi.document.api.dto.DtoMapper;
import pl.strefakursow.restapi.document.service.DocumentAppService;

import java.net.URI;

import static org.springframework.hateoas.Link.REL_SELF;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api/documents", produces = {ApiVersion.V3_HAL_JSON})
public class DocumentRestApiControllerV3
{
	private DocumentAppService documentService;
	private DtoMapper dtoMapper;

	public DocumentRestApiControllerV3(DocumentAppService documentService, DtoMapper dtoMapper)
    {
		this.documentService = documentService;
		this.dtoMapper = dtoMapper;
	}

	@GetMapping(params = "query")
	public ResponseEntity<Resource<DocumentDto>> findDocumentByPredicate(@QuerydslPredicate(root = Document.class) Predicate predicate)
    {
        return documentService.findDocumentByPredicate(predicate).map(this::toDto).map(this::resourceFromDto).map(this::ok)
                .orElse(ResponseEntity.notFound().build());
    }

	@GetMapping(value = "/{number}")
	public ResponseEntity<Resource<DocumentDto>> getDocument(@PathVariable long number)
	{
		return documentService.getDocument(number).map(this::toDto)
			.map(this::resourceFromDto).map(this::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> addDocument(@RequestBody AddDocumentDto command)
	{
		Document document = fromDto(command);
		DocumentDto addedDocument = toDto(documentService.addDocument(document));
		return ResponseEntity.created(URI.create(resourceFromDto(addedDocument).getLink(REL_SELF).getHref())).build();
	}


	private <T> ResponseEntity<T> ok(T body)
	{
		return ResponseEntity.ok().body(body);
	}

	private Resource<DocumentDto> resourceFromDto(DocumentDto document)
    {
	    Resource<DocumentDto> documentResource = new Resource<>(document);
		documentResource.add(linkTo(methodOn(DocumentRestApiControllerV3.class).getDocument(document.getId())).withSelfRel());
		return documentResource;
	}

	private Document fromDto(AddDocumentDto command) {
		return dtoMapper.fromDto(command);
	}

	private DocumentDto toDto(Document document) {
		return dtoMapper.toDto(document);
	}
}
