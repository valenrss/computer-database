package validator;

import java.util.Date;
import exception.DateOrderException;

public class Validator {
	
	private static Validator validatorInstance;

	public Validator() {
		
	}
	
	public void checkDate(Date dateIntroduced,Date dateDiscontinued) throws DateOrderException {
		
		if (dateIntroduced.after(dateDiscontinued)) {
			throw new DateOrderException();
		}
		
	}
	
	public static Validator getInstance() {
		if (validatorInstance == null) {
			validatorInstance = new Validator();
		}
		return validatorInstance;
	}

}
