package validator;

import java.util.Date;

import exception.ComputerNameEmptyException;
import exception.DateOrderException;

public class Validator {

	private static Validator validatorInstance;

	public void checkDate(Date dateIntroduced, Date dateDiscontinued) throws DateOrderException {

		if (dateIntroduced != null && dateDiscontinued != null && dateIntroduced.after(dateDiscontinued)) {
			throw new DateOrderException(
					"Date " + dateIntroduced.toString() + " must be after " + dateDiscontinued.toString());
		}

	}

	public void checkName(String name) throws ComputerNameEmptyException {
		if (name == null || name == "") {
			throw new ComputerNameEmptyException("Name must not be empty");
		}
	}

	public static Validator getInstance() {
		if (validatorInstance == null) {
			validatorInstance = new Validator();
		}
		return validatorInstance;
	}

}
