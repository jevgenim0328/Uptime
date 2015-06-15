package pack;
// Class Goods contain information about searched items.
public class Goods implements GoodsInterface {
	private String name;   // Goods name .
	private Double price;   // Price directly from amazon in original currency.
	private Double convertedPrice;  // Price in EUR.
	private String currencyBuy;  //  Currency name from amazon directly.
	private String currencyGet;  //  Second currency name to convert. In this project value is "EUR".
	private String pictureLink;  // Link to small image of good.
// ____Fist constructor___
	public Goods() {}

// _________________ One more constructor______________________	
	public Goods(String name,Double price,String currencyBuy, String pictureLink) {
		this.name = name;
		this.price = price;
		this.currencyBuy = currencyBuy;
		this.pictureLink = pictureLink;
	}
// Getters and setters and toString() methods.
	public String getName() {
		return name;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getConvertedPrice() {
		return convertedPrice;
	}

	public void setConvertedPrice(Double price) {
		this.convertedPrice = price;
	}

	public String getCurrencyBuy() {
		return currencyBuy;
	}

	public void setCurrencyBuy(String currencyBuy) {
		this.currencyBuy = currencyBuy;
	}

	public String getCurrencyGet() {
		return currencyGet;
	}

	public void setCurrencyGet(String currencyGet) {
		this.currencyGet = currencyGet;
	}

	@Override
	public String toString() {
		return "Goods [name=" + name + ", price=" + price + ", convertedPrice=" + convertedPrice + ", currencyBuy="
				+ currencyBuy + ", currencyGet=" + currencyGet + ", pictureLink=" + pictureLink + "]";
	}

	
}
