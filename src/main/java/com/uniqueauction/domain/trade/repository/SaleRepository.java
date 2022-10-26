package com.uniqueauction.domain.trade.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.trade.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
	Optional<Sale> findByProductAndProductSize(Product product, String productSize);

	boolean existsByProductAndProductSize(Product product, String productSize);
}
