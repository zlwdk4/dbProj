import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.synth.SynthSpinnerUI;

@WebServlet("/Questions")
public class ShowQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionGateway gateway = new QuestionGateway();
    public ShowQuestions() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    gateway = new QuestionGateway();   
	    
		response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        
	        out.println("<html>");
	        out.println("<head>");
	        out.println("<title>Questions</title>");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<h1>Questions</h1>");
	        
	        ArrayList<Question> questions = gateway.getQuestionsFromDB();
	        System.out.println("running this " + questions.size());
	        int count = 0;
	        for (Question q : questions) {
	        	count++;
		        out.print("<p>" + count + ". <a href=\"Answers?id="+ Integer.toString(count) + " \">" + q.question + "</a></p>");	        	
	        }
	        out.println("<form method=\"POST\" action=\"Questions\">");
	        out.println("<p><input type=\"text\" name=\"theQuestion\" size=\"50\"> <input type=\"submit\" value=\"Add Question\"></p>");
	        out.println("</form>");
	        out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	        
	        String theQuestion = request.getParameter("theQuestion");
	        gateway.addQuestion(theQuestion);
	        //gateway = new QuestionGateway();
	        
	        response.reset();
	        //gateway.mock.add(new Question(gateway.getQuestionsCount(),theQuestion));
	        gateway = new QuestionGateway();
	        gateway.mock = gateway.getQuestionsFromDB();	
	        gateway.mockAdd(new Question(gateway.mock.size(), theQuestion ));
	       
	        System.out.println(gateway.getQuestionsFromDB().size());
	        
	        
	        
	        //request.getRequestDispatcher("Questions").include(request, response);
	        //doGet(request, response);
	        response.sendRedirect("/dbProj/Questions");
	        
	        
	}

}
