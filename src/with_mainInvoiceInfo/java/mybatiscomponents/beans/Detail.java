package mybatiscomponents.beans;

public class Detail{
    private String Detail_Item;
    private double Detail_Amount;
    private float  Detail_Tax_Rate;
    private double Detail_Tax;

    public Detail(){};

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
}