package es.unileon.prg;

public class MainDate {

	public static void main(String[] args) {
		Date today;
		
		try {
			today = new Date(15,11,2020);
			System.out.println(today);
		} catch (DateException e) {
			System.out.println(e.getMessage());
		}
	}

}