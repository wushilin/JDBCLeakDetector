package net.wushilin.jdbc.debug;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class WrappingConnection implements Connection {
    private Connection src;

    private long created = System.currentTimeMillis();
    private DebuggingDataSource ds;
    public WrappingConnection(DebuggingDataSource source, Connection src) {
        this.src = src;
        ds = source;
    }
    @Override
    public Statement createStatement() throws SQLException {
        return ds.wrap(this, src.createStatement());
    }

    @Override
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return ds.wrap(this, src.prepareStatement(sql));
    }

    @Override
    public CallableStatement prepareCall(String sql) throws SQLException {
        return ds.wrap(this, src.prepareCall(sql));
    }

    @Override
    public String nativeSQL(String sql) throws SQLException {
        return src.nativeSQL(sql);
    }

    @Override
    public void setAutoCommit(boolean autoCommit) throws SQLException {
        src.setAutoCommit(autoCommit);
    }

    @Override
    public boolean getAutoCommit() throws SQLException {
        return src.getAutoCommit();
    }

    @Override
    public void commit() throws SQLException {
        src.commit();
    }

    @Override
    public void rollback() throws SQLException {
        src.rollback();
    }

    @Override
    public void close() throws SQLException {
        src.close();
        ds.release(this);
    }

    @Override
    public boolean isClosed() throws SQLException {
        return src.isClosed();
    }

    @Override
    public DatabaseMetaData getMetaData() throws SQLException {
        return src.getMetaData();
    }

    @Override
    public void setReadOnly(boolean readOnly) throws SQLException {
        src.setReadOnly(readOnly);
    }

    @Override
    public boolean isReadOnly() throws SQLException {
        return src.isReadOnly();
    }

    @Override
    public void setCatalog(String catalog) throws SQLException {
        src.setCatalog(catalog);
    }

    @Override
    public String getCatalog() throws SQLException {
        return src.getCatalog();
    }

    @Override
    public void setTransactionIsolation(int level) throws SQLException {
        src.setTransactionIsolation(level);
    }

    @Override
    public int getTransactionIsolation() throws SQLException {
        return src.getTransactionIsolation();
    }

    @Override
    public SQLWarning getWarnings() throws SQLException {
        return src.getWarnings();
    }

    @Override
    public void clearWarnings() throws SQLException {
        src.clearWarnings();
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
        return ds.wrap(this, src.createStatement(resultSetType, resultSetConcurrency));
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return ds.wrap(this, src.prepareStatement(sql, resultSetType, resultSetConcurrency));
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        return ds.wrap(this, src.prepareCall(sql, resultSetType, resultSetConcurrency));
    }

    @Override
    public Map<String, Class<?>> getTypeMap() throws SQLException {
        return src.getTypeMap();
    }

    @Override
    public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
        src.setTypeMap(map);
    }

    @Override
    public void setHoldability(int holdability) throws SQLException {
        src.setHoldability(holdability);
    }

    @Override
    public int getHoldability() throws SQLException {
        return src.getHoldability();
    }

    @Override
    public Savepoint setSavepoint() throws SQLException {
        return src.setSavepoint();
    }

    @Override
    public Savepoint setSavepoint(String name) throws SQLException {
        return src.setSavepoint(name);
    }

    @Override
    public void rollback(Savepoint savepoint) throws SQLException {
        src.rollback(savepoint);
    }

    @Override
    public void releaseSavepoint(Savepoint savepoint) throws SQLException {
        src.releaseSavepoint(savepoint);
    }

    @Override
    public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return ds.wrap(this, src.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return ds.wrap(this, src.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency, int resultSetHoldability) throws SQLException {
        return ds.wrap(this, src.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability));
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
        return ds.wrap(this, src.prepareStatement(sql, autoGeneratedKeys));
    }

    @Override
    public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
        return ds.wrap(this, src.prepareStatement(sql, columnIndexes));
    }

    @Override
    public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
        return ds.wrap(this, src.prepareStatement(sql, columnNames));
    }

    @Override
    public Clob createClob() throws SQLException {
        return src.createClob();
    }

    @Override
    public Blob createBlob() throws SQLException {
        return src.createBlob();
    }

    @Override
    public NClob createNClob() throws SQLException {
        return src.createNClob();
    }

    @Override
    public SQLXML createSQLXML() throws SQLException {
        return src.createSQLXML();
    }

    @Override
    public boolean isValid(int timeout) throws SQLException {
        return src.isValid(timeout);
    }

    @Override
    public void setClientInfo(String name, String value) throws SQLClientInfoException {
        src.setClientInfo(name, value);
    }

    @Override
    public void setClientInfo(Properties properties) throws SQLClientInfoException {
        src.setClientInfo(properties);
    }

    @Override
    public String getClientInfo(String name) throws SQLException {
        return src.getClientInfo(name);
    }

    @Override
    public Properties getClientInfo() throws SQLException {
        return src.getClientInfo();
    }

    @Override
    public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
        return src.createArrayOf(typeName, elements);
    }

    @Override
    public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
        return src.createStruct(typeName, attributes);
    }

    @Override
    public void setSchema(String schema) throws SQLException {
        src.setSchema(schema);
    }

    @Override
    public String getSchema() throws SQLException {
        return src.getSchema();
    }

    @Override
    public void abort(Executor executor) throws SQLException {
        src.abort(executor);
    }

    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        src.setNetworkTimeout(executor, milliseconds);
    }

    @Override
    public int getNetworkTimeout() throws SQLException {
        return src.getNetworkTimeout();
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return src.unwrap(iface);
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return src.isWrapperFor(iface);
    }

    public String toString() {
        return String.format("[Created %d ms ago] "+this.getClass().getSimpleName()+"@" + System.identityHashCode(this) + "[ %s ]", System.currentTimeMillis() - created, src);
    }
}
