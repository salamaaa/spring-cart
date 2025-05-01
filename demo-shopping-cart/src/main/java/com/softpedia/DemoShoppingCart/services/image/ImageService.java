package com.softpedia.DemoShoppingCart.services.image;

import com.softpedia.DemoShoppingCart.dto.ImageDto;
import com.softpedia.DemoShoppingCart.dto.ProductDto;
import com.softpedia.DemoShoppingCart.models.Image;
import com.softpedia.DemoShoppingCart.models.Product;

import java.util.List;

public interface ImageService {
    Image findById(Long id);
    Image addImage(ImageDto imageDto);
    Image CreateImage(ImageDto imageDto, Product product);
    Image updateImageById(ImageDto imageDto,Long id);
    Image updateExistingImage(Image existingImage, ImageDto imageDto);
    void deleteImage(Long id);
    List<Image> getAllImages();
}
