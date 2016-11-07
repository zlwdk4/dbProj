import java.util.ArrayList;

public class AnswerGateway {
	public static ArrayList<String> mock;
	private static DBAbstraction dba = new DBAbstraction();
	public AnswerGateway(int qID) {
		mock = dba.getAnswersFromDB(qID);
	}
}
