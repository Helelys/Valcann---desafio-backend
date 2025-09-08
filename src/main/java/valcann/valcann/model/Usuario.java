package valcann.valcann.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class Usuario {

    private Long id;
    private String name;
    private String email;
    private String role;
    @JsonProperty("is_active")
    private Boolean isActive;
    @JsonProperty("created_at")
    private Date created_at;
}
