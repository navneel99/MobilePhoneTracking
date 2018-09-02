public class Exchange{
  public int id;
  public Exchange parent; //Exchange id of parent
  public MobilePhoneSet phoneset;
  public ExchangeList children;
  public Exchange(int number){
    id = number;
  }
  public Exchange Parent(){
    return parent;
  }
  public int numChildren(){
    return children.length();
  }
  public Exchange child(int i){
    return children.atIndex(i);
  }
  public Boolean isRoot(){
    if (this.parent == null){
      return false;
    } else {
      return true;
    }
    //is Parent is null
  }
  public RoutingMapTree subtree(int i){

  }
  //Any other I may need
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
}
