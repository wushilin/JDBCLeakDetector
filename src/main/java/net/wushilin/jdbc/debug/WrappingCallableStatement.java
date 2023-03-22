package net.wushilin.jdbc.debug;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.Callable;

public class WrappingCallableStatement implements  CallableStatement{
    private DebuggingDataSource ds;
    private CallableStatement src;

    private long created = System.currentTimeMillis();
    private Connection conn;
    public WrappingCallableStatement(DebuggingDataSource ds, Connection conn, CallableStatement stmt) {
        this.ds = ds;
        src = stmt;
        this.conn = conn;
    }

    public void registerOutParameter(int parameterIndex, int sqlType) throws SQLException {
        src.registerOutParameter(parameterIndex, sqlType);
    }

    public void registerOutParameter(int parameterIndex, int sqlType, int scale) throws SQLException {
        src.registerOutParameter(parameterIndex, sqlType, scale);
    }

    public boolean wasNull() throws SQLException {
        return src.wasNull();
    }

    public String getString(int parameterIndex) throws SQLException {
        return src.getString(parameterIndex);
    }

    public boolean getBoolean(int parameterIndex) throws SQLException {
        return src.getBoolean(parameterIndex);
    }

    public byte getByte(int parameterIndex) throws SQLException {
        return src.getByte(parameterIndex);
    }

    public short getShort(int parameterIndex) throws SQLException {
        return src.getShort(parameterIndex);
    }

    public int getInt(int parameterIndex) throws SQLException {
        return src.getInt(parameterIndex);
    }

    public long getLong(int parameterIndex) throws SQLException {
        return src.getLong(parameterIndex);
    }

    public float getFloat(int parameterIndex) throws SQLException {
        return src.getFloat(parameterIndex);
    }

    public double getDouble(int parameterIndex) throws SQLException {
        return src.getDouble(parameterIndex);
    }

    @Deprecated
    public BigDecimal getBigDecimal(int parameterIndex, int scale) throws SQLException {
        return src.getBigDecimal(parameterIndex, scale);
    }

    public byte[] getBytes(int parameterIndex) throws SQLException {
        return src.getBytes(parameterIndex);
    }

    public Date getDate(int parameterIndex) throws SQLException {
        return src.getDate(parameterIndex);
    }

    public Time getTime(int parameterIndex) throws SQLException {
        return src.getTime(parameterIndex);
    }

    public Timestamp getTimestamp(int parameterIndex) throws SQLException {
        return src.getTimestamp(parameterIndex);
    }

    public Object getObject(int parameterIndex) throws SQLException {
        return src.getObject(parameterIndex);
    }

    public BigDecimal getBigDecimal(int parameterIndex) throws SQLException {
        return src.getBigDecimal(parameterIndex);
    }

    public Object getObject(int parameterIndex, Map<String, Class<?>> map) throws SQLException {
        return src.getObject(parameterIndex, map);
    }

    public Ref getRef(int parameterIndex) throws SQLException {
        return src.getRef(parameterIndex);
    }

    public Blob getBlob(int parameterIndex) throws SQLException {
        return src.getBlob(parameterIndex);
    }

    public Clob getClob(int parameterIndex) throws SQLException {
        return src.getClob(parameterIndex);
    }

    public Array getArray(int parameterIndex) throws SQLException {
        return src.getArray(parameterIndex);
    }

    public Date getDate(int parameterIndex, Calendar cal) throws SQLException {
        return src.getDate(parameterIndex, cal);
    }

    public Time getTime(int parameterIndex, Calendar cal) throws SQLException {
        return src.getTime(parameterIndex, cal);
    }

    public Timestamp getTimestamp(int parameterIndex, Calendar cal) throws SQLException {
        return src.getTimestamp(parameterIndex, cal);
    }

    public void registerOutParameter(int parameterIndex, int sqlType, String typeName) throws SQLException {
        src.registerOutParameter(parameterIndex, sqlType, typeName);
    }

    public void registerOutParameter(String parameterName, int sqlType) throws SQLException {
        src.registerOutParameter(parameterName, sqlType);
    }

