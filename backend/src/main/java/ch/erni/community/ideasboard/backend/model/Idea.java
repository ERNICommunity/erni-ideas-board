package ch.erni.community.ideasboard.backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Idea {

    @Id
    private String id;

    @Min(5)
    @Max(160)
    private String name;

    private String description;

    private String author;

    private List<String> tags;

    @CreatedDate
    private Date createdDate;

    private Status status = Status.DRAFT;
}
