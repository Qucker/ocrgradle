package mybatiscomponents.beans;

import java.io.Serializable;
import java.util.List;

import mybatiscomponents.beans.*;

public class AllInvoiceInfo implements Serializable{

    private String Invoice_Code;
    private String Invoice_Num;
    private String Invoice_Date;
    private String Check_Code;
    private String Purchaser_Name;
    private String Purchaser_Tax_Id;
    private String Purchaser_Add_Tel;
    private String Purchaser_Bank;
    private String Seller_Name;
    private String Seller_Tax_Id;
    private String Seller_Add_Tel;
    private String Seller_Bank;
    private double Total_Amount;
    private String Image_Path;

    private List<Detail> details;

    public AllInvoiceInfo(){}

    public String getInvoice_Code() {
        return Invoice_Code;
    }

    public void setInvoice_Code(String invoice_Code) {
        Invoice_Code = invoice_Code;
    }

    public String getInvoice_Num() {
        return Invoice_Num;
    }

    public void setInvoice_Num(String invoice_Num) {
        Invoice_Num = invoice_Num;
    }

    public String getInvoice_Date() {
        return Invoice_Date;
    }

    public void setInvoice_Date(String invoice_Date) {
        Invoice_Date = invoice_Date;
    }

    public String getCheck_Code() {
        return Check_Code;
    }

    public void setCheck_Code(String check_Code) {
        Check_Code = check_Code;
    }

    public String getPurchaser_Name() {
        return Purchaser_Name;
    }

    public void setPurchaser_Name(String purchaser_Name) {
        Purchaser_Name = purchaser_Name;
    }

    public String getPurchaser_Tax_Id() {
        return Purchaser_Tax_Id;
    }

    public void setPurchaser_Tax_Id(String purchaser_Tax_Id) {
        Purchaser_Tax_Id = purchaser_Tax_Id;
    }

    public String getPurchaser_Add_Tel() {
        return Purchaser_Add_Tel;
    }

    public void setPurchaser_Add_Tel(String purchaser_Add_Tel) {
        Purchaser_Add_Tel = purchaser_Add_Tel;
    }

    public String getPurchaser_Bank() {
        return Purchaser_Bank;
    }

    public void setPurchaser_Bank(String purchaser_Bank) {
        Purchaser_Bank = purchaser_Bank;
    }

    public String getSeller_Name() {
        return Seller_Name;
    }

    public void setSeller_Name(String seller_Name) {
        Seller_Name = seller_Name;
    }

    public String getSeller_Tax_Id() {
        return Seller_Tax_Id;
    }

    public void setSeller_Tax_Id(String seller_Tax_Id) {
        Seller_Tax_Id = seller_Tax_Id;
    }

    public String getSeller_Add_Tel() {
        return Seller_Add_Tel;
    }

    public void setSeller_Add_Tel(String seller_Add_Tel) {
        Seller_Add_Tel = seller_Add_Tel;
    }

    public String getSeller_Bank() {
        return Seller_Bank;
    }

    public void setSeller_Bank(String seller_Bank) {
        Seller_Bank = seller_Bank;
    }

    public double getTotal_Amount() {
        return Total_Amount;
    }

    public void setTotal_Amount(double total_Amount) {
        Total_Amount = total_Amount;
    }

    public String getImage_Path() {
        return Image_Path;
    }

    public void setImage_Path(String image_Path) {
        Image_Path = image_Path;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }
}
