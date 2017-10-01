package io.github.biezhi.tgbot.request;

import io.github.biezhi.tgbot.response.BotResponse;

import java.io.File;

/**
 * stas
 * 5/2/16.
 */
public class SetWebhook extends BotRequest<SetWebhook, BotResponse> {

    private boolean isMultipart = false;

    public SetWebhook() {
        super(BotResponse.class);
    }

    public SetWebhook url(String url) {
        return add("url", url);
    }

    public SetWebhook certificate(byte[] certificate) {
        isMultipart = true;
        return add("certificate", certificate);
    }

    public SetWebhook certificate(File certificate) {
        isMultipart = true;
        return add("certificate", certificate);
    }

    public SetWebhook maxConnections(int maxConnections) {
        return add("max_connections", maxConnections);
    }

    public SetWebhook allowedUpdates(String... allowedUpdates) {
        return add("allowed_updates", serialize(allowedUpdates));
    }

    @Override
    public boolean isMultipart() {
        return isMultipart;
    }
}
