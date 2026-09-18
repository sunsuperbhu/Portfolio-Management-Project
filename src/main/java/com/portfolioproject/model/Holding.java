package com.portfolioproject.model;

public class Holding {

    private String holdingId;

    private MutualFund asset;

    private int quantity;

    // Constructor
    public Holding(String holdingId,
                   MutualFund mutualFund,
                   int quantity) {

        this.holdingId = holdingId;
        this.asset = mutualFund;
        this.quantity = quantity;
    }

    // Getters

    public Holding(String stockHoldingId, Stock stock, int quantity2) {
		// TODO Auto-generated constructor stub
	}

	public String getHoldingId() {
        return holdingId;
    }

    public MutualFund getAsset() {
        return asset;
    }

    public int getQuantity() {
        return quantity;
    }

    // Calculate total current value
    public double getCurrentValue() {

        return asset.calculateCurrentValue() * quantity;
    }

    @Override
    public String toString() {

        return "Holding{" +
                "Holding ID='" + holdingId + '\'' +
                ", Asset=" + asset +
                ", Quantity=" + quantity +
                ", Current Value=" + getCurrentValue() +
                '}';
    }
}