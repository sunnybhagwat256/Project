package com.infosys.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.infosys.order.entity.CompositeKey;
import com.infosys.order.entity.ProductsOrdered;

@Repository
public interface ProductOrderedRepository extends JpaRepository<ProductsOrdered, CompositeKey> {

	List<ProductsOrdered> findByorderid(int orderid);

}
