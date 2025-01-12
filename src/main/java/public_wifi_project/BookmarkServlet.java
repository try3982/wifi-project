//package public_wifi_project;
//
//import dao.BookmarkDao;
//import dto.Bookmark;
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
//@WebServlet(name = "BookmarkServlet", value = "/bookmark")
//public class BookmarkServlet extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        BookmarkDao bookmarkDao = new BookmarkDao();
//        List<Bookmark> bookmarkList = bookmarkDao.getAllBookmarks();
//
//        request.setAttribute("bookmarkList", bookmarkList);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("bookmark_list.jsp");
//        dispatcher.forward(request, response);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String name = request.getParameter("name");
//        int order = Integer.parseInt(request.getParameter("order"));
//
//        BookmarkDao bookmarkDao = new BookmarkDao();
//        int result = bookmarkDao.addBookGroup(name, order);
//
//        if (result > 0) {
//            System.out.println("Bookmark group successfully added.");
//        } else {
//            System.out.println("Failed to add bookmark group.");
//        }
//
//        // 북마크 그룹 리스트 페이지로 리다이렉트
//        response.sendRedirect("bookmark");
//    }
//
//}
