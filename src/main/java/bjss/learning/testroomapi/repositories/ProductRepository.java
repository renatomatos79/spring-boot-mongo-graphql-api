package bjss.learning.testroomapi.repositories;

import bjss.learning.testroomapi.domain.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, String> {

}