package com.mgj.admin.directive;

/**
 * Created by yanqu on 2016/7/11.
 */
public class EnumVo {
    private String name;
    private String display;

    public EnumVo() {
    }

    public EnumVo(String name, String display) {
        this.name = name;
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public String getDisplay() {
        return display;
    }

    @Override
    public String toString() {
        return "EnumVo{" +
                "name='" + name + '\'' +
                ", display='" + display + '\'' +
                '}';
    }
}
