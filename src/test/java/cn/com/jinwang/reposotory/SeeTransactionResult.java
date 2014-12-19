package cn.com.jinwang.reposotory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

public class SeeTransactionResult {

  private ResultSetHandler<Long> countHandler = new ResultSetHandler<Long>() {
    public Long handle(ResultSet rs) throws SQLException {
      if (!rs.next()) {
        return null;
      }
//      ResultSetMetaData meta = rs.getMetaData();
//      int cols = meta.getColumnCount();
      return rs.getLong(1);
    }
  };


  public Connection getConn() {
    Connection conn = null;
    try {
      conn = DriverManager.getConnection("jdbc:hsqldb:file:testdbfolder/testdb", "SA", "");
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return conn;
  }

  public void initDriver() {
    try {
      Class.forName("org.hsqldb.jdbc.JDBCDriver");
    } catch (Exception e) {
      System.err.println("ERROR: failed to load HSQLDB JDBC driver.");
      e.printStackTrace();
    }
  }

  public Long getLocalUserCount() {
    Connection conn = getConn();
    QueryRunner run = new QueryRunner();
    Long result = 0L;

    try {
      result = run.query(conn, "SELECT COUNT(*) FROM LOCAL_USER", countHandler);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        DbUtils.close(conn);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return result;
  }

  /**
   * @param args
   * @throws ClassNotFoundException
   */
  public static void main(String[] args) {
    System.out.println(System.getProperty("user.dir"));
    System.out.println(new SeeTransactionResult().getLocalUserCount());
  }

}