    public void registerOutParameter(String parameterName, int sqlType, int scale) throws SQLException {
        src.registerOutParameter(parameterName, sqlType, scale);
    }

    public void registerOutParameter(String parameterName, int sqlType, String typeName) throws SQLException {
        src.registerOutParameter(parameterName, sqlType, typeName);
    }

    public URL getURL(int parameterIndex) throws SQLException {
        return src.getURL(parameterIndex);
    }

    public void setURL(String parameterName, URL val) throws SQLException {
        src.setURL(parameterName, val);
    }

    public void setNull(String parameterName, int sqlType) throws SQLException {
        src.setNull(parameterName, sqlType);
    }

    public void setBoolean(String parameterName, boolean x) throws SQLException {
        src.setBoolean(parameterName, x);
    }

    public void setByte(String parameterName, byte x) throws SQLException {
        src.setByte(parameterName, x);
    }

    public void setShort(String parameterName, short x) throws SQLException {
        src.setShort(parameterName, x);
    }

    public void setInt(String parameterName, int x) throws SQLException {
        src.setInt(parameterName, x);
    }

    public void setLong(String parameterName, long x) throws SQLException {
        src.setLong(parameterName, x);
    }

    public void setFloat(String parameterName, float x) throws SQLException {
        src.setFloat(parameterName, x);
    }

    public void setDouble(String parameterName, double x) throws SQLException {
        src.setDouble(parameterName, x);
    }

    public void setBigDecimal(String parameterName, BigDecimal x) throws SQLException {
        src.setBigDecimal(parameterName, x);
    }

    public void setString(String parameterName, String x) throws SQLException {
        src.setString(parameterName, x);
    }

    public void setBytes(String parameterName, byte[] x) throws SQLException {
        src.setBytes(parameterName, x);
    }

    public void setDate(String parameterName, Date x) throws SQLException {
        src.setDate(parameterName, x);
    }

    public void setTime(String parameterName, Time x) throws SQLException {
        src.setTime(parameterName, x);
    }

    public void setTimestamp(String parameterName, Timestamp x) throws SQLException {
        src.setTimestamp(parameterName, x);
    }

    public void setAsciiStream(String parameterName, InputStream x, int length) throws SQLException {
        src.setAsciiStream(parameterName, x, length);
    }

    public void setBinaryStream(String parameterName, InputStream x, int length) throws SQLException {
        src.setBinaryStream(parameterName, x, length);
    }

    public void setObject(String parameterName, Object x, int targetSqlType, int scale) throws SQLException {
        src.setObject(parameterName, x, targetSqlType, scale);
    }

    public void setObject(String parameterName, Object x, int targetSqlType) throws SQLException {
        src.setObject(parameterName, x, targetSqlType);
    }

    public void setObject(String parameterName, Object x) throws SQLException {
        src.setObject(parameterName, x);
    }

    public void setCharacterStream(String parameterName, Reader reader, int length) throws SQLException {
        src.setCharacterStream(parameterName, reader, length);
    }

    public void setDate(String parameterName, Date x, Calendar cal) throws SQLException {
        src.setDate(parameterName, x, cal);
    }

    public void setTime(String parameterName, Time x, Calendar cal) throws SQLException {
        src.setTime(parameterName, x, cal);
    }

    public void setTimestamp(String parameterName, Timestamp x, Calendar cal) throws SQLException {
        src.setTimestamp(parameterName, x, cal);
    }

    public void setNull(String parameterName, int sqlType, String typeName) throws SQLException {
        src.setNull(parameterName, sqlType, typeName);
    }

    public String getString(String parameterName) throws SQLException {
        return src.getString(parameterName);
    }

    public boolean getBoolean(String parameterName) throws SQLException {
        return src.getBoolean(parameterName);
    }

    public byte getByte(String parameterName) throws SQLException {
        return src.getByte(parameterName);
    }

    public short getShort(String parameterName) throws SQLException {
        return src.getShort(parameterName);
    }

    public int getInt(String parameterName) throws SQLException {
        return src.getInt(parameterName);
    }

