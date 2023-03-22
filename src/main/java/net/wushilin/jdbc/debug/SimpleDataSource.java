package net.wushilin.jdbc.debug;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

public class SimpleDataSource implements DataSource {
    private String driverClass="";
    private String url = "";
    private String username = "";
    private String password = "";

    private PrintWriter writer;
    public SimpleDataSource() {

    }


    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return getConnection(username, password);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        try {
            Class.forName(driverClass);
        } catch(Exception ex) {
            throw new SQLException("Invalid driver " + driverClass);
        }
        Connection con = DriverManager.getConnection(
                url, username, password);
        return con;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return writer;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        this.writer = out;
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return -1;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        throw new SQLFeatureNotSupportedException();
    }


    private boolean isWrapperForThis(Class<?> iface)
    { return iface.isAssignableFrom( this.getClass() ); }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException
    {
        return isWrapperForThis( iface );
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException
    {
        if ( this.isWrapperForThis( iface ) )
            return (T) this;
        else
            throw new SQLException(this + " is not a wrapper for or implementation of " + iface.getName());
    }
}
