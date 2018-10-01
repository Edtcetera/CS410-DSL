## Task: 
You have a list of classes you are interested in taking, input them into your favorite scheduling app

***
List of classes you are interested in:

CPSC 110 
DMP 100
Lecture M-W-F 8AM - 9AM
Tutorial M-W-F 9AM - 10AM
Notes:

CPSC 121
DMP 200
Lecture Tu - Th 10AM - 11.30AM
Tutorial Tu - Th 11.30AM - 12.30AM
Notes:

PHY 100
OSBO 100
Lecture Tu - Th 12PM - 1.30PM
Tutorial Tu - Th 2PM - 3PM
Notes: 

***

## Code snippets:

How to add an all-day event to an annual schedule:
```
Edit my 2018 schedule.
Add an event called "Ed's Birthday Party" on May 24.
Add an event called "Clean up party" on May 25.
Update my 2018 schedule.
```

How to add a reccuring event to the schedule:
Occurence can be one of {weekly/monthly/daily/annual} and start on specified day.
```
Edit my 2017 schedule.
Add a daily event called "Plan Ed's party" starting on May 20.
Update my 2017 schedule.
```

Adding additional information to created event using 'This event':
'This event' refers to last event added.
'This event happens at' edits location.
'This event ends on' specifies end date for recurring events.
'This event occurs from' specifies time. If no time is specified, it assumes an all-day event.
```
Edit my 2016 schedule.
Add a daily event called "Plan Ed's party" starting on May 20.
This event happens at 7777 Imagine Street.
This event ends on June 3.

Add an event called "Book Ed's Birthday party room" on May 20.
This event occurs from 1PM to 2PM.
This event happens at 5555 Discovery Street.
Update my 2016 schedule.
```