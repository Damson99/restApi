package pl.strefakursow.restapi.document.api.dto;

import java.util.Objects;

public class AddDocumentDto {
	private String title;

	@Override
	public String toString() {
		return "AddDocumentDto{" + "title='" + title + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		AddDocumentDto that = (AddDocumentDto) o;
		return Objects.equals(title, that.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
