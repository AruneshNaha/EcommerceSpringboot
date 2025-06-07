package com.ecom.ecom.Service;

import com.ecom.ecom.Model.Product;
import com.ecom.ecom.Repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productrepo;

    public List<Product> getAllProducts(){

        return productrepo.findAll();
    }

//    public Product insertProduct(Product product){
//        return productrepo.save(product);
//    }

    public Product getProductByID(int ID) {
        return productrepo.findById(ID).orElse(null);
    }

    public void addProduct(Product product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        productrepo.save(product);
    }
}
