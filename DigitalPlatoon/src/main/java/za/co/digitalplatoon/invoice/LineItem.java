package za.co.digitalplatoon.invoice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class LineItem {

    @Id
    @GeneratedValue
    private Long id;
    private Long quantity;
    private String description;
    private BigDecimal unitPrice;

    public LineItem() {
    }

    public LineItem(Long quantity, String description, BigDecimal unitPrice) {
        this.quantity = quantity;
        this.description = description;
        this.unitPrice = unitPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getLineItemTotal()
    {
        BigDecimal total = BigDecimal.ZERO;

        total = this.unitPrice.multiply(new BigDecimal(quantity));

        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
