package com.org.mintos.configs;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class CustomPaging implements Pageable {

    private long offset;
    private int limit;
    private Sort sort;

    public CustomPaging(long offset, int limit, Sort sort) {

        this.limit = limit;
        this.offset = offset;
        this.sort = sort;
    }



    @Override
    public int getPageNumber() {
        return Math.toIntExact(offset / limit);
    }

    @Override
    public int getPageSize() {
        return limit;
    }

    @Override
    public long getOffset() {
        return offset;
    }

    @Override
    public Sort getSort() {
        return sort;
    }

    @Override
    public Pageable next() {
        return new CustomPaging(getOffset() + getPageSize(), getPageSize(), getSort());
    }

    public CustomPaging previous() {
        return hasPrevious() ? new CustomPaging(getOffset() - getPageSize(), getPageSize(), getSort()) : this;
    }


    @Override
    public Pageable previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    @Override
    public Pageable first() {
        return new CustomPaging(0, getPageSize(), getSort());
    }

    @Override
    public Pageable withPage(int pageNumber) {
        return new CustomPaging(pageNumber * getPageNumber(),getPageSize(),getSort());
    }

    @Override
    public boolean hasPrevious() {
        return offset > limit;
    }

}
