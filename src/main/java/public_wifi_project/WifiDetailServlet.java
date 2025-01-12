//package public_wifi_project;
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
//
//@WebServlet(name = "WifiDetailServlet", value = "/wifi-detail")
//public class WifiDetailServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String manageNum = request.getParameter("manageNum");
//
//        try {
//            WifiDao wifiDao = new WifiDao();
//            WifiInfo wifiDetail = wifiDao.findByManageNum(manageNum);
//
//            request.setAttribute("wifiDetail", wifiDetail);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("wifi_detail.jsp");
//            dispatcher.forward(request, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "와이파이 상세 정보를 가져오는 중 오류가 발생했습니다.");
//        }
//    }
//}
