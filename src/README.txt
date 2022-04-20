a) encode is O(n) since it will iteratively go through the entire list once to encode each set of values
   decode is O(n) since it will add the values to the list based off of the times the decodes list says;
   n being the size of the final list not the string.
   equals is O(n) because it iterates through both lists at once and compares value to value.

b) Advantages to RLE is certainly the fact that it is lossless, data can be put back into its original form once obtained
   Large list will not need to take up so much memory when being transfferred. Or if the list is stored as a string
   the encoded list will make it a lot easier to read especially if there are hundreds of input

   Disadvantage would be that when encoding it could become pretty slow especially as input size gets quite large.
   Encoding would have the implication that the final source the string ends up in has the capability to decode it,
   and if it does this means it will require another tedious operation to decode the string and place it into a list.

c) I spent and estimated time of 8 hours. The biggest challenge was implementing the decodign algorithm, but at some point
   it just clicked so that was nice. The other issues I faced was printing the output as specified by the method description
   Originally my driver printed everything and my RLE returned strings but this was not what we were supposed to do. So,
   I had to pass the RLE a PrintStream object to its constructor and luckily that seemed to work well.