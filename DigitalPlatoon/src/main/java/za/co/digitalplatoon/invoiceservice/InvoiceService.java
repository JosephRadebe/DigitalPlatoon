package za.co.digitalplatoon.invoiceservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.digitalplatoon.invoice.Invoice;
import za.co.digitalplatoon.invoicerepo.InvoiceRepo;

import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepo invoiceRepo;

    public Invoice addInvoice(Invoice invoice)
    {
        return invoiceRepo.save(invoice);
    }

    public Iterable<Invoice> viewAllInvoices()
    {
        return invoiceRepo.findAll();
    }

    public Optional<Invoice> viewInvoice(Long id)
    {
        return invoiceRepo.findById(id);
    }
}
