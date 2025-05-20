package org.xpdojo.bank.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Collectors;

@WebServlet("/api/login")
public class LoginServlet extends HttpServlet {
    private static final Gson gson = new Gson();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String requestBody = request.getReader().lines().collect(Collectors.joining());
        LoginRequest loginRequest = gson.fromJson(requestBody, LoginRequest.class);
        
        // Simple authentication logic (replace with real authentication)
        boolean isValid = "testuser".equals(loginRequest.username) && 
                         "password123".equals(loginRequest.password);
        
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.success = isValid;
        loginResponse.message = isValid ? "Login successful" : "Invalid credentials";
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.print(gson.toJson(loginResponse));
        out.flush();
    }

    private static class LoginRequest {
        String username;
        String password;
    }

    private static class LoginResponse {
        boolean success;
        String message;
    }
}
