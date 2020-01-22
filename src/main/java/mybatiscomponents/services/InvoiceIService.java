package mybatiscomponents.services;

import mybatiscomponents.beans.*;

import java.util.List;

public interface InvoiceIService {
    public void insert(Invoice invoice);

    public void update(InvoiceUpdate invoice);

    public List<AllInvoiceInfo> find(Invoice invoice);

    public void delete(Invoice invoice);

    public List<Invoice> findByElements(Invoice invoiceInfoForSearch);
}
