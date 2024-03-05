package aptech.dating.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ImageData")
@Data
@NoArgsConstructor
@Builder
public class ImageData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String type;
	@Lob
	@Column(name = "imagedata", length = 1000)
	private byte[] imageData;

	public static ImageDataBuilder builder() {
		return new ImageDataBuilder();
	}

	public ImageData(Long id, String name, String type, byte[] imageData) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.imageData = imageData;
	}

	public ImageData(String name, String type, byte[] imageData) {
		super();
		this.name = name;
		this.type = type;
		this.imageData = imageData;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	public static class ImageDataBuilder {

		private String name;
		private String type;
		private byte[] imageData;

		private ImageDataBuilder() {
		}

		public ImageDataBuilder name(String name) {
			this.name = name;
			return this;
		}

		public ImageDataBuilder type(String type) {
			this.type = type;
			return this;
		}

		public ImageDataBuilder imageData(byte[] imageData) {
			this.imageData = imageData;
			return this;
		}

		public ImageData build() {
			return new ImageData(name, type, imageData);
		}

	}
}