    public long getLong(String parameterName) throws SQLException {
        return src.getLong(parameterName);
    }

    public float getFloat(String parameterName) throws SQLException {
        return src.getFloat(parameterName);
    }

    public double getDouble(String parameterName) throws SQLException {
        return src.getDouble(parameterName);
    }

    public byte[] getBytes(String parameterName) throws SQLException {
        return src.getBytes(parameterName);
    }

    public Date getDate(String parameterName) throws SQLException {
        return src.getDate(parameterName);
    }

    public Time getTime(String parameterName) throws SQLException {
        return src.getTime(parameterName);
    }

    public Timestamp getTimestamp(String parameterName) throws SQLException {
        return src.getTimestamp(parameterName);
    }

    public Object getObject(String parameterName) throws SQLException {
        return src.getObject(parameterName);
    }

    public BigDecimal getBigDecimal(String parameterName) throws SQLException {
        return src.getBigDecimal(parameterName);
    }

    public Object getObject(String parameterName, Map<String, Class<?>> map) throws SQLException {
        return src.getObject(parameterName, map);
    }

    public Ref getRef(String parameterName) throws SQLException {
        return src.getRef(parameterName);
    }

    public Blob getBlob(String parameterName) throws SQLException {
        return src.getBlob(parameterName);
    }

    public Clob getClob(String parameterName) throws SQLException {
        return src.getClob(parameterName);
    }

    public Array getArray(String parameterName) throws SQLException {
        return src.getArray(parameterName);
    }

    public Date getDate(String parameterName, Calendar cal) throws SQLException {
        return src.getDate(parameterName, cal);
    }

    public Time getTime(String parameterName, Calendar cal) throws SQLException {
        return src.getTime(parameterName, cal);
    }

    public Timestamp getTimestamp(String parameterName, Calendar cal) throws SQLException {
        return src.getTimestamp(parameterName, cal);
    }

    public URL getURL(String parameterName) throws SQLException {
        return src.getURL(parameterName);
    }

    public RowId getRowId(int parameterIndex) throws SQLException {
        return src.getRowId(parameterIndex);
    }

    public RowId getRowId(String parameterName) throws SQLException {
        return src.getRowId(parameterName);
    }

    public void setRowId(String parameterName, RowId x) throws SQLException {
        src.setRowId(parameterName, x);
    }

    public void setNString(String parameterName, String value) throws SQLException {
        src.setNString(parameterName, value);
    }

    public void setNCharacterStream(String parameterName, Reader value, long length) throws SQLException {
        src.setNCharacterStream(parameterName, value, length);
    }

    public void setNClob(String parameterName, NClob value) throws SQLException {
        src.setNClob(parameterName, value);
    }

    public void setClob(String parameterName, Reader reader, long length) throws SQLException {
        src.setClob(parameterName, reader, length);
    }

    public void setBlob(String parameterName, InputStream inputStream, long length) throws SQLException {
        src.setBlob(parameterName, inputStream, length);
    }

    public void setNClob(String parameterName, Reader reader, long length) throws SQLException {
        src.setNClob(parameterName, reader, length);
    }

    public NClob getNClob(int parameterIndex) throws SQLException {
        return src.getNClob(parameterIndex);
    }

    public NClob getNClob(String parameterName) throws SQLException {
        return src.getNClob(parameterName);
    }

    public void setSQLXML(String parameterName, SQLXML xmlObject) throws SQLException {
        src.setSQLXML(parameterName, xmlObject);
    }

    public SQLXML getSQLXML(int parameterIndex) throws SQLException {
        return src.getSQLXML(parameterIndex);
    }

    public SQLXML getSQLXML(String parameterName) throws SQLException {
        return src.getSQLXML(parameterName);
    }

    public String getNString(int parameterIndex) throws SQLException {
        return src.getNString(parameterIndex);
    }

    public String getNString(String parameterName) throws SQLException {
        return src.getNString(parameterName);
    }

    public Reader getNCharacterStream(int parameterIndex) throws SQLException {
        return src.getNCharacterStream(parameterIndex);
    }

