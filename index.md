<center>
  <kbd><img src="profilepic.jpg" alt="Profile Picture" height=200 width=169></kbd>
</center>

# Computer Science Capstone

## CS 499 | SNHU

### About Me
**My name is Joseph Caron. I am currently a student at Southern New Hampshire University about to earn my Bachelor of Science in Computer Science with a concentration in Software Engineering. My goal is to begin a career as a software developer.**

### Code Review
**I created a code review video in order to get a comprehensive look at one of my earlier projects from my college career. I went over the entirety of the code, explaining it’s intended use and faults, as well as going over my planned enhancements.**

**<code>You can watch the video <a href="https://youtu.be/VLXJZ9kP_cU">here</a>.</code>**

**The entirety of this project, three rounds of enhancements on a artifact from a previous course at SNHU, was completed on a single artifact from my CS 410 Reverse Software Engineering course. It was originally designed as a customer tracking program for an investment company, accessed through a text only terminal window, and was very minimal.**

#### Project 1: Software Design and Engineering

**Translating the artifact from C++ to Java showcases my proficiency with multiple programming languages. The original code had some methods to validate integers and strings, methods for logging in, and for updating certain components of clients in the list. This gave me a chance to use object oriented programming principles to make the code more modular by creating new classes. I also had the opportunity to make a much more user friendly interface with text entry fields, dropdown menus and clickable buttons. The security in the original code was worse than poor. Users had to enter a username and password to “log in” to the system, but the username was never checked against anything to make sure it was a good username, and the only password that allowed access was “123”. This gave me a chance to showcase my skill in secure coding by making a more robust log in validation. I’m very pleased to say that I have managed to do all of the above.**

**<code>A full narrative of this round of enhancements can be read <a href="https://github.com/joey-caron/my-eportfolio/blob/Software-Design-and-Engineering/Milestone2-narrative.md">here</a>.</code>**

<center>
  <a href="https://github.com/joey-caron/my-eportfolio/tree/Software-Design-and-Engineering" title="View the first enhancement report">
    <kbd><img src="Add Customer.png" alt="Add Customer screen after enhancements" height=350></kbd>
  </a>
</center>
  
**<code>See the first enhancement report and code <a href="https://github.com/joey-caron/my-eportfolio/tree/Software-Design-and-Engineering">here</a>.</code>**

#### Project 2: Data Structures and Algorithms

**This is the second round of enhancements made to the project_1 artifact. This original code had a “list” of customers included in it, but it wasn’t truly a means of storage. Everything except their enrollment status was just an output string that got printed to the screen when prompted. There was no storage of information apart from five variables, one for each “customer”, that each held either a 1 or a 2. There was no ability to actually track customers, nor to add to the list by anyone except someone with access to the source code. There was also no way to search for a specific customer. I was able to showcase my skills and abilities with data structures and algorithms by including a full compliment of CRUD methods, which allow for client base growth, updating client information, removing clients from the database and searching through the entirety of the database using almost any field in the customer table. While a bit minor, the class that handles all of the user interface components also makes use of array lists to handle dropdown menus.**

**<code>A full narrative of this round of enhancements can be read <a href="https://github.com/joey-caron/my-eportfolio/blob/Data-Structures-and-Algorithms/Milestone3-narrative.md">here</a>.</code>**

<center>
  <a href="https://github.com/joey-caron/my-eportfolio/tree/Data-Structures-and-Algorithms" title="View the second enhancement report">
    <kbd><img src="CRUD.png" alt="CRUD Class code" height=350></kbd>
  </a>
</center>

<center>
  <a href="https://github.com/joey-caron/my-eportfolio/tree/Data-Structures-and-Algorithms" title="View the second enhancement report">
    <kbd><img src="search1.png" alt="Search Algorithm code" height=350></kbd>
  </a>
</center>

**<code>See the second enhancement report and code <a href="https://github.com/joey-caron/my-eportfolio/tree/Data-Structures-and-Algorithms">here</a>.</code>**

#### Project 3: Databases

**This round of enhancements was for category three, Databases. So, I created an SQLite database. I chose SQLite because this application doesn’t need a web-based database; just an embedded database is enough. I also chose SQLite over MongoDB, because I wanted the structure of a relational database. However, in terms of company growth, if the SNHU Investments company became larger, it would be wise to switch to a non-embedded database. I would most likely switch to a database made with MySQL, as it is still a relational database and would be easily transferred over, but it would be better for a larger, networked system.**
	
**The database I created in SQLite holds two tables: The first is a table of users, which holds the unique username, which is the primary key for the table, hashed password for security, employee name and job title. The other table is a customer table. It holds the unique Customer ID, which is the primary key, first name, last name, address, phone number, email and account status (brokerage or retirement). For security, I made sure to implement prepared statements to avoid SQL injection, and I made sure that after navigating away from any panel in the UI, all text entry fields would be wiped empty.**

**<code>A full narrative of this round of enhancements can be read <a href="https://github.com/joey-caron/my-eportfolio/blob/Databases/Milestone4-narrative.md">here</a>.</code>**

<center>
  <a href="https://github.com/joey_caron/my-eportfolio/tree/Databases" title="View the third enhancement report">
    <kbd><img src="Customers.png" alt="Customers Table in DBeaver" height=350></kbd>
  </a>
</center>

**<code>See the third enhancement report and code <a href="https://github.com/joey_caron/my-eportfolio/tree/Databases">here</a>.</code>**
