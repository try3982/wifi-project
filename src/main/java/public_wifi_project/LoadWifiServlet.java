package public_wifi_project;

import dao.WifiApiFetcher;
import dao.WifiDao;
import dto.WifiInfo;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadWifiServlet", value = "/load-wifi")
public class LoadWifiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Open API에서 데이터를 가져옵니다.
            WifiApiFetcher fetcher = new WifiApiFetcher();
            List<WifiInfo> wifiList = fetcher.fetchAllWifiData();

            // 데이터를 데이터베이스에 저장합니다.
            WifiDao wifiDao = new WifiDao();
            int savedCount = wifiDao.save(wifiList);

            // 저장된 데이터 개수를 JSP에 전달합니다.
            request.setAttribute("savedCount", savedCount);

            // 성공 메시지를 표시하는 JSP로 포워딩합니다.
            RequestDispatcher dispatcher = request.getRequestDispatcher("load_wifi.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "데이터 처리 중 오류 발생");
        }
    }
}
