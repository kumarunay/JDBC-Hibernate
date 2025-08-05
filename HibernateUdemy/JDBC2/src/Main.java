import java.sql.*;
public class Main {
    public static void main(String[] args) throws Exception {
    /*
    7 steps/
    import package
    load and register
    create connection
    create statement
    execute statment
    process and results
    close

     */

        String url="jdbc:postgresql://localhost:5432/demo2";
        String uname ="postgres";
        String pass = "Yannam@57";

        int sid=105;
        String sname="JaganYS";
        int marks =122;
//        String sqlQuiry= "select sname from student where sid=2";//1st
        String sqlQuiry= "select * from student ";
        //CRUD Operations/Create
        String sqlQ2= "insert into student values (6,'mahima',34)";
        String sql3 ="update student set sname='Max' where sid=6";
        String sql4 ="delete from student where sid=4";

        String sql5= "insert into student values (101,'Josb',57)";
        //----***************problem with statement as if we need to insert dynamic input lilke this it will be big confusion
        //-------- but we can achive dynamic input inject by PreparedStatement is interface which extends statement class
        String sql_5 ="insert into student values("+sid+","+"'"+sname+"',"+marks+")";

        /*for PreparedStatement----------------*/

        String sql6= "insert into student values(?,?,?)";


       // Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection(url,uname,pass);
        System.out.println("Connection Established");




//        Statement st = con.createStatement();
////                boolean status =st.execute(sql4);
//        st.execute(sql_5);


        PreparedStatement pst = con.prepareStatement(sql6);
        pst.setInt(1,sid);
        pst.setString(2,sname);
        pst.setInt(3,marks);
        pst.execute();

//        ResultSet rs = pst.executeQuery(sqlQuiry);
//        rs.next();
//       String name = rs.getString("sname");
//////        System.out.println(rs.next());
////        System.out.println("Name of Student :"+name);
//

//        while (rs.next()){
//            System.out.println("Name of Student :"+ rs.getString("sname"));
//        }
//        System.out.println(status);

//        while(rs.next()){
//            System.out.println(rs.getInt(1)+":"+rs.getString(2)+":"+rs.getInt(3));
//        }

        con.close();
        System.out.println("Connection closed");





    }
}