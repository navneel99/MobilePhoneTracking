import java.util.*;
public class ExchangeList{
  LinkedList <Exchange>list = new LinkedList<Exchange>();
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
}
