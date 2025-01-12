package public_wifi_project;

import api.WifiApiFetcher;
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

@WebServlet(name = "LoadWifiServlet", value = "/load-wifi")
public class LoadWifiServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int savedCount = 0;

        try {
            WifiApiFetcher fetcher = new WifiApiFetcher();
            List<WifiInfo> wifiList = fetcher.fetchAllWifiData();

            if (wifiList.isEmpty()) {
                throw new RuntimeException("가져온 WiFi 데이터가 없습니다.");
            }

            WifiDao wifiDao = new WifiDao();
            savedCount = wifiDao.save(wifiList);

            request.setAttribute("savedCount", savedCount);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "와이파이 데이터를 가져오는 중 오류가 발생했습니다.");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("load_wifi.jsp");
        dispatcher.forward(request, response);
    }
}
