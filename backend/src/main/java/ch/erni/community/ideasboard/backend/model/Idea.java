package ch.erni.community.ideasboard.backend.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;

/**
 * @author rap
 */
@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Idea {

	@Id
	private String id;

	@Min(5) @Max(160)
	private String name;

	private String description;

	private String author;

	private List<String> tags;

	@CreatedDate
	private Date createdDate;

	private Status status = Status.DRAFT;
}
