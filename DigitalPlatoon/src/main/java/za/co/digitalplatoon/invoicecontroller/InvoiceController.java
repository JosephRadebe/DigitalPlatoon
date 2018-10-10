package za.co.digitalplatoon.invoicecontroller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.digitalplatoon.invoice.Invoice;
import za.co.digitalplatoon.invoice.LineItem;
import za.co.digitalplatoon.invoiceservice.InvoiceService;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("/Invoice")
    public ResponseEntity addInvoice(@RequestBody String json) throws IOException, ParseException
    {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");

        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(json);

        Invoice invoice = new Invoice();

        if(!data.isNull())
        {
            String client = data.get("client").asText();
            Long vatRate = data.get("vatRate").asLong();
            Date invoiceDate=new SimpleDateFormat("dd/MM/yyyy").parse(data.get("invoiceDate").asText());
            invoice = new Invoice(client,vatRate,invoiceDate);


            for(LineItem lineItem: invoice.getLineItems())
            {
                Long quantity = data.get("quantity").asLong();
                lineItem.setQuantity(quantity);
                String description = data.get("description").asText();
                lineItem.setDescription(description);
                BigDecimal unitPrice = new BigDecimal(data.get("unitPrice").asText());
                lineItem.setUnitPrice(unitPrice);
            }

        }

        return new ResponseEntity<>(invoiceService.addInvoice(invoice), HttpStatus.CREATED);
    }

    @GetMapping("/viewAllInvoices")
    public Iterable<Invoice> viewAllInvoices()
    {
        return invoiceService.viewAllInvoices();
    }

    @GetMapping("/Invoices/{id}")
    public Optional<Invoice> viewInvoice(@PathVariable Long id)
    {
        return invoiceService.viewInvoice(id);
    }


}
