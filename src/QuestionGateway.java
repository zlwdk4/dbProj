import java.util.ArrayList;


public class QuestionGateway {
	public static ArrayList<Question> mock;
	private static DBAbstraction dba = new DBAbstraction();
	public QuestionGateway() {
		mock = dba.getQuestionsFromDB();
	}
	
	public int getQuestionsCount(){
		return dba.getQuestionCount();
	}
	/*
	static {
		mock = new ArrayList<Question>();
		mock.add(new Question(1,"What experiences have you had with functional programming?"));
		mock.add(new Question(2,"Are you reasonably confident you can complete the assignment now?"));
		mock.add(new Question(3,"What other Java topics would you like to discuss this semester?"));
	}
	*/

	public void addQuestion(String theQuestion) {
		dba.addQuestion(theQuestion);
		
	}
	
	public ArrayList<Question> getQuestionsFromDB(){
		return dba.getQuestionsFromDB(); 
	}
	

	public void mockAdd(Question q) {
		mock.add(q);
	}
}
