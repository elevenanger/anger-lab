package cn.anger.util.base;

import lombok.Data;

import java.io.Serializable;

@Data
public class SingleInt implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int single;
}
