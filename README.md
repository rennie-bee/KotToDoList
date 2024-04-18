# KotToDoList
A simple todo-list app developed with Kotlin.

## Functionalities
- Task List Browsing
- New Task Adding
- Existing Task Editing
- Existing Task Removing

## Time Used
Cost 2 hours to accomplish the UI/UX Design, and 15 hours to implement the whole application.

## Challenging Parts
In this project, I encountered two challenging technical points. Firstly, managing the communication between different components of the app, such as activities, fragments, adapters, and view models, required careful attention to ensure smooth data flow and interaction. Implementing interfaces like `TaskItemClickListener` to handle clicks on task items and coordinating between the `MainActivity`, `NewTaskSheet`, and `TaskItemAdapter` demanded a clear understanding of their roles in the app's architecture. Ensuring proper implementation of these interfaces was crucial to maintain the separation of concerns and ensure the app's functionality.

Secondly, implementing the edit task feature posed a challenge as I had to distinguish between editing existing tasks and adding new ones within the `NewTaskSheet` dialog. Handling this distinction required thoughtful logic within the dialog to determine whether to update an existing task's information or create a new task altogether. It involved accessing and modifying the data passed to the dialog while ensuring that the appropriate action was taken based on the context. Maintaining clarity and consistency in the edit feature's behavior while ensuring data integrity was essential for providing a seamless user experience.

## Quick Showcase
Video Link: https://drive.google.com/file/d/1m0v8TBeGtwaUy30D_eGSDA1qsvn92WOq/view?usp=sharing
