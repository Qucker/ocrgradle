package mybatiscomponents.services;

import mybatiscomponents.beans.*;
import org.springframework.stereotype.Service;

import java.util.List;

public interface DetailIService {
    public List<Detail> findByElements(MainInvoiceInfo mainInvoiceInfo);
}
