/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.testvaadin;

import com.vaadin.data.util.sqlcontainer.SQLContainer;
import com.vaadin.data.util.sqlcontainer.connection.*;
import com.vaadin.data.util.sqlcontainer.query.FreeformQuery;
import com.vaadin.data.util.sqlcontainer.query.TableQuery;
import com.vaadin.data.util.sqlcontainer.query.generator.DefaultSQLGenerator;
import com.vaadin.data.util.sqlcontainer.query.generator.OracleGenerator;
import java.sql.SQLException;

public class Oracle {

    private SimpleJDBCConnectionPool connectionPool;

    private DefaultSQLGenerator generator;

    public Oracle(String server, String user, String pwd) {

        try {//test change on oracle java
            //test comm 2
            connectionPool = new SimpleJDBCConnectionPool("com.mysql.jdbc.Driver",
                    "jdbc:mysql://localhost:3306/babyboard", "root",
                    "root", 2, 5);

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        generator = new DefaultSQLGenerator();

    }

    public SQLContainer queryTable(String tableName) {

        SQLContainer container = null;

        try {

            TableQuery tq = new TableQuery(tableName, connectionPool, generator);

            container = new SQLContainer(tq);

            System.out.println("container created for table " + tableName);

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return container;

    }

    public SQLContainer dataView(String viewName) {

        SQLContainer container = null;

        try {

            FreeformQuery tq = new FreeformQuery("select * from " + viewName, connectionPool);

            container = new SQLContainer(tq);

            System.out.println("container created for view " + viewName);

        } catch (SQLException e) {

            e.printStackTrace();

        }

        return container;

    }
}
