Nama: Jessica Tandra  
NPM: 2406355445  
Kelas: Adpro B  

## Reflection 1
In this tutorial, I applied several clean code principles and secure coding practices, including:
1. **Meaningful names**  
I used descriptive and self-explanatory names for variables, methods, and classes that clearly indicate their purpose without needing extra comments.
2. **Avoiding duplication (DRY)**  
I have avoided code duplication by reusing components that I have previously made.
3. **Single Responsibility Principle**  
I separated the application logic into three distinct layers: Controller, Service, and Repository. Each class has only one responsibility, making the code modular and easier to test.
4. **Functions do one thing**  
I ensured that methods are small and focused. For instance, the `create` method only adds a product to the list. 
5. **UUID for identifiers**  
I implemented UUID for identifiers like product IDs. This prevents ID enumeration attacks, making it harder for attackers to guess other product IDs.

I also identified a few areas that need improvement to meet better standards:
1. **Lack of input validation**  
Currently, user can enter an empty name or a negative number for quantity. I should implement validation annotations in the Product model.
2. **Improper error handling**  
In `ProductController`, if `service.findById(productId)` returns `null`, I currently just redirect back to the list. It would be better to show an error message to inform the user.

## Reflection 2
1. After writing unit tests, I feel more confident about my code because they help me understand how it behaves in different situations, including edge cases and possible errors. There is no fixed number of tests required for a class, it depends on how complex the class is and how many functions it has. Ideally, each method should have at least one unit test to cover different input cases and edge cases. To check whether the tests are sufficient, we can use code coverage tools to see which parts of the code are executed during testing and find areas that are not tested yet. However, even if we reach 100% code coverage, it does not mean the code is free from bugs, because coverage only shows that the code runs, not that it works correctly as we wanted or not.

2. When creating a new functional test suite, simply copying variables and setup steps from previous tests is not a clean approach. It is not aligned with the do not repeat yourself principle. It causes duplicated code, which makes the program harder to maintain and update later. For example, repeating the same base URL setup in many places can lead to inconsistency if changes are needed. A better solution is to move shared setup code into a base functional test class or helper method so it can be reused. This keeps the test code simpler, more organized, and easier to manage.