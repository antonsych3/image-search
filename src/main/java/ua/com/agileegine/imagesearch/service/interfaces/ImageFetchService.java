package ua.com.agileegine.imagesearch.service.interfaces;

import ua.com.agileegine.imagesearch.entity.Page;
import ua.com.agileegine.imagesearch.entity.Picture;

public interface ImageFetchService {

    Page getPageInformation(int page);

    Picture getPictureInformation(String id);

}
