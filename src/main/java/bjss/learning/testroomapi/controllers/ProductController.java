package bjss.learning.testroomapi.controllers;

import bjss.learning.testroomapi.domain.ProductModel;
import bjss.learning.testroomapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<ProductModel> getAllProducts(){
        return productRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<ProductModel> getProduct(@PathVariable String id, HttpServletResponse response) {
        Optional<ProductModel> model = productRepository.findById(id);
        if (model.isEmpty()){
            response.setStatus(404);
        } else {
            response.setStatus(200);
        }
        return model;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ProductModel createProduct(@RequestBody ProductModel model, HttpServletResponse response) {
        Optional<ProductModel> data = productRepository.findById(model.getId());
        if (!data.isEmpty()){
            response.setStatus(409);
            return null;
        } else {
            return productRepository.save(model);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable String id, HttpServletResponse response) {
        Optional<ProductModel> model = productRepository.findById(id);
        if (model.isEmpty()){
            response.setStatus(404);
        } else {
            productRepository.deleteById(id);
            response.setStatus(204);
        }
    }
}