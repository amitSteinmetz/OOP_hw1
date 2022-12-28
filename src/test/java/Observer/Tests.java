package Observer;

import observer.ConcreteMember;
import observer.groupAdmin;
import org.junit.jupiter.api.Test;


public class Tests {

    groupAdmin g = new groupAdmin();
    ConcreteMember m1 = new ConcreteMember();
    ConcreteMember m2 = new ConcreteMember();
    ConcreteMember m3 = new ConcreteMember();


    @Test
    public void simple_size_test() {

        // Print size of some objects, only to see what will be the results.

        System.out.println(JvmUtilities.objectTotalSize(g));
        System.out.println(JvmUtilities.objectTotalSize(m1));
        System.out.println(JvmUtilities.objectTotalSize(m2));

        // We expect that: m1 size == m2 size.

    }
    @Test
    public void HowUnregisterEffectSize(){

        g.append("to be or not to be");

        g.register(m1);
        g.register(m2);
        g.register(m3);

        System.out.println(g.getMembers());
        System.out.println(JvmUtilities.objectTotalSize(g.getMembers()));

        g.unregister(m2);

        // We expect that after unregister m2, the size of Members list will be smaller.

        System.out.println(g.getMembers());
        System.out.println(JvmUtilities.objectTotalSize(g.getMembers()));

    }

    @Test
    public void Follow_footPrints_of_Members() {

        /*
            In this test, we wish to follow after the references
            of the objects, and expect to see any changes about them during this test.
         */

        System.out.println(JvmUtilities.objectFootprint(m1,m2,m3));

        g.register(m1);
        g.register(m2);
        g.register(m3);

        System.out.println(JvmUtilities.objectFootprint(m1,m2,m3));

        g.append("to be or not to be");

        System.out.println(JvmUtilities.objectFootprint(m1,m2,m3));
    }

    @Test
    public void Total_amount_of_memory_for_the_benefit_of_the_program() {
        System.out.println(JvmUtilities.jvmInfo());
    }

}
