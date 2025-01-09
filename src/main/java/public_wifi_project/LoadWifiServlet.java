package public_wifi_project;

import api.WifiApiFetcher;
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
        int savedCount = 0; // 저장된 데이터 개수 초기화
        try {
            // Open API에서 데이터를 가져옵니다.
            WifiApiFetcher fetcher = new WifiApiFetcher();
            List<WifiInfo> wifiList = fetcher.fetchAllWifiData();

            // 가져온 데이터 크기 확인
            System.out.println("가져온 WiFi 데이터 크기: " + wifiList.size());

            if (wifiList.isEmpty()) {
                throw new RuntimeException("가져온 WiFi 데이터가 없습니다.");
            }

            // 데이터를 데이터베이스에 저장
            WifiDao wifiDao = new WifiDao();
            savedCount = wifiDao.save(wifiList);

            // 저장된 데이터 개수 확인
            System.out.println("저장된 WiFi 데이터 개수: " + savedCount);

            // 저장된 데이터 개수를 JSP에 전달
            request.setAttribute("savedCount", savedCount);

        } catch (Exception e) {
            // 예외 발생 시 로그 출력 및 에러 메시지 전달
            System.err.println("오류 발생: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("errorMessage", "와이파이 데이터를 처리하는 중 오류가 발생했습니다.");
        }

        // 결과 페이지로 포워딩
        RequestDispatcher dispatcher = request.getRequestDispatcher("load_wifi.jsp");
        dispatcher.forward(request, response);
    }
}
