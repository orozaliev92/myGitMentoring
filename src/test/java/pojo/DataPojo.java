package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPojo {

    private int id;
    private String email;
    private String first_name;

    private String last_name;

    private String avatar;
}
