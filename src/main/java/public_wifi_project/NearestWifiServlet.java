package public_wifi_project;

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

@WebServlet(name = "NearestWifiServlet", value = "/nearest-wifi")
public class NearestWifiServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        double lat = Double.parseDouble(request.getParameter("lat"));
//        double lnt = Double.parseDouble(request.getParameter("lnt"));
//
//        try {
//            WifiDao wifiDao = new WifiDao();
//            List<WifiInfo> nearestWifiList = wifiDao.findNearestWifi(lat, lnt);
//
//            request.setAttribute("wifiList", nearestWifiList);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("wifi_list.jsp");
//            dispatcher.forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "와이파이 정보를 가져오는 중 오류가 발생했습니다.");
//        }
    }
}
