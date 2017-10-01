package io.github.biezhi.tgbot.api;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static io.github.biezhi.tgbot.Const.FILE_API;

public class FileApi {

    private final String apiUrl;

    public FileApi(String token) {
        this.apiUrl = FILE_API + token + "/";
    }

    public String getFullFilePath(String filePath) {
        int slash = filePath.lastIndexOf('/') + 1;
        String path = filePath.substring(0, slash);
        String fileName = filePath.substring(slash);
        try {
            return apiUrl + path + URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            return apiUrl + filePath;
        }
    }

}
