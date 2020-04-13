import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 使用jdbc修改数据库中的信息
 *
 * @author DingYi
 * @date 2020/4/5 15:43
 */
public class UpdateUserDemo {
  public static void main(String[] args) {
    updateUser("张三","2580",3);
  }

  /**
   * 修改用户的方法
   *
   * @param username 用户名称
   * @param userpass 用户密码
   * @param id 用户编号
   */
  public static void updateUser(String username,String userpass,int id){
    Connection conn = null;
    Statement stat = null;
    try{
      // 1. 注册数据库驱动
      Class.forName("com.mysql.jdbc.Driver");

      // 2. 建立和数据库之间的连接
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_demo?useSSL=false&characterEncoding=utf8","root","root");

      // 3. 拼写SQL语句
      String sql = "update users set username ='"+ username +"',userpass = '"+ userpass +"' where id = " + id;

      // 4. 向数据库发送并执行SQL语句
      stat = conn.createStatement();
      int rows = stat.executeUpdate(sql);

      // 5. 处理执行结果
      System.out.println("数据库中有" + rows + "条数据被执行……");
    }catch(ClassNotFoundException | SQLException e){
      e.printStackTrace();
    }finally {
      // 6. 关闭资源
      if(conn != null){
        try{
          conn.close();
          conn = null;
        }catch(SQLException e){
          e.printStackTrace();
        }
      }
      if(stat != null){
        try{
          stat.close();
          stat = null;
        }catch(SQLException e){
          e.printStackTrace();
        }
      }
    }
  }

}
