package ua.com.agileegine.imagesearch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@AllArgsConstructor
@Data
public class AuthRequestDto implements Serializable {

    String apiKey;
}
