import java.io.*;
import java.util.*;
import java.awt.*;
class StudentFile extends Frame
{
	TextField number,name,marks;
	Button enter,done;
	Label numLabel,nameLabel,markLabel;
	DataOutputStream dos;
	public StudentFile()
	{
		super("Create Student File");
	}
	public void setup()throws IOException
	{
		resize(400,200);
		setLayout(new GridLayout(4,2));
		number=new TextField(25);
		numLabel=new Label("Roll Number ");
		name=new TextField(25);
		nameLabel=new Label("Student Name :");
		marks=new TextField(25);
		markLabel=new Label("Marks ");
		enter=new Button("Enter ");
		done=new Button("Done ");
		add(numLabel);
		add(number);
		add(nameLabel);
		add(name);
		add(markLabel);
		add(marks);
		add(enter);
		add(done);
		show();
		dos=new DataOutputStream(new FileOutputStream("Student.dat"));
	}
	public void addRecord()throws Exception
	{
		int num;
		Double d;
		num=(new Integer(number.getText())).intValue();
		dos.writeInt(num);
		dos.writeUTF(name.getText());
		d=new Double(marks.getText());
		dos.writeDouble(d.doubleValue());
		number.setText("");
		name.setText("");
		marks.setText("");
	}
	public void cleanup()throws Exception
	{
		if(!number.getText().equals(" "))
		   	addRecord();
		dos.flush();
		dos.close();
	}
	public boolean action(Event event,Object o)
	{
		if(event.target instanceof Button)
		if(event.arg.equals("Enter "))
		{	
			try { 
			        addRecord();  } catch(Exception e){}
			return true;
		}
		return super.action(event,o);
	}
	public boolean handleEvent(Event event)
	{
		if(event.target instanceof Button)
		if(event.arg.equals("Done "))
		{
			try { 
			       cleanup();  }  catch(Exception e){}
			System.exit(0);
			return true;
		}
		return super.handleEvent(event);
	}
	public static void main(String s[])throws Exception
	{
		StudentFile student=new StudentFile();
		student.setup();
	}
}