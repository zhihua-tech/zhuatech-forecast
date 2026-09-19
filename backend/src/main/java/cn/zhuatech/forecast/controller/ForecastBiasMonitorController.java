/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast.controller;
import cn.zhuatech.forecast.common.ApiResponse;import cn.zhuatech.forecast.service.ForecastBiasMonitorService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/forecast/insights/bias-monitor") public class ForecastBiasMonitorController {private final ForecastBiasMonitorService service;/**
                                                                                                                                                                     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                     */
public ForecastBiasMonitorController(ForecastBiasMonitorService service){this.service=service;}/**
                                                                                                                                                                                                                                                                    * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                    */
@PostMapping ApiResponse<ForecastBiasMonitorService.Result> evaluate(@Valid @RequestBody ForecastBiasMonitorService.Request request){return ApiResponse.ok(service.evaluate(request));}}
