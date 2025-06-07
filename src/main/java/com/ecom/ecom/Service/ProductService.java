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

    // ecom/src/main/java/com/ecom/ecom/Service/ProductService.java
    public Product updateProduct(int id, Product updatedProduct, MultipartFile imageFile) throws IOException {
        Product existingProduct = productrepo.findById(id).orElse(null);
        if (existingProduct == null) {
            return null;
        }
        // Update fields
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        // ... update other fields as needed

        if (imageFile != null && !imageFile.isEmpty()) {
            existingProduct.setImageData(imageFile.getBytes());
            existingProduct.setImageName(imageFile.getOriginalFilename());
            existingProduct.setImageType(imageFile.getContentType());
        }
        return productrepo.save(existingProduct);
    }

    public void deleteProduct(int id) {

        productrepo.deleteById(id);

    }
}
