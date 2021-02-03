package com.sline.sline.repository.project.product;

import com.sline.sline.entity.project.protuct.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * FROM product T INNER JOIN types ON types.product_id = T.id INNER JOIN amount ON amount.type_id = types.id where amount.amount>=:amountSize and T.company_id=:companyId",
            nativeQuery = true)
    Page<Product> findAllByAmount(@Param("amountSize") Integer amountSize, @Param("companyId") Long companyId, Pageable pageable);

    @Query(value = "SELECT * FROM product T INNER JOIN types ON types.product_id = T.id INNER JOIN amount ON amount.type_id = types.id where (amount.amount=0 or T.isAmount) and T.company_id=:companyId IS FALSE",
            nativeQuery = true)
    Page<Product> findAllByAmountIsFalse(@Param("companyId") Long companyId, Pageable pageable);

    Page<Product> findAllByCompany_Id(Long companyId, Pageable pageable);

    Product findByUuidAndCompany_Id(String uuid, Long companyId);

    void deleteByUuid(String uuid);
}
