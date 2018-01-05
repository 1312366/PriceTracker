package dto;

/**
 *
 * @author Minh Tran
 */
public class PriceDTO {
    int lowestPrice;
    int avgPrice;
    int highestPrice;
    int price;
    String dateUpdated;
    String url;
    
    public PriceDTO(){
        this.price =0;
        this.avgPrice=0;
        this.highestPrice=0;
        this.lowestPrice=0;
        this.dateUpdated="";
        this.url="";
    }   

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public int getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(int lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public int getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(int avgPrice) {
        this.avgPrice = avgPrice;
    }

    public int getHighestPrice() {
        return highestPrice;
    }

    public void setHighestPrice(int highestPrice) {
        this.highestPrice = highestPrice;
    }
    
}
