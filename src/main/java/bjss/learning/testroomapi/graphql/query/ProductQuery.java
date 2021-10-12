package bjss.learning.testroomapi.graphql.query;

import bjss.learning.testroomapi.domain.ProductQL;
import bjss.learning.testroomapi.domain.ProductModel;
import bjss.learning.testroomapi.repositories.ProductRepository;
import bjss.learning.testroomapi.util.ProductConvert;
import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductQuery implements GraphQLQueryResolver {

    private final ProductRepository productRepository;

    @Autowired
    public ProductQuery(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductQL> getProducts(final int count, final String sortBy) {
        org.springframework.data.domain.Pageable paging =  PageRequest.of(0, count, Sort.by(sortBy));
        Page<ProductModel> page = this.productRepository.findAll(paging);
        List<ProductModel> records = Lists.newArrayList(page);
        List<ProductQL> items = new ArrayList<>();
        for (ProductModel item : records)
        {
            items.add(ProductConvert.from(item));
        }
        return items;
    }

    public ProductQL getProduct(final String id) {
        Optional<ProductModel> item = this.productRepository.findById(id);
        if (!item.isEmpty())
        {
            return ProductConvert.from(item.get());
        }
        return null;
    }
}
