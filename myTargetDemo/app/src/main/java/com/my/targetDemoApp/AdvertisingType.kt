package com.my.targetDemoApp

enum class AdvertisingType(val defaultSlot: Int) {
    STANDARD_BANNER_320X50(7250),
    STANDARD_BANNER_320X50_HTML(93229),

    STANDARD_BANNER_300X250_WEB(64525),
    STANDARD_BANNER_300X250_HTML(93231),

    STANDARD_BANNER_728X90_WEB(81624),
    STANDARD_BANNER_728X90_HTML(328709),

    INTERSTITIAL_PROMO_STATIC(6896),
    INTERSTITIAL_PROMO_VIDEO(10138),
    INTERSTITIAL_PROMO_VIDEO_BLACK(38837),
    INTERSTITIAL_IMAGE(6481),
    INTERSTITIAL_HTML(93233),
    INTERSTITIAL_CAROUSEL(102652),
    INTERSTITIAL_VAST(101600),
    INTERSTITIAL_STYLE_2(577498),
    REWARDED(45101),

    NATIVE_AD(6590),
    NATIVE_AD_VIDEO(30150),
    NATIVE_AD_CAROUSEL(54923),

    NATIVE_BANNER(708247),

    INSTREAM(9525),
}