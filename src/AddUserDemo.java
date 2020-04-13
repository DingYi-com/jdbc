import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用jdbc添加用户
 *
 * @author DingYi
 * @date 2020/4/4 15:11
 */
public class AddUserDemo {
  public static void main(String[] args) {
    addUser("小明", "23456");
  }

  /**
   * 添加用户的方法
   *
   * @param username 用户名称
   * @param userpass 用户密码
   */

  private static void addUser(String username, String userpass) {
    Connection conn = null;
    Statement stat = null;
    try {
      // 1.注册数据库驱动
      Class.forName("com.mysql.jdbc.Driver");

      // 2.建立和数据库之间的驱动

      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false&characterEncoding=utf8", "root", "root");

      // 3. 拼写SQL语句
      String sql = "insert into users(username,userpass) values ('" + username + "','" + userpass + "')";


      // 4. 向数据库发送并执行SQL语句
      stat = conn.createStatement();
      int rows = stat.executeUpdate(sql);

      // 5. 处理执行结果
      System.out.println("数据库中有" + rows + "条数据被执行……");
    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    } finally {
      // 6. 关闭资源
      if (conn != null) {
        try {
          conn.close();
          conn = null;
        } catch (SQLException e) {
          e.printStackTrace();
        }
        if (stat != null) {
          try {
            stat.close();
            stat = null;
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
}
