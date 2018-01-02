package dto;

/**
 *
 * @author Minh Tran
 */
public class PriceDTO {
    int lowestPrice;
    int avgPrice;
    int highestPrice;
    public PriceDTO(){
        this.avgPrice=0;
        this.highestPrice=0;
        this.lowestPrice=0;
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
