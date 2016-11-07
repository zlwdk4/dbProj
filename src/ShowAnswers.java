import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Answers")
public class ShowAnswers extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static QuestionGateway questionGateway = new QuestionGateway();
	
    public ShowAnswers() {
        super();
    }

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		String questionID = request.getParameter("id");
		
		AnswerGateway aGate = new AnswerGateway(Integer.parseInt(questionID));

		
		out.println("<html>");
		out.println("<body>");

		out.println("<p>Answers for question " + questionID + "</p>");
		int count = 0;
		for(String s: aGate.mock){
			count++;
			out.println("<p>" + count + ". " + s);
		}

		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
