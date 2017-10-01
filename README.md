# telegram-bot-api

这是一个 Telegram 的机器人库，能够帮你快速搭建一个机器人程序运行。

<p align="center">
    <a href="https://travis-ci.org/biezhi/telegram-bot-api"><img src="https://img.shields.io/travis/biezhi/telegram-bot-api.svg?style=flat-square"></a>
    <a href="http://search.maven.org/#search%7Cga%7C1%7Coh-my-request"><img src="https://img.shields.io/maven-central/v/io.github.biezhi/telegram-bot-api.svg?style=flat-square"></a>
    <a href="LICENSE"><img src="https://img.shields.io/badge/license-Apache%202-4EB1BA.svg?style=flat-square"></a>
</p>

## 特性

- 支持代理
- 快速接入
- 依赖更少
- 灵活配置

## 示例代码

### GetMe

```java
TelegramBot bot = new TelegramBot(TOKEN);
System.out.println(bot.getMe());
```

### 监听命令

```java
TelegramBot bot = new TelegramBot(TOKEN);
bot.onCmd("/help", message -> {
    log.info("收到消息: {}", message);
    bot.text(message, "/echo\r\n/me\r\n/hi");
}).await();
```