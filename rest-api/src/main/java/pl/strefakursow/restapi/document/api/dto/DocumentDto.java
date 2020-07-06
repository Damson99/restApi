package pl.strefakursow.restapi.document.api.dto;

import java.util.Objects;

public class DocumentDto {
	private String title;
	private long id;

	@Override
	public String toString() {
		return "DocumentDto{" + "title='" + title + '\'' + ", id=" + id + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		DocumentDto that = (DocumentDto) o;
		return id == that.id && Objects.equals(title, that.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, id);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
}
