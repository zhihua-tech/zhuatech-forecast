/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.forecast.service;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class ForecastBiasMonitorService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public Result evaluate(Request r){double actual=0,forecast=0,ape=0;int over=0;for(PeriodValue p:r.periods()){actual+=p.actual();forecast+=p.forecast();ape+=Math.abs(p.forecast()-p.actual())/p.actual()*100;if(p.forecast()>p.actual())over++;}double bias=(forecast-actual)/actual*100;double mape=ape/r.periods().size();String status=Math.abs(bias)>=10||mape>=20?"RECALIBRATE":Math.abs(bias)>=5||mape>=12?"WATCH":"STABLE";List<String> actions=new ArrayList<>();if(bias>5)actions.add("修正持续高估并复核增长假设");if(bias<-5)actions.add("修正持续低估并检查需求漏记");if(mape>=12)actions.add("按产品或区域拆分误差来源");if(actions.isEmpty())actions.add("预测误差稳定，维持当前模型参数");return new Result(round(bias),round(mape),over,status,actions);}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 private double round(double v){return Math.round(v*10)/10.0;}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Request(@NotNull @Size(min=2) List<@Valid PeriodValue> periods){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record PeriodValue(@NotBlank String period,@DecimalMin("0.01") double actual,@DecimalMin("0") double forecast){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record Result(double biasRate,double mape,int overForecastPeriods,String status,List<String> actions){}
}
