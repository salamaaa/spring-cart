package com.softpedia.DemoShoppingCart.services.image;

import com.softpedia.DemoShoppingCart.dto.ImageDto;
import com.softpedia.DemoShoppingCart.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface ImageService {
    Image findById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImageById(MultipartFile multipartFile,Long imageId);
    void deleteImage(Long id);
}
