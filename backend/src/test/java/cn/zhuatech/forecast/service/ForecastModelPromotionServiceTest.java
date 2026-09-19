/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ForecastModelPromotionServiceTest {
    private final ForecastModelPromotionService service = new ForecastModelPromotionService();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void promotesGovernedModel() {
        var result = service.assess(new ForecastModelPromotionService.Request("M1", 8, 12, true, true,
                true, true, true, true, true));
        assertThat(result.decision()).isEqualTo(ForecastModelPromotionService.Decision.PROMOTE);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void usesCanaryForOperationalGaps() {
        var result = service.assess(new ForecastModelPromotionService.Request("M2", 8, 12, true, true,
                true, false, false, true, false));
        assertThat(result.actions()).hasSize(3);
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksUnqualifiedModel() {
        var result = service.assess(new ForecastModelPromotionService.Request("M3", 20, 12, false, false,
                false, true, true, false, true));
        assertThat(result.blockers()).hasSize(5);
    }
}
