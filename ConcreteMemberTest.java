package Matalot.Matala1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConcreteMemberTest {

    groupAdmin g = new groupAdmin();

    @Test
    void update() {

        ConcreteMember m1 = new ConcreteMember();
        g.register(m1);
        g.stringBuilder.append("To be or not to be");

        // In "append" func there is a calling to "update" func (by notify func), so let's see if it works:
        assertEquals("To be or not to be", m1.stringBuilder.toString());

        /*
         In all UndoableStringBuilder methods we wrote the same kind of code,
         so if it worked here, it will work in all of them.
        */
    }
    @Test
    void correctID() {

        ConcreteMember m1 = new ConcreteMember();
        ConcreteMember m2 = new ConcreteMember();

        // If the code works, m2 ID should be equal to 2.
        assertEquals(2, m2.MemberID);
    }
    @Test
    void stateWhenMemberCreated() {

        g.stringBuilder = new UndoableStringBuilder("It's important to check code!");
        ConcreteMember m1 = new ConcreteMember();
        g.register(m1);

        // There was no use in update method, only member creating. Let's see the result:
        assertEquals("It's important to check code!", m1.stringBuilder.toString());

    }


}