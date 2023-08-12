package by.project.myspringdatamvcsecuritygit.repositories.specifications;

import by.project.myspringdatamvcsecuritygit.models.Product;
import org.springframework.data.jpa.domain.Specification;


import java.math.BigDecimal;

public class ProductSpecs {   // фильтры спецификации для фильтрации на API Criteria
        public static Specification<Product> titleContains(String word ){
          return (Specification<Product>) (root, criteriaQuery, criteriaBuilder )->criteriaBuilder.like(root.get("title"),"%"+word+"%");
        }

    public static Specification<Product> priceGreatThenOrEq(BigDecimal value ){
        return (Specification<Product>) (root,criteriaQuery,criteriaBuilder )->criteriaBuilder.greaterThanOrEqualTo(root.get("price"),value);

    }

    public static Specification<Product> priceLessThenOrEq(BigDecimal value ){
        return (Specification<Product>) (root,criteriaQuery,criteriaBuilder )->criteriaBuilder.lessThanOrEqualTo(root.get("price"),value);

    }
}
