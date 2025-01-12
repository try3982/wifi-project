//package public_wifi_project.controller;
//
//import dao.WifiDao;
//import dto.WifiInfo;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "WifiServlet", value = "/wifi-servlet")
//public class WifiServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        try {
//            // 파라미터 검증
//            String latParam = request.getParameter("lat");
//            String lntParam = request.getParameter("lnt");
//
//            if (latParam == null || lntParam == null) {
//                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "위도(LAT)와 경도(LNT)는 필수입니다.");
//                return;
//            }
//
//            double lat = Double.parseDouble(latParam);
//            double lnt = Double.parseDouble(lntParam);
//
//            // WiFi 데이터 검색
//            WifiDao wifiDao = new WifiDao();
//            List<WifiInfo> wifiList = wifiDao.findNearestWifi(lat, lnt);
//
//            // 검색 결과가 없는 경우 처리
//            if (wifiList == null || wifiList.isEmpty()) {
//                request.setAttribute("message", "근처 WiFi 정보를 찾을 수 없습니다.");
//            } else {
//                request.setAttribute("wifiList", wifiList);
//            }
//
//            // JSP로 포워딩
//            RequestDispatcher dispatcher = request.getRequestDispatcher("wifi_list.jsp");
//            dispatcher.forward(request, response);
//
//        } catch (NumberFormatException e) {
//            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "위도와 경도는 숫자여야 합니다.");
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버에서 데이터를 처리하는 동안 오류가 발생했습니다.");
//        }
//    }
//}
