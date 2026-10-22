/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast.workbench;

import static cn.zhuatech.forecast.workbench.WorkbenchInput.*;

import java.math.*;
import java.util.*;
import org.springframework.stereotype.Service;

/** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
@Service
public class AiWorkbenchService {
  /** 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。 */
  public Map<String, Object> execute(Map<String, Object> r) {
    validate(r);
    keys(r, "window", "actuals");
    int window = integer(r, "window", 1, 100);
    var raw = list(r, "actuals", 2, 500);
    if (raw.size() <= window) throw new IllegalArgumentException("实际序列必须长于窗口");
    List<Double> values = new ArrayList<>();
    for (Object v : raw)
      values.add(decimal(Collections.singletonMap("v", v), "v", 0, 1e12).doubleValue());
    double abs = 0, bias = 0, actualTotal = 0;
    List<Map<String, Object>> out = new ArrayList<>();
    for (int i = window; i < values.size(); i++) {
      double sum = 0;
      for (int j = i - window; j < i; j++) sum += values.get(j);
      double prediction = sum / window, actual = values.get(i), error = prediction - actual;
      abs += Math.abs(error);
      bias += error;
      actualTotal += actual;
      out.add(
          Map.of(
              "period",
              i + 1,
              "actual",
              round(actual),
              "forecast",
              round(prediction),
              "error",
              round(error)));
    }
    return Map.of(
        "mae",
        round(abs / out.size()),
        "meanError",
        round(bias / out.size()),
        "wapeDefined",
        actualTotal > 0,
        "wapePercent",
        actualTotal == 0 ? BigDecimal.ZERO : round(abs / actualTotal * 100),
        "forecasts",
        out,
        "trainingWindow",
        window);
  }
}
