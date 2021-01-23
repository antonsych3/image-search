package ua.com.agileegine.imagesearch.service;

import org.springframework.stereotype.Service;
import ua.com.agileegine.imagesearch.entity.Picture;
import ua.com.agileegine.imagesearch.repository.PictureRepository;
import ua.com.agileegine.imagesearch.service.interfaces.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;

    public PictureServiceImpl(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Override
    public Picture savePicture(Picture picture) {
        return pictureRepository.save(picture);
    }
}
