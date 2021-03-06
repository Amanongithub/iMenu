package ca.ibs.imenu.repository;

import ca.ibs.imenu.entity.Category;
import ca.ibs.imenu.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository
        extends JpaRepository<Product, Long>, PagingAndSortingRepository<Product, Long>, JpaSpecificationExecutor<IProductRepository> {

    List<Product> findByCategory(Category category);
}
