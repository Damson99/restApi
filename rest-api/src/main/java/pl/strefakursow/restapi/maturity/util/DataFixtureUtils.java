package pl.strefakursow.restapi.maturity.util;

import pl.strefakursow.restapi.maturity.Document;
import pl.strefakursow.restapi.maturity.swamp.Profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataFixtureUtils {
	private static Document documentWithTitle(String documentTitle) {
		Document document = new Document(documentTitle);
		document.setNumber(new Random().nextLong());
		document.setTags(List.of("tag0", "tag1", "tag2"));
		return document;
	}

	public static List<Document> initDocuments() {
		ArrayList<Document> documents = new ArrayList<>();
		documents.add(documentWithTitle("doc0"));
		documents.add(documentWithTitle("doc1"));
		documents.add(documentWithTitle("doc2"));
		return documents;
	}

	public static List<Profile> allProfiles() {
		return List
			.of(new Profile("goobar"), new Profile("foobar"),
				new Profile("hoobar"));
	}
}
