import javax.swing.*;                                                                                     //6-06-2017
import java.awt.event.*;
import java.sql.*;
public class Library implements ActionListener{
JFrame f,jf,nf,lf,lib,table,fw,delete,book,addb,viewb,biss,viewi,rebook;
JLabel issue1,issue2,issue3,issue4,ret1,ret2;
JLabel l1,l2,liblabel1,liblabel2,liblabel3,liblabel4,liblabel5,liblabel6,enterid,lname,lpassword,ad1,ad2,ad3,ad4,ad5;
JTextField tf1,tf2,libtext1,libtext2,libtext3,libtext4,libtext5,enterId,lnam,add1,add2,add3,add4,add5,iss1,iss2,iss3,iss4,re1,re2;
JPasswordField pw,libp,lpass;
JButton b1,b2,lb1,la1,la2,la3,la4,libbt1,libbt2,dlt,back,lbtn,book1,book2,book3,book4,book5,book6,bad1,bad2,is1,is2,rb1,rb2;
JTable jt,jtb,jti;
JScrollPane js,jsb,jsi;
Library(){
f=new JFrame("Library Management System");

b1=new JButton("Admin");
b2=new JButton("Librarian");
b1.setBounds(120,50,120,80);
b2.setBounds(120,190,120,80);
b1.addActionListener(this);
b2.addActionListener(this);

f.add(b1);
f.add(b2);
f.setLayout(null);
f.setSize(400,400);
f.setVisible(true);
}
public void actionPerformed(ActionEvent e){
if (e.getSource()==b1){
jf= new JFrame("Admin login page");

l1=new JLabel("Enter the name:");
l2=new JLabel("Enter the Password:");

tf1=new JTextField();
pw=new JPasswordField(15);

lb1=new JButton("Login");
l1.setBounds(50,50,150,30);
l2.setBounds(50,100,150,30);
tf1.setBounds(200,50,150,30);
pw.setBounds(200,100,150,30);
lb1.setBounds(100,150,80,60);



lb1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		String s1 = tf1.getText();
		String s2 = pw.getText();
		if(s1.equals("admin") && s2.equals("admin")){
			JOptionPane.showMessageDialog(null,"logged in successfully!");
		lf = new JFrame("Admin Section");
la1=new JButton("Add Librarian");
la2=new JButton("View Librarian");
la3=new JButton("Delete Librarian");
la4=new JButton("Logout");

la1.setBounds(150,50,150,70);
la2.setBounds(150,130,150,70);
la3.setBounds(150,210,150,70);
la4.setBounds(150,290,150,70);

lf.add(la1);lf.add(la2);lf.add(la3);lf.add(la4);
lf.setSize(500,500);
lf.setLayout(null);
lf.setVisible(true);
jf.setVisible(false);
la1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
lib = new JFrame("Add Librarian");
liblabel1 = new JLabel("Name:");
liblabel2 = new JLabel("Password:");
liblabel3 = new JLabel("Email:");
liblabel4 = new JLabel("Address:");
liblabel5 = new JLabel("City:");
liblabel6 = new JLabel("Contact No.:");

libtext1 = new JTextField();
libp = new JPasswordField(15);
libtext2 = new JTextField();
libtext3 = new JTextField();
libtext4 = new JTextField();
libtext5 = new JTextField();

libbt1 = new JButton("Add Librarian");
libbt2 = new JButton("Back");

liblabel1.setBounds(50,50,150,30);
liblabel2.setBounds(50,80,150,30);
liblabel3.setBounds(50,110,150,30);
liblabel4.setBounds(50,140,150,30);
liblabel5.setBounds(50,170,150,30);
liblabel6.setBounds(50,200,150,30);

libtext1.setBounds(200,50,150,30);
libp.setBounds(200,80,150,30);
libtext2.setBounds(200,110,150,30);
libtext3.setBounds(200,140,150,30);
libtext4.setBounds(200,170,150,30);
libtext5.setBounds(200,200,150,30);

libbt1.setBounds(100,240,180,40);
libbt2.setBounds(100,300,120,40);

lib.add(liblabel1);lib.add(liblabel2);lib.add(liblabel3);lib.add(liblabel4);lib.add(liblabel5);lib.add(liblabel6);
lib.add(libtext1);lib.add(libp);lib.add(libtext2);lib.add(libtext3);lib.add(libtext4);lib.add(libtext5);
lib.add(libbt1);lib.add(libbt2);

lib.setSize(500,500);
lib.setLayout(null);
lib.setVisible(true);

libbt1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	
String name = libtext1.getText();	
String password = String.valueOf(libp.getPassword()); 
String email = libtext2.getText();
String address = libtext3.getText();
String city = libtext4.getText();
String contact = libtext5.getText();

int i = Mysql.insert(name,password,email,address,city,contact);
if (i>0){
JOptionPane.showMessageDialog(null,"Added successfully");
}else{
JOptionPane.showMessageDialog(null,"could not save !");
}
           
}
});


libbt2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
		lf.setVisible(true);
		lib.setVisible(false);
		
}
});
}
});

