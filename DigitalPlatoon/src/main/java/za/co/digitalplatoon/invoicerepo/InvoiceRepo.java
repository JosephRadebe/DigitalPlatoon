package za.co.digitalplatoon.invoicerepo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import za.co.digitalplatoon.invoice.Invoice;

@Repository
public interface InvoiceRepo extends CrudRepository<Invoice, Long> {

}
