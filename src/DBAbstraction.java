
import java.sql.*;
import java.util.ArrayList;
import java.io.*;

public class DBAbstraction {
	private Connection con;
	private Statement stmt; 
	private int questionCount; 
	
	public DBAbstraction(){
		//connectDB();
		
		questionCount = getQuestionCountFromDB();
	}
	
	private int getQuestionCountFromDB() {
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(java.lang.ClassNotFoundException e){
			System.out.println(e);
			System.exit(0);
		}
		
		String query = "SELECT COUNT(*) FROM Questions";
		String url = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/zlwdk4";
		String userID = "zlwdk4"; 
		String password = "dntdCgBGP";
		try{
			con = DriverManager.getConnection(url, userID, password);
			stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			result.next();
			questionCount = result.getInt(1);
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return questionCount;
	}
	
	public ArrayList<Question> getQuestionsFromDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(java.lang.ClassNotFoundException e){
			System.out.println(e);
			System.exit(0);
		}
		
		String query = "SELECT COUNT(*) FROM Questions";
		String url = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/zlwdk4";
		String userID = "zlwdk4"; 
		String password = "dntdCgBGP";
		
		try {
			con = DriverManager.getConnection(url, userID, password);
			stmt = con.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			stmt.executeQuery(query);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ArrayList<Question> theQuestions = new ArrayList<Question>();
		int theCount = getQuestionCount();
		
		ResultSet rs = getQuestionsResultSet(); 
		for(int i=1; i <= theCount; ++i){
			try {
				rs.next();
				theQuestions.add(new Question(rs.getInt(1),rs.getString(2)));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		closeUp();
		
		return theQuestions;
	}

	private ResultSet getQuestionsResultSet() {
		String query = "SELECT * FROM Questions";
		ResultSet rs = null; 
		try{
			rs = stmt.executeQuery(query);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return rs;
	}

	private void connectDB() {
		String url = "jdbc:mysql://kc-sce-appdb01.kc.umkc.edu/zlwdk4";
		String userID = "zlwdk4"; 
		String password = "dntdCgBGP";
		

		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(java.lang.ClassNotFoundException e){
			System.out.println(e);
			System.exit(0);
		}
		
		try{
			con = DriverManager.getConnection(url, userID, password);
		}
		catch(SQLException sqle){
			sqle.printStackTrace();
		}
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getQuestionCount(){
		return questionCount;
	}

	public void addQuestion(String theQuestion) {
		connectDB();
		String query = "INSERT INTO Questions (question) VALUES (\""+ theQuestion + "\")" ;
		
		try {
			stmt.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("added");
		closeUp();
	}

	private void closeUp() {
		// TODO Auto-generated method stub
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ArrayList<String> getAnswersFromDB(int qID) {
		connectDB();
		ArrayList<String> theAnswersList = new ArrayList<String>();
		
		String query = "SELECT * FROM Answers WHERE question_id_fk = " + Integer.toString(qID);
		ResultSet rs = null;
		
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			while(rs.next()){
				theAnswersList.add(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return theAnswersList;
		
	}
	
	
	
	
}
