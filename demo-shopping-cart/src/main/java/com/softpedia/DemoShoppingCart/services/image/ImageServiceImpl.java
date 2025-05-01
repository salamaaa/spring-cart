package com.softpedia.DemoShoppingCart.services.image;

import com.softpedia.DemoShoppingCart.dto.ImageDto;
import com.softpedia.DemoShoppingCart.exception.ImageNotFoundException;
import com.softpedia.DemoShoppingCart.exception.ProductNotFoundException;
import com.softpedia.DemoShoppingCart.models.Image;
import com.softpedia.DemoShoppingCart.models.Product;
import com.softpedia.DemoShoppingCart.repos.ImageRepository;
import com.softpedia.DemoShoppingCart.repos.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService{

    private final ImageRepository imageRepository;
    private final ProductRepository productRepository;

    @Override
    public Image findById(Long id) {
        return imageRepository.findById(id).orElseThrow(
                ()-> new ImageNotFoundException("Image not Found!")
        );
    }

    @Override
    @Transactional
    public List<ImageDto> saveImages(List<MultipartFile> files, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                ()-> new ProductNotFoundException("Product not found!")
        );
        List<ImageDto> imageDtos = new ArrayList<>();
        for (MultipartFile file : files){
            try {
                Image image = new Image();
                image.setImageName(file.getOriginalFilename());
                image.setImageType(file.getContentType());
                image.setImage(new SerialBlob(file.getBytes()));
                image.setProduct(product);

                String buildDownloadUrl = "/api/v1/images/image/download/";
                String downloadUrl = buildDownloadUrl+image.getId();
                image.setDownloadUrl(downloadUrl);

                Image savedImage = imageRepository.save(image);
                savedImage.setDownloadUrl(buildDownloadUrl+savedImage.getId());
                imageRepository.save(savedImage);

                ImageDto imageDto = new ImageDto();
                imageDto.setId(savedImage.getId());
                imageDto.setImageName(savedImage.getImageName());
                imageDto.setDownloadUrl(savedImage.getDownloadUrl());
                imageDtos.add(imageDto);

            }catch (IOException | SQLException e){
                throw new RuntimeException(e.getMessage());
            }
        }
        return imageDtos;
    }

    @Override
    @Transactional
    public void updateImageById(MultipartFile file, Long imageId) {
        Image image = findById(imageId);
        try {
            image.setImageName(file.getOriginalFilename());
            image.setImage(new SerialBlob(file.getBytes()));
            imageRepository.save(image);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void deleteImage(Long id) {
        imageRepository.findById(id).ifPresentOrElse(
                imageRepository::delete,
                ()-> {
                    throw new ImageNotFoundException("Image not Found to delete");
                }
        );
    }
}
