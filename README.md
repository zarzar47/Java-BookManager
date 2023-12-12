# BooksRUs - A prototype Book Management System

***A Project by @zarzar47, @Imag1neBaggins, and @al-Jurjani***

Hey, welcome to our repository. This repo is a project that we created from scratch for our Data Structures and Algorithms Course. We chose to make a BMS as we wanted to work with as many data structures as possible, in order to enhance our own knowledge and understanding of them. Plus, we all love books!

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Data Structres Implemented**

We used five Data Structures, each with their own uses:
- *Linked Lists* - Used for keeping a database, all the references are held in this list, makes insertion and deletion easy
- *Dynamic Array* - Used for storing the books as well as displaying the current book list based on the users search paramaters, as well as sorting that list according to the user’s need (by name, price, or popularity, either ascending or descending).
- *Binary Search Trees (BSTs)* -
- *Hash Maps* -

---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Storage of Books for Efficient Searching**

In order to most efficiently search for books, and manipulate them if needed (changing book stocks, removing books from the store, etc), we decided on storing the references of the books from
the linked list in a hybrid "2D-Array":
 - The "2D-Array" is in fact an array of DynamicArrays, of length 27 - index 0-26 for letters a-z, and an extra index for special characters and/or numbers.
 - For each DynamicArray, their index denotes a specific genre. The index of the genre is retrived through a hash map that was created for this purpose.
 - In each index position (although not all need to be used necessarly), there are BSTs. It is in these BSTs that the refernces of the books are actually being stored.

In essence, we are storing the book references firstly based on their String, then their genre. So, all books starting with the letter 'A' and of genre "Thriller" are stored in the first index of the array of Dynamic Arrays, in the first index of that Dyanmic Array, in the BST of that index. Likewise books starting with the letter 'B' and of genre "Thriller" are stored in the second index of the array of Dynamic Arrays, in the first index of that Dynamic Array, in the BST of that index. So, by storing the references of the books in this manner, we are generating, at most, 27 * m BSTs, with m being the number of total genres. 

Below is a diagram explaining this structure:

![image](https://github.com/zarzar47/Book-store/assets/74672461/6e21481d-d6f6-4025-b425-fefb830d818b)


When searching using the entire name of the book, selecting genre and ticking the “specific” indicator. We will very efficiently find the book in O(logN) time. But the actual time will be faster than this as we are dividing the number of books (N) by 27*m so it has the capability to be even faster given enough data (N/27*m).
When searching using the name of the book only and genre set to “All” we will have find the book by using its first letter and searching through the entire ArrayList for books with matching starting substrings. this will be O(N).
When searching with the book’s name blank and the genre specified we will search through each ArrayList at the specified location of the genre’s given by a HashTable of Genre to Integer.

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

The Bookstore class is *the* class the glues the entire project together. It links the GUI, the file reading, the sorting and searching of books, and the management of the users purchases together. Below are some of Bog-Os of some of the important methods of the BookStore class in the Warehouse Package:

![image](https://github.com/zarzar47/Book-store/assets/74672461/9a0b4e6d-9305-456c-a294-9dcddf21d5af)

----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

