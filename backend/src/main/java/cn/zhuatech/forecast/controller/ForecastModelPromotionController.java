/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast.controller;

import cn.zhuatech.forecast.common.ApiResponse;
import cn.zhuatech.forecast.service.ForecastModelPromotionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/forecast")
public class ForecastModelPromotionController {
    private final ForecastModelPromotionService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ForecastModelPromotionController(ForecastModelPromotionService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/model-promotion")
    public ApiResponse<ForecastModelPromotionService.Assessment> assess(
            @Valid @RequestBody ForecastModelPromotionService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
