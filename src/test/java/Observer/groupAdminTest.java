package Observer;

import observer.ConcreteMember;
import observer.groupAdmin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class groupAdminTest {

    groupAdmin g = new groupAdmin();

    @Test
    public void register() {

        ConcreteMember m1 = new ConcreteMember();
        g.register(m1);

        // If the code works, member m1 should be in the ArrayList that contains all members.
        assertTrue(g.getMembers().contains(m1));
    }

    @Test
    public void unregister() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        g.register(m1);
        g.register(m2);

        g.unregister(m1);

        // If the code works, member m1 should *not* be in the ArrayList that contains all members.
        assertFalse(g.getMembers().contains(m1));
    }

    /*
     In the following methods ("insert" till "undo") the main idea is common:
     we activate the method, and see the result.
     The result should the same as result if the method was activated from UndoableStringBuilder class.
    */
    @Test
    public void insert() {
        ConcreteMember m1 = new ConcreteMember();
        g.append("To be ");

        g.insert(5, " or not to be");

        assertEquals("To be or not to be ", g.currentUndoableStringBuilder());

    }

    @Test
    public void append() {
        ConcreteMember m1 = new ConcreteMember();
        g.append("To be ");

        assertEquals("To be ", g.currentUndoableStringBuilder());

    }

    @Test
    public void delete() {
        ConcreteMember m1 = new ConcreteMember();
        g.append("To be or not to be bla bla");

        g.delete(19,26);

        assertEquals("To be or not to be ", g.currentUndoableStringBuilder());
    }

    @Test
    public void undo() {
        ConcreteMember m1 = new ConcreteMember();
        g.append("To be or not to ");
        g.insert(15, " be");

        g.undo();

        assertEquals("To be or not to ", g.currentUndoableStringBuilder());
    }

    @Test
    public void notifyObservers() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();

        g.register(m1);
        g.register(m2);
        g.register(m3);

        g.append("To be or not to be");

        // notifyObservers() is implemented when append() (or similar functions) called.

        assertEquals("To be or not to be", m1.stringBuilder.toString());
        assertEquals("To be or not to be", m2.stringBuilder.toString());
        assertEquals("To be or not to be", m3.stringBuilder.toString());
    }

    @Test
    public void notifyAboutNothing() {
        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();
        ConcreteMember m3 = new ConcreteMember();

        g.register(m1);
        g.register(m2);
        g.register(m3);

        // When we notify about nothing, members stringBuilder field should be empty

        assertEquals("", m1.stringBuilder.toString());
        assertEquals("", m2.stringBuilder.toString());
        assertEquals("", m3.stringBuilder.toString());
    }

}