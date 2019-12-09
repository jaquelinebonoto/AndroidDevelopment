package com.ssi.entrega2.model;

import com.ssi.entrega2.R;

public class CorStatic {
    private String desc;
    private int code;

    public static final CorStatic[] cores = {
            new CorStatic("Azul", 15),
            new CorStatic("Pérola", 14),
            new CorStatic("Carmim", 06),
            new CorStatic("Preto", 27)
    };

    public CorStatic(String desc, int code) {
        this.desc = desc;
        this.code = code;
    };
    public CorStatic() {};

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static CorStatic[] getCores() {
        return cores;
    }


}



