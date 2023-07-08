package zw.co.kez.movies.dtos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import zw.co.kez.movies.enums.MovieStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    @NotBlank(message = "The name cannot be empty")
    private String name;
    @NotBlank(message = "Movie description is missing")
    private String description;
    private String director;
    private String publisher;
    @NotBlank(message = "Date published cannot be empty")
    private String datePublished;
    @Enumerated(EnumType.STRING)
    private MovieStatus movieStatus;
    //@NotBlank(message = "Category cannot be empty")
    private CategoryDTO category;
}
