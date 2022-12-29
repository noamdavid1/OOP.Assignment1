package observer;

import java.util.HashMap;

public class main {
    public static void main(String[] args) {
        UndoableStringBuilder sb = new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        ConcreteMember rami  = new ConcreteMember("Rami",sb);
        HashMap<Member,String> ls = new HashMap<>();
        System.out.println(1);
        GroupAdmin ga = new GroupAdmin(ls, sb);
        ga.append(" ok");
        System.out.println(ga);
        System.out.println("**********");
        ga.register(yuval);
        ga.register(rami);
        System.out.println("*********************");
        System.out.println(ls);
        System.out.println("*********************8");
        System.out.println(2);
        ga.append(" okkkkkk!");
        ConcreteMember noam = new ConcreteMember("noam",sb);
        ga.undo();
        ga.insert(3, "index number 3");
        System.out.println(noam.getUsb());
        System.out.println(yuval.getUsb());
        ga.register(noam);
        System.out.println(3);
        ga.register(noam);
        System.out.println(4);
        ga.append(" okk");
        System.out.println(yuval.getUsb());
        System.out.println(noam.getUsb());
        System.out.println(5);
    }
}
