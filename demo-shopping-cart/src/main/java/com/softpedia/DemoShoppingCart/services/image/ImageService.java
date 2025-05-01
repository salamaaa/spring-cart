package com.softpedia.DemoShoppingCart.services.image;

import com.softpedia.DemoShoppingCart.models.Image;
import org.springframework.web.multipart.MultipartFile;



public interface ImageService {
    Image findById(Long id);
    Image saveImage(MultipartFile file, Long productId);
    void updateImageById(MultipartFile multipartFile,Long imageId);
    void deleteImage(Long id);
}
