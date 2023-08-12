package by.project.myspringdatamvcsecuritygit.util;

import by.project.myspringdatamvcsecuritygit.models.Product;
import by.project.myspringdatamvcsecuritygit.repositories.IProductREpository;
import by.project.myspringdatamvcsecuritygit.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

;

@Service
public class ProductsService {

    private IProductREpository iproductRepository;
    private ProductRepository productRepository;

    @Autowired
    public  void setIproductRepository(IProductREpository iproductRepository ){
        this.iproductRepository = iproductRepository;
    }
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getById(Long id) {
        return productRepository.findById(id);
    }

    public Optional<Product> getIById(Long id) {
        return iproductRepository.findById(id); //db
    }

    public List<Product> getAllProductsFiltr(String sword) {
        return productRepository.getProductsWithFiltr(sword);
    }

    public List<Product> getdbAllProductsFiltr(String sword) {//db
        return iproductRepository.findByTitle(sword); // сконсруированный запрос в интерфейсе IProductREpository
    }


    public List<Product> getAllProducts() {
        return  productRepository.findAll();
    }

    public List<Product> getAllIProducts() {
        return iproductRepository.findAll(); // db
    }

    public void adddb(Product product) { //db
        iproductRepository. save(product);

    }

    public void add(Product product) {
        productRepository.save(product);

    }
    public void addEdit(Product product) {
        productRepository.saveEdit(product);
    }

    public void deleteProductdb(Long id)//dbProduct entityProduct
    {
        iproductRepository.deleteById(id); //delete(entityProduct);
    }
    public void deleteProduct(Long id )//Product product)
    {
        productRepository.deleteProduct(id);

    }
    // метод для пагинации  и фильтрации
   public Page<Product> getProductWithPagingFiltr(Specification<Product> productspecification  , Pageable pagebale){
        return iproductRepository.findAll(productspecification,pagebale);
   }
}
