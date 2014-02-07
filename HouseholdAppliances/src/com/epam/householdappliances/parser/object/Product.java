package com.epam.householdappliances.parser.object;

public final class Product {
	private String name;
	private String producer;
	private String model;
	private String dateIssue;
	private String color;
	private String missMessage;
	private float price;

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public String getDateIssue() {
		return dateIssue;
	}

	public void setDateIssue(String dateIssue) {
		this.dateIssue = dateIssue;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMissMessage() {
		return missMessage;
	}

	public void setMissMessage(String missMessage) {
		this.missMessage = missMessage;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result
				+ ((dateIssue == null) ? 0 : dateIssue.hashCode());
		result = prime * result
				+ ((missMessage == null) ? 0 : missMessage.hashCode());
		result = prime * result + ((model == null) ? 0 : model.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result
				+ ((producer == null) ? 0 : producer.hashCode());
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
		Product other = (Product) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (dateIssue == null) {
			if (other.dateIssue != null)
				return false;
		} else if (!dateIssue.equals(other.dateIssue))
			return false;
		if (missMessage == null) {
			if (other.missMessage != null)
				return false;
		} else if (!missMessage.equals(other.missMessage))
			return false;
		if (model == null) {
			if (other.model != null)
				return false;
		} else if (!model.equals(other.model))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (producer == null) {
			if (other.producer != null)
				return false;
		} else if (!producer.equals(other.producer))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getName() + " [name=" + name + ", producer="
				+ producer + ", model=" + model + ", dateIssue=" + dateIssue
				+ ", color=" + color + ", missMessage=" + missMessage
				+ ", price=" + price + "]";
	}

}
