package com.petshop.PetShop_management.repository;

import com.petshop.PetShop_management.entity.ProductOrder;
import com.petshop.PetShop_management.entity.Customer;
import com.petshop.PetShop_management.entity.Employee;
import com.petshop.PetShop_management.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> { 

    List<ProductOrder> findByOrderDate(LocalDate orderDate); 
    List<ProductOrder> findByOrderDateBetween(LocalDate startDate, LocalDate endDate); 

    List<ProductOrder> findByOrderPrice(Integer orderPrice); 

    List<ProductOrder> findByStatus(String status);  

    List<ProductOrder> findByCustomer(Customer customer); 
    List<ProductOrder> findByEmployee(Employee employee);  
    List<ProductOrder> findBySupplier(Supplier supplier); 

    @Query("SELECT SUM(po.orderPrice) FROM ProductOrder po WHERE po.status IN :statuses")
    Long getTotalPriceForStatuses(@Param("statuses") List<String> statuses);

}
