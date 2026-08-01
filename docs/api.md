# Forecast API 摘要

版权所有 © 2026 上海如静知华信息科技有限公司。

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| POST | `/api/auth/login` | 登录并获取 JWT |
| GET | `/api/admin/dashboard` | 预测与决策控制塔数据 |
| GET | `/api/admin/work-orders` | 预测场景任务清单 |
| GET | `/api/shopfloor/dashboard` | 需求计划员工作台 |
| POST | `/api/shopfloor/work-orders/{id}/reports` | 提交业务修正 |
| POST | `/api/shopfloor/ai-preview` | 调用可替换预测 AI Provider |
