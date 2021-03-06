package mybatiscomponents.mapper;

import org.apache.ibatis.annotations.Mapper;
import mybatiscomponents.beans.*;

import java.util.List;

@Mapper
public interface InvoiceMapper {
    public void insert(Invoice invoice);

    public void update(Invoice invoice);

    public void delete(Invoice invoice);

    public List<Invoice> findByElements(Invoice invoiceInfoForSearch);
}