la2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	table = new JFrame("View Library");
	String data[][]=null;
String column[]={"id","name","password","email","address","city","contact"};
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");

PreparedStatement ps = con.prepareStatement("select * from form");
ResultSet rs= ps.executeQuery();
ResultSetMetaData md = rs.getMetaData();
int cols = md.getColumnCount();
rs.last();
int rows=rs.getRow();
rs.beforeFirst();

data=new String[rows][cols];
int j=0;
while(rs.next()){
for(int i=1;i<=cols;i++){
   data[j][i-1]=rs.getString(i);
}
j++;
}
con.close();
}catch(Exception e1){
System.out.println(e1);
}
jt = new JTable(data,column);
jt.setBounds(50,50,400,900);
js = new JScrollPane(jt);
table.add(js);
table.setSize(900,500);
table.setVisible(true);
}

});

la3.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	delete = new JFrame("Deletion of Details");
enterid = new JLabel("Enter id:");
enterId = new JTextField();
enterid.setBounds(50,100,60,40);
enterId.setBounds(120,100,100,40);
dlt = new JButton("Delete");
back = new JButton("Back");
dlt.setBounds(150,200,100,60);
back.setBounds(200,280,100,60);
delete.add(enterid);delete.add(enterId);delete.add(dlt);delete.add(back);
dlt.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
String id = enterId.getText();
int t = Mysql.delete(id);
if (t>=1){
JOptionPane.showMessageDialog(null,"Successfully deleted!");
}else{
JOptionPane.showMessageDialog(null,"nahi gaya bc");
}
}
});
back.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){

lf.setVisible(true);
}
});
delete.setSize(500,500);
delete.setLayout(null);
delete.setVisible(true);
}
});


