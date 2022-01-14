<h3>How to run:</h3>
Open the ActionExecutorApplication, and run it as Java Application.
<br/>

<h3>About Project:</h3>
In each scheduler run cycle application should load data from the file, parse it and determine should the action be done at the current time in Lagos, Nigeria.


<h3>Bitmask format:</h3>
It represents decimal value of 7 digit binary value which each digit represent the days of week
from Monday to Sunday orderly. First digit represents Monday and last one 7th Sunday,
Digit 0 means NOT CONFIGURED, 1 means CONFIGURED for that day. 
<a href="https://www.rapidtables.com/convert/number/binary-to-decimal.html">Converter</a> might be used in order to generate bitmask.

<br/>
Examples:
   <ul>
   <li>bitmask = 3 is equal to 0000011 in binary format. Saturday and Sunday</li>
   <li>bitmask = 71 is equal to 1000111 in binary format. Monday, Friday, Saturday, Sunday</li>
   <li>bitmask = 64 is equal to 1000000 in binary format. Monday</li>
   <li>bitmask = 127 is equal to 1111111 in binary format. Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday</li>
   </ul>
   
