package Matalot.Matala1;

/**
 * A class that implements "Member" interface, that describe the
 * update's receiver, or in other words - Observer.
 *
 * @version 1.0 Dec 2022
 * @author Amit steinmetz & Michael Idan
 *
 */

public class ConcreteMember implements Member {

    /**
     * The class contains a copy of the state stock (UndoableStringBuilder).
     * In addition, every member that created gets an ID.
     */
         UndoableStringBuilder stringBuilder;
         static public int MemberIDCounter = 0;
         public int MemberID;

    /**
     * Constructor that gives unique ID to the new member.
     */
    public ConcreteMember() {
        this.MemberID = ++MemberIDCounter;
    }

    /**
     * Function that has to be implemented because its from "Member" interface.
     *
     * The updating is actually doing a shallow copy of the UndoableStringBuilder that given.
     * Moreover, we print a message about the new update.
     *
     * @param usb the new state
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.stringBuilder = usb;
        System.out.println("Member no." + MemberID + " State changed to: " + usb);
    }

    @Override
    public String toString() {
        return "Member no."+ MemberID + " : " + stringBuilder;
    }
}
