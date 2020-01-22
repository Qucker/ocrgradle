package mybatiscomponents.mapper;

import mybatiscomponents.beans.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MainInvoiceInfoMapper {

    public List<MainInvoiceInfo> findByElements(Invoice invoiceInfoForSearch);
}
