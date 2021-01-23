package ua.com.agileegine.imagesearch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.agileegine.imagesearch.entity.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, String> {

}
