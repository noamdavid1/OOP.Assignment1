package observer;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class GroupAdminTest {


    @Test
    void notifyRegisters() {
//        change the USB for empty has-map
//        change the USB for unregister member
        UndoableStringBuilder sb= new UndoableStringBuilder();
        UndoableStringBuilder st= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval", sb);
        ConcreteMember rami = new ConcreteMember("rami",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.register(rami);
        ga.append(" okk");
        assertEquals(yuval.getUsb(),rami.getUsb());
        //*******
        ga.undo();
//        assertNotEquals(noam.getUsb(),yuval.getUsb());
        ConcreteMember noam= new ConcreteMember("noam", st);
        ga.register(noam);
        ga.append(" okk");
        System.out.println(noam.getUsb());
        System.out.println(rami.getUsb());
        assertNotEquals(noam.getUsb(),rami.getUsb());
    }


    @Test
    void register() {
//        try to register 2 different strings to the same groupAdmin
//        register the same user twice
//        register 2 same names with different ConcreteMemeber
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval", sb);
        ConcreteMember rami = new ConcreteMember("rami",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        assertTrue(hm.isEmpty());
        ga.register(yuval);
        ga.register(rami);
        assertTrue(hm.containsValue(yuval));
        assertTrue(hm.containsValue(rami));
        assertEquals(2, hm.size());
        ConcreteMember noam = new ConcreteMember("noam",sb);
        assertEquals(2, hm.size());
        ga.register(noam);
        assertEquals(3, hm.size());
    }

    @Test
    void unregister() {
//        try to unregister someone who isn't register at all.
//        try to unregister empty hash-map.
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        ConcreteMember rami = new ConcreteMember("rami",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.register(rami);
        ga.unregister(yuval);
        assertFalse(hm.containsValue(yuval));
        assertTrue(hm.containsValue(rami));
        assertEquals(1, hm.size());
        ConcreteMember noam = new ConcreteMember("noam",sb);
        ga.register(noam);
        assertEquals(2, hm.size());
        ga.unregister(noam);
        ga.unregister(rami);
        assertTrue(hm.isEmpty());
    }

    @Test
    void insert() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.insert(0,"ok ");
        assertEquals(yuval.getUsb(),sb);
    }

    @Test
    void append() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        HashMap<Member,String> ls = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(ls, sb);
        ga.register(yuval);
        ga.append("ok ");
        assertEquals(yuval.getUsb(),sb);
    }

    @Test
    void delete() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        HashMap<Member,String> ls = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(ls, sb);
        ga.register(yuval);
        ga.delete(7,11);
        assertEquals(yuval.getUsb(),sb);
    }

    @Test
    void undo() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        HashMap<Member,String> ls = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(ls, sb);
        ga.register(yuval);
        ga.undo();
        assertEquals(yuval.getUsb(), sb);
        ga.append(" ok");
        ga.undo();
        assertEquals(yuval.getUsb(),sb);
    }
}