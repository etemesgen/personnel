package personnel;

public class DateImpossible extends Exception {

	public DateImpossible()
	{
		System.err.println("La date d'arriv�e doit �tre inf�rieur � la date de d�part");
	}
}
