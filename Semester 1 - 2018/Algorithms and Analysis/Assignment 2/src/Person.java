import java.util.ArrayList;
import java.util.List;
public class Person{
   
    public String name;
    public List<String> attributeList;

    public Person(String name, ArrayList<String> attributeList)
    {
	this.name = name;
	this.attributeList = attributeList;
	printDetails();
    }

    public void printDetails()
    {
	System.out.printf("Name:%s\n",name);
	for (String attribute : attributeList)
	{
	    System.out.println(attribute);
	}
    }

}
