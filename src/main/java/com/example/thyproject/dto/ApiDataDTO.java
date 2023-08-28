package com.example.thyproject.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiDataDTO {

    private String symbol;
    private String name;
    private BigDecimal price;
    private BigDecimal changesPercentage;
    private BigDecimal change;
    private BigDecimal dayLow;
    private BigDecimal dayHigh;
    private BigDecimal yearHigh;
    private BigDecimal yearLow;
    private Long marketCap;
    private BigDecimal priceAvg50;
    private BigDecimal priceAvg200;
    private String exchange;
    private Long volume;
    private Long avgVolume;
    private BigDecimal open;
    private BigDecimal previousClose;
    private BigDecimal eps;
    private BigDecimal pe;
    @JsonIgnore
    private LocalDateTime earningsAnnouncement;
    private Long sharesOutstanding;
    private Long timestamp;

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
