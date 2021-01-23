package ua.com.agileegine.imagesearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.Scheduled;
import ua.com.agileegine.imagesearch.entity.Page;
import ua.com.agileegine.imagesearch.entity.PagePicture;
import ua.com.agileegine.imagesearch.entity.Picture;
import ua.com.agileegine.imagesearch.service.interfaces.ImageFetchService;
import ua.com.agileegine.imagesearch.service.interfaces.PictureService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class ImageSearchApplication {

    private final ImageFetchService imageFetchService;
    private final PictureService pictureService;

    public ImageSearchApplication(ImageFetchService imageFetchService, PictureService pictureService) {
        this.imageFetchService = imageFetchService;
        this.pictureService = pictureService;
    }

    @PostConstruct
    @Scheduled(fixedRateString = "${interview.agileengine.fetch-data.fixed-rate}")
    public void fetchData() {
        fetchAndSaveData();
    }

    public static void main(String[] args) {
        SpringApplication.run(ImageSearchApplication.class, args);
    }

    private void fetchAndSaveData() {
        Page startPage = imageFetchService.getPageInformation(1);
        for (int i = 1; i <= startPage.getPageCount(); i++) {
            Page page = imageFetchService.getPageInformation(i);
        }
    }

}
