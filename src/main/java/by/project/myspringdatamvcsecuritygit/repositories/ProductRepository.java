package by.project.myspringdatamvcsecuritygit.repositories;

import by.project.myspringdatamvcsecuritygit.models.Product;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Component  //List<Product> products вместо базы данных
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() { // имммтация  базы данных
        products = new ArrayList<>();
        products.add(new Product(1L, "Bread",  new BigDecimal(40)));
        products.add(new Product(2L, "Milk", new BigDecimal(90)));
        products.add(new Product(3L, "Cheese", new BigDecimal( 200)));
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByTitle(String title) {
        return products.stream().filter(p -> p.getTitle().equals(title)).findFirst().get();
    }

    public  List<Product> getProductsWithFiltr(String word){
        List<Product> fullList =  findAll();
        if(word == null ){
           return  fullList;
        }
        return fullList.stream().filter(p-> p.getTitle().contains(word)).collect(Collectors.toList());
    }

    public Product findById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst().get();
    }

    public void save(Product product) {
        products.add(product);
   }

   public void saveEdit(Product product) {
            //products.add(product);
            if (product.getId() == null ){ // новый элемент

                // generate iD
                Optional<Product> maxIdProduct = products.stream().max(Comparator.comparingLong(Product::getId));
                Long newId = maxIdProduct.get().getId()+1;
                product.setId(newId);
                products.add(product);
                return;
            }

            Iterator<Product> productsIter =  products.iterator();//изменение имеющегося элемента
            while (productsIter.hasNext()){
                Product p = productsIter.next();
                if( p.getId().equals(product.getId())){
                    p.setPrice(product.getPrice());
                    p.setTitle(product.getTitle());
                    return;

                }
            }

    }
    public void deleteProduct(Long id ){//Product product
      Iterator<Product> productsIter =  products.iterator();
        while (productsIter.hasNext()){
            Product productforDelete = productsIter.next();
            if( productforDelete.getId().equals(id)){
                 productsIter.remove();
            }
        }
     }
}
