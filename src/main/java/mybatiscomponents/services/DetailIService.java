package mybatiscomponents.services;

import mybatiscomponents.beans.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DetailIService {

    public void delete(Invoice invoice);

    public void update(DetailUpdate detailUpdate);

    public List<Detail> findByElements(Invoice mainInvoiceInfo);

    public List<Detail> find(Invoice mainInvoiceInfo);

}
