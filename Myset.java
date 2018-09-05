package mobile;
//import java.util.*;
public class Myset{
  public LinkedList<Object> Set;
  public Myset(){
    Set = new LinkedList<Object>();
  }
  public int length(){
    return Set.size();
  }
  public Object atIndex(int i){
    return Set.get(i);
  }
  public Boolean IsEmpty(){
    if (Set.size() == 0){
      return true;
    }
    else{
      return false;
    }
  }
  public Boolean IsMember(Object o){
    if (Set.contains(o)){
      return true;
    } else {
      return false;
    }
  }
  public void Insert(Object o){
    Set.add(o);
  }
  public void Delete(Object o){
    if (this.IsMember(o)){
      Set.remove(o);
    } else {
      System.out.println("The element is not found.");
      //RAISE EXCEPTION ELEMENT NOT FOUND
    }
  }
  public Myset Union(Myset a){
    int length = a.Set.size();
    for (int i = 0; i < length; i++){
      Object element = a.Set.get(i);
      if (this.IsMember(element)){
        continue;
      } else {
        this.Insert(element);
      }
    }
    return this;
  }
  public Myset Intersection(Myset a){
    Myset c =new Myset();
    int lng1 = this.Set.size();
    int lng2 = a.Set.size();
    int min = lng1<lng2 ? lng1 : lng2 ;
    int which = lng1<lng2 ? 0 : 1 ;
    Object element;
    for (int i = 0; i < min; i++){
      if (which == 0){
        element = this.Set.get(i);
      }
      else{
        element = a.Set.get(i);
      }
      if (this.IsMember(element) && a.IsMember(element)) {
        c.Insert(element);
      } else {
        continue;
      }
    }
    return c;
  }
  /*
  public static void main(String[] args){
    System.out.println("Ok");
    Myset a = new Myset();
    Myset b = new Myset();
    b.Insert(2);
    b.Insert(5);
    a.Insert(5);
    System.out.println(a.IsMember(5));
    //a.Delete(5);
    //System.out.println(a.IsMember(5));
    Myset c = a.Intersection(b);
    System.out.println(c.IsMember(5));
    System.out.println(c.IsMember(2));
    System.out.println(c);

  }
  */
}
