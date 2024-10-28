import java.util.*;
import java.io.*;
public class PhoneBook implements IMap
{
   private Entry[] eN;
   private int size;
   private int capacity;
   private double limit;
   
  private class Entry
  {
    private Person p;
    private PhoneNumber ph;
    private Entry next;
   
    public Entry(Person pP, PhoneNumber pH)
    {
      p = pP;
      ph= pH;
    }
      
    @Override
    public String toString()
    {
      return p + " " + ph;
    }
         
   } 
   public PhoneBook(int cap)
   {
      eN = new Entry[cap];
      capacity = cap;
      size = 0;
      limit = 0.5;
   }
   
   public PhoneBook()
   {
      eN = new Entry[11];
      capacity = 11;
      size = 0;
      limit = 0.5;
   }
 
   public PhoneNumber put(Person person, PhoneNumber phone)
   {
     if(size > limit * capacity)
     {
       resizeAndRehash();
     }
     
     int index = person.hashCode() % capacity;
     Entry s = eN[index];
     while(s!= null)
     {
       if(s.p.equals(person) == true)
       {
         PhoneNumber oldP = s.ph;
         s.ph = phone;
         return oldP;
        }
         s = s.next;
      }
      Entry add = new Entry(person, phone);
      if(eN[index] != null)
      {
        eN[index].next = add;
        size++;
      }
      else
      {
        eN[index] = add;
        size++;
      }
      return null;
     }
   
   public PhoneNumber get(Person person)
   {
     int index = person.hashCode() % capacity;
       
     Entry current = eN[index];
     while(current!=null)
     {
       if(current.p.equals(person) == true)
       {
         return current.ph;
       }
       current = current.next;
     }      
      return null;
    }
    
   public PhoneNumber remove(Person person)
   {
      int index = person.hashCode() % capacity;
      
      if(eN[index] != null)
      {
        if (eN[index].p.equals(person))
        {
          Entry c = eN[index];
          size--;
          PhoneNumber oldP = c.ph;
          eN[index] = c.next;
          return oldP;
         }
      }
      if (eN[index] != null)
      {
        Entry s = eN[index];
        while(s.next != null)
        {
          if(s.next.p.equals(person))
          {
            PhoneNumber oldP = s.next.ph;
            size--;
            s.next = s.next.next;
            return oldP;
           }
            s = s.next;
         }
       }
       return null;
    }
    
   public int size()
   {
      return size;
   }

   public void printHashTable()
   {
      for(int i = 0; i < eN.length; i++)
      {
        System.out.print("Index " + i + " >> "); 
        if (eN[i] == null)
        {
          System.out.print("Empty");
          System.out.println();
          continue;
        }
        Entry s = eN[i];
        if(s.next == null)
        {
          System.out.print(s.toString());
        }
        if(s.next != null)
        {
          while(s.next != null)
          {
            System.out.print(s.toString() + " >> ");
            s = s.next;
           }
           System.out.print(s.toString());
         }
         System.out.println();
        }
   }
   
   public void resizeAndRehash()
   {
      Entry[] nA = eN;
      capacity = capacity * 2;
      size = 0;
      eN = new Entry[capacity];
      for(int i = 0; i < nA.length; i++)
      {
         while(nA[i] != null)
         {
            PhoneNumber nP = put(nA[i].p, nA[i].ph);
            nA[i] = nA[i].next;
         }
      }    
       
   }
   
   public void setLimit(double percentage)
   {
     limit = percentage;
   }
   
   
   
}