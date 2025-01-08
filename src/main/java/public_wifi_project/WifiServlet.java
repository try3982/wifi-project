package public_wifi_project;

import api.ApiWifiFetcher;
import dto.WifiInfo;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "WifiServlet", value = "/wifi-servlet")
public class WifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // 1. API 데이터 가져오기
            ApiWifiFetcher fetcher = new ApiWifiFetcher();
            String jsonData = fetcher.fetchData("json", "TbPublicWifiInfo", 1, 20);

            // 2. JSON 데이터 파싱
            List<WifiInfo> wifiList = fetcher.parseJsonResponse(jsonData);

            // 3. JSP에 데이터 전달
            request.setAttribute("wifiList", wifiList);

            // 4. JSP로 포워딩
            RequestDispatcher dispatcher = request.getRequestDispatcher("wifi_list.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 처리 중 오류 발생");
        }
    }
}
