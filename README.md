# Happy ~~Dungeon~~ Adventure!

Repository for the Dungeon Adventure project for TCSS 360 (Software Development And Quality Assurance Techniques).

Created by: Carmina Cruz, Anastasia Vilenius, and Hui Wagner.

**How to play:**

What you'll need:
- This game as an extracted .zip
- IntelliJ IDEA
- JavaFX Library
- Java SDK 20
- Jupiter (JUnit 5) Library
- SQLite Library

Directions:
1. Open IntelliJ IDEA
2. Import the game folder you extracted
3. File -> Project Structure -> Libraries -> + ("New Project Library") -> Java
4. Navigate inside the JavaFX folder and add the "lib" folder
5. Repeat step 3 and navigate to the SQLite .jar file inside the game folder
6. Apply -> OK
7. Back in IntelliJ, Run -> Edit Configurations -> Add New Configuration -> "Main.java"
8. Modify Options -> Add VM options 
9. Make sure the following is correct:
- VM Options: "--module-path "PATH\TO\JAVAFX\lib" --add-modules javafx.controls,javafx.fxml,javafx.media"
- Main class: "src.Main.Main"
- JRE: Java SDK 20
11. Apply -> OK
12. Run the application!