package mobile;
public class Exchange{
  public int id;
  public Exchange parent; //Exchange id of parent
  public MobilePhoneSet phoneset = new MobilePhoneSet();
  public ExchangeList children = new ExchangeList();
  public Exchange(int number){
    id = number;
  }
  public Exchange Parent(){
    return parent;
  }
  public int numChildren(){
    return children.length();
  }
  void a() throws NullPointerException{
    throw new NullPointerException("lolol");
  }
  public Exchange child(int i){
    try {
          if (i < this.numChildren()){
            return children.atIndex(i);
          } else {
            a();
            return null;
        }
    }  catch(Exception e){
        //System.out.println("Specified index is higher than the number of children.");
        return null;
        }

  }

  public Boolean isRoot(){
    if (this.parent == null){
      return true;
    } else {
      return false;
    }
    //is Parent is null
  }
  /*public RoutingMapTree subtree(int i){

  }*/
  //Any other I may need
  public boolean containsExchange(Exchange a){
    if (this == a){
      return true;
    }else
    {
    int i = 0;
    while (i < this.numChildren()){
      Exchange curr = this.child(i);
      if (curr == a){
        return true;
      } else {
        if (curr.containsExchange(a) == true){
          return curr.containsExchange(a);
        }
      }
      i++;
    }
    return false;
  }
  }
  /*
  public Exchange lowestExchange(Exchange a , Exchange b){
    if (this == a){
      return true;
    }else
    {
    int i = 0;
    while (i < this.numChildren()){
      Exchange curr = this.child(i);
      if (curr == a){
        return true;
      } else {
        if (curr.containsExchange(a) == true){
          return curr.containsExchange(a);
        }
      }
      i++;
    }
    return false;
  }
  }
*/

  public MobilePhoneSet residentSet(){
    int lng = this.numChildren();
    if (lng == 0) {
    	return phoneset;
    	//Return number of mobile phones
    } else {
    for (int k = 0; k< lng; k++){
      Exchange ele = this.child(k);
      MobilePhoneSet mobiles = ele.residentSet();
      phoneset.Union(mobiles);
    }
    return phoneset;
    //returns a set of phones of the exchange
  }
  }
  public static void main(String[] args){
    Exchange r =new Exchange(1);
    Exchange s =new Exchange(2);
    Exchange t =new Exchange(3);
    Exchange u =new Exchange(4);
    Exchange v =new Exchange(5);

    s.parent = r;
    r.children.Insert(s);
    u.parent = s;
    s.children.Insert(u);
    v.parent = s;
    s.children.Insert(t);
    t.parent = s;
    s.children.Insert(t);
    System.out.println(s.numChildren());
    System.out.println(t.containsExchange(u));
    System.out.println("Finished!");

  }
}
