package com.shop.repository;

import com.shop.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>,
QuerydslPredicateExecutor<Item>, ItemRepositoryCustom{

    //이름으로 조회
    List<Item> findByItemNm(String itemNm);

    //이름 또는 상세 설명 조회
    List<Item> findByItemNmOrItemDetail(String itemNm, String itemDetail);

    //가격
    List<Item> findByPriceLessThan(Integer price);

    //가격 내림차순
    List<Item> findByPriceIsLessThanOrderByPriceDesc(Integer price);

    @Query("select  i from Item i where i.itemDetail like %:itemDetail% order by i.price desc")
    List<Item> findByItemDetail(@Param("itemDetail")String itemDetail);



}
