package public_wifi_project.controller;

import dao.HistoryDao;
import dto.History;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "HistoryServlet", value = "/history-servlet")
public class HistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HistoryDao historyDao = new HistoryDao();
        List<History> historyList = historyDao.getAllHistory();
        request.setAttribute("historyList", historyList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("history_list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                HistoryDao historyDao = new HistoryDao();
                historyDao.deleteHistoryById(id);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "올바른 ID를 입력하세요.");
            }
        }
        doGet(request, response);
    }
}