la4.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	f.setVisible(true);
	lf.setVisible(false);
}
});
}else{
	JOptionPane.showMessageDialog(null,"incorrect input");
}
}
});
jf.add(l1);jf.add(l2);jf.add(tf1);jf.add(pw);jf.add(lb1);
jf.setSize(500,500);
jf.setLayout(null);
jf.setVisible(true);
f.setVisible(false);
}
else if(e.getSource()==b2){
 nf= new JFrame("Librarian login page");
lname = new JLabel("Enter name:");
lpassword = new JLabel("enter password:");
lnam = new JTextField();
lpass = new JPasswordField(15);
lbtn = new JButton("Login");
lname.setBounds(50,50,100,40);
lpassword.setBounds(50,100,100,40);
lnam.setBounds(180,50,200,40);
lpass.setBounds(180,100,200,40);
lbtn.setBounds(100,160,100,50);
nf.add(lname);nf.add(lpassword);nf.add(lnam);nf.add(lpass);nf.add(lbtn);
lbtn.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
String nam = lnam.getText();
String pass = lpass.getText();
boolean i =Mysql.verify(nam,pass);
if (i==true){
JOptionPane.showMessageDialog(null,"logged in successfully!");
nf.setVisible(false);
book =new JFrame("Book Section");
book1 = new JButton("Add Book");
book2 = new JButton("View Book");
book3 = new JButton("Issue Book");
book4 = new JButton("View issued books");
book5 = new JButton("Return book");
book6 = new JButton("logout");

book1.setBounds(150,30,120,50);
book2.setBounds(150,80,120,50);
book3.setBounds(150,130,120,50);
book4.setBounds(150,180,120,50);
book5.setBounds(150,230,120,50);
book6.setBounds(150,280,120,50);

book.add(book1);book.add(book2);book.add(book3);book.add(book4);book.add(book5);book.add(book6);

book1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	addb= new JFrame("Add Books");

ad1= new JLabel("Call no:");
ad2= new JLabel("Book Name:");
ad3= new JLabel("Author:");
ad4= new JLabel("Publisher:");
ad5= new JLabel("Quantity:");

add1 = new JTextField();
add2 = new JTextField();
add3 = new JTextField();
add4 = new JTextField();
add5 = new JTextField();

bad1= new JButton("Add");
bad2= new JButton("Back");

ad1.setBounds(50,50,150,30);
ad2.setBounds(50,90,150,30);
ad3.setBounds(50,130,150,30);
ad4.setBounds(50,170,150,30);
ad5.setBounds(50,210,150,30);


add1.setBounds(200,50,150,30);
add2.setBounds(200,90,150,30);
add3.setBounds(200,130,150,30);
add4.setBounds(200,170,150,30);
add5.setBounds(200,210,150,30);


bad1.setBounds(100,250,120,40);
bad2.setBounds(100,300,120,40);

addb.add(ad1);addb.add(ad2);addb.add(ad3);addb.add(ad4);addb.add(ad5);
addb.add(add1);addb.add(add2);addb.add(add3);addb.add(add4);addb.add(add5);
addb.add(bad2);addb.add(bad1);



addb.setSize(500,500);
addb.setLayout(null);
addb.setVisible(true);
book.setVisible(false);

bad1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
String calln= add1.getText();
String name= add2.getText();
String author= add3.getText();
String publisher= add4.getText();
String quantity= add5.getText();

int i = Mysql1.add(calln,name,author,publisher,quantity);
if (i>0){
JOptionPane.showMessageDialog(null,"Added Successfully!");}else{
JOptionPane.showMessageDialog(null,"Could not add");}


}
});
bad2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
book.setVisible(true);
addb.setVisible(false);
}
});
}
});

book2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
viewb =new JFrame("view books");

String data[][]=null;
String column[]={"id","calln","name","author","publisher","quantity","issue","created_at"};
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");

PreparedStatement ps = con.prepareStatement("select * from book");
ResultSet rs= ps.executeQuery();
ResultSetMetaData md = rs.getMetaData();
int cols = md.getColumnCount();
rs.last();
int rows=rs.getRow();
rs.beforeFirst();

data=new String[rows][cols];
int j=0;
while(rs.next()){
for(int i=1;i<=cols;i++){
   data[j][i-1]=rs.getString(i);
}
j++;
}
con.close();
}catch(Exception e1){
System.out.println(e1);
}
jtb = new JTable(data,column);
jtb.setBounds(50,50,400,900);
jsb = new JScrollPane(jtb);
viewb.add(jsb);
viewb.setSize(900,500);
viewb.setVisible(true);

}
});

book3.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
	biss =new JFrame("Issue Book");
issue1 = new JLabel("Book call no:");
issue2 = new JLabel("Student Id:");
issue3 = new JLabel("Student name:");
issue4 = new JLabel("Student Contact:");

iss1 = new JTextField();
iss2 = new JTextField();
iss3 = new JTextField();
iss4 = new JTextField();

is1 =new JButton("Issue Book");
is2 = new JButton("Back");

