public class RoutingMapTree{
  Myset tree;
  public RoutingMapTree(){
    tree = new Myset();
    Exchange root = new Exchange(0);
    root.parent = null;
    tree.Insert(root);
  }
  public void InsertNodeinTree(int i , Exchange a){  //Inserts child  under node b
    Exchange b = new Exchange(i);
    b.parent = a;
    a.children.Insert(b);
    tree.Insert(b);
  }

  public Boolean containsNode(Exchange a){
    if (tree.IsMember(a)){
      return true;
    } else{
      return false;
    }
  }
  public void switchOn(MobilePhone a, Exchange b){
    if (a.status == true) {}
    else {
      a.status = true;
      a.baseStation = b;
    }
  }
  public void switchOff(MobilePhone a){
    if (a.status == true){
      a.status = false;
    } else {};
  }
  public void performAction(String actionMessage){

  }
}
