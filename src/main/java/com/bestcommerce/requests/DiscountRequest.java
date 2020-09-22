package com.bestcommerce.requests;

import java.time.LocalDate;

public class DiscountRequest {

    private LocalDate start;
    private LocalDate end;
    private Integer discount;

    public DiscountRequest(Integer discount,LocalDate start, LocalDate end)  {

        this.start = start;
        this.end = end;
        this.discount = discount;
    }

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }
}
