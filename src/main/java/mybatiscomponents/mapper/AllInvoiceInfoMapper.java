package mybatiscomponents.mapper;

import org.apache.ibatis.annotations.Mapper;
import mybatiscomponents.beans.*;

import java.util.List;

@Mapper
public interface AllInvoiceInfoMapper {

    public void insert(AllInvoiceInfo allInvoiceInfo);

    public void delete(AllInvoiceInfo allInvoiceInfo);

    public void update(AllInvoiceInfo allInvoiceInfo);

    public List<AllInvoiceInfo> find(AllInvoiceInfo allInvoiceInfo);

    public List<AllInvoiceInfo> findByElements(Invoice invoiceInfoForSearch);

}
