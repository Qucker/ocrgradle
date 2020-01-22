package mybatiscomponents.services;

import mybatiscomponents.beans.*;
import org.json.JSONObject;

import java.util.List;

public interface AllInvoiceInfoIService {

    public void insert(JSONObject input,String path);

    public void delete(AllInvoiceInfo allInvoiceInfo);

    public void update(AllInvoiceInfo allInvoiceInfo);

    public List<AllInvoiceInfo> find(AllInvoiceInfo allInvoiceInfo);

    public List<AllInvoiceInfo> findByElements(Invoice invoiceInfoForSearch);

}
