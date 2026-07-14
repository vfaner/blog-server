package com.rgh;

public class GoodsItem {
    private String goodsCode;
    private String goodsName;
    private float goodsPrice;
    private float accounts;

    public GoodsItem(String goodsCode, String goodsName, float goodsPrice, float accounts) {
        this.goodsCode = goodsCode;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
        this.accounts = accounts;
    }

    public float getTotalPrice() {
        return goodsPrice * accounts;
    }

    public String toString() {
        return "商品编号：" + goodsCode + "，商品名称：" + goodsName + "，商品单价：" + goodsPrice + "，商品数量：" + accounts + "，商品总价：" + getTotalPrice();
    }
}
