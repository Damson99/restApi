package pl.strefakursow.restapi.maturity;

import java.util.List;

public class Document {
	private long number;
	private String title;
	private List<String> tags;

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public Document(String title) {
		this.title = title;
	}

	public Document() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
