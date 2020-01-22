package mybatiscomponents.services;

import mybatiscomponents.beans.AllInvoiceInfo;
import mybatiscomponents.beans.Invoice;
import mybatiscomponents.beans.MainInvoiceInfo;

import java.util.List;

public interface MainInvoiceInfoIService {



    public List<MainInvoiceInfo> findByElements(Invoice invoiceInfoForSearch);

}