    public Reader getNCharacterStream(String parameterName) throws SQLException {
        return src.getNCharacterStream(parameterName);
    }

    public Reader getCharacterStream(int parameterIndex) throws SQLException {
        return src.getCharacterStream(parameterIndex);
    }

    public Reader getCharacterStream(String parameterName) throws SQLException {
        return src.getCharacterStream(parameterName);
    }

    public void setBlob(String parameterName, Blob x) throws SQLException {
        src.setBlob(parameterName, x);
    }

    public void setClob(String parameterName, Clob x) throws SQLException {
        src.setClob(parameterName, x);
    }

    public void setAsciiStream(String parameterName, InputStream x, long length) throws SQLException {
        src.setAsciiStream(parameterName, x, length);
    }

    public void setBinaryStream(String parameterName, InputStream x, long length) throws SQLException {
        src.setBinaryStream(parameterName, x, length);
    }

    public void setCharacterStream(String parameterName, Reader reader, long length) throws SQLException {
        src.setCharacterStream(parameterName, reader, length);
    }

    public void setAsciiStream(String parameterName, InputStream x) throws SQLException {
        src.setAsciiStream(parameterName, x);
    }

    public void setBinaryStream(String parameterName, InputStream x) throws SQLException {
        src.setBinaryStream(parameterName, x);
    }

    public void setCharacterStream(String parameterName, Reader reader) throws SQLException {
        src.setCharacterStream(parameterName, reader);
    }

    public void setNCharacterStream(String parameterName, Reader value) throws SQLException {
        src.setNCharacterStream(parameterName, value);
    }

    public void setClob(String parameterName, Reader reader) throws SQLException {
        src.setClob(parameterName, reader);
    }

    public void setBlob(String parameterName, InputStream inputStream) throws SQLException {
        src.setBlob(parameterName, inputStream);
    }

    public void setNClob(String parameterName, Reader reader) throws SQLException {
        src.setNClob(parameterName, reader);
    }

    public <T> T getObject(int parameterIndex, Class<T> type) throws SQLException {
        return src.getObject(parameterIndex, type);
    }

    public <T> T getObject(String parameterName, Class<T> type) throws SQLException {
        return src.getObject(parameterName, type);
    }

    public void setObject(String parameterName, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        src.setObject(parameterName, x, targetSqlType, scaleOrLength);
    }

    public void setObject(String parameterName, Object x, SQLType targetSqlType) throws SQLException {
        src.setObject(parameterName, x, targetSqlType);
    }

    public void registerOutParameter(int parameterIndex, SQLType sqlType) throws SQLException {
        src.registerOutParameter(parameterIndex, sqlType);
    }

    public void registerOutParameter(int parameterIndex, SQLType sqlType, int scale) throws SQLException {
        src.registerOutParameter(parameterIndex, sqlType, scale);
    }

    public void registerOutParameter(int parameterIndex, SQLType sqlType, String typeName) throws SQLException {
        src.registerOutParameter(parameterIndex, sqlType, typeName);
    }

    public void registerOutParameter(String parameterName, SQLType sqlType) throws SQLException {
        src.registerOutParameter(parameterName, sqlType);
    }

    public void registerOutParameter(String parameterName, SQLType sqlType, int scale) throws SQLException {
        src.registerOutParameter(parameterName, sqlType, scale);
    }

    public void registerOutParameter(String parameterName, SQLType sqlType, String typeName) throws SQLException {
        src.registerOutParameter(parameterName, sqlType, typeName);
    }

    public ResultSet executeQuery() throws SQLException {
        return ds.wrap(this, src.executeQuery());
    }

    public int executeUpdate() throws SQLException {
        return src.executeUpdate();
    }

    public void setNull(int parameterIndex, int sqlType) throws SQLException {
        src.setNull(parameterIndex, sqlType);
    }

    public void setBoolean(int parameterIndex, boolean x) throws SQLException {
        src.setBoolean(parameterIndex, x);
    }

    public void setByte(int parameterIndex, byte x) throws SQLException {
        src.setByte(parameterIndex, x);
    }

