package mobile;
public class RoutingMapTree{
  Myset tree;
  Exchange root;
  public RoutingMapTree(){
    tree = new Myset();
    root = new Exchange(0);
    root.parent = null;
    tree.Insert(root);
  }
  public void InsertNodeinTree(int i , Exchange a){  //Inserts child  under node b
    if (this.containsNode(a)){
    Exchange b = new Exchange(i);
    b.parent = a;
    tree.Insert(b);
    a.children.Insert(b);
  } else {
    System.out.println("The exchange was not found.");
  }
  }

  public Boolean containsNode(Exchange a){
    if (tree.IsMember(a)){
      return true;
    } else{
      return false;
    }
  }
  public Exchange getNode(int a){
    int lngth = this.tree.length();
    //System.out.println("The length of the tree is: ");
    //System.out.println(lngth);
    for (int k = 0; k< lngth ; k++){
      Object node = tree.atIndex(k);
      Exchange nodes = (Exchange) node;
      if (nodes.id == a){
        return nodes;
      } else{
        continue;
      }
    }
    return null;
  }

  public void switchOn(MobilePhone a, Exchange b){

    if (a.status == true) {
      if (a.baseStation == b){
        System.out.println("The Phone is switched on. It is already registered to the specified Exchange.");
      } else {
        System.out.println("The phone is currently on. Please switch it off to change the Station.");
      }
    }
    else {
      Exchange old_baseStation = a.baseStation;
      a.status = true;
      a.baseStation = b;
      b.phoneset.add(a);
    }
  }
  public void switchOff(MobilePhone a){
    if (a.status == true){
      a.status = false;
    } else {};
  }
  public void performAction(String actionMessage){
    String[] words =new String[3];
    int l = actionMessage.length();
    //System.out.println(l);
    int t =0;
    String word = "";
    for (int m = 0 ; m < l ; m++ ){
      if (actionMessage.charAt(m) == ' ' || actionMessage.charAt(m) == '\n'){
        words[t] = word ;
        //word = words[t];
        //System.out.println(words[2]);
        word = "";
        t++;
      } else{
        word += Character.toString(actionMessage.charAt(m));
      }
    }

    if (words[0].equals("addExchange")){
      System.out.println("Started addExchange!");
      String a = words[1];
      int a1 = Integer.parseInt(a);
      String b = words[2];
      int b1 = Integer.parseInt(b);
      Exchange newer = new Exchange(b1);
      Exchange old = this.getNode(a1);
      if (old != null){
        newer.parent = old;
        old.children.Insert(newer);
        tree.Insert(newer);
      } else{
        System.out.println("The original node doesn't exist.");
        //EXCEPTION (old doesn't exist)
      }

    } else if (words[0].equals("switchOnMobile")){
      System.out.println("Switched on mobile!");
      String a = words[1];
      String b = words[2];
      int a1 = Integer.parseInt(a);
      int b1 = Integer.parseInt(b);
      Exchange old=this.getNode(b1);
      if (old != null){              //THE exchange exists
      MobilePhone phone = old.phoneset.getPhone(a1); //Mobilephone belonged at the givn input exchange.

      if (phone != null){  //The Mobile phone was already registered at old.
        phone.status = true;
      } else { //Mobile phone did not belong at the old exchange.
      //Either it existed at some other node or none. Remove that.
      MobilePhone phone1 = root.residentSet().getPhone(a1);
      //System.out.println("A phone did not exist originally in this node.");
      if (phone1 == null){
        MobilePhone newer = new MobilePhone(a1);
        newer.status = true;
        newer.baseStation = old;
        old.phoneset.add(newer);
      }
        else{
          //System.out.println("The phone was in some other node.");
          boolean curr_stat = phone1.status;
          if (curr_stat == true){
            System.out.println("The mobile is currently switched on at another Exchange. Please switch it off to change the Exchange.");
          } else{
            phone1.status = true;
            Exchange old_parent = phone1.location();
            old_parent.phoneset.remove(phone1);
            Exchange parent = old_parent.Parent();
            while (parent != null){
              parent.phoneset.remove(phone1);
              parent = parent.Parent();
            }
            phone1.baseStation = old;
            old.phoneset.add(phone1);
          }
        }
      //MobilePhone newer = new MobilePhone(a1);
      //newer.status = true;
      //newer.baseStation = old;
      //old.phoneset.add(newer);
        }
      } else {
        System.out.println("The Exchange doesn't exist.");
      //Throw EXCEPTION
      }
    } else if (words[0].equals("switchOffMobile")){
      System.out.println("Switched Off Mobile!");
      String a = words[1];
      int a1 = Integer.parseInt(a);
      Object root_object = tree.atIndex(0);
      Exchange root = (Exchange) root_object;
      MobilePhoneSet all_phones = root.residentSet();
      MobilePhone phone = all_phones.getPhone(a1);
      if (phone != null){
        phone.status = false;
      } else {
        //RETURN EXCEPTION
      }
    } else if (words[0].equals("queryNthChild")){
      String a = words[1];
      String b = words[2];
      int a1 = Integer.parseInt(a);
      int b1 = Integer.parseInt(b);
      //System.out.println(a1);
      Exchange old = this.getNode(a1);

      //System.out.println(old);

      Exchange newer = old.child(b1);
      int result = newer.id;
      System.out.println(result);

    } else if (words[0].equals("queryMobilePhoneSet")){
      String a = words[1];
      int a1 = Integer.parseInt(a);
      Exchange curr_node = this.getNode(a1);

      MobilePhoneSet phones = curr_node.residentSet();
      int ln = phones.length();
      for (int k =0 ; k<ln; k++){
        MobilePhone phone = phones.getPhoneByIndex(k);
        if (phone.status == true){
        System.out.print(phone.phno);
        System.out.print("\t");
      } else{}
    }
      System.out.print("\n");
      //Print Identifiers of all the mobilephones in the set
    } else {
      //EXCEPTION
    }
    //System.out.println(actionMessage);
  }
  public static void main(String args []){
    /*
    System.out.println("Working!");
    RoutingMapTree t = new RoutingMapTree();   //New tree initialized!
    System.out.println(t.tree.length());  //Length working!
    Object l =t.tree.atIndex(0);
    Exchange l1 = (Exchange) l;
    //System.out.println(l1.numChildren()); //id, Parent(),isRoot(),
    //Exchange lol = new Exchange(1);  //Adding new node
    t.InsertNodeinTree(1,l1);   //InsertNodeinTree working
    System.out.println(t.tree.length());
    Object l2 =t.tree.atIndex(1);
    Exchange l3 = (Exchange) l2;
    MobilePhone mob = new MobilePhone(987);
    t.switchOn(mob,l3); //mob is registered to the new node
    t.InsertNodeinTree(2,l1);
    System.out.println(t.tree.length());
    Object l4 =t.tree.atIndex(2);
    Exchange l5 = (Exchange) l4;
    MobilePhoneSet set = l5.residentSet();
    System.out.println(set.length()); */
    /*
    RoutingMapTree t = new RoutingMapTree();
    Object objroot = t.tree.atIndex(0);
    Exchange root = (Exchange) objroot;
    t.InsertNodeinTree(1,root);
    t.InsertNodeinTree(2,root);
    Object objnode1 = t.tree.atIndex(1);
    Object objnode2 = t.tree.atIndex(2);
    Exchange node1 = (Exchange) objnode1;
    Exchange node2 = (Exchange) objnode2;
    t.InsertNodeinTree(1,node1);
    t.InsertNodeinTree(1,node1);
    t.InsertNodeinTree(1,node1);
    Object objnode3 = t.tree.atIndex(3);
    Exchange node3 = (Exchange) objnode3;
    MobilePhone mob  = new MobilePhone(987);
    MobilePhone mob2 = new MobilePhone(654);
    t.switchOn(mob,node3);
    t.switchOn(mob2,node3);
    System.out.println("Mobilephones in root: ");
    System.out.println(root.residentSet().length());
    System.out.println("Mobilephones in node1: ");
    System.out.println(node1.residentSet().length());
    System.out.println("Mobilephones in node2: ");
    System.out.println(node2.residentSet().length());
    System.out.println("Mobilephones in node3: ");
    System.out.println(node3.residentSet().length()); //WORKING! */
    RoutingMapTree t = new RoutingMapTree();
    t.performAction("addExchange 0 1 ");
    //System.out.println("Done!");
    t.performAction("addExchange 0 2 ");
    t.performAction("addExchange 0 3 ");
    t.performAction("queryNthChild 0 0 ");
    t.performAction("queryNthChild 0 2 ");
    t.performAction("addExchange 1 4 ");
    t.performAction("addExchange 1 5 ");
    t.performAction("addExchange 2 6 ");
    t.performAction("addExchange 2 7 ");
    t.performAction("addExchange 2 8 ");
    t.performAction("addExchange 3 9 ");
    t.performAction("queryNthChild 2 0 ");
    t.performAction("queryNthChild 3 0 ");
    t.performAction("switchOnMobile 989 4 ");
    t.performAction("switchOnMobile 876 4 ");
    t.performAction("queryMobilePhoneSet 4 ");
    t.performAction("queryMobilePhoneSet 1 ");
    t.performAction("switchOnMobile 656 5 ");
    t.performAction("switchOnMobile 54 5 ");
    t.performAction("queryMobilePhoneSet 1 ");
    //t.performAction("switchOffMobile 656 ");
    t.performAction("switchOnMobile 213 6 ");
    t.performAction("switchOnMobile 568 7 ");
    t.performAction("switchOnMobile 897 8 ");
    t.performAction("switchOnMobile 295 8 ");
    t.performAction("switchOnMobile 346 9 ");
    System.out.println("Going to add 656 in node 6");
    t.performAction("switchOnMobile 656 6 ");
    t.performAction("queryMobilePhoneSet 0 ");
    t.performAction("queryMobilePhoneSet 1 ");
    t.performAction("queryMobilePhoneSet 2 ");
    t.performAction("queryMobilePhoneSet 3 ");
  }
}
