package todo.servlet;

import todo.model.Task;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateStatusServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String status = request.getParameter("status");

        for (Task task : TaskServlet.tasks) {
            if (task.getId() == id) {
                task.setStatus(status);
                break;
            }
        }

        response.sendRedirect("tasks");
    }
}
