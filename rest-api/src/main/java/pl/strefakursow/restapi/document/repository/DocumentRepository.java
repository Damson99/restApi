package pl.strefakursow.restapi.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pl.strefakursow.restapi.document.Document;

import java.util.Collection;

public interface DocumentRepository extends JpaRepository<Document, Long>, QuerydslPredicateExecutor<Document> {
	Collection<Document> findByTitle(String title);
}
