package com.intuit.craft.networth.model;

public class NetWorthCalculation {
	
	private double totalAssets = 0;
	private double totalLiabilities = 0;
	private double netWorth = 0;
	
	public NetWorthCalculation(double totalAssets, double totalLiabilities, double netWorth) {
		super();
		this.totalAssets = totalAssets;
		this.totalLiabilities = totalLiabilities;
		this.netWorth = netWorth;
	}
	
	public NetWorthCalculation(int totalAssets) {
		super();
		this.totalAssets = totalAssets;
	}


	public NetWorthCalculation() {
		super();
	}

	public double getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(double totalAssets) {
		this.totalAssets = totalAssets;
	}
	public double getTotalLiabilities() {
		return totalLiabilities;
	}
	public void setTotalLiabilities(double totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}
	public double getNetWorth() {
		return netWorth;
	}
	public void setNetWorth(double netWorth) {
		this.netWorth = netWorth;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(netWorth);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalAssets);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(totalLiabilities);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NetWorthCalculation other = (NetWorthCalculation) obj;
		if (Double.doubleToLongBits(netWorth) != Double.doubleToLongBits(other.netWorth))
			return false;
		if (Double.doubleToLongBits(totalAssets) != Double.doubleToLongBits(other.totalAssets))
			return false;
		if (Double.doubleToLongBits(totalLiabilities) != Double.doubleToLongBits(other.totalLiabilities))
			return false;
		return true;
	}

	

}
