package net.wushilin.jdbc.debug;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.io.File;
import java.util.Date;

public class DebuggingDataSource implements DataSource {
    private static Map<Connection, StackTraceElement[]> outstandingConnections = new HashMap<>();
    private static Map<Statement, StackTraceElement[]> outstandingStatements = new HashMap<>();
    private static Map<ResultSet, StackTraceElement[]> outstandingResultSet = new HashMap<>();


    private DataSource src;

    private Thread debugger;
    private void startThread() {
        debugger = new Thread() {
            public void run() {
                while (true) {
                    try {
                        File target = new File("/tmp/debug-data-source");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                        String fileTarget = "/tmp/datasource-" + sdf.format(new Date()) + ".txt";
                        if (target.exists() && target.isFile() && target.canWrite()) {
                            File dest = new File(fileTarget);
                            try(FileOutputStream fos = new FileOutputStream(dest);
                                PrintStream ps = new PrintStream(fos);) {
                                ps.println("Outstanding connections:");
                                Map<Connection, StackTraceElement[]> connections = copy(outstandingConnections);
                                for(Map.Entry<Connection, StackTraceElement[]> next:connections.entrySet()) {
                                    Connection conn = next.getKey();
                                    StackTraceElement[] value = next.getValue();
                                    ps.println("  Connection: " + conn);
                                    int index = 0;
                                    for(StackTraceElement nextStack:value) {
                                        boolean shouldPrint = index > 0 && !nextStack.getClassName().startsWith("net.wushilin.jdbc.debug");
                                        if(shouldPrint) {
                                            ps.println("    at " + nextStack);
                                        }
                                        index++;
                                    }
                                }

                                ps.println("Outstanding statements:");
                                Map<Statement, StackTraceElement[]> statements = copy(outstandingStatements);
                                for(Map.Entry<Statement, StackTraceElement[]> next:statements.entrySet()) {
                                    Statement stmt = next.getKey();
                                    StackTraceElement[] value = next.getValue();
                                    ps.println("  Statement: " + stmt);
                                    int index = 0;
                                    for(StackTraceElement nextStack:value) {
                                        boolean shouldPrint = index > 0 && !nextStack.getClassName().startsWith("net.wushilin.jdbc.debug");
                                        if(shouldPrint) {
                                            ps.println("    at " + nextStack);
                                        }
                                        index++;
                                    }
                                }

                                ps.println("Outstanding resultsets:");
                                Map<ResultSet, StackTraceElement[]> resultSets = copy(outstandingResultSet);
                                for(Map.Entry<ResultSet, StackTraceElement[]> next:resultSets.entrySet()) {
                                    ResultSet rs = next.getKey();
                                    StackTraceElement[] value = next.getValue();
                                    ps.println("  ResultSet: " + rs);
                                    int index = 0;
                                    for(StackTraceElement nextStack:value) {
                                        boolean shouldPrint = index > 0 && !nextStack.getClassName().startsWith("net.wushilin.jdbc.debug");
                                        if(shouldPrint) {
                                            ps.println("    at " + nextStack);
                                        }
                                        index++;
                                    }
                                }
                            }
                            target.delete();
                        }
                        Thread.sleep(5000);
                    } catch(Throwable ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        System.out.println("Debugger thread started. Please touch /tmp/debug-data-source to trigger log to /tmp folder!");
        debugger.setDaemon(true);
        debugger.start();
    }
    public DebuggingDataSource(DataSource source) {
        src = source;
        startThread();
    }

    private String url;
    private String driverClass;
    private String username;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
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

    public DebuggingDataSource() {
        startThread();
    }

    private void init() {
        SimpleDataSource theSrc = new SimpleDataSource();
        theSrc.setDriverClass(driverClass);
        theSrc.setUrl(url);
        theSrc.setUsername(username);
        theSrc.setPassword(password);

        src = theSrc;
    }

    private <K,V> Map<K,V> copy(Map<K, V> input) {
        HashMap<K, V> what = new HashMap<>();
        what.putAll(input);
        return what;
    }

    @Override
    public synchronized  Connection getConnection() throws SQLException {
        if(src == null) {
            init();
        }
        return wrap(src.getConnection());
    }


    protected ResultSet wrap(Statement stmt, ResultSet what) {
        try {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            record(what, elements);
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
        return new WrappingResultSet(this, stmt, what);
    }

    protected Statement wrap(Connection conn, Statement stmt) {
        try {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            record(stmt, elements);
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
        return new WrappingStatement(this, conn, stmt);
    }

    protected PreparedStatement wrap(Connection conn, PreparedStatement stmt) {
        try {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            record(stmt, elements);
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
        return new WrappingPreparedStatement(this, conn, stmt);
    }
    protected CallableStatement wrap(Connection conn, CallableStatement stmt) {
        try {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            record(stmt, elements);
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
        return new WrappingCallableStatement(this, conn, stmt);
    }

    private Connection wrap(Connection result) {
        try {
            StackTraceElement[] elements = Thread.currentThread().getStackTrace();
            record(result, elements);
        } catch(Throwable ex) {
            ex.printStackTrace();
        }
        return new WrappingConnection(this, result);
    }

    protected synchronized void record(Connection conn, StackTraceElement[] trace) {
        outstandingConnections.put(conn, trace);
    }

    protected synchronized void record(Statement stmt, StackTraceElement[] trace) {
        outstandingStatements.put(stmt, trace);
    }

    protected synchronized void record(ResultSet rs, StackTraceElement[] trace) {
        outstandingResultSet.put(rs, trace);
    }

    protected synchronized void release(Connection conn) {
        outstandingConnections.remove(conn);
    }

    protected synchronized void release(ResultSet rs) {
        outstandingResultSet.remove(rs);
    }

    protected synchronized void release(Statement stmt) {
        outstandingStatements.remove(stmt);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return wrap(src.getConnection(username, password));
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return src.getLogWriter();
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {
        src.setLogWriter(out);
    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {
        src.setLoginTimeout(seconds);
    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return src.getLoginTimeout();
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return src.getParentLogger();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return src.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return src.isWrapperFor(iface);
    }

}
