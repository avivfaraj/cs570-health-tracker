# cs570-health-tracker

This is an Object Oriented Programming project that I developed as part of CS570 Programming Foundations course at Drexel University.
This program helps the user keep track of calories consumed everyday by storing information such as food consumed and workouts. 
In order to make it easier on the user, the program is also connected to [FoodData Central database (USDA)](https://fdc.nal.usda.gov/index.html) by using their API.
This way, the user does not need to enter the nutritions of each product. Instead, there is an option of searching for a specific product and this will send a query to the API.

# Setup
1. First an API KEY is required. Go to [FoodData Central database (USDA)](https://fdc.nal.usda.gov/index.html) and create a new key
2. On terminal run the following:
    ```
    echo 'export food_api={your API key}' >>~/.bash_profile
    source ~/.bash_profile
    ```
    ** NOTE: Replace {your API key} with your key. for example: food_api="6ofddpdsf"
3. Compile all files by running the following command on terminal (make sure you are in the project's directory):
    ```
    javac *.java
    ```
4. You are all set. Run HealthTracker.java: 
    ```
    java HealthTracker
    ```

# Running Instructions
At first, it will ask for your id (press any number to create a new user). Then a menu will be shown. Follow the menu to add food, workouts etc.
