package com.laioffer.jupiter2.servlet;

import com.laioffer.jupiter2.entity.Item;
import com.laioffer.jupiter2.recommendation.ItemRecommender;
import com.laioffer.jupiter2.recommendation.RecommendationException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "RecommendationServlet", urlPatterns = {"/recommendation"})
public class RecommendationServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        ItemRecommender itemRecommender = new ItemRecommender();
        Map<String, List<Item>> itemMap;
        try {
            if (session == null) {
                itemMap = itemRecommender.recommendItemsByDefault();
            } else {
                String userId = (String) request.getSession().getAttribute("user_id");
                itemMap = itemRecommender.recommendItemsByUser(userId);
            }
        } catch (RecommendationException e) {
            throw new ServletException(e);
        }

        ServletUtil.writeItemMap(response, itemMap);
    }

}
