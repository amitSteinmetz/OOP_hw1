package observer;

import java.util.Stack;

/**
 * A simple class that similar to StringBuilder class,
 * with one addition - an "undo" operation.
 * @version 1.0 Nov 2022
 * @author Amit steinmetz & Michael Idan
 */
public class UndoableStringBuilder {
    private StringBuilder text;
    /**
     * Save memory in data_base, for "undo" operation
     */
    private Stack<String> data_base;

    /**
     * Constructs an empty string builder,
     * and initialize a new stack.
     */
    public UndoableStringBuilder() {
        this.text = new StringBuilder();
        this.data_base = new Stack<>();
    }

    /**
     * Appends the specified string to this character sequence.
     * The characters of the String argument are appended, in order, increasing the length of this sequence by the length of the argument.
     * The full explanation is at StringBuilder javaDoc, in "append" method.
     *
     * @param str the string to append
     * @return  this object
     */
    public UndoableStringBuilder append(String str) {

        this.text = text.append(str);   // calling "append" in StringBuilder class
        data_base.push(text.toString());  // push the current text to stack, for documentation purposes
        return this;
    }

    /**
     * Removes the characters in a substring of this sequence.
     * The full explanation is at StringBuilder javaDoc, in "delete" method.
     *
     * @param start the index of first character in the sequence to delete
     * @param end  the index of last character in the sequence to delete
     * @return  this object
     */
    public UndoableStringBuilder delete(int start, int end) {

        try {  // running the rest of program even if error will occur, with notification
            this.text = text.delete(start, end);   // calling "delete" in StringBuilder class
        } catch (StringIndexOutOfBoundsException ex) {
            System.err.println("Invalid input");
        }

        data_base.push(text.toString());   // push the current text to stack, for documentation purposes
        return this;
    }

    /**
     * Inserts the string into this character sequence.
     * The full explanation is at StringBuilder javaDoc, in "insert" method.
     *
     * @param offset the index in current sequence, that first character of the string will be inserted to.
     * @param str  the string to insert
     * @return this object
     */
    public UndoableStringBuilder insert(int offset, String str) {

        try {    // running the rest of program even if error will occur, with notification
            this.text = text.insert(offset, str);  // calling "insert" in StringBuilder class
        } catch (StringIndexOutOfBoundsException ex) {
            System.err.println("Offset is invalid");
        }

        data_base.push(text.toString());  // push the current text to stack, for documentation purposes
        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in the specified String.
     * The full explanation is at StringBuilder javaDoc, in "replace" method.
     *
     * @param start the beginning index for replacement
     * @param end   the ending index for replacement
     * @param str   String that will replace previous sequence
     * @return  this object
     */
    public UndoableStringBuilder replace(int start, int end, String str) {

        try {   // running the rest of program even if error will occur, with notification
            this.text = text.replace(start, end, str); // calling "replace" in StringBuilder class
        } catch (StringIndexOutOfBoundsException ex) {
            System.err.println("Invalid input");
        } catch (NullPointerException ex) {
            System.err.println("Invalid input");
        }

        data_base.push(text.toString());  // push the current text to stack, for documentation purposes
        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the sequence.
     * The full explanation is at StringBuilder javaDoc, in "reverse" method.
     */
    public UndoableStringBuilder reverse() {

        this.text = text.reverse(); // calling "reverse" in StringBuilder class
        data_base.push(text.toString()); // push the current text to stack, for documentation purposes
        return this;
    }

    public UndoableStringBuilder(String sb) {
        this.text = new StringBuilder(sb);
        this.data_base = new Stack<>();
    }

    /**
     * Cancel the last operation.
     * If no operations were made, it does nothing.
     *
     */
    public void undo() {
        // in order to cancel the last operation, the last object in stack is popped,
        // but it happens only if the stack isn't empty.

        if (!data_base.isEmpty()) data_base.pop(); // in order to cancel the last operation, we

        if (data_base.isEmpty()) {
            this.text = text.delete(0, text.length()); // the stack is empty,
            // and the string inside class object also becomes empty.
            return;
        }
        this.text = new StringBuilder((String) data_base.peek()); // the last phase in canceling last operation,
        // is to take the object that now existing at top of the stack.

    }

    public StringBuilder getText() {
        return text;
    }

    public void setText(StringBuilder text) {
        this.text = text;
    }

    public Stack getData_base() {
        return data_base;
    }

    public void setData_base(Stack data_base) {
        this.data_base = data_base;
    }

    @Override
    public String toString() {
        return text + "";
    }

    public static void main(String[] args) {
        UndoableStringBuilder usb = new UndoableStringBuilder();
        usb.append("to be or not to be") ;
        System.out.println(usb) ;
        usb.replace(6, 5, "eat") ;
        System.out.println(usb) ;
        usb.replace(17, 19, "eat") ;
        System.out.println(usb) ;
        usb.reverse();
        System.out.println(usb) ;
        System.out.println() ;
        usb.undo() ;
        System.out.println(usb) ;
        usb.undo();
        System.out.println(usb) ;
        usb.undo() ;
        System.out.println(usb) ;
    }
}

