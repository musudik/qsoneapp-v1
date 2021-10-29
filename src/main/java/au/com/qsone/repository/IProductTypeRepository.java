package au.com.qsone.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import au.com.qsone.entity.ProductType;

public interface IProductTypeRepository extends JpaRepository<ProductType, Long> {

}