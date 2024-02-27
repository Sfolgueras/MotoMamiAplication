package com.motomami.model;

import java.util.Date;

public class InvoiceDto {
    private long id;
    private long invoiceNum;
    private Date invoiceDate;
    private String providerName;
    private Integer peopleQuantity;
    private double unitPrice;
    private Integer tax;

    // Constructor, getters y setters
    public InvoiceDto() {}

    public InvoiceDto(long id, long invoiceNum, Date invoiceDate, String providerName, Integer peopleQuantity, double unitPrice, Integer tax) {
        this.id = id;
        this.invoiceNum = invoiceNum;
        this.invoiceDate = invoiceDate;
        this.providerName = providerName;
        this.peopleQuantity = peopleQuantity;
        this.unitPrice = unitPrice;
        this.tax = tax;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getInvoiceNum() {
        return invoiceNum;
    }

    public void setInvoiceNum(long invoiceNum) {
        this.invoiceNum = invoiceNum;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public Integer getPeopleQuantity() {
        return peopleQuantity;
    }

    public void setPeopleQuantity(Integer peopleQuantity) {
        this.peopleQuantity = peopleQuantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getTax() {
        return tax;
    }

    public void setTax(Integer tax) {
        this.tax = tax;
    }
}
