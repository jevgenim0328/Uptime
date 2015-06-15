package pack;

public interface GoodsInterface {
	String name = "";   // Goods name .
	Double price = 0.0;   // Price directly from amazon in original currency.
	Double convertedPrice = 0.0;  // Price in EUR.
	String currencyBuy = "";  //  Currency name from amazon directly.
	String currencyGet = "";  //  Second currency name to convert. In this project value is "EUR".
	String pictureLink ="";  // Link to small image of good.
}
