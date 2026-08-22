/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast.service;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;

/** 生成可解释的基准、上行和下行情景，并标识低置信度预测。 */
@Service
public class ForecastScenarioService {
    public ScenarioResult simulate(ScenarioRequest request) {
        double adjusted = request.baselineDemand() * (1 + request.trendRate()) * request.seasonalIndex() * (1 + request.promotionLift());
        int baseline = Math.max(0, (int) Math.round(adjusted));
        double uncertainty = Math.max(0.05, 1 - request.confidence());
        int downside = Math.max(0, (int) Math.round(baseline * (1 - uncertainty)));
        int upside = (int) Math.round(baseline * (1 + uncertainty));
        String recommendation = request.confidence() < 0.65 ? "BUSINESS_REVIEW" : request.promotionLift() > 0.3 ? "CAPACITY_CHECK" : "ACCEPT";
        return new ScenarioResult(baseline, downside, upside, round(uncertainty), recommendation,
            "ACCEPT".equals(recommendation) ? "可进入协同计划" : "BUSINESS_REVIEW".equals(recommendation) ? "补充业务判断后再发布" : "确认供应与履约能力后发布");
    }

    private double round(double value) { return Math.round(value * 1000d) / 1000d; }

    public record ScenarioRequest(
        @NotBlank(message = "请输入预测对象") String item,
        @Positive int baselineDemand,
        @DecimalMin("-1.0") @DecimalMax("1.0") double trendRate,
        @DecimalMin("0.1") @DecimalMax("3.0") double seasonalIndex,
        @DecimalMin("0.0") @DecimalMax("2.0") double promotionLift,
        @DecimalMin("0.0") @DecimalMax("1.0") double confidence
    ) {}

    public record ScenarioResult(int baseline, int downside, int upside, double uncertainty, String recommendation, String nextAction) {}
}
