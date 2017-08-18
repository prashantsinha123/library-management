import java.sql.*;
public class Mysql1{
public static int add(String calln,String name,String author,String publisher,String quantity){
int i=0;
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");
String query="insert into book(calln,name,author,publisher,quantity,issue) values(?,?,?,?,?,0)";
PreparedStatement ps= con.prepareStatement(query);
ps.setString(1,calln);
ps.setString(2,name);
ps.setString(3,author);
ps.setString(4,publisher);
ps.setString(5,quantity);
i=ps.executeUpdate();
i+=1;
con.close();
}
catch(Exception e){System.out.println(e);}
return i;
}

public static int issue(String calln,String sid,String sname,String scontact){
int i=0;
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");
String query="insert into issue(calln,sid,sname,scontact) values(?,?,?,?)";
PreparedStatement ps = con.prepareStatement(query);
ps.setString(1,calln);
ps.setString(2,sid);
ps.setString(3,sname);
ps.setString(4,scontact);
i=ps.executeUpdate();
i+=1;
con.close();
}catch(Exception e){
System.out.println(e);}
return i;
}
public static void update(String calln){
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");
String query="update book set quantity=quantity-1,issue=issue+1 where calln=?";
PreparedStatement ps = con.prepareStatement(query);
ps.setString(1,calln);
if(ps.execute()){
System.out.println("success");
}else{
System.out.println("not successful");
}
con.close();

}
catch(Exception e){System.out.println(e);}
}
public static int delis(String calln,String sid){
int i=0;
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");

String query= "delete from issue where calln=? AND sid =?";

PreparedStatement ps = con.prepareStatement(query);

ps.setString(1,calln);
ps.setString(2,sid);



if(ps.execute()==true){
i+=1;
}
con.close();

}
catch(Exception e){System.out.println(e);}
return i;

}
public static void updateb(String calln){
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");
String query="update book set quantity=quantity+1,issue=issue-1 where calln=?";
PreparedStatement ps = con.prepareStatement(query);
ps.setString(1,calln);
if(ps.execute()){
System.out.println("success");
}else{
System.out.println("not successful");
}
con.close();

}
catch(Exception e){System.out.println(e);}

}
}
