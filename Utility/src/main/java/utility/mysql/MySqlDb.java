package utility.mysql;

import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;

public class MySqlDb implements Connection
{
	private static final String LOCAL_HOST_MYSQL_USER_NAME     = "root";
	private static final String LOCAL_HOST_MYSQL_USER_PASSWORD = "syzygy";
	
	private static final String LOCAL_HOST_CONNECTION_STRING = "jdbc:mysql://localhost/";
	
	static
	{
		try
		{
			Class.forName ( "com.mysql.jdbc.Driver" );
		}
		catch ( ClassNotFoundException e )
		{
			e.printStackTrace();
		}
	}

	private Connection mySqlConnection;
	
	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#abort(java.util.concurrent.Executor)
	 */
	public void abort(Executor arg0) throws SQLException {
		mySqlConnection.abort(arg0);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getNetworkTimeout()
	 */
	public int getNetworkTimeout() throws SQLException {
		return mySqlConnection.getNetworkTimeout();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getSchema()
	 */
	public String getSchema() throws SQLException {
		return mySqlConnection.getSchema();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws SQLException
	 * @see java.sql.Connection#setNetworkTimeout(java.util.concurrent.Executor, int)
	 */
	public void setNetworkTimeout(Executor arg0, int arg1) throws SQLException {
		mySqlConnection.setNetworkTimeout(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#setSchema(java.lang.String)
	 */
	public void setSchema(String arg0) throws SQLException {
		mySqlConnection.setSchema(arg0);
	}

	public MySqlDb ( String schemaName )
	{
		this ( schemaName, LOCAL_HOST_MYSQL_USER_NAME, LOCAL_HOST_MYSQL_USER_PASSWORD );
	}

	public MySqlDb ( String schemaName, String userName, String password )
	{
		try
		{
			StringBuilder connectionStringBuilder = new StringBuilder();
			
			connectionStringBuilder.append ( LOCAL_HOST_CONNECTION_STRING );
			connectionStringBuilder.append ( schemaName );
	
			mySqlConnection = DriverManager.getConnection ( connectionStringBuilder.toString(), userName, password );
		}
		catch ( SQLException e )
		{
			e.printStackTrace();
		}
	}

	/**
	 * @throws SQLException
	 * @see java.sql.Connection#clearWarnings()
	 */
	public void clearWarnings() throws SQLException {
		mySqlConnection.clearWarnings();
	}

	/**
	 * @throws SQLException
	 * @see java.sql.Connection#close()
	 */
	public void close() throws SQLException {
		mySqlConnection.close();
	}

	/**
	 * @throws SQLException
	 * @see java.sql.Connection#commit()
	 */
	public void commit() throws SQLException {
		mySqlConnection.commit();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createArrayOf(java.lang.String, java.lang.Object[])
	 */
	public Array createArrayOf(String arg0, Object[] arg1) throws SQLException {
		return mySqlConnection.createArrayOf(arg0, arg1);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createBlob()
	 */
	public Blob createBlob() throws SQLException {
		return mySqlConnection.createBlob();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createClob()
	 */
	public Clob createClob() throws SQLException {
		return mySqlConnection.createClob();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createNClob()
	 */
	public NClob createNClob() throws SQLException {
		return mySqlConnection.createNClob();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createSQLXML()
	 */
	public SQLXML createSQLXML() throws SQLException {
		return mySqlConnection.createSQLXML();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createStatement()
	 */
	public Statement createStatement() throws SQLException {
		return mySqlConnection.createStatement();
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createStatement(int, int, int)
	 */
	public Statement createStatement(int arg0, int arg1, int arg2)
			throws SQLException {
		return mySqlConnection.createStatement(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createStatement(int, int)
	 */
	public Statement createStatement(int arg0, int arg1) throws SQLException {
		return mySqlConnection.createStatement(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#createStruct(java.lang.String, java.lang.Object[])
	 */
	public Struct createStruct(String arg0, Object[] arg1) throws SQLException {
		return mySqlConnection.createStruct(arg0, arg1);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getAutoCommit()
	 */
	public boolean getAutoCommit() throws SQLException {
		return mySqlConnection.getAutoCommit();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getCatalog()
	 */
	public String getCatalog() throws SQLException {
		return mySqlConnection.getCatalog();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getClientInfo()
	 */
	public Properties getClientInfo() throws SQLException {
		return mySqlConnection.getClientInfo();
	}

	/**
	 * @param arg0
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getClientInfo(java.lang.String)
	 */
	public String getClientInfo(String arg0) throws SQLException {
		return mySqlConnection.getClientInfo(arg0);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getHoldability()
	 */
	public int getHoldability() throws SQLException {
		return mySqlConnection.getHoldability();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getMetaData()
	 */
	public DatabaseMetaData getMetaData() throws SQLException {
		return mySqlConnection.getMetaData();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getTransactionIsolation()
	 */
	public int getTransactionIsolation() throws SQLException {
		return mySqlConnection.getTransactionIsolation();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getTypeMap()
	 */
	public Map<String, Class<?>> getTypeMap() throws SQLException {
		return mySqlConnection.getTypeMap();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#getWarnings()
	 */
	public SQLWarning getWarnings() throws SQLException {
		return mySqlConnection.getWarnings();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#isClosed()
	 */
	public boolean isClosed() throws SQLException {
		return mySqlConnection.isClosed();
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#isReadOnly()
	 */
	public boolean isReadOnly() throws SQLException {
		return mySqlConnection.isReadOnly();
	}

	/**
	 * @param arg0
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#isValid(int)
	 */
	public boolean isValid(int arg0) throws SQLException {
		return mySqlConnection.isValid(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @throws SQLException
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	public boolean isWrapperFor(Class<?> arg0) throws SQLException {
		return mySqlConnection.isWrapperFor(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#nativeSQL(java.lang.String)
	 */
	public String nativeSQL(String arg0) throws SQLException {
		return mySqlConnection.nativeSQL(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int, int)
	 */
	public CallableStatement prepareCall(String arg0, int arg1, int arg2,
			int arg3) throws SQLException {
		return mySqlConnection.prepareCall(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareCall(java.lang.String, int, int)
	 */
	public CallableStatement prepareCall(String arg0, int arg1, int arg2)
			throws SQLException {
		return mySqlConnection.prepareCall(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareCall(java.lang.String)
	 */
	public CallableStatement prepareCall(String arg0) throws SQLException {
		return mySqlConnection.prepareCall(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int, int)
	 */
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2,
			int arg3) throws SQLException {
		return mySqlConnection.prepareStatement(arg0, arg1, arg2, arg3);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int, int)
	 */
	public PreparedStatement prepareStatement(String arg0, int arg1, int arg2)
			throws SQLException {
		return mySqlConnection.prepareStatement(arg0, arg1, arg2);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int)
	 */
	public PreparedStatement prepareStatement(String arg0, int arg1)
			throws SQLException {
		return mySqlConnection.prepareStatement(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, int[])
	 */
	public PreparedStatement prepareStatement(String arg0, int[] arg1)
			throws SQLException {
		return mySqlConnection.prepareStatement(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String, java.lang.String[])
	 */
	public PreparedStatement prepareStatement(String arg0, String[] arg1)
			throws SQLException {
		return mySqlConnection.prepareStatement(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#prepareStatement(java.lang.String)
	 */
	public PreparedStatement prepareStatement(String arg0) throws SQLException {
		return mySqlConnection.prepareStatement(arg0);
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#releaseSavepoint(java.sql.Savepoint)
	 */
	public void releaseSavepoint(Savepoint arg0) throws SQLException {
		mySqlConnection.releaseSavepoint(arg0);
	}

	/**
	 * @throws SQLException
	 * @see java.sql.Connection#rollback()
	 */
	public void rollback() throws SQLException {
		mySqlConnection.rollback();
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#rollback(java.sql.Savepoint)
	 */
	public void rollback(Savepoint arg0) throws SQLException {
		mySqlConnection.rollback(arg0);
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#setAutoCommit(boolean)
	 */
	public void setAutoCommit(boolean arg0) throws SQLException {
		mySqlConnection.setAutoCommit(arg0);
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#setCatalog(java.lang.String)
	 */
	public void setCatalog(String arg0) throws SQLException {
		mySqlConnection.setCatalog(arg0);
	}

	/**
	 * @param arg0
	 * @throws SQLClientInfoException
	 * @see java.sql.Connection#setClientInfo(java.util.Properties)
	 */
	public void setClientInfo(Properties arg0) throws SQLClientInfoException {
		mySqlConnection.setClientInfo(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @throws SQLClientInfoException
	 * @see java.sql.Connection#setClientInfo(java.lang.String, java.lang.String)
	 */
	public void setClientInfo(String arg0, String arg1)
			throws SQLClientInfoException {
		mySqlConnection.setClientInfo(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#setHoldability(int)
	 */
	public void setHoldability(int arg0) throws SQLException {
		mySqlConnection.setHoldability(arg0);
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#setReadOnly(boolean)
	 */
	public void setReadOnly(boolean arg0) throws SQLException {
		mySqlConnection.setReadOnly(arg0);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#setSavepoint()
	 */
	public Savepoint setSavepoint() throws SQLException {
		return mySqlConnection.setSavepoint();
	}

	/**
	 * @param arg0
	 * @return
	 * @throws SQLException
	 * @see java.sql.Connection#setSavepoint(java.lang.String)
	 */
	public Savepoint setSavepoint(String arg0) throws SQLException {
		return mySqlConnection.setSavepoint(arg0);
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#setTransactionIsolation(int)
	 */
	public void setTransactionIsolation(int arg0) throws SQLException {
		mySqlConnection.setTransactionIsolation(arg0);
	}

	/**
	 * @param arg0
	 * @throws SQLException
	 * @see java.sql.Connection#setTypeMap(java.util.Map)
	 */
	public void setTypeMap(Map<String, Class<?>> arg0) throws SQLException {
		mySqlConnection.setTypeMap(arg0);
	}

	/**
	 * @param arg0
	 * @return
	 * @throws SQLException
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	public <T> T unwrap(Class<T> arg0) throws SQLException {
		return mySqlConnection.unwrap(arg0);
	}
}
