package mybatiscomponents.mapper;

import org.apache.ibatis.annotations.Mapper;
import mybatiscomponents.beans.*;

import java.util.List;

@Mapper
public interface DetailMapper {

    public void delete(Invoice invoice);

    public void update(DetailUpdate detailUpdate);

    public List<Detail> findByElements(Invoice mainInvoiceInfo);

    public List<Detail> find(Invoice mainInvoiceInfo);
}
