/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast.ai;
import org.springframework.stereotype.Component; import java.util.Map;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
public interface AiProvider { /**
                               * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                               */
AiResult execute(String prompt,Map<String,String> context); /**
                                                                                           * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                                                           */
record AiResult(String provider,String answer,Map<String,Object> evidence){} }
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component class DemoAiProvider implements AiProvider { /**
                                                         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
                                                         */
public AiResult execute(String prompt,Map<String,String> context){return new AiResult("demo-forecast-provider","演示模式已生成需求预测与影响因子说明，生产环境请替换 AiProvider。",Map.of("factors",6,"confidence",0.93,"promptLength",prompt.length()));} }
