package ua.com.agileegine.imagesearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PagePicture {
    private String id;
    @JsonProperty("cropped_picture")
    private String croppedPicture;
}
