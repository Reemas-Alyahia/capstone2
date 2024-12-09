package com.example.capston2.Service;

import com.example.capston2.ApiResponse.ApiExption;
import com.example.capston2.Model.Cat;
import com.example.capston2.Model.Product;
import com.example.capston2.Repository.CatRepository;
import com.example.capston2.Repository.ProdectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProdectRepository prodectRepository;
    private final CatRepository catRepository;

    public ProductService(ProdectRepository prodectRepository, CatRepository catRepository) {
        this.prodectRepository = prodectRepository;
        this.catRepository = catRepository;
    }

    public List<Product>getAllProduct(){
        return prodectRepository.findAll();
    }
    public void addNewProduct(Product product){
        prodectRepository.save(product);
    }
    public void updateProduct(Product product,Integer id){
        Product oldProduct=prodectRepository.findProductByProductId(id);
        if(oldProduct==null){
            throw new ApiExption("Cannot found this Product");
        }
        oldProduct.setCategory(product.getCategory());
        oldProduct.setName(product.getName());
        oldProduct.setDescription(product.getDescription());
        oldProduct.setPrice(product.getPrice());
        oldProduct.setStock(product.getStock());

        prodectRepository.save(oldProduct);
    }

    public void deleteProduct(Integer id){
        Product product=prodectRepository.findProductByProductId(id);
        if(product==null){
            throw new ApiExption("Cannot found this Product");

        }
        prodectRepository.delete(product);
    }

public Product addDiscount(Integer productId,Double discount) {
    Product product = prodectRepository.findProductByProductId(productId);
    if (product == null) {
        throw new ApiExption("Product not found");}

        if (discount < 0 || discount > 100) {
            throw new ApiExption("discount must be between 0 and 100");
        }

        Double originalPrice = product.getPrice().doubleValue();
        Double discountPrice = originalPrice - (originalPrice * (discount / 100));

        product.setDiscount(discount);
        product.setPrice(discountPrice.intValue());

        prodectRepository.save(product);
        return product;
    }

    public List<Product> getDiscountProduct(Double discount){
        if (discount < 0 || discount > 100) {
            throw new ApiExption("discount must be between 0 and 100");
        }
        List<Product>discountProduct=prodectRepository.getProductsByDiscountGreaterThan(discount);
       if(discountProduct.isEmpty()){
    throw new ApiExption("there no product have this discount right now");

}
        return discountProduct;
    }

    public List<Product> getProductByCategoryAndAge(String category,Integer min,Integer max){
        List<Product>productList=prodectRepository.getProductsByCategoryAndage(category, min, max);
        if(productList.isEmpty()){
            throw new ApiExption("there no product in this category right now");
        }
        return productList;
    }

    public List<Product> getProductByCategory(String category){
        List<Product>productList=prodectRepository.getProductsByCategory(category);
        if(productList.isEmpty()){
            throw new ApiExption("there no product in this category right now");
        }
        return productList;
    }

public List<Product>getProductByPrice(Integer min,Integer max){
    List<Product>productList=prodectRepository.getProductsByPrice(min,max);
    if(productList.isEmpty()){
        throw new ApiExption("there no product in this price right now");
    }
    return productList;
}
/// /
    public List<Product>getProductByName(String name){
        List<Product>productList=prodectRepository.getProductsByName(name);
        if(productList.isEmpty()){
            throw new ApiExption("there no product like this name right now");
        }
        return productList;
    }

}