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
Date format is {Day/Month}
```
UPDATE 2018.
SCHEDULE "Ed's Birthday Party" 01/02 13:00-14:00.
ADD "notes would be here".
UPDATE 2018.
```

How to add a reccuring event to the schedule:
Occurence can be one of {weekly/monthly/daily/annual} and start on specified day.
```
UPDATE 2017
SCHEDULE yearly "Daniela's Birthday Party" 03/12 00:00-24:00
SCHEDULE reoccurring MWF "Grocery Shopping" 01/01 12:00-12:30
SCHEDULE daily "Pet my Dog" 01/01 13:00-15:00
UPDATE 2017
```

Editing an existing event
```
UPDATE 2018
SCHEDULE reoccurring MWF "Grocery Shopping" 01/01 12:00-12:30
SCHEDULE daily "Pet my Dog" 01/01 13:00-15:00
EDIT "Grocery Shopping" 01/03 "buy milk and eggs"
EDIT "Pet my Dog" 01/01 16:00-17:00
UPDATE 2018
```