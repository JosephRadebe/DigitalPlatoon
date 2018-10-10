package za.co.digitalplatoon.invoice;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Invoice implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private String client;
    private Long vatRate;
    private Date invoiceDate;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "invoice")
    private Set<LineItem> lineItems = new HashSet<>();

    public Invoice()
    {}

    public Invoice(String client,Long vatRate, Date invoiceDate)
    {this.client=client;this.vatRate=vatRate;this.invoiceDate=invoiceDate;}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVatRate() {
        return vatRate;
    }

    public void setVatRate(Long vatRate) {
        this.vatRate = vatRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Set<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(Set<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public BigDecimal getSubTotal()
    {
        BigDecimal subTotal = BigDecimal.ZERO;

        for(LineItem lineItem: getLineItems())
        {
            subTotal.add(lineItem.getLineItemTotal());
        }
        return subTotal.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getVat()
    {
        BigDecimal vat = BigDecimal.ZERO;

        for(LineItem lineItem: getLineItems())
        {
            vat = lineItem.getLineItemTotal().multiply(new BigDecimal(vatRate));
        }
        return vat.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTotal()
    {

        return getSubTotal().add(getVat()).setScale(2, RoundingMode.HALF_UP);
    }


}
