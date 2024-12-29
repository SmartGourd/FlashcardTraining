# User Documentation for the Learning Application
- To see the programming documentation see the Main.java file for an overview and architecture or the comments and javadoc in code
- Welcome to the Learning Application! 
- This application will help you practice your flashcards and award you with gems to build your city
- Below is a guide to navigating and using the application

## Application data
- The application data is stored in the appData.json file, put it into the project root
- If no file is provided a blank application state is loaded
- Many pages allow you to exit the application which saves the current application state
- Unless you leave the application like this your new data WON'T BE SAVED!

## Pages and Features
### Main Menu Page
- The starting page of the application where you can navigate to other pages.
- You can either go to modify your sets or do some training
- Or you can go to game where you can continue to improve your city by spending gems

### Sets pages
- Here you can view names of created sets
- You can select the one you want to see in detail by a number next to it
- Create a new set with a specified name
- You can also return to main menu or exit the application

### Set Detail Page
- Used for editing the set or to launch training on it
- Allows you to view all terms and definitions in the set
- You can change the set name
- You can also delete the set be aware that this is irreversible and all terms and definitions in this set will be LOST!
- You can too add or remove term-definition pairs
- You can also start training or a game with the selected set

### Training Page
- Here you can train pairs of the selected set
- At first you select a learning algorithm (box or weight-based).
- The application presents terms, and you type the corresponding definitions.
- The session ends when you type stop123
- After the session ends statistics for this session are provided with the number of correct and incorrect answers and your accuracy
- By answering correctly you earn gems, by answering wrong you lose gems
- These gems can be used in the game page to improve your city
- Correct Answer: Earn 10 gems.
- Incorrect Answer: Lose 11 gems.

### Game Page
- Allows you to see how many gems you have
- Shows you how your city currently looks
- Your city is on a 10x10 map, you can change a field on this map
- The indexes of the fields are counted from 1
- So for example third row, fourth field would be on index 24 = 20 + 4
- If you have enough gems, you cna change what is on the field you specify
- It will cost you gems which will be removed from your gem count, if you don't have enough gems you will be notified
- To earn gems answer correctly questions in training
- Correct Answer: Earn 10 gems.
- Incorrect Answer: Lose 11 gems.

### Algorithms to pick pairs
#### Box System (Default):
- Each pair has a box number which starts on 1 and can never be lower than 1
- This number tells how often the pair should not be picked when picked randomly by the algorithm
- The higher the number, more often does the pair get skipped
- For each pair we also count how many times it was skipped to know later when to not skip it
- If you answer the pair correctly its box number increases by one
- If you answer incorrectly the box number decreases by one

#### Weight System:
- Each pair has a weight
- The weight divided by summed weight of all pairs represents the probability the item will get picked as next pair
- If you answer correctly the weight divides by two
- If you answer incorrectly the weight is increased by one

### FAQs
- Q: How can I end a training session?
- A: Type stop123 during training to end the session and view your statistics.
-
- Q: Can each user have its own appData.json and switch it when switching
- A: Yes! Each user will than have its own app state and state for the game and sets too. You can share the sets by copying them to the json file, to sets property
- 
- Q: What happens if I don't select a learning algorithm?
- A: The application will use the default Box System algorithm.
-
- Q: What happens if I have no term-definition pairs in a set and I start a training?
- A: The application will notify you that the set is empty and redirect you to the Set Detail Page.
-
- Q: What is the purpose of gems?
- A: Gems track your performance and act as rewards for correct answers during training or games.