package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {
    //현재 시간과 상품 등록일을 비교해서 상품 데이터를 조회
    private String searchDateType;
    //판매 상태
    private ItemSellStatus searchSellStatus;
    //상품명 or 등록자 아이디로 검색 유형
    private String searchBy;
    //조회할 검색어 저장할 변수
    private String searchQuery="";
}
