package io.github.biezhi.tgbot.api;

import lombok.Data;

import java.io.Serializable;

@Data
public class FileItem implements Serializable {

    private final static long serialVersionUID = 0L;

    private String  file_id;
    private Integer file_size;
    private String  file_path;

}
