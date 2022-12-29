package observer;
import java.util.Stack;

/*
Use the class you've implemented in previous assignment
 */
public class UndoableStringBuilder {
    StringBuilder st ;
    Stack <StringBuilder> sb = new Stack<>();
    /**
     * constructor.
     */
    public UndoableStringBuilder(){
        this.st= new StringBuilder();
    }
    /**
     *  Appends the specified string to this character sequence.
     * @param str The string to append to the end of the given string.
     * @return UndoableStringBuilder.
     */
    public UndoableStringBuilder append(String str) {
        StringBuilder temp = new StringBuilder(st);
        sb.push(temp);
        st.append(str);
        return this;
    }
    /**
     *Removes the characters in a substring of this sequence. The substring begins
     *at the specified start and extends to the character at index
     *end - 1 or to the end of the sequence if no such character exists.
     *If start is equal to end, no changes are made.
     * @param start start index to delete.
     * @param end  end index to delete.
     * @return UndoableStringBuilder.
     */
    public UndoableStringBuilder delete(int start, int end) {
        try {
            StringBuilder temp = new StringBuilder(st);
            sb.push(temp);
            st.delete(start, end);
            return this;
        } catch (StringIndexOutOfBoundsException ex) {
            System.err.println("String index out of bounds exception!");
            ex.printStackTrace();
            return this;
        }
    }
    /**
     *Insert the str in the offset index without override the current string.
     * @param offset - the str will insert in the offset index
     * @param str - the string that will insert in the offset index
     * @return UndoableStringBuilder.
     */
    public UndoableStringBuilder insert(int offset, String str) {
        try{
            StringBuilder temp = new StringBuilder(st);
            sb.push(temp);
            st.insert(offset, str);
            return this;
        }
        catch(StringIndexOutOfBoundsException ex){
            System.err.println("String index out of bounds exception!");
            ex.printStackTrace();
            return this;
        }
    }
    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String. The substring begins at the specified start and
     * extends to the character at index end - 1 or to the end of the sequence if
     * no such character exists. First the characters in the substring are removed
     * and then the specified String is inserted at start. (This sequence will be
     * lengthened to accommodate the specified String if necessary).
     *
     * @param start - start index for the string replace.
     * @param end - end index for the string replace.
     * @param str - the string that will replace the original string between the indexes above.
     * @return UndoableStringBuilder.
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        try {
            StringBuilder temp = new StringBuilder(st);
            sb.push(temp);
            st.replace(start, end, str);
            return this;
        }
        catch(StringIndexOutOfBoundsException ex){
            System.err.println("String index out of bounds exception!");
            ex.printStackTrace();
            return this;
        }
        catch(NullPointerException ex){
            System.err.println("Input can't be null!");
            ex.printStackTrace();
            return this;
        }
    }
    /**
     * Causes this character sequence to be replaced by the reverse of the
     * sequence.
     * @return UndoableStringBuilder.
     */
    public UndoableStringBuilder reverse() {
        StringBuilder temp = new StringBuilder(st);
        sb.push(temp);
        st.reverse();
        return this;
    }
    /**
     * Cancel the last operation.
     */
    public void undo() {
        if(!sb.isEmpty()) {
            StringBuilder temp = new StringBuilder(sb.pop());
            st = temp;
        }
    }
    /**
     * Print the current string
     */
    @Override
    public String toString() {
        return st.toString();
    }
}
