package org.shop;

import java.util.Arrays;
import java.util.List;

import org.shop.data.Product;
import org.shop.repository.map.ProductMapRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    
    @RequestMapping("/")
    public String index() {
        List<Product> products = new ProductMapRepository().getProducts();
		return Arrays.toString(products.toArray());
    }
    
}
