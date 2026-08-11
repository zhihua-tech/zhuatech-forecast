/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.forecast.controller;
import cn.zhuatech.forecast.common.ApiResponse;import cn.zhuatech.forecast.service.ForecastBiasMonitorService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/forecast/insights/bias-monitor") public class ForecastBiasMonitorController {private final ForecastBiasMonitorService service;public ForecastBiasMonitorController(ForecastBiasMonitorService service){this.service=service;}@PostMapping ApiResponse<ForecastBiasMonitorService.Result> evaluate(@Valid @RequestBody ForecastBiasMonitorService.Request request){return ApiResponse.ok(service.evaluate(request));}}
