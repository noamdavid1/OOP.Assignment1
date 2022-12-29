package observer;

import java.util.HashMap;


public class GroupAdmin implements Sender {
    private HashMap<Member, String> registers;
    private UndoableStringBuilder usb;
    /**
     * constructor.
     * @param hmp represents hashmap of members, so that the key it's the name of the
     *            member and the value it's the member.
     * @param usb represents the string that connected to the members.
     */
    public GroupAdmin(HashMap<Member, String> hmp, UndoableStringBuilder usb) {
        this.usb = usb;
        this.registers = hmp;
    }

    /**
     * This method notifies all registers of a change in the string
     * using the update function on ConcreteMember class.
     */
    public void notifyRegisters() {
        if (registers.size() != 0) { //if the size of the registers is 0 there is no one to notify about changes.
            for (Member member : this.registers.keySet()) { //notify all the members that register to the registers.
                member.update(this.usb);
            }
        } else {
            System.err.println("There are no registers linked to this undo string builder");
        }
    }

    /**
     *
     * @return the UndoStringBuilder that relate to this GroupAdmin.
     */
    public UndoableStringBuilder getUsb(){
        return usb;
    }
    /**
     *
     * @return the current string
     */
    @Override
    public String toString() {
        return "GroupAdmin{" +
                "registers=" + registers +
                ", usb=" + usb +
                '}';
    }

    /**
     * this method register observers
     * @param obj is a concrete member that register to the hashmap so that if
     *            there is a change in the string that relate to this member he will
     *            get notification on this change.
     */
    @Override
    public void register(Member obj) {
        boolean flag = true;
        if (registers.isEmpty() || ((ConcreteMember) obj).getUsb() == this.usb) { //can't attach different string to the same group
            if (registers.containsValue(obj)) { //there is no option to register more than one time.
                System.out.println("This user already exist in the registers list!");
                flag = false;
            }
            if (flag) {
                this.registers.put(obj, ((ConcreteMember) obj).getName()); //register the obj the hashmap.
                System.out.println("Add new register: " + ((ConcreteMember) obj).getName() + "!");
            }
        } else{
            System.err.println("You can't attach different string to this group.");
        }
    }

    /**
     * this method unregister observers.
     * @param obj is a concrete member that wants to unregister to the hashmap so that if
     *            there is a change in the string that relate to this member he will
     *            not get notification on this change.
     */
    @Override
    public void unregister(Member obj) {
        if (registers.containsValue(obj)) {
            registers.remove( obj,((ConcreteMember) obj).getName()); //unregister obj
            System.out.println("Unregister member!");
        } else {
            System.err.println("There is no registered member by this name.");
        }
    }
    /**
     * Inserts the string into this character sequence.
     * @param offset- the str will insert in the offset index.
     * @param obj - the string that will insert in the offset index.
     */
    @Override
    public void insert(int offset, String obj) {
        usb.insert(offset, obj);
        notifyRegisters();
    }

    /**
     * Appends the specified string to this character sequence.
     * @param obj The string to append to the end of the given string.
     */
    @Override
    public void append(String obj) {
        usb.append(obj);
        notifyRegisters();
    }

    /**
     * Removes the characters in a substring of this sequence.
     * @param start- start index to delete.
     * @param end- end index to delete.
     */
    @Override
    public void delete(int start, int end) {
        usb.delete(start, end);
        notifyRegisters();
    }

    /**
     * Erases the last change done to the document, reverting
     * it to an older state.
     */
    @Override
    public void undo() {
        usb.undo();
        notifyRegisters();
    }

}
