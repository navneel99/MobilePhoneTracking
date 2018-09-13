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
  public ExchangeList reverse(){
    int len = this.length() -1 ;
    ExchangeList rev = new ExchangeList();
    while (len != -1){
      rev.Insert(this.atIndex(len));
      len--;
    }
    return rev;

  }

  public void remove(int index){
    list.remove(index);
  }
  public void extend(ExchangeList el){
    int len = el.length();
    for ( int i = 0; i < len ; i++){
       this.Insert(el.atIndex(i));
    }
  }


  //All exchanges here
  public static void main(String args[]){
    ExchangeList el = new ExchangeList();
    //System.out.println("ExchangeList working!");

    Exchange e = new Exchange(4);
    Exchange f = new Exchange(5);

    el.Insert(e);
    el.Insert(f);
    ExchangeList a= el.reverse();
    System.out.println(el.atIndex(1).id + a.atIndex(0).id);
    System.out.println(el.IsMember(e));
    System.out.println(el.length());
  }

}
