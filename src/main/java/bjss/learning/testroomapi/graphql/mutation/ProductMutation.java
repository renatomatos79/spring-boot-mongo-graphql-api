package bjss.learning.testroomapi.graphql.mutation;

import bjss.learning.testroomapi.domain.ProductModel;
import bjss.learning.testroomapi.domain.ProductQL;
import bjss.learning.testroomapi.repositories.ProductRepository;
import bjss.learning.testroomapi.util.ProductConvert;
import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductMutation implements GraphQLMutationResolver {

    private final ProductRepository productRepository;

    @Autowired
    public ProductMutation(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductQL createProduct(final String id, final String sku, final String name, final String price) {
        ProductModel model = new ProductModel();
        model.setId(id);
        model.setSku(sku);
        model.setName(name);
        model.setPrice(Double.valueOf(price));
        ProductModel modelResult = this.productRepository.save(model);
        return ProductConvert.from(modelResult);
    }

    public ProductQL deleteProduct(final String id) {
        Optional<ProductModel> model = productRepository.findById(id);
        if (!model.isEmpty())
        {
            ProductModel queryResult = model.get();
            productRepository.deleteById(id);
            return ProductConvert.from(queryResult);
        }
        return null;
    }
}