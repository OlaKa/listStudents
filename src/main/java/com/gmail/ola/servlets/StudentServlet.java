package com.gmail.ola.servlets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<!DOCTYPE html>");
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<link href=\"styles/default.css\" rel=\"stylesheet\" type=\"text/css\" />");
        writer.println("</head>");
        writer.println("<body>");
        writer.println("<div class=\"container\"  align=\"center\">");
        writer.println("<h1>JAVA16 Klasslista</h1>");
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>Namn</th>");
        writer.println("<th>Mail</th>");
        writer.println("</tr>");

        String res = getFile();
        String[] nameandadr = res.split("\n");
        for (String s : nameandadr) {
            String[] splitString = s.split(",");
            writer.println("<tr>");
            writer.println("<td>" + splitString[0] + "</td>");
            writer.println("<td>" + splitString[1] + "</td>");
            writer.println("</tr>");
        }
        writer.println("</table>");
        writer.println("</div>");
        writer.println("</body>");
        writer.println("</html>");
    }

    private String getFile() {

        URL urlandfile = getClass().getResource("/klasslista.csv");
        File file = new File(urlandfile.getPath());
        try (FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader)) {

            StringBuffer stringBuffer = new StringBuffer();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuffer.append(line);
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
