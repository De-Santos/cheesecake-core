package dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class PhotoDto {
    private String photoName;
    private Byte file;
}
