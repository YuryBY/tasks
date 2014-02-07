package com.epam.stuffshop.validation;

public final class ValidationUtil {

	private String nameMessage;
	private String producerMessage;
	private String modelMessage;
	private String dateMessage;
	private String colorMessage;
	private String priceMessage;

	private boolean isNameValid;
	private boolean isProducerValid;
	private boolean isModelValid;
	private boolean isDateValid;
	private boolean isColorValid;
	private boolean isPriceValid;

	public ValidationUtil() {
	}

	public void isDateValid(String isDateValid) {
		this.isDateValid = Boolean.valueOf(isDateValid);
	}

	public void isNameValid(String isNameValid) {
		this.isNameValid = Boolean.valueOf(isNameValid);
	}

	public void isProducerValid(String isProducerValid) {
		this.isProducerValid = Boolean.valueOf(isProducerValid);
	}

	public void isModelValid(String isModelValid) {
		this.isModelValid = Boolean.valueOf(isModelValid);
	}

	public void isColorValid(String isColorValid) {
		this.isColorValid = Boolean.valueOf(isColorValid);
	}

	public void isPriceValid(String isPriceValid) {
		this.isPriceValid = Boolean.valueOf(isPriceValid);
	}

	public boolean validationResult() {
		return isNameValid && isProducerValid && isModelValid && isDateValid
				&& isColorValid && isPriceValid;

	}

	public String getNameMessage() {
		return nameMessage;
	}

	public String getProducerMessage() {
		return producerMessage;
	}

	public String getModelMessage() {
		return modelMessage;
	}

	public String getDateMessage() {
		return dateMessage;
	}

	public String getColorMessage() {
		return colorMessage;
	}

	public String getPriceMessage() {
		return priceMessage;
	}

	public void detectFailedMessages() {
		if (!isNameValid) {
			nameMessage = "You should specify Product name";
		}
		if (!isProducerValid) {
			producerMessage = "You should specify Product producer";
		}
		if (!isModelValid) {
			modelMessage = "Wrong model.Should be in xx000 format";
		}
		if (!isDateValid) {
			dateMessage = "Wrong date issue.Should be in 00-00-0000 format";
		}
		if (!isColorValid) {
			colorMessage = "You should specify Product color";
		}
		if (!isPriceValid) {
			priceMessage = "Wrong price format."
					+ "Should be missing massage or positive number price";
		}

	}

}
