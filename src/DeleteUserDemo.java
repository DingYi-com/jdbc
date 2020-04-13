import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用JDBC删除表中数据
 *
 * @author DingYi
 * @date 2020/4/5 16:14
 */
public class DeleteUserDemo {
  public static void main(String[] args) {
    deleteUser(3);
  }

  /**
   * 删除用户方法
   *
   * @param id 用户编号
   */
  private static void deleteUser(int id){
    Connection conn = null;
    Statement stat = null;
    try{
      // 1.注册数据库驱动
      Class.forName("com.mysql.jdbc.Driver");

      // 2.建立和数据库之间的连接
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false&characterEncoding = utf8","root","root");

      // 3.拼写SQL语句
      String sql = "delete from users where id = " + id;

      // 4.向数据库发送并执行SQL语句
      stat = conn.createStatement();
      int rows = stat.executeUpdate(sql);

      // 5.处理执行结果
      System.out.println("数据库中有" + rows + "条语句被执行...");

    }catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }finally {
      // 6.关闭资源
      if (conn != null) {
        try{
          conn.close();
          conn = null;
        }catch (SQLException e){
          e.printStackTrace();
        }
      }
      if (stat != null) {
        try {
          stat.close();
          stat = null;
        }catch (SQLException e){
          e.printStackTrace();
        }
      }
    }
  }
}
