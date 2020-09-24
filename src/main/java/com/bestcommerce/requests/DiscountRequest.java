package com.bestcommerce.requests;

import java.time.LocalDate;

public class DiscountRequest {

    private LocalDate start;
    private LocalDate end;
    private Double discount;

    public DiscountRequest(Double discount,LocalDate start, LocalDate end)  {

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

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
