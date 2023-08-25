package com.example.thyproject.dto;


public class SaleStockDTO {
    private Long id;
    private String symbol;
    private String name;
    private String exchange;
    private String type;
    private double price;
    private int quantity;

    //************************************
    private double currentPrice;


    public SaleStockDTO() {
        // Default constructor
    }

    public SaleStockDTO(Long id, String symbol, String name, String exchange, String type,
                        double price, int quantity, double currentPrice) {
        this.id = id;
        this.symbol = symbol;
        this.name = name;
        this.exchange = exchange;
        this.type = type;
        this.price = price;
        this.quantity = quantity;
        this.currentPrice = currentPrice;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
}
