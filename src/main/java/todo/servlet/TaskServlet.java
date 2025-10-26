package todo.servlet;

import todo.model.Task;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class TaskServlet extends HttpServlet {
    public static List<Task> tasks = new ArrayList<>();
    private static int idCounter = 1;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        showTasksPage(response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        if (title != null && !title.isEmpty()) {
            tasks.add(new Task(idCounter++, title, "Not Started"));
        }
        showTasksPage(response);
    }

    private void showTasksPage(HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>To-Do List</title>");
        out.println("<link rel='stylesheet' href='style.css'></head><body>");
        out.println("<h1>To-Do List</h1>");

        // Form to add task
        out.println("<form action='tasks' method='post'>");
        out.println("<input type='text' name='title' placeholder='Add new task' required>");
        out.println("<button type='submit'>Add Task</button></form>");

        // Columns
        out.println("<div class='container'>");
        printColumn(out, "Not Started");
        printColumn(out, "Ongoing");
        printColumn(out, "Done");
        out.println("</div></body></html>");
    }

    private void printColumn(PrintWriter out, String status) {
        out.println("<div class='column'><h2>" + status + "</h2>");
        for (Task t : tasks) {
            if (t.getStatus().equals(status)) {
                out.println("<form action='updateStatus' method='post'>");
                out.println("<input type='hidden' name='id' value='" + t.getId() + "'>");
                out.println("<select name='status' onchange='this.form.submit()'>");
                out.println("<option value='Not Started'" + (status.equals("Not Started") ? " selected" : "") + ">Not Started</option>");
                out.println("<option value='Ongoing'" + (status.equals("Ongoing") ? " selected" : "") + ">Ongoing</option>");
                out.println("<option value='Done'" + (status.equals("Done") ? " selected" : "") + ">Done</option>");
                out.println("</select>");
                out.println("<span>" + t.getTitle() + "</span>");
                out.println("</form>");
            }
        }
        out.println("</div>");
    }
}
