package Link;


public class TestLinked {
    public static void main(String[] args) throws CloneNotSupportedException {
        Linked<String> linked = new Linked<>();
        linked.add("1", 0);
        linked.add("0", 0);
        Linked<String> t = (Linked<String>) linked.clone();
        System.out.println(linked.getHead());
        System.out.println(t.getHead());
        System.out.println(linked.equals(t));
        System.out.println(linked.equals(linked));

        //System.out.println(System.identityHashCode(t));
        //System.out.println(System.identityHashCode(linked));
        /*Linked.Itr itr=linked.getItr();
        while (itr.hasNext()){
            System.out.println(itr.next());
        }*/


        //System.out.println(linked.getSize());
        //System.out.println(t.getSize());
        /*ArrayList<String> dad=new ArrayList<String>();
        dad.add("test");
        linked.addAll(0,dad);
        System.out.println(linked.getSize());*/
    }

}