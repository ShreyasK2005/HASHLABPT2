import java.util.*;
public class Person
{
  private String n;
  public Person(String name)
  {
    n = name;
  }
   @Override
   public int hashCode()
   {
     int total = 0;
     for (int i = 0; i < n.length(); i++) 
     {
       total += (int) n.charAt(i);
     }
     return total % n.length();
   }
  
  public boolean equals(Person z)
  {
    if(z.n == n)
    {
      return true;
    }
    return false;
  }
  
  
   @Override
   public String toString()
   {
      return n;
   }  
}