    public void setShort(int parameterIndex, short x) throws SQLException {
        src.setShort(parameterIndex, x);
    }

    public void setInt(int parameterIndex, int x) throws SQLException {
        src.setInt(parameterIndex, x);
    }

    public void setLong(int parameterIndex, long x) throws SQLException {
        src.setLong(parameterIndex, x);
    }

    public void setFloat(int parameterIndex, float x) throws SQLException {
        src.setFloat(parameterIndex, x);
    }

    public void setDouble(int parameterIndex, double x) throws SQLException {
        src.setDouble(parameterIndex, x);
    }

    public void setBigDecimal(int parameterIndex, BigDecimal x) throws SQLException {
        src.setBigDecimal(parameterIndex, x);
    }

    public void setString(int parameterIndex, String x) throws SQLException {
        src.setString(parameterIndex, x);
    }

    public void setBytes(int parameterIndex, byte[] x) throws SQLException {
        src.setBytes(parameterIndex, x);
    }

    public void setDate(int parameterIndex, Date x) throws SQLException {
        src.setDate(parameterIndex, x);
    }

    public void setTime(int parameterIndex, Time x) throws SQLException {
        src.setTime(parameterIndex, x);
    }

    public void setTimestamp(int parameterIndex, Timestamp x) throws SQLException {
        src.setTimestamp(parameterIndex, x);
    }

    public void setAsciiStream(int parameterIndex, InputStream x, int length) throws SQLException {
        src.setAsciiStream(parameterIndex, x, length);
    }

    @Deprecated
    public void setUnicodeStream(int parameterIndex, InputStream x, int length) throws SQLException {
        src.setUnicodeStream(parameterIndex, x, length);
    }

    public void setBinaryStream(int parameterIndex, InputStream x, int length) throws SQLException {
        src.setBinaryStream(parameterIndex, x, length);
    }

    public void clearParameters() throws SQLException {
        src.clearParameters();
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType) throws SQLException {
        src.setObject(parameterIndex, x, targetSqlType);
    }

    public void setObject(int parameterIndex, Object x) throws SQLException {
        src.setObject(parameterIndex, x);
    }

    public boolean execute() throws SQLException {
        return src.execute();
    }

    public void addBatch() throws SQLException {
        src.addBatch();
    }

    public void setCharacterStream(int parameterIndex, Reader reader, int length) throws SQLException {
        src.setCharacterStream(parameterIndex, reader, length);
    }

    public void setRef(int parameterIndex, Ref x) throws SQLException {
        src.setRef(parameterIndex, x);
    }

    public void setBlob(int parameterIndex, Blob x) throws SQLException {
        src.setBlob(parameterIndex, x);
    }

    public void setClob(int parameterIndex, Clob x) throws SQLException {
        src.setClob(parameterIndex, x);
    }

    public void setArray(int parameterIndex, Array x) throws SQLException {
        src.setArray(parameterIndex, x);
    }

    public ResultSetMetaData getMetaData() throws SQLException {
        return src.getMetaData();
    }

    public void setDate(int parameterIndex, Date x, Calendar cal) throws SQLException {
        src.setDate(parameterIndex, x, cal);
    }

    public void setTime(int parameterIndex, Time x, Calendar cal) throws SQLException {
        src.setTime(parameterIndex, x, cal);
    }

    public void setTimestamp(int parameterIndex, Timestamp x, Calendar cal) throws SQLException {
        src.setTimestamp(parameterIndex, x, cal);
    }

    public void setNull(int parameterIndex, int sqlType, String typeName) throws SQLException {
        src.setNull(parameterIndex, sqlType, typeName);
    }

    public void setURL(int parameterIndex, URL x) throws SQLException {
        src.setURL(parameterIndex, x);
    }

    public ParameterMetaData getParameterMetaData() throws SQLException {
        return src.getParameterMetaData();
    }

    public void setRowId(int parameterIndex, RowId x) throws SQLException {
        src.setRowId(parameterIndex, x);
    }

    public void setNString(int parameterIndex, String value) throws SQLException {
        src.setNString(parameterIndex, value);
    }

    public void setNCharacterStream(int parameterIndex, Reader value, long length) throws SQLException {
        src.setNCharacterStream(parameterIndex, value, length);
    }

    public void setNClob(int parameterIndex, NClob value) throws SQLException {
        src.setNClob(parameterIndex, value);
    }

    public void setClob(int parameterIndex, Reader reader, long length) throws SQLException {
        src.setClob(parameterIndex, reader, length);
    }

    public void setBlob(int parameterIndex, InputStream inputStream, long length) throws SQLException {
        src.setBlob(parameterIndex, inputStream, length);
    }

    public void setNClob(int parameterIndex, Reader reader, long length) throws SQLException {
        src.setNClob(parameterIndex, reader, length);
    }

    public void setSQLXML(int parameterIndex, SQLXML xmlObject) throws SQLException {
        src.setSQLXML(parameterIndex, xmlObject);
    }

    public void setObject(int parameterIndex, Object x, int targetSqlType, int scaleOrLength) throws SQLException {
        src.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    public void setAsciiStream(int parameterIndex, InputStream x, long length) throws SQLException {
        src.setAsciiStream(parameterIndex, x, length);
    }

    public void setBinaryStream(int parameterIndex, InputStream x, long length) throws SQLException {
        src.setBinaryStream(parameterIndex, x, length);
    }

    public void setCharacterStream(int parameterIndex, Reader reader, long length) throws SQLException {
        src.setCharacterStream(parameterIndex, reader, length);
    }

    public void setAsciiStream(int parameterIndex, InputStream x) throws SQLException {
        src.setAsciiStream(parameterIndex, x);
    }

    public void setBinaryStream(int parameterIndex, InputStream x) throws SQLException {
        src.setBinaryStream(parameterIndex, x);
    }

    public void setCharacterStream(int parameterIndex, Reader reader) throws SQLException {
        src.setCharacterStream(parameterIndex, reader);
    }

    public void setNCharacterStream(int parameterIndex, Reader value) throws SQLException {
        src.setNCharacterStream(parameterIndex, value);
    }

    public void setClob(int parameterIndex, Reader reader) throws SQLException {
        src.setClob(parameterIndex, reader);
    }

    public void setBlob(int parameterIndex, InputStream inputStream) throws SQLException {
        src.setBlob(parameterIndex, inputStream);
    }

    public void setNClob(int parameterIndex, Reader reader) throws SQLException {
        src.setNClob(parameterIndex, reader);
    }

    public void setObject(int parameterIndex, Object x, SQLType targetSqlType, int scaleOrLength) throws SQLException {
        src.setObject(parameterIndex, x, targetSqlType, scaleOrLength);
    }

    public void setObject(int parameterIndex, Object x, SQLType targetSqlType) throws SQLException {
        src.setObject(parameterIndex, x, targetSqlType);
    }

    public long executeLargeUpdate() throws SQLException {
        return src.executeLargeUpdate();
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        return ds.wrap(this, src.executeQuery(sql));
    }

    public int executeUpdate(String sql) throws SQLException {
        return src.executeUpdate(sql);
    }

    public void close() throws SQLException {
        src.close();
        ds.release(src);
    }

    public int getMaxFieldSize() throws SQLException {
        return src.getMaxFieldSize();
    }

    public void setMaxFieldSize(int max) throws SQLException {
        src.setMaxFieldSize(max);
    }

    public int getMaxRows() throws SQLException {
        return src.getMaxRows();
    }

    public void setMaxRows(int max) throws SQLException {
        src.setMaxRows(max);
    }

    public void setEscapeProcessing(boolean enable) throws SQLException {
        src.setEscapeProcessing(enable);
    }

    public int getQueryTimeout() throws SQLException {
        return src.getQueryTimeout();
    }

    public void setQueryTimeout(int seconds) throws SQLException {
        src.setQueryTimeout(seconds);
    }

    public void cancel() throws SQLException {
        src.cancel();
    }

    public SQLWarning getWarnings() throws SQLException {
        return src.getWarnings();
    }

    public void clearWarnings() throws SQLException {
        src.clearWarnings();
    }

    public void setCursorName(String name) throws SQLException {
        src.setCursorName(name);
    }

    public boolean execute(String sql) throws SQLException {
        return src.execute(sql);
    }

    public ResultSet getResultSet() throws SQLException {
        return ds.wrap(this, src.getResultSet());
    }

    public int getUpdateCount() throws SQLException {
        return src.getUpdateCount();
    }

    public boolean getMoreResults() throws SQLException {
        return src.getMoreResults();
    }

    public void setFetchDirection(int direction) throws SQLException {
        src.setFetchDirection(direction);
    }

    public int getFetchDirection() throws SQLException {
        return src.getFetchDirection();
    }

    public void setFetchSize(int rows) throws SQLException {
        src.setFetchSize(rows);
    }

    public int getFetchSize() throws SQLException {
        return src.getFetchSize();
    }

    public int getResultSetConcurrency() throws SQLException {
        return src.getResultSetConcurrency();
    }

    public int getResultSetType() throws SQLException {
        return src.getResultSetType();
    }

    public void addBatch(String sql) throws SQLException {
        src.addBatch(sql);
    }

    public void clearBatch() throws SQLException {
        src.clearBatch();
    }

    public int[] executeBatch() throws SQLException {
        return src.executeBatch();
    }

    public Connection getConnection() throws SQLException {
        return conn;
    }

    public boolean getMoreResults(int current) throws SQLException {
        return src.getMoreResults(current);
    }

    public ResultSet getGeneratedKeys() throws SQLException {
        return ds.wrap(this, src.getGeneratedKeys());
    }

    public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return src.executeUpdate(sql, autoGeneratedKeys);
    }

    public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return src.executeUpdate(sql, columnIndexes);
    }

    public int executeUpdate(String sql, String[] columnNames) throws SQLException {
        return src.executeUpdate(sql, columnNames);
    }

    public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
        return src.execute(sql, autoGeneratedKeys);
    }

    public boolean execute(String sql, int[] columnIndexes) throws SQLException {
        return src.execute(sql, columnIndexes);
    }

    public boolean execute(String sql, String[] columnNames) throws SQLException {
        return src.execute(sql, columnNames);
    }

    public int getResultSetHoldability() throws SQLException {
        return src.getResultSetHoldability();
    }

    public boolean isClosed() throws SQLException {
        return src.isClosed();
    }

    public void setPoolable(boolean poolable) throws SQLException {
        src.setPoolable(poolable);
    }

    public boolean isPoolable() throws SQLException {
        return src.isPoolable();
    }

    public void closeOnCompletion() throws SQLException {
        src.closeOnCompletion();
    }

    public boolean isCloseOnCompletion() throws SQLException {
        return src.isCloseOnCompletion();
    }

    public long getLargeUpdateCount() throws SQLException {
        return src.getLargeUpdateCount();
    }

    public void setLargeMaxRows(long max) throws SQLException {
        src.setLargeMaxRows(max);
    }

    public long getLargeMaxRows() throws SQLException {
        return src.getLargeMaxRows();
    }

    public long[] executeLargeBatch() throws SQLException {
        return src.executeLargeBatch();
    }

    public long executeLargeUpdate(String sql) throws SQLException {
        return src.executeLargeUpdate(sql);
    }

    public long executeLargeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
        return src.executeLargeUpdate(sql, autoGeneratedKeys);
    }

    public long executeLargeUpdate(String sql, int[] columnIndexes) throws SQLException {
        return src.executeLargeUpdate(sql, columnIndexes);
    }

    public long executeLargeUpdate(String sql, String[] columnNames) throws SQLException {
        return src.executeLargeUpdate(sql, columnNames);
    }

    public <T> T unwrap(Class<T> iface) throws SQLException {
        return src.unwrap(iface);
    }

    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return src.isWrapperFor(iface);
    }

    public String toString() {
        return String.format("[Created %d ms ago] "+this.getClass().getSimpleName()+"@" + System.identityHashCode(this) + "[ %s ]", System.currentTimeMillis() - created, src);

    }
}
