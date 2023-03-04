package ua.cheesecake.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.Collection;

@Data
@Builder
@Jacksonized
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String versionId;
    private String activeVersionId;
    private String name;
    private String description;
    private Collection<String> imagesId;
    private String descriptionImageId;
    private Integer price;
    private String createDate;
    private boolean active;
}
