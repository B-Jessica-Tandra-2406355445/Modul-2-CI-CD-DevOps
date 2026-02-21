Nama: Jessica Tandra  
NPM: 2406355445  
Kelas: Adpro B  

# Reflection 2: CI/CD and DevOps
> List the code quality issue(s) that you fixed during the exercise and explain your strategy on fixing them.

1. **Avoid Field Dependency Injection**: I refactored `ProductController` and `ProductServiceImpl` to use constructor-based injection instead of using `@Autowired` directly on fields. This ensures objects are instantiated in a valid state and makes unit testing much easier.
2. **Extract Duplicated String Literals**: I replaced multiple instances of the "redirect:/product/list" string with a single constant variable. This reduces the risk of typos and makes the code easier to maintain.
3. **Remove Redundant HTML Attributes**: I removed the `role="button"` attribute from standard anchor (<a>) tags. Since standard HTML elements already have inherent semantics, removing this prevents screen reader confusion and improves accessibility.
4. **Delete Empty Setup Methods**: I completely removed an empty `setUp()` method in my test class. This eliminates dead code and keeps the test class clean and focused.
5. **Group Gradle Dependencies**: I reorganized `build.gradle.kts` to group dependencies logically by their destination. This makes the build configuration file much easier to read and manage.
6. **Remove Unnecessary Exceptions**: I removed `throws Exception` declarations from test methods that didn't actually throw any checked exceptions. This cleans up the method signatures and improves code readability.

> Look at your CI/CD workflows (GitHub)/pipelines (GitLab). Do you think the current implementation has met the definition of Continuous Integration and Continuous Deployment? Explain the reasons (minimum 3 sentences)!

Yes, I believe my current implementation has successfully met the definition of both Continuous Integration (CI) and Continuous Deployment (CD). For the CI part, whenever I push new changes to the repository, the GitHub Action workflows (ci.yml and scorecard.yml) automatically execute. These workflows reliably build the project, run the test, and perform code quality and security analysis using SonarCloud and OSSF Scorecard. This automated process ensures that any new code is continuously verified and free from major bugs or code smells. As for the CD part, the deployment process is fully automated using a pull-based approach. Once the CI workflows passes, Koyeb automatically detects the update and deploys the latest master branch of the application without requiring any manual intervention. All of these reasons show that my project has successfully implemented a complete CI/CD pipeline.  


<details>
# Reflection 1: Coding Standards and Secure Coding
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
3. </details>

<details>
# Reflection 2: Unit Test and Functional Test
1. After writing unit tests, I feel more confident about my code because they help me understand how it behaves in different situations, including edge cases and possible errors. There is no fixed number of tests required for a class, it depends on how complex the class is and how many functions it has. Ideally, each method should have at least one unit test to cover different input cases and edge cases. To check whether the tests are sufficient, we can use code coverage tools to see which parts of the code are executed during testing and find areas that are not tested yet. However, even if we reach 100% code coverage, it does not mean the code is free from bugs, because coverage only shows that the code runs, not that it works correctly as we wanted or not.

2. When creating a new functional test suite, simply copying variables and setup steps from previous tests is not a clean approach. It is not aligned with the do not repeat yourself principle. It causes duplicated code, which makes the program harder to maintain and update later. For example, repeating the same base URL setup in many places can lead to inconsistency if changes are needed. A better solution is to move shared setup code into a base functional test class or helper method so it can be reused. This keeps the test code simpler, more organized, and easier to manage.
</details>