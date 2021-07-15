/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Worker;
import model.WorkerDAO;

/**
 *
 * @author excus
 */
public class MainServlet extends HttpServlet {

    private static final int MINSALARY = 0;
    private static final int MAXSALARY = 100;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getServletPath();

        switch (action) {
            case "/add":
        try {
                String message = "";
                String url = "/add.jsp";
                String fullname = request.getParameter("fullname");
                String id = request.getParameter("id");
                Double salary = Double.parseDouble(request.getParameter("salary"));

                if (id == null || id.trim().isEmpty()
                        || fullname == null || fullname.trim().isEmpty()
                        || id == null || id.trim().isEmpty()
                        || salary == null || salary.toString().trim().isEmpty()) {

                    throw new Exception("there is an input error");
                }

                //Using DAO
                Worker worker = new Worker(id, fullname, salary);
                boolean result = WorkerDAO.addWorker(worker);
                if (result) {
                    request.setAttribute("message", "== New Worker to be Added:" + worker);
                    request.setAttribute("Worker", worker);
                } else {
                    request.setAttribute("message", "== Cannot add new worker to DB.");
                    request.setAttribute("Worker", " Input data error");
                }

            } catch (Exception ex) {
                request.setAttribute("searchmessage", "== Something is wrong when searching: " + ex.getMessage());
            }
            getServletContext().getRequestDispatcher("/add.jsp").forward(request, response);

            break;

            case "/search":

                String topworker = "";
                double maxsalary = 0.0;
                int totalworkers = 0;
                double total = 0.0;

                int minsal = MINSALARY;
                int maxsal = MAXSALARY;
                try {
                    if (request.getParameter("minsal") != null
                            && !request.getParameter("minsal").trim().isEmpty()) {
                        minsal = Integer.parseInt(request.getParameter("minsal"));
                    }
                    if (request.getParameter("maxsal") != null
                            && !request.getParameter("maxsal").trim().isEmpty()) {
                        maxsal = Integer.parseInt(request.getParameter("maxsal"));
                    }
                    if (minsal < MINSALARY) {
                        minsal = MINSALARY;
                    }
                    if (maxsal > MAXSALARY) {
                        maxsal = MAXSALARY;
                    }
                    ArrayList<Worker> list = new ArrayList<>(); //or null?
                    String salary = "";

                    list = WorkerDAO.selectSearchSalaryWorkers(minsal, maxsal);

                    request.setAttribute("message", "== Find workers (" + salary + ", salary " + minsal + "-" + maxsal + "):");
                    request.setAttribute("list", list);

                    for (Worker w : list) {
                        total += w.getSalary();
                        totalworkers++;

                        if (w.getSalary() > maxsalary) {
                            maxsalary = w.getSalary();
                            topworker = w.getFullname();
                        }
                    }

                    request.setAttribute("totalworkers", totalworkers);
                    request.setAttribute("maxsalary", maxsalary);
                    request.setAttribute("topworker", topworker);
                    request.setAttribute("averagesalary", String.format("%.2f", total / list.size()));

                } catch (Exception e) {
                    request.setAttribute("message", "== Can't search, input error. ==");
                }
                break;
            default:
                response.sendRedirect("index.html");
                return;
        }

        getServletContext().getRequestDispatcher("/search.jsp").forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
