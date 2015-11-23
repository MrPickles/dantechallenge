# Dante Inc. Challenge Problem!

Consider a two dimensional matrix of integers that represents terrain elevation. The greater the number in an index represents a higher elevation at that index. Rain water will fill the lowest points in the terrain. Water could even fill several connected indices if they are bounded as a group above, below, to the left and to the right. The challenge is to write a program that will compute the maximum amount of water the terrain can hold. 

You can use the programming language of your choice. We have created a starter project for you in java but you are not required to use it. Please visit https://github.com/jfjessup/dantechallenge to clone the project. 

### Consider the following details:

1. The integers in the matrix will be non-negative.
2. The integers do not need to be unique.
3. An index or region of the terrain can hold water if the values above, below, left and right of it are greater.
4. You'll be judged on efficiency and code style.
5. Comment your code.

### Examples:

* Example One

1  1  1

1  2  1

1  1  1

maximumWaterHeld = 0

_____


* Example Two

2  2  2  2

2  1  1  2

2  2  2  2

maximumWaterHeld = 2

_____


* Example Three

1  1  1  1  1  1

1  4  4  4  4  1

1  4  2  2  4  1

1  4  4  4  4  1

1  1  1  1  1  1

maximumWaterHeld = 4

_____


* Example Four

1  1  1  1  1  1

1  4  4  4  4  1

1  4  2  2  2  1  <- Note the difference compared to example 3. The center region is not bounded!

1  4  4  4  4  1

1  1  1  1  1  1

maximumWaterHeld = 0

### Good Luck!
