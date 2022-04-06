package com.rootzwy.bbs.common.dto;

import java.util.List;

/**
 * @author zwy
 * @date 2022/3/20
 */
public class CommonPage<T> extends DTO {

    protected List<T> records;

    protected long total;

    protected long size;

    protected long current;

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }
}