issue1.setBounds(50,50,150,30);
issue2.setBounds(50,90,150,30);
issue3.setBounds(50,130,150,30);
issue4.setBounds(50,170,150,30);

iss1.setBounds(200,50,150,30);
iss2.setBounds(200,90,150,30);
iss3.setBounds(200,130,150,30);
iss4.setBounds(200,170,150,30);

is1.setBounds(100,210,120,40);
is2.setBounds(100,260,120,40);

biss.add(issue1);biss.add(issue2);biss.add(issue3);biss.add(issue4);
biss.add(iss1);biss.add(iss2);biss.add(iss3);biss.add(iss4);
biss.add(is1);biss.add(is2);

biss.setSize(500,500);
biss.setLayout(null);
biss.setVisible(true);

is1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
String calln = iss1.getText();
String sid = iss2.getText();
String sname = iss3.getText();
String scontact = iss4.getText();

int i=Mysql1.issue(calln,sid,sname,scontact);
if (i>0){
JOptionPane.showMessageDialog(null,"successfully issued");
Mysql1.update(calln);// updation with selection
}
else{JOptionPane.showMessageDialog(null,"could not issue");}
}
});

is2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
biss.setVisible(false);
book.setVisible(true);
}
});

}
});

book4.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
viewi = new JFrame("View Issued book");
String data[][]=null;
String column[]={"id","calln","sid","sname","scontact","issued_at"};
try{
Class.forName("com.mysql.jdbc.Driver");
Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","Savage-123");
PreparedStatement ps = con.prepareStatement("select * from issue");
ResultSet rs= ps.executeQuery();
ResultSetMetaData rmd= rs.getMetaData();
int columns= rmd.getColumnCount();
rs.last();
int rows= rs.getRow();
rs.beforeFirst();
data= new String[rows][columns];
int j=0;
while(rs.next()){
for(int i=1;i<=columns;i++){
data[j][i-1]=rs.getString(i);
}
j++;

}
con.close();
}
catch(Exception e1){System.out.println(e1);}
jti = new JTable(data,column);
jti.setBounds(50,50,400,900);
jsi = new JScrollPane(jti);
viewi.add(jti);
viewi.setSize(900,500);
viewi.setVisible(true);
}
});

book5.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
rebook = new JFrame("Return of Book");
ret1 = new JLabel("Call no:");
ret2 = new JLabel("Student Id:");
re1 = new JTextField();
re2 = new JTextField();
rb1 = new JButton("Return");
rb2 = new JButton("Back");
ret1.setBounds(50,100,150,50);
ret2.setBounds(50,170,150,50);

re1.setBounds(200,100,150,50);
re2.setBounds(200,170,150,50);

rb1.setBounds(70,250,120,50);
rb2.setBounds(200,250,120,50);

rebook.add(ret1);rebook.add(ret2);rebook.add(re1);rebook.add(re2);rebook.add(rb1);rebook.add(rb2);
rebook.setSize(500,500);
rebook.setLayout(null);
rebook.setVisible(true);
rb1.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
String calln = re1.getText();
String sid = re2.getText();
int i =Mysql1.delis(calln,sid);
if (i>0){
JOptionPane.showMessageDialog(null,"invalid input");}
else{
JOptionPane.showMessageDialog(null,"Successfully returned");
Mysql1.updateb(calln);}
}
});
rb2.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
rebook.setVisible(false);
book.setVisible(true);
}
});
}
});

book6.addActionListener(new ActionListener(){
	public void actionPerformed(ActionEvent e){
f.setVisible(true);
book.setVisible(false);
}
});

book.setSize(500,500);
book.setLayout(null);
book.setVisible(true);
}else{
JOptionPane.showMessageDialog(null,"invalid input");
}



}
});
nf.setSize(500,500);
nf.setLayout(null);
nf.setVisible(true);
f.setVisible(false);
}
}
public static void main(String[] args) {  
        new Library();  
    } }  




