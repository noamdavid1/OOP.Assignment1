# OOP.Assignment1

## Overview:
In this assingment we were asked to add an option to organize a group of memebers of updates
on the state of the string and send updates to it in real time.
In order to do this, we were asked to familiarize ourselves with the principles of observer's design patterns.
the process of building this data structure was to implement and program the interfaces that we got.
structrue:

|Class    |Description|
|:---------|:-------------|
|GroupAdmin | implements the Sender interface which describes the updates, groupAdmin class it contains the state pool of the UndoStringBuilder class and a pool customers who should receive updates on any changes made to the database.|
|ConcreteMember| The ConcreteMember class implements the Member interface which describes the receiver of the updates. The class contains the UndoStringBuilder repository copy (shallow copy).|
|Tests| A test class for each class in the program in order to increase the confidence in the code and check the execution of different cases.|

## GroupAdmin
+ *notifyRegisters:* This method notifies all registers of a change in the string using the update function on ConcreteMember class.
+ *getUsb:* This method return the UndoStringBuilder that relate to the GroupAdmin.
+ *register:* this method register observers.
+ *unregister:* This method unregister observers.
+ *insert:* This method inserts the string into this character sequence.
+ *append:* This method appends the specified string to this character sequence.
+ *delete:* This method removes the characters in a substring of this sequence.
+ *undo:* This method erases the last change done to the document, reverting it to an older state.

## ConcreteMember
+ *getUsb:* This method return the string that the member is connected to.
+ *getName:* This method return the string that represents the name of the member.
+ *update:* This method prints an update message to all the registers associated with this string.

### Class's diagrams: 
![צילום מסך_20221229_115604](https://user-images.githubusercontent.com/93923600/209935539-fcfecfaa-7978-43b2-8822-7803adeb5ff2.png)

### UML:
![diagram-4846508793507524739](https://user-images.githubusercontent.com/93923600/209975227-fe3813e5-f20e-444b-a7aa-1e37771d320c.png)


## Implementation of the program:
A groupadmin classifier object consists of a HashMap and an UndoStringBuilder, 
so that  the hashmap is the data structure we chose to use in order to register the observers, where its key is a specific member of the ConcreteMember type and its value string that identifies the name of the observer.
In the GroupAdmin class we implemented all the functions that we expanded on above.
The functions register and unregister register and delete a register of a particular observer to which an UndoStringBuilder is associated. 
The rest of the functions make changes to the string using UndoStringBuilder class we implements in assingment 1, 
and after each change there is a call to the notify function so that everyone linked to the hashmap will receive an update message.

An object from the ConcreteMember class holds a string that represents the observer's name and also holds an undostringbuilder that the observer is linked to and receives updates on.
We expanded on the functions getUsb and getName above.
The update function receives an UndoStringBuilder that has been changed and prints a message to all viewers about the string update.
The concrete member class contains a shallow copy of the UndoStringBuilder database that we implemented in assignment 1, so when we are satisfied with a shallow copy, we copy a pointer from object to object, which means that 2 objects will use the same pointer so that if one changes, the other also changes.
Therefore, in the event that we have a member who has not registered to the hashmap and is linked to the same string that another member who has registered is linked to, the changes will be updated on the UndoStringBuilder for both members, but only the member who have registered to the hashmap will receive an update on this.

### Collaborators
- *Noam David*
- *Yuval Bar-Maoz*
