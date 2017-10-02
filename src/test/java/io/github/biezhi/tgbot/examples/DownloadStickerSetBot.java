package io.github.biezhi.tgbot.examples;

import io.github.biezhi.request.Request;
import io.github.biezhi.tgbot.Options;
import io.github.biezhi.tgbot.TelegramBot;
import io.github.biezhi.tgbot.api.Sticker;
import io.github.biezhi.tgbot.request.GetFile;
import io.github.biezhi.tgbot.request.GetStickerSet;
import io.github.biezhi.tgbot.response.GetFileResponse;
import io.github.biezhi.tgbot.response.GetStickerSetResponse;
import io.github.biezhi.webp.WebpIO;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 这个机器人帮你将贴纸导出为png
 *
 * @author biezhi
 * @date 2017/10/2
 */
@Slf4j
public class DownloadStickerSetBot {

    public static void main(String[] args) {

        String saveDir = "/Users/biezhi/Desktop/miaomiao";

        Set<String> stickerSet = new HashSet<>();

        TelegramBot bot = new TelegramBot("")
                .options(Options.builder().debug(true).build())
                .useProxy("127.0.0.1", 1087);

        bot.onSticker(message -> {
            log.info("接收到贴图请求");
            Sticker sticker  = message.getSticker();
            String  set_name = sticker.getSet_name();
            if (stickerSet.contains(set_name)) {
                return;
            }
            stickerSet.add(set_name);
            GetStickerSet         getStickerSet      = new GetStickerSet(set_name);
            GetStickerSetResponse stickerSetResponse = bot.execute(getStickerSet);
            Stream.of(stickerSetResponse.getResult().getStickers())
                    .parallel()
                    .map(Sticker::getFile_id)
                    .map(fileId -> {
                        GetFile getFile = new GetFile(fileId);
                        log.info("开始获取FileItem => {}", fileId);
                        GetFileResponse getFileResponse = bot.execute(getFile);
                        return getFileResponse.getResult();
                    })
                    .map(bot::getFullFilePath)
                    .map(fileUrl -> {
                        log.info("开始下载 => {}", fileUrl);
                        File file = new File(saveDir, UUID.randomUUID().toString() + ".webp");

                        Request.get(fileUrl)
                                .useProxy("127.0.0.1", 1087)
                                .connectTimeout(30_000).readTimeout(10_000)
                                .receive(file);

                        log.info("下载完成 => {}", fileUrl);
                        return file.getPath();
                    })
                    .forEach(webpPath -> WebpIO.toNormalImage(webpPath, webpPath.replace(".webp", ".png")));
        }).await();
    }

}
