package com.example.ecommerce.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    /**
     * find all item with the name itemName
     * @param itemName name of the product
     * @return iterable object of matching result
     */
    Optional<Item> finByItemName(String itemName);

    /**
     * update item by name itemName
     * @param itemName name of the product
     * @param price new price
     * @param quantity new quantity
     */
    @Transactional
    @Modifying
    @Query("UPDATE Item i SET i.itemName = 1?, i.price = 1?, i.quantity = 3?")
    void updateItemByName(String itemName, Integer price, Integer quantity);

    /**
     * Delete item by name itemName
     * @param itemName name of the product
     */
    @Transactional
    @Modifying
    @Query("DELETE from Item i WHERE i.itemName = 1?")
    void deleteItemByName(String itemName);
}
