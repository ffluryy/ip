# Megatron 2 User Guide

Megatron 2 is a command-line task management chatbot. It allows users to manage tasks by adding, listing, marking, deleting, and searching for tasks. The bot accepts text commands and responds with structured feedback.

The system supports three task types:
- ToDo – a basic task with no time constraint
- Deadline – a task that must be completed before a specific time
- Event – a task that occurs within a defined time range

The bot maintains a task list during runtime and allows users to manipulate it using simple commands. The bot saves the task list to disk on exit using the "bye" command.

--------------------------------------------------

## Quick Start

1. Download the `ip.jar` file.
2. Move the `.jar` file into an empty directory.
3. Open a terminal in that directory.
4. Run the program with:
```
java -jar ip.jar
```
When the program runs for the first time, it will automatically create a new folder called `data`.

Inside this folder, a file named `tasks.txt` will be created. This file stores the task list used by the chatbot.

--------------------------------------------------

## Greeting the bot

Displays a greeting message.

Command:
```
hi
```
Expected behavior:
```
Hello! I'm Megatron2.
What can I do for you?
```
--------------------------------------------------

## Listing tasks

Displays all tasks currently stored.

Command:
```
list
```
Example output:
```
1. [T][ ] Read book
2. [D][ ] Submit report (by: Friday)
3. [E][ ] Team meeting (from: 3pm to: 5pm)
```
If no tasks exist:
```
Your task list is empty
```

--------------------------------------------------

## Adding a ToDo task

Adds a simple task without time constraints.

Command:
```
todo <task description>
```
Example:
```todo Buy groceries```

Expected behavior:

```
ToDo added: [T][ ] Buy groceries
Now you have 1 tasks
```
--------------------------------------------------

## Adding a Deadline task

Adds a task with a deadline.

Command:
```
deadline <task description> / <deadline>
```

Example:
```deadline Submit assignment / Friday```

Expected behavior:
```
Deadline added: [D][ ] Submit assignment (by: Friday)
Now you have 2 tasks
```

--------------------------------------------------

## Adding an Event

Adds a task that occurs within a time period.

Command:
```
event <task description> / <start time> / <end time>
```
Example:
```event Project meeting / Monday 2pm / Monday 4pm```

Expected behavior:
```
Event added: [E][ ] Project meeting (from: Monday 2pm to: Monday 4pm)
Now you have 3 tasks
```
--------------------------------------------------

## Marking a task as done

Marks a task as completed.

Command:
```    
mark <task number>
```
Example:
```mark 2```

Expected behavior:
```
Marked "Submit assignment" as done
```
--------------------------------------------------

## Unmarking a task

Marks a task as not completed.

Command:
```
unmark <task number>
```
Example:
```unmark 2```

Expected behavior:
```
Marked "Submit assignment" as not done
```
--------------------------------------------------

## Deleting a task

Removes a task from the list.

Command:
```
delete <task number>
```
Example:
```delete 1```

Expected behavior:
```
Task 1 removed
```

--------------------------------------------------

## Finding tasks

Searches for tasks containing one or more keywords.

Command:
```
find <keyword(s)>
```
Multiple keywords may be provided. Keywords are case-insensitive.

Example:
```Find mEEting pRoJeCt```

Expected behavior:
```
Tasks found:
[E][ ] Project meeting (from: Monday 2pm to: Monday 4pm)
```

--------------------------------------------------

## Echo command

Returns the text provided by the user.

Command:
```
echo <text>
```
Example:
```echo hello world```

Expected behavior:
```
hello world
```
--------------------------------------------------

## Help / Manual

Displays all available commands.

Command:
```
help
```
Example output:
```
List of commands: greet, bye, echo, list, mark, unmark, todo, deadline, event, delete, help, find
```
--------------------------------------------------

## Exiting the program

Ends the chatbot session.

Command:
```
bye
```
The tasklist is saved to disk when the bot is given this command.

Expected behavior:
```
Bye. Hope to see you again soon!
```
--------------------------------------------------

## Error handling

The chatbot handles common input errors.

Invalid format example:
```
Proper format is: "mark <int>"
```
```
Out-of-range task example:
You only have X items
```
```
Invalid task number example:
"abc" is not an int
```
These messages help guide the user toward the correct command format.
