# CodeLens Agent

基于简历技术栈和业务逻辑的模块化单体 MVP：Java 21、Spring Boot、MySQL、Redis、DeepSeek/LangChain4j 接入位、GitLab Webhook、异步队列、幂等任务与有限重试。

## 启动

1. `docker compose up -d`
2. 设置 `DEEPSEEK_API_KEY` 与 `GITLAB_WEBHOOK_SECRET`
3. `mvn spring-boot:run`
4. GitLab MR Webhook 指向 `POST /api/webhooks/gitlab`，请求头 `X-Gitlab-Token` 必须匹配 Secret。

当前 Worker 已完成任务状态流转骨架；GitLab Diff、只读工具、LangChain4j 结构化输出和评论回写应在 `service` 边界继续接入，避免把不可信仓库内容当作系统指令。
