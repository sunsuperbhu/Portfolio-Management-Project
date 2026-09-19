package com.portfolioproject.model;
public class Stock extends Asset
{
	private double currentPrice;

	public Stock(String stockid, String stockname, double purchasePrice, double currentPrice)
	{
		super(stockid, stockname, purchasePrice);
		this.currentPrice = currentPrice;
	}

	@Override
	public double calculateCurrentValue() {
		return currentPrice;
	}

	public double getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}

	@Override
	public String toString() {
		return "Stock{" +
				"Asset ID='" + getAssetId() + '\'' +
				", Asset Name='" + getAssetName() + '\'' +
				", Purchase Price=" + getPurchasePrice() +
				", Current Price=" + currentPrice +
				'}';
	}
}