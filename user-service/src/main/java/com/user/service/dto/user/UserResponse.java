package com.user.service.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class UserResponse {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("second_name")
    private String secondName;

    @JsonProperty("blocked")
    private Boolean blocked;

    @JsonProperty("deleted")
    private Boolean deleted;
}
