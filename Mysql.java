    import java.sql.*;  
    public class Mysql{  
    
public static int insert(String name, String password,String email,String address,String city,String contact){
int i=0;
    try{  
 
    Class.forName("com.mysql.jdbc.Driver");  
    Connection con=DriverManager.getConnection(  
    "jdbc:mysql://localhost:3306/library","root","Savage-123");   
    String query="insert into form (name,password,email,address,city,contact) values(?,?,?,?,?,?)"; 
    PreparedStatement ps= con.prepareStatement(query); 
ps.setString(1,name);
ps.setString(2,password);
ps.setString(3,email);
ps.setString(4,address);
ps.setString(5,city);
ps.setString(6,contact);


i = ps.executeUpdate();
System.out.println("program executiin successful:"+i);

    con.close();  
    

}catch(Exception e){ System.out.println(e);} 
return i; 
    }  


public static int select(){
int n=0;
try{
Class.forName("com.mysql.jdbc.Driver");  
    Connection con=DriverManager.getConnection(  
    "jdbc:mysql://localhost:3306/library","root","Savage-123"); 
 Statement st =con.createStatement();
ResultSet rs= st.executeQuery("select *from form");
int c=0;
while(rs.next()){
String name = rs.getString("name");
    String password = rs.getString("password");
    String email = rs.getString("email");
    String address = rs.getString("address");
    String city = rs.getString("city");
    String contact = rs.getString("contact");
String output = "User #%d: %s - %s - %s - %s - %s - %s";
System.out.println(String.format(output,c++,name,password,email,address,city,contact));
}
System.out.println("viewed successfully:"+n);
con.close();
}catch(Exception e){System.out.println(e);}
return n;
}

public static int delete(String id){
int t=0;
try{

Class.forName("com.mysql.jdbc.Driver");
Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");
String query="delete from form where id=?";
PreparedStatement ps = con.prepareStatement(query);
ps.setString(1,id);

t=ps.executeUpdate();
System.out.println("Successfully deleted" +t);
con.close();
}catch(Exception e){System.out.println(e);}
return t;
}

public static boolean verify(String nam,String pass){

boolean i=false;
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");
String query = "select * from form where name=? and password=?";
PreparedStatement ps = con.prepareStatement(query);
ps.setString(1,nam);
ps.setString(2,pass);
ResultSet rs=ps.executeQuery();
i=rs.next();
con.close();
}
catch(Exception e){System.out.println(e);}
return i;
}



    }  
