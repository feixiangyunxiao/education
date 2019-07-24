package com.bootdo.common.utils;

import java.io.Serializable;
import java.util.List;

public class LayuiTree implements Serializable {
    private String title;

    private Long id;

    private List<LayuiTree> children;

    private String href;

    private boolean spread;

    private boolean checked;

    private boolean disabled;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<LayuiTree> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiTree> children) {
        this.children = children;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    @Override
    public String toString() {
        return "LayuiTree{" +
                "title='" + title + '\'' +
                ", id=" + id +
                ", children=" + children +
                ", href='" + href + '\'' +
                ", spread=" + spread +
                ", checked=" + checked +
                ", disabled=" + disabled +
                '}';
    }
}
