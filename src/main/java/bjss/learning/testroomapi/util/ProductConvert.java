package bjss.learning.testroomapi.util;

import bjss.learning.testroomapi.domain.ProductModel;
import bjss.learning.testroomapi.domain.ProductQL;

public class ProductConvert {
    public static ProductQL from(ProductModel model){
        if (model == null){
            return null;
        }
        ProductQL ql = new ProductQL();
        ql.setId(Integer.parseInt(model.getId()));
        ql.setSku(model.getSku());
        ql.setName(model.getName());
        ql.setPrice(Double.toString(model.getPrice()));
        return ql;
    }
}
