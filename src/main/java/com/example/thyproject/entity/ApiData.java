package com.example.thyproject.entity;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name="api_data")
public class ApiData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "changesPercentage")
    private BigDecimal changesPercentage;

    @Column(name = "change")
    private BigDecimal change;

    @Column(name = "dayLow")
    private BigDecimal dayLow;

    @Column(name = "dayHigh")
    private BigDecimal dayHigh;

    @Column(name = "yearHigh")
    private BigDecimal yearHigh;

    @Column(name = "yearLow")
    private BigDecimal yearLow;

    @Column(name = "marketCap")
    private Long marketCap;

    @Column(name = "priceAvg50")
    private BigDecimal priceAvg50;

    @Column(name = "priceAvg200")
    private BigDecimal priceAvg200;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "avgVolume")
    private Long avgVolume;

    @Column(name="open_close")
    private BigDecimal open;

    @Column(name = "previousClose")
    private BigDecimal previousClose;

    @Column(name = "eps")
    private BigDecimal eps;

    @Column(name = "pe")
    private BigDecimal pe;

    @Column(name = "earningsAnnouncement")
    private LocalDateTime earningsAnnouncement;

    @Column(name = "sharesOutstanding")
    private Long sharesOutstanding;

    @Column(name = "timestamp")
    private Long timestamp;


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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getChangesPercentage() {
        return changesPercentage;
    }

    public void setChangesPercentage(BigDecimal changesPercentage) {
        this.changesPercentage = changesPercentage;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getDayLow() {
        return dayLow;
    }

    public void setDayLow(BigDecimal dayLow) {
        this.dayLow = dayLow;
    }

    public BigDecimal getDayHigh() {
        return dayHigh;
    }

    public void setDayHigh(BigDecimal dayHigh) {
        this.dayHigh = dayHigh;
    }

    public BigDecimal getYearHigh() {
        return yearHigh;
    }

    public void setYearHigh(BigDecimal yearHigh) {
        this.yearHigh = yearHigh;
    }

    public BigDecimal getYearLow() {
        return yearLow;
    }

    public void setYearLow(BigDecimal yearLow) {
        this.yearLow = yearLow;
    }

    public Long getMarketCap() {
        return marketCap;
    }

    public void setMarketCap(Long marketCap) {
        this.marketCap = marketCap;
    }

    public BigDecimal getPriceAvg50() {
        return priceAvg50;
    }

    public void setPriceAvg50(BigDecimal priceAvg50) {
        this.priceAvg50 = priceAvg50;
    }

    public BigDecimal getPriceAvg200() {
        return priceAvg200;
    }

    public void setPriceAvg200(BigDecimal priceAvg200) {
        this.priceAvg200 = priceAvg200;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Long getAvgVolume() {
        return avgVolume;
    }

    public void setAvgVolume(Long avgVolume) {
        this.avgVolume = avgVolume;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getPreviousClose() {
        return previousClose;
    }

    public void setPreviousClose(BigDecimal previousClose) {
        this.previousClose = previousClose;
    }

    public BigDecimal getEps() {
        return eps;
    }

    public void setEps(BigDecimal eps) {
        this.eps = eps;
    }

    public BigDecimal getPe() {
        return pe;
    }

    public void setPe(BigDecimal pe) {
        this.pe = pe;
    }

    public LocalDateTime getEarningsAnnouncement() {
        return earningsAnnouncement;
    }

    public void setEarningsAnnouncement(LocalDateTime earningsAnnouncement) {
        this.earningsAnnouncement = earningsAnnouncement;
    }

    public Long getSharesOutstanding() {
        return sharesOutstanding;
    }

    public void setSharesOutstanding(Long sharesOutstanding) {
        this.sharesOutstanding = sharesOutstanding;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }
}
