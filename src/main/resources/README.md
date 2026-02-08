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