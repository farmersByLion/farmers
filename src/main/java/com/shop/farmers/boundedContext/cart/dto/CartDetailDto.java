package com.shop.farmers.boundedContext.cart.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDetailDto {
    private Long cartItemId; // 카트 아이템 아이디
    private String itemNm; // 상품명
    private int price; // 상품금액
    private int count; // 상품 수량
    private String imgUrl; // 상품 이미지 경로

    public CartDetailDto(Long cartItemId, String itemNm, int price, int count, String imgUrl) {
        this.cartItemId = cartItemId;
        this.itemNm = itemNm;
        this.price = price;
        this.count = count;
        this.imgUrl = imgUrl;
    }
}
