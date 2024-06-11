# <h1>PASSWORD MANAGER</h1>

#### <h2>Managing passwords just got easier </h2>

My term project is a robust password manager that is designed
to provide users with a secure and organized method to manage
their passwords for various online accounts. 
The application will allow users to **add**, **store**, **update**, 
**delete**, and securely **view** their passwords. 
Additionally, it will incorporate a feature to generate *strong*, 
*random* *passwords*, while indicating the strength of the current 
password set
by the user, to enhance account security.

It is intended for users who use 
multiple online services and require a secure, organized and user-friendly 
interface to manage their diverse passwords. I'm keenly interested 
to design the password manager because I want 
to tackle the critical issue of handling lots of online accounts 
and the risk that comes with using the same password for many of 
them. This tool will securely store and handle passwords, providing 
a smart solution to ease the struggle of remembering numerous passwords, therefore
enhancing user experience. In this way, it will also aid in 
instilling a sense of digital security and personal data protection 
among the users.

### USER STORIES:
- As a user, I want to be able to **add** my password to the list of passwords in 
my Password Manager.
- As a user, I want to be able to **update** an existing password in my Password Manager.
- As a user, I want to be able to **view** my passwords in my Password Manager.
- As a user, I want to be able to **generate** strong random passwords for my accounts to enhance
their security.
- As a user, I want to be able to **delete** my password from my Password Manager.
- As a user, I want to be able to **save** my passwords to file for future use.
- As a user, I want to be able to **load** my passwords from file.
- As a user, I want to be able to **view** the password for a particular website of my choice.

# Instructions for Grader

- You can access the main menu through the user login window by entering the username : "hdalal" and password "Hdalal@1918".
- You can generate the first required action related to the user story "adding multiple Xs to a Y" by clicking on the "Add Password" button. You will be prompted to enter the 3 entries for a password: **Website**, **Username** and a *valid* **Pass** (the password should be atleast 5 characters long, should have an uppercase letter, lowercase letter and a number).
- You can generate the second required action related to the user story "adding multiple Xs to a Y" by clicking on the button labelled "View All Passwords", which would display the list of all passwords in the password manager.
- You can locate my visual component by running the application at the "top of the main menu" (after User Login).
- You can save the state of my application by clicking the "**Save Passwords to file**" button.
- You can reload the state of my application by clicking the "**Load Passwords from file**" button.

## Phase 4: Task 2

- Fri Dec 01 15:49:17 PST 2023
- User logged in
- Fri Dec 01 15:49:22 PST 2023
- Loaded passwords
- Fri Dec 01 15:49:22 PST 2023
- Added password for Website: vwfef & Username: fefef
- Fri Dec 01 15:49:22 PST 2023
- Added password for Website: kjuhv & Username: jhfuyf
- Fri Dec 01 15:49:22 PST 2023
- Added password for Website: pwrt & Username: atry
- Fri Dec 01 15:49:22 PST 2023
- Added password for Website: aarush & Username: flash
- Fri Dec 01 15:49:24 PST 2023
- Viewed 4 Passwords in Password Manager
- Fri Dec 01 15:49:36 PST 2023
- Added password for Website: nike & Username: abcd
- Fri Dec 01 15:49:56 PST 2023
- Added password for Website: puma & Username: hd
- Fri Dec 01 15:50:03 PST 2023
- Deleted password for website: puma
- Fri Dec 01 15:50:15 PST 2023
- Viewed password for : pwrt
- Fri Dec 01 15:50:17 PST 2023
- Viewed 5 Passwords in Password Manager
- Fri Dec 01 15:50:21 PST 2023
- Saved passwords


## Phase 4: Task 3

- The lack of appropriate exception handling in the project could be a weakness that jeopardizes the code's dependability. Well-designed exception handling techniques will allow the program to elegantly manage unforeseen situations, reducing the risk of crashes and enhancing overall user experience. This will include locating potential exception-prone places in the code and adding try-catch blocks to handle them.

- Another thing I can do to enhance the code would be to implement the Singleton pattern for the Json Writer and Json Reader classes. Using this approach would guarantee that there's only one instance of these classes, which would help us prevent problems that arise from multiple instances altering the same data source.
