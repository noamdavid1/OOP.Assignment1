import observer.ConcreteMember;
import observer.GroupAdmin;
import observer.Member;
import observer.UndoableStringBuilder;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    // stub method to check external dependencies compatibility
    @Test
    public void test() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval", sb);
        ConcreteMember rami = new ConcreteMember("rami",sb);
        ConcreteMember noam = new ConcreteMember("rami",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.register(rami);

        System.out.println(JvmUtilities.objectTotalSize(ga));

        ga.register(noam);
        ga.append(" ok");
        System.out.println(JvmUtilities.objectTotalSize(ga));

        logger.info(() -> JvmUtilities.objectFootprint(yuval));

        logger.info(() -> JvmUtilities.objectFootprint(yuval, rami));

        logger.info(() -> JvmUtilities.objectTotalSize(yuval));

        logger.info(() -> JvmUtilities.jvmInfo());
    }
}

class GroupAdminTest {


    @Test
    void notifyRegisters() {
//        change the USB for empty has-map
//        change the USB for unregister member
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval", sb);
        ConcreteMember rami = new ConcreteMember("rami",sb);
        HashMap<Member,String > hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.register(rami);
        ga.append(" okk");
        assertEquals(yuval.getUsb(),rami.getUsb());
    }


    @Test
    void register() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        UndoableStringBuilder st= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval", sb);
        ConcreteMember yuval2 = new ConcreteMember("yuval", sb);
        ConcreteMember rami = new ConcreteMember("rami",sb);
        ConcreteMember noam = new ConcreteMember("noam",st);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        assertTrue(hm.isEmpty()); //before the registration the hashmap is empty.
        ga.register(yuval);
        ga.register(yuval2); //There is a possibility that the hashmap will have two users with the same name but they are different members.
        ga.register(rami);
        ga.register(noam);      //try to register 2 different strings to the same groupAdmin.
        assertTrue(hm.containsValue(yuval));
        assertTrue(hm.containsValue(rami));
        assertFalse(hm.containsValue(noam));
        assertEquals(2, hm.size()); //verification that users are registered.
        ConcreteMember tal = new ConcreteMember("tal",sb);
        assertEquals(2, hm.size()); //create a new concrete member(tal) but without registration so that the size of the hashmap still 2.
        ga.register(tal);
        assertEquals(3, hm.size());
        ga.register(tal);      //register the same user twice
        assertEquals(3, hm.size());
    }

    @Test
    void unregister() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        ConcreteMember rami = new ConcreteMember("rami",sb);
        ConcreteMember tal = new ConcreteMember("tal",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.register(rami);
        ga.unregister(yuval);
        ga.unregister(tal);
        assertFalse(hm.containsValue(yuval)); //Yuval is no longer part of the registry.
        assertFalse(hm.containsValue(tal));  //try to unregister someone who isn't register at all(line 98) so that the hashmap not contains this member.
        assertTrue(hm.containsValue(rami));
        assertEquals(1, hm.size());
        ConcreteMember noam = new ConcreteMember("noam",sb);
        ga.register(noam);
        assertEquals(2, hm.size());
        ga.unregister(noam);
        ga.unregister(rami);
        assertTrue(hm.isEmpty()); //unregisters all the members so that the hashmap is empty.
        ga.unregister(rami);   //try to unregister empty hash-map.
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
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.append("ok ");
        assertEquals(yuval.getUsb(),sb);
    }

    @Test
    void delete() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.delete(7,11);
        assertEquals(yuval.getUsb(),sb);
    }

    @Test
    void undo() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("strings bla blab");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        ga.undo();
        assertEquals(yuval.getUsb(), sb);
        ga.append(" ok");
        ga.undo();
        assertEquals(yuval.getUsb(),sb);
    }
}


class ConcreteMemberTest {
    @Test
    void update() {
        UndoableStringBuilder sb= new UndoableStringBuilder();
        sb.append("bla");
        ConcreteMember yuval = new ConcreteMember("yuval",sb);
        HashMap<Member,String> hm = new HashMap<>();
        GroupAdmin ga = new GroupAdmin(hm, sb);
        ga.register(yuval);
        sb.append(" ok");
        yuval.update(sb);
        assertEquals("bla ok",yuval.getUsb().toString());
        ga.undo();
        assertEquals("bla",ga.getUsb().toString());
    }
}
