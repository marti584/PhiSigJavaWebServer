package com.PhiSig;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This class will particularly be where we find out information from the VMs as well as do any administrator panel stuff
 */
@Path("/API")
public final class API {


    @GET@Path("/getNumVMs")
    public static int getNumVMs() {
        //SQL Connection
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            connection = DataSource.getInstance().getConnection();
            stmt = connection.createStatement();
            String query = "SELECT COUNT(*) FROM vm";
            resultSet = stmt.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @GET@Path("/getNumVMsPoweredOn")
    public static int getNumVMsPoweredOn() {
        //SQL Connection
        Connection connection = null;
        Statement stmt = null;
        ResultSet resultSet = null;
        try {
            connection = DataSource.getInstance().getConnection();
            stmt = connection.createStatement();
            String query = "SELECT COUNT(*) FROM vm WHERE inUse = 1";
            resultSet = stmt.executeQuery(query);
            resultSet.next();
            return resultSet.getInt("COUNT(*)");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (stmt != null) try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @GET@Path("/getStorageForVM")
    public static String getStorageForVM() {
        return VMFactoryWin.getStorageForVM("vm-183");
    }

    @POST@Path("/getStorageForDatastore")
    public static String getStorageForDatastore(String s) {
        s = s.replace("[", "").replace("]", "").replace("\"", "");
        return VMFactoryWin.getStorageForDatastore(s);
    }

    @GET@Path("/getStorageForAllDatastores")
    public static String getStorageForDatastore() {
        return VMFactoryWin.getStorageForAllDatastores();
    }

    @GET@Produces(MediaType.APPLICATION_JSON)@Path("/getDatastores")
    public static Datastores getDatastoreNames() {
        ArrayList<DatastoreObject> ds = VMFactoryWin.getDatastores();
        Datastores dss = new Datastores();
        dss.setDatastores(ds);
        return dss;
    }

    private static ResultSet queryDB(String query) {
        try {
            Statement stmt = Main.connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public static User getUser() {
    
        return new User(1);
    }

    public static void setUser(User user) {
    
    }
  
}