package public_wifi_project.controller;

import dao.WifiDao;
import dto.WifiInfo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WifiServlet", value = "/wifi-servlet")
public class WifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try {
            // 사용자로부터 위도와 경도 값을 입력받음
            String latParam = request.getParameter("lat");
            String lntParam = request.getParameter("lnt");

            if (latParam == null || lntParam == null) {
                request.setAttribute("errorMessage", "위도와 경도 값이 필요합니다.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("wifi_list.jsp");
                dispatcher.forward(request, response);
                return;
            }

            double lat = Double.parseDouble(latParam);
            double lnt = Double.parseDouble(lntParam);

            // WiFi 데이터를 조회
            WifiDao wifiDao = new WifiDao();
            List<WifiInfo> wifiList = wifiDao.findNearestWifi(lat, lnt);

            // 조회 결과를 JSP로 전달
            request.setAttribute("wifiList", wifiList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("wifi_list.jsp");
            dispatcher.forward(request, response);

        } catch (NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "위도와 경도는 숫자여야 합니다.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("wifi_list.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버에서 데이터를 처리하는 동안 오류가 발생했습니다.");
        }
    }
}
