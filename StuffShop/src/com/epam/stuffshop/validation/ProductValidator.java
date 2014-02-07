package com.epam.stuffshop.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class ProductValidator {

	public boolean validateGood(String good) {
		boolean check = true;
		if (good.isEmpty()) {
			check = false;
		}
		return check;
	}

	public boolean validateProducer(String producer) {
		boolean check = true;
		if (producer.isEmpty()) {
			check = false;
		}
		return check;
	}

	public boolean validateModel(String model) {
		boolean check = true;
		if (model.isEmpty()) {
			check = false;
		} else {
			Pattern pattern = Pattern.compile("[a-zA-Z]{2}\\d{3}");
			Matcher matcher = pattern.matcher(model);
			if (!matcher.matches()) {
				check = false;
			}
		}
		return check;
	}

	public boolean validateDateOfIssue(String dateOfIssue) {
		boolean check = true;
		if (dateOfIssue.isEmpty()) {
			check = false;
		} else {
			Pattern pattern = Pattern
					.compile("(0[1-9]|[12][0-9]|3[01])[-](0[1-9]|1[012])[-](19|20)\\d\\d");
			Matcher matcher = pattern.matcher(dateOfIssue);
			if (!matcher.matches()) {
				check = false;
			}
		}
		return check;
	}

	public boolean validateColor(String color) {
		boolean check = true;
		if (color.isEmpty()) {
			check = false;
		}
		return check;
	}

	public boolean validatePrice(String price) {
		boolean check = true;
		if (price.isEmpty()) {
			check = false;
		} else {
			try {
				int priceInt = Integer.parseInt(price);
				if (priceInt <= 0) {
					check = false;
				}
			} catch (NumberFormatException nfe) {
				check = false;
			}
		}
		return check;
	}

	public boolean validateMissing(String miss) {
		boolean check = true;
		if (miss.isEmpty()) {
			check = false;
		}
		return check;
	}

}
