/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ForecastModelPromotionService {
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (request.backtestMape() > request.maxMape()) blockers.add("回测 MAPE 超过晋级阈值");
        if (!request.biasWithinTolerance()) blockers.add("预测偏差超出容忍范围");
        if (!request.explainabilityComplete()) blockers.add("模型解释性材料不完整");
        if (!request.driftWithinThreshold()) blockers.add("数据或模型漂移超出阈值");
        if (!request.approvalComplete()) blockers.add("模型晋级审批未完成");
        if (!blockers.isEmpty()) {
            actions.add("阻断模型晋级并完成评估、治理或重新训练");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if (!request.overrideOwnerAssigned() || !request.championChallengerComplete() || !request.rollbackReady()) {
            if (!request.overrideOwnerAssigned()) actions.add("指定人工调整责任人和留痕规则");
            if (!request.championChallengerComplete()) actions.add("完成冠军/挑战者并行评估");
            if (!request.rollbackReady()) actions.add("准备模型和预测结果回滚方案");
            return new Assessment(Decision.CANARY, blockers, actions);
        }
        actions.add("批准模型晋级并持续监控准确率、偏差和漂移");
        return new Assessment(Decision.PROMOTE, blockers, actions);
    }

    public record Request(@NotBlank String modelVersion, @DecimalMin("0.0") double backtestMape,
                          @DecimalMin("0.0") double maxMape, boolean biasWithinTolerance,
                          boolean explainabilityComplete, boolean driftWithinThreshold,
                          boolean overrideOwnerAssigned, boolean championChallengerComplete,
                          boolean approvalComplete, boolean rollbackReady) {}
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    public enum Decision { PROMOTE, CANARY, BLOCKED }
}
