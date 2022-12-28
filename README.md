##  Assignment 1 in OOP course:

<br>

This assignment is about performing a type of behavioral-design-pattern called "**Observer**".


Among this whole project, the files that we've been required to create is: **GroupAdmin, ConcreteMember, and test classes for each of those class**.

In the second phase of the assignment, at "Tests" file, we checked **memory space** of an object from those classes, and the space that all the program needs. 
Not only an object, but also its references was explored in our "Tests" file.

<br>

**GroupAdmin** is a class that implements "Sender" interface, that describe the **update's sender**, or in other words - **Observable**.

**ConcreteMember** is a class that implements "Member" interface, that describe the **update's receiver**, or in other words - **Observer**.
<br>
<br>

Practically, GroupAdmin object includes the state stock - **UndoableStringBuilder** - and a list of
costumers - **Members** - that will be updated when the state changes.

<br>
<br>

In order to find the files that we created ourselves, go to:

**GroupAdmin, ConcreteMember**:  branch master -> src -> main

**Tests, GroupAdminTest, ConcreteMemberTest**:  branch master -> src -> test
