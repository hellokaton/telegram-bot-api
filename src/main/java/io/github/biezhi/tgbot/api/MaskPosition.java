package io.github.biezhi.tgbot.api;

import com.google.gson.Gson;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class MaskPosition implements Serializable {
    private final static long serialVersionUID = 0L;
    private final static Gson gson = new Gson();

    public enum Point {
        forehead, eyes, mouth, chin
    }

    private String point;
    private Float x_shift, y_shift;
    private Float scale;

    public MaskPosition(Point point, Float x_shift, Float y_shift, Float scale) {
        this(point.name(), x_shift, y_shift, scale);
    }

    public MaskPosition(String point, Float xShift, Float yShift, Float scale) {
        this.point = point;
        this.x_shift = xShift;
        this.y_shift = yShift;
        this.scale = scale;
    }

}
