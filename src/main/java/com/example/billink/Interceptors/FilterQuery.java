package com.example.billink.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Enumeration;


//public class FilterQuery  implements HandlerInterceptor {
  //  @Override
 //   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
  //      String requestUri = request.getRequestURI();
   //     if (requestUri.contains("/graphql")) {
   //         System.out.println("Intercepting GraphQL request...");


//            Enumeration<String> headerNames = request.getHeaderNames();
  //          if (headerNames != null) {
   //             while (headerNames.hasMoreElements()) {
     //               String headerName = headerNames.nextElement();
       //             String headerValue = request.getHeader(headerName);
         //           System.out.println("Header:"+ headerName+ headerValue);
//                }
  //          }
    //    }
 //       return true; // Allow the request to proceed
   // }
//}
