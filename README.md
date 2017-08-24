# PicoPlaca
This respository provides a Java implementation for a PicoPlacaPredictor. 
Pico y Placa here in Quito has the following schedules:

**Monday:** Licence plate numbers which finish with number 1-2   
**Tuesday:** Licence plate numbers which finish with number 3-4    
**Wednesday:** Licence plate numbers which finish with number 5-6    
**Thrusday:** Licence plate numbers which finish with number 7-8    
**Friday:** Licence plate numbers which finish with number 9-0

On all these days the restriction schedule is from 07:00 to 09:30 in the morning and in the afternoon the schedule is from 16:00 to 19:30 
To use the Java program you have to execute the Main class. You can use IntelliJ IDEA or just command line. The program can be run alone or you can send as arguments the licence plate number, date ant time to use in the prediction. 

The needed format for the program is the following: 
Licence plate numbers: Match the following regular expression. **Three letters following of three or four digits.**

```
[A-Za-z]{3}\d{3,4}
```

**Date:** The date format is dd/MM/yyyy
**Time:** The time format is HH/mm

```
   javac Main.java PCR1232 24/08/2017 13:42   
```
or just
```
   javac Main.java
```

There is a javadoc documentation and you can find the explanation to every single function and attribute of the classes. There are also JUnit Test, inside the package com.jorgeoandres.picoplacatest.
