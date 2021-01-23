package ua.com.agileegine.imagesearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Page {

    private List<PagePicture> pictures;
    @JsonProperty("page")
    private int pageNumber;
    private int pageCount;
    private boolean hasMore;
}
