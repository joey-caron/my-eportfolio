# <pre align="center">Software Design and Engineering Enhancements</pre>
This is in the SNHU Enhancement 1 Folder.
This artifact was part of a project for my CS 410: Reverse Software Engineering course. The prompt behind it was a simple client tracking program, and it was mainly to be used just to translate into assembly language, then into binary, and then back to C++. It is a very simple, single class program with a command prompt style text entry interface. I selected this artifact because while the original program was very simple, the idea behind it is a good one. It was also very barebones, which made it a great candidate for enhancement, as it could use a lot of enhancing.

The enhancements done here include re-writing the entire program in Java for memory management security and platform portability. I made use of object oriented programming principles to create multiple classes for the users, customers, input validation, login functions, and the display. I also created a whole new user interface from scratch, as the original program was completely text based in the console rather than having a dedicated UI. I didn't have much experience with front-end design, so this was a challenge. That being said, I am quite proud of how it came out. 

The following course outcomes have been met:
“Design, develop, and deliver professional-quality oral, written, and visual communications that are coherent, technically sound, and appropriately adapted to specific audiences and contexts” was met by designing a new user interface from scratch and including helpful code comments. I also achieved this outcome with this narrative. “Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution while managing the trade-offs involved in design choices” was met by porting the code from C++ to Java, making it platform independent and making memory management safer while also improving modularity and functionality by creating new classes using object oriented programming principles. I had to take trade-offs into account for this as well. While Java is more portable as it is platform independent and has increased safety in terms of memory management, it also takes a hit in performance as it is not pre-compiled as it is in C++. I decided this was an acceptable trade-off, as it is a smaller scale software solution with limited functionality. I may have made a different decision if this was a veery large and complex program.

“Demonstrate an ability to use well-founded and innovative techniques, skills, and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry-specific goals” was met by using my skills and techniques to add value to the program by making it more accessible to everyone by porting it to a platform independent language. The addition of extra security, modularity and functionality also added value and accomplished industry-specific goals. “Develop a security mindset that anticipates adversarial exploits in software architecture and designs to expose potential vulnerabilities, mitigate design flaws, and ensure privacy and enhanced security of data and resources” was met by mitigating memory vulnerabilities by porting to Java from C++, the addition of password hashing to encrypt passwords, and a more robust password validation method.

### <pre align="center">Future updates</pre>

In the future, I will be performing enhancements for both categories 2 and 3, Data structures and algorithms, and Databases, respectively. These will include implementing full CRUD functionality and a robust search function that allows searching for customers via any of their attributes such as ID, first name or address. The Database updates will also include the creation of an embedded SQLite database to hold tables of users and customers. I will also be continuing to make changes to the UI design and tweaks to the existing functionality to allow seamless integration of the new functionality.

### <pre align="center">Original Version</pre>

<img src="Original.png">

### <pre align="center">Enhanced Screens</pre>

<img src="Start.png">

<img src="Register.png">

<img src="Login.png">

<img src="Main.png">

<img src="Add Customer.png">

<img src="Search.png">

<img src="Edit.png">

<img src="Delete.png">
