/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast;
import cn.zhuatech.forecast.service.ForecastBiasMonitorService;import org.junit.jupiter.api.Test;import java.util.*;import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ForecastBiasMonitorServiceTests {private final ForecastBiasMonitorService service=new ForecastBiasMonitorService();/**
                                                                                                                          * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                          */
@Test void recalibratesPersistentOverForecast(){var r=service.evaluate(new ForecastBiasMonitorService.Request(List.of(new ForecastBiasMonitorService.PeriodValue("M1",100,120),new ForecastBiasMonitorService.PeriodValue("M2",100,120))));assertEquals("RECALIBRATE",r.status());assertEquals(20.0,r.biasRate());}/**
                                                                                                                                                                                                                                                                                                                                                                                                                                             * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                                                                                                                                                                                                                                                                                                                                                                             */
@Test void acceptsAccurateForecast(){var r=service.evaluate(new ForecastBiasMonitorService.Request(List.of(new ForecastBiasMonitorService.PeriodValue("M1",100,102),new ForecastBiasMonitorService.PeriodValue("M2",100,98))));assertEquals("STABLE",r.status());}}
