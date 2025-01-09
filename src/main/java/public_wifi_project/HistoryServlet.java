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
        try {
            // 히스토리 목록 조회
            HistoryDao historyDao = new HistoryDao();
            List<History> historyList = historyDao.getAllHistory();

            // 히스토리 목록을 JSP로 전달
            request.setAttribute("historyList", historyList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("history_list.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "히스토리 데이터를 처리하는 중 오류가 발생했습니다.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            if ("save".equals(action)) {
                saveHistory(request, response);
            } else if ("delete".equals(action)) {
                deleteHistory(request, response);
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "유효하지 않은 요청입니다.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "히스토리 데이터를 처리하는 중 오류가 발생했습니다.");
        }
    }

    private void saveHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 사용자가 입력한 위도와 경도 가져오기
            String latParam = request.getParameter("latitude");
            String lntParam = request.getParameter("longitude");

            if (latParam == null || lntParam == null || latParam.isEmpty() || lntParam.isEmpty()) {
                request.setAttribute("errorMessage", "위도와 경도 값이 필요합니다.");
                doGet(request, response);
                return;
            }

            double latitude = Double.parseDouble(latParam);
            double longitude = Double.parseDouble(lntParam);

            // 히스토리 저장
            HistoryDao historyDao = new HistoryDao();
            History history = new History();
            history.setLatitude(latitude);
            history.setLongitude(longitude);
            history.setQueryTime(LocalDateTime.now());
            history.setRemarks("사용자 입력 위치");

            int rowsSaved = historyDao.saveHistory(history);

            if (rowsSaved > 0) {
                System.out.println("히스토리 저장 성공!");
            }

            // 히스토리 목록 조회
            doGet(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "위도와 경도 값이 올바른 숫자여야 합니다.");
            doGet(request, response);
        }
    }

    private void deleteHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 삭제할 히스토리 ID 가져오기
            String idParam = request.getParameter("id");

            if (idParam == null || idParam.isEmpty()) {
                request.setAttribute("errorMessage", "삭제할 히스토리 ID가 필요합니다.");
                doGet(request, response);
                return;
            }

            long id = Long.parseLong(idParam);

            // 히스토리 삭제
            HistoryDao historyDao = new HistoryDao();
            int rowsDeleted = historyDao.deleteHistoryById((int) id);

            if (rowsDeleted > 0) {
                System.out.println("히스토리 삭제 성공!");
            }

            // 히스토리 목록 조회
            doGet(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "삭제할 히스토리 ID가 올바른 숫자여야 합니다.");
            doGet(request, response);
        }
    }
}
