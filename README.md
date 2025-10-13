# <pre align="center">Databases</pre>
This is in the SNHU Enhancement 3 Folder.
This artifact was part of a project for my CS 410: Reverse Software Engineering course. The prompt behind it was a simple client tracking program, and it was mainly to be used just to translate into assembly language, then into binary, and then back to C++. It is a very simple, single class program with a command prompt style text entry interface. I selected this artifact because while the original program was very simple, the idea behind it is a good one. It was also very barebones, which made it a great candidate for enhancement, as it could use a lot of enhancing.

The enhancements completed on this round were creating a SQLite database using the program DBeaver to hold the information on all users and customers, as well as integrating the database to the rest of the java program. Now users can register as a new user, login properly, and make use of all of the CRUD functionality incorporated in the previous round of enhancements. I also made some slight changes to security by adding improved exception logging, and also requiring a user registering to confirm their password by entering it twice, to ensure that they typed it correctly. I also changed the UI to have a full console output field in the bottom half of the program window.

The following course outcomes have been met:
"Employ strategies for building collaborative environments that enable diverse audiences to support organizational decision-making in the field of computer science" was met by creating an embedded SQLite database coupled with the java UI that allows users with any level of computer literacy to collaborate to add, edit or delete customers. The program's built in CRUD functions allow any registered user to make alterations to the customer database.

"Design and evaluate computing solutions that solve a given problem using algorithmic principles and computer science practices and standards appropriate to its solution while managing the trade-offs involved in design choices" was met by using algorithmic principles and computer science practices and standards to create a relational database to solve the problem of customer base growth. The previous text-based program allowed no customer growth at all. I also managed the trade-off of using a relational database rather than a non-relational database such as MongoDB.

"Demonstrate an ability to use well-founded and innovative techniques, skills, and tools in computing practices for the purpose of implementing computer solutions that deliver value and accomplish industry-specific goals" was met by using well-founded and innovating techniques, skills and tools in computer practices to implement a relational database to hold all user and customer information, adding value to the program by allowing for improved application security and customer base growth.

### <pre align="center">Future updates</pre>

In the future, I may choose to continue to make updates to this program for both my own satisfaction as well as because a better portfolio is always helpful for a software engineer. At this time, nothing is planned but that could easily change.

### <pre align="center">Original Version</pre>

<img src="Original.png">

### <pre align="center">Enhanced Screens after Databases enhancements</pre>

<img src="Customers.png">

<img src="Users.png">

<img src="Register2.png">

<img src="Search2.png">
