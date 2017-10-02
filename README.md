# telegram-bot-api

这是一个 Telegram 的机器人库，能够帮你快速搭建一个机器人程序运行。

[![CI](https://img.shields.io/travis/biezhi/telegram-bot-api.svg)](https://travis-ci.org/biezhi/telegram-bot-api)
[![Maven](https://img.shields.io/maven-central/v/io.github.biezhi/telegram-bot-api.svg)](http://search.maven.org/#search%7Cga%7C1%7C/telegram-bot-api)
[![LICENSE](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](LICENSE)

## 特性

- 快速接入API
- 内置多种实现
- 支持代理，不怕被墙
- 依赖更少，简单最好
- 异步调用，效率更高
- 灵活配置，满足自定义需求

## 快速接入

**下面是Maven坐标**

```java
<dependency>
    <groupId>io.github.biezhi</groupId>
    <artifactId>telegram-bot-api</artifactId>
    <version>最新版本</version>
</dependency>
```

## 示例代码

### GetMe

```java
TelegramBot bot = new TelegramBot(TOKEN);
System.out.println(bot.getMe());
```

### 监听文本指令

```java
TelegramBot bot = new TelegramBot(TOKEN);
bot.onCmd("/help", message -> {
    log.info("收到消息: {}", message);
    bot.text(message, "/echo\r\n/me\r\n/hi");
}).await();
```

### 监听贴纸消息

```java
TelegramBot bot = new TelegramBot(TOKEN);
bot.onSticker(message -> {
    log.info("收到贴图: {}", message);
}).await();
```

### 监听其他消息

```java
TelegramBot bot = new TelegramBot(TOKEN);
bot.onOther(message -> {
    log.info("收到消息: {}", message);
}).await();
```

## 开源协议

[Apache2](LICENSE)

