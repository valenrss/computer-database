package validator;

import java.util.Date;

import org.springframework.stereotype.Component;

import exception.ComputerNameEmptyException;
import exception.DateOrderException;

@Component
public class Validator {



	public void checkDate(Date dateIntroduced, Date dateDiscontinued) throws DateOrderException {

		if (dateIntroduced != null && dateDiscontinued != null && dateIntroduced.after(dateDiscontinued)) {
			throw new DateOrderException(
					"Date " + dateIntroduced.toString() + " must be after " + dateDiscontinued.toString());
		}

	}

	public void checkName(String name) throws ComputerNameEmptyException {
		if (name == null || "".equals(name)) {
			throw new ComputerNameEmptyException("Name must not be empty");
		}
	}

}
