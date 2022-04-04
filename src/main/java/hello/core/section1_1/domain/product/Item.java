package hello.core.section1_1.domain.product;

public class Item {
    private  String itemName;
    private Integer price;
    private Integer count;
    private  Integer stockCount;

    public Item(String itemName, Integer price, Integer count, Integer stockCount) {
        this.itemName = itemName;
        this.price = price;
        this.count = count;
        this.stockCount = stockCount;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public Integer getStockCount() {
        return stockCount;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", price=" + price +
                ", count=" + count +
                ", stockCount=" + stockCount +
                '}';
    }
}
