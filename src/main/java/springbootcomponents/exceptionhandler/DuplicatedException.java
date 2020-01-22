package springbootcomponents.exceptionhandler;


public class DuplicatedException extends RuntimeException{

    String invoiceCode;
    String invoiceNum;
    public DuplicatedException(String invoicecode,String invoicenum){
        invoiceCode=invoicecode;
        invoiceNum=invoicenum;
    }
    public String getInvoiceCode(){
        return invoiceCode;
    }
    public String getInvoiceNum(){
        return invoiceNum;
    }
    public void setInvoiceCode(String invoicecode){
        invoiceCode=invoicecode;
    }
    public void setInvoiceNum(String invoicenum){
        invoiceNum=invoiceNum;
    }
}

