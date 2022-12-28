package observer;

import java.util.ArrayList;


/**
 * A class that implements "Sender" interface, that describe the update's sender,
 * or in other words - Observable.
 *
 *  @version 1.0 Dec 2022
 *  @author Amit steinmetz & Michael Idan
 */
public class groupAdmin implements Sender {

    /**
     * The class contains the state stock (UndoableStringBuilder), and a list of
     * costumers (members) that will be updated when the state changes.
     */
    public UndoableStringBuilder stringBuilder;
    private ArrayList<Member> members;


    /**
     * Basic constructor - creating a new state stock, and a new
     * repository (represent by arraylist) of members.
     */
    public groupAdmin() {
        this.stringBuilder = new UndoableStringBuilder();
        this.members = new ArrayList<Member>();
    }

    /**
     * Register a new member to the repository by adding it to the ArrayList.
     *
     * @param obj the member to register
     */
    @Override
    public void register(Member obj) {

        /* We want that the new member.undoablestringbuilder will point at
           the groupAdmin undoablestringbuilder repository. To do so, we need to be able
           to reach to data member "stringBuilder" in obj. Therefor, we
           cast obj to ConcreteMember.
                */

        if (obj instanceof ConcreteMember) {
            ((ConcreteMember) obj).stringBuilder = this.stringBuilder;
        }

        // Using "add" method in order to add a new member to observers repository:
        this.members.add(obj);
    }

    /**
     * Unregister an existing member from members repository by removing it from the ArrayList.
     *
     * @param obj the member to unregister.
     */
    @Override
    public void unregister(Member obj) {

        // Using "remove" method in order to remove member from observers repository:
        this.members.remove(obj);
    }

    /**
     * Function that has to be implemented because its from "Sender" interface.
     *
     * Works almost the same as "insert" function in our undoablestringbuilder class,
     * with addition - after inserting, we notify all members (Observers) about the change in stock state.
     *
     * @param offset offset the index in current sequence, that first character of the string will be inserted to.
     * @param obj  the string to insert
     */
    @Override
    public void insert(int offset, String obj) {
        stringBuilder.insert(offset,obj);
        this.notifyObservers();
    }

    /**
     * Function that has to be implemented because its from "Sender" interface.
     *
     * Works almost the same as "append" function in our undoablestringbuilder class,
     * with addition - after appending, we notify all members (Observers) about the change in stock state.

     * @param obj the string to append
     */
    @Override
    public void append(String obj) {
        stringBuilder.append(obj);
        this.notifyObservers();
    }

    /**
     * Function that has to be implemented because its from "Sender" interface.
     *
     * Works almost the same as "delete" function in our undoablestringbuilder class,
     * with addition - after deleting, we notify all members (Observers) about the change in stock state.
     *
     * @param start the index of first character in the sequence to delete
     * @param end the index of last character in the sequence to delete
     */
    @Override
    public void delete(int start, int end) {
        stringBuilder.delete(start, end);
        this.notifyObservers();
    }

    /**
     *  Function that has to be implemented because its from "Sender" interface.
     *
     *  Works almost the same as "undo" function in our undoablestringbuilder class,
     *  with addition - after undo the last operation, we notify all members (Observers) about the change in stock state.
     */
    @Override
    public void undo() {
        stringBuilder.undo();
        this.notifyObservers();
    }

    /**
     * Notify all members (observers) in the repository that a change was made in stock state.
     */
    public void notifyObservers() {

        // In order to notify, we use "update" method that is taken from "Member" Interface
        for (Member m: members) {
            m.update(stringBuilder);
        }
        System.out.println();
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Current state: " + this.stringBuilder + "\n" + "members: " + members;
    }

    public String currentUndoableStringBuilder() {
        return this.stringBuilder + "";
    }

}
