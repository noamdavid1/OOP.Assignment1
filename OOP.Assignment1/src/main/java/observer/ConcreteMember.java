package observer;

public class ConcreteMember implements Member{
    private String name;
    private UndoableStringBuilder usb;
    /**
     * constructor.
     * @param name is a string represents the name of the member.
     * @param usb is an UndoStringBuilder
     */
    public ConcreteMember(String name, UndoableStringBuilder usb){
     this.name= name;
     this.usb= usb;
    }
    /**
     * @return the string that the member is connected to.
     */
    public UndoableStringBuilder getUsb() {
        return usb;
    }
    /**
     * @return the string that represents the name of the member.
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ConcreteMember{" +
                "name=" + name +
                ", usb=" + usb +
                '}';
    }
    /**
     * This method prints an update message to all the registers associated with this string.
     * @param usb is the string that connected to the member.
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        System.out.println("message for:"+name+"    UndoableStringBuilder have been changed!:"  + usb);
    }
}
