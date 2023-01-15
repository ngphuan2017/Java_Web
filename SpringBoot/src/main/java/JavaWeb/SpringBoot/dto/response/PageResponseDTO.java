package JavaWeb.SpringBoot.dto.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class PageResponseDTO implements Serializable {
    private List<?> data;
    private Integer page;
    private Integer size;
    private String sort;
    private long totalRecord;

    private int totalPages;
}
