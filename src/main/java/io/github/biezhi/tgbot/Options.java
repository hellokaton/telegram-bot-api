package io.github.biezhi.tgbot;

import lombok.Builder;
import lombok.Data;

/**
 * 配置项
 *
 * @author biezhi
 * @date 2017/10/1
 */
@Data
@Builder
public class Options {

    // 每次请求API延迟，建议不要低于当前值，以免CPU消耗过高
    @Builder.Default
    private long    duration         = 100L;
    // 线程池大小，默认为当前CPU核数
    @Builder.Default
    private int     executorPoolSize = Runtime.getRuntime().availableProcessors();
    // 调用API连接超时时间，单位毫秒，默认为30秒
    @Builder.Default
    private long    connectTimeout   = 30_000L;
    // 调用API读取超时时间，单位毫秒，默认为10秒
    @Builder.Default
    private long    readTimeout      = 10_000;
    // 是否开启debug模式，开启debug则会打印请求和响应的详情
    @Builder.Default
    private boolean debug            = false;

}
