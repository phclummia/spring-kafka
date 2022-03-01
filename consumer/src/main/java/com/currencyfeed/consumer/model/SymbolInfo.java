package com.currencyfeed.consumer.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class SymbolInfo {

    private String symbol;
    private String status;
    private String baseAsset;
    private Long baseAssetPrecision;
    private String quoteAsset;
    private Long quotePrecision;
    private Long quoteAssetPrecision;
    private Long baseCommissionPrecision;
    private Long quoteCommissionPrecision;
    private List<OrderType> orderTypes;
    private boolean icebergAllowed;
    private boolean ocoAllowed;
    private boolean quoteOrderQtyMarketAllowed;
    private boolean isSpotTradingAllowed;
    private boolean isMarginTradingAllowed;
    private List<Filter> filters;
    private List<String> permissions;
}
