package mybatiscomponents.beans;

import java.io.Serializable;
import java.util.List;
import mybatiscomponents.beans.*;
public class Invoice implements Serializable{

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
    private double Total_Amount=-1;
    private String Detail_Item;
    private double Detail_Amount=-1;
    private float  Detail_Tax_Rate=-1;
    private double Detail_Tax=-1;
    private String Image_Path;

    public Invoice(){}

    public Invoice(String invoice_Code, String invoice_Num, String invoice_Date, String check_Code, String purchaser_Name, String purchaser_Tax_Id, String purchaser_Add_Tel, String purchaser_Bank, String seller_Name, String seller_Tax_Id, String seller_Add_Tel, String seller_Bank, double total_Amount, String detail_Item, double detail_Amount, float detail_Tax_Rate, double detail_Tax, String image_Path) {
        Invoice_Code = invoice_Code;
        Invoice_Num = invoice_Num;
        Invoice_Date = invoice_Date;
        Check_Code = check_Code;
        Purchaser_Name = purchaser_Name;
        Purchaser_Tax_Id = purchaser_Tax_Id;
        Purchaser_Add_Tel = purchaser_Add_Tel;
        Purchaser_Bank = purchaser_Bank;
        Seller_Name = seller_Name;
        Seller_Tax_Id = seller_Tax_Id;
        Seller_Add_Tel = seller_Add_Tel;
        Seller_Bank = seller_Bank;
        Total_Amount = total_Amount;
        Detail_Item = detail_Item;
        Detail_Amount = detail_Amount;
        Detail_Tax_Rate = detail_Tax_Rate;
        Detail_Tax = detail_Tax;
        Image_Path = image_Path;
    }

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

    public String getDetail_Item() {
        return Detail_Item;
    }

    public void setDetail_Item(String detail_Item) {
        Detail_Item = detail_Item;
    }

    public double getDetail_Amount() {
        return Detail_Amount;
    }

    public void setDetail_Amount(double detail_Amount) {
        Detail_Amount = detail_Amount;
    }

    public float getDetail_Tax_Rate() {
        return Detail_Tax_Rate;
    }

    public void setDetail_Tax_Rate(float detail_Tax_Rate) {
        Detail_Tax_Rate = detail_Tax_Rate;
    }

    public double getDetail_Tax() {
        return Detail_Tax;
    }

    public void setDetail_Tax(double detail_Tax) {
        Detail_Tax = detail_Tax;
    }

    public String getImage_Path() {
        return Image_Path;
    }

    public void setImage_Path(String image_Path) {
        Image_Path = image_Path;
    }
}
