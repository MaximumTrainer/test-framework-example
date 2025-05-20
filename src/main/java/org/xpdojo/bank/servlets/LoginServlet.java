/*
 *
 * Copyright (c) 2019 xp-dojo organisation and committers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
