package com.softpedia.DemoShoppingCart.services.image;

import com.softpedia.DemoShoppingCart.dto.ImageDto;
import com.softpedia.DemoShoppingCart.dto.ProductDto;
import com.softpedia.DemoShoppingCart.models.Image;
import com.softpedia.DemoShoppingCart.models.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    Image findById(Long id);
    Image saveImage(MultipartFile file, Long productId);
    void updateImageById(MultipartFile multipartFile,Long imageId);
    void deleteImage(Long id);
}
