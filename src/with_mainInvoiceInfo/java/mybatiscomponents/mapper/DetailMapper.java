package mybatiscomponents.mapper;

import org.apache.ibatis.annotations.Mapper;
import mybatiscomponents.beans.*;

import java.util.List;

@Mapper
public interface DetailMapper {
    public List<Detail> findByElements(MainInvoiceInfo mainInvoiceInfo);
}
