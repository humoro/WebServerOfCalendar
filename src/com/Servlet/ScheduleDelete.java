package com.Servlet;

import com.DBTool.DBUtils;
import com.DBTool.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

public class ScheduleDelete extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter(StringUtils.HttpScheduleIdKey);
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        int type = 0;
        try {
            Connection con= DBUtils.getConnection();
            Statement stmt=con.createStatement();
            //mysql数据库中的数据表，表名叫：demotable ，需要自己预先在数据库中进行创建，包含相应的字段和记录。
            String sql = "delete from " + StringUtils.schedulesTableName + " where " + StringUtils.scheduleTableIdKey + " = '" + id + "'";
            type = stmt.executeUpdate(sql);
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            DBUtils.Close();
            out.print(type);
            out.flush();
            out.close();
        }
    }
}
