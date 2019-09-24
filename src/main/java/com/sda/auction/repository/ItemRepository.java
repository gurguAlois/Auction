package com.sda.auction.repository;

import com.sda.auction.model.Item;
import com.sda.auction.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

}
