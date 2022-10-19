package com.uniqueauction.domain.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniqueauction.domain.product.entity.Product;
import com.uniqueauction.domain.trade.entity.Purchase;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
	Purchase findByProductAndProductSize(Product product, String productSize);
}
