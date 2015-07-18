package com.PhiSig;

import com.IN3.Configuration;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DataSource {

    private static DataSource     datasource;
    private ComboPooledDataSource cpds;

    private DataSource() throws IOException, SQLException, PropertyVetoException {
        String dbUrl = Configuration.getInstance().getConfig().get("MYSQL_DB_URL");
        String dbClass = "com.mysql.jdbc.Driver";
        String username = Configuration.getInstance().getConfig().get("MYSQL_ADMIN");
        String password = Configuration.getInstance().getConfig().get("MYSQL_PASS");
        //C3P0
        cpds = new ComboPooledDataSource();
        cpds.setDriverClass(dbClass); //loads the jdbc driver            
        cpds.setJdbcUrl(dbUrl);
        cpds.setUser(username);
        cpds.setPassword(password);

        // the settings below are optional -- c3p0 can work with defaults
       cpds.setMinPoolSize(5);
       cpds.setAcquireIncrement(5);
       cpds.setMaxPoolSize(20);
       cpds.setMaxStatements(180);

    }

    public static DataSource getInstance() throws IOException, SQLException, PropertyVetoException {
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public Connection getConnection() throws SQLException {
        return this.cpds.getConnection();
    }

}