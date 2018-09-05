package mobile;
import java.util.*;
public class ExchangeList{  //For each individual node
  LinkedList <Exchange> list = new LinkedList<Exchange>();
  public int length(){
    return list.size();
  }
  public Exchange atIndex(int i){
    return list.get(i); //No error handling
  }
  public void Insert(Exchange e){
    list.add(e);
  }
  public boolean IsMember(Exchange e){
    if(list.contains(e)){
      return true;
    } else {
      return false;
    }
  }
  //All exchanges here
  public static void main(String args[]){
    ExchangeList el = new ExchangeList();
    System.out.println("ExchangeList working!");
    
    Exchange e = new Exchange(4);
    el.Insert(e);
    el.Insert(e);

    System.out.println(el.IsMember(e));
    System.out.println(el.length());
  }

}
