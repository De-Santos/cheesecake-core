package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PhotoDto {
    private String photoName;
    private Byte file;
}
