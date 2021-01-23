package ua.com.agileegine.imagesearch.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pictures")
public class Picture {

    @Id
    private String id;

    private String author;
    private String camera;
    private String tags;

    @JsonProperty("cropped_picture")
    @Column(name = "cropped_picture", length = 200)
    private String croppedPicture;

    @JsonProperty("full_picture")
    @Column(name = "full_picture", length = 200)
    private String fullPicture;
}
