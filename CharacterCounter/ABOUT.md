Declaring the use of public main generically. Using the banner class I had created, and passing the name of the assignment

'''
    public static void main(String[] args)
        ...
'''

There is one scanner declared for handling user interaction. When you call new on and Object (e.g. Scanner)
you are instantiating that object in memory, akin to re-hydrating it. Every object reserves a portion of memory
space. Short lived objects can put unnecessary pressure on the garbage collection which handles the clean up on
memory for variables.

When you create an object like Scanner, it will live the life of the block it is inside of and be destroyed at the 
end of the block. This relies on the garbage collection mechanism to release this memory and de-allocate the assigned
resources. 

If this is happening every time a function is called, that means we are constantly reserving resources in the computer
and then assigning the garbage collector to clean up those de-allocated resources repeatedly.

'''
    Scanner file_scanner = null;
    // try / catch / finally for opening and reading file
    try {
        file_scanner = new Scanner(file);
    } catch(Exception exception) {
        _handle_err(String.format("File %s not found as %s", input, dir+input), _current_);
        System.out.println(exception.getLocalizedMessage());
    } finally {
        System.out.println(String.format("Completed %s", ((file_scanner != null) ? input+"\n" : "")));
    } 
'''

In the above code, we declare the file_scanner as null object, so in the case that it is unable to read the file
no Scanner is instantiated. This allows us a low-cost way of knowing if the file was good. 
If the file was readable, the try passed -> file_scanner is NOT null 
if the file was NOT readable, then the try failed -> file_scanner does not exist (is still null, or empty)

The try is wrapped around the primary function that throws the error, allowing us to immediately identify the problem
and stop all work in the case of failure. This allows us to better use other compute resources in the case of failure.

The catch block handles the error, using a custom error handler (one style for private methods is to start them with an
underscore, hence _handle_err is easily identifiable as a private method without having to refer to the actual method.)

'''
    if (file_scanner != null) {
        int character_count = 0;
        int word_count = 0;
        int line_count = 0;

        while (file_scanner.hasNextLine()) {
            String line = file_scanner.nextLine();
            character_count = countCharacters(line, character_count);
            word_count = countWords(line, word_count);
            line_count = countLines(line, line_count);
        }
    }
'''
In the above block, we check if file_Scanner is NOT null; as described above, the only condition that file_scanner should be 
NOT null is when it succeeds in reading the file.

a scanner can only do one pass through a file before it needs to re-instantiate to start over. To solve this, we must operate 
the login on each line during its pass in the while loop.
First we loop WHILE the file_scanner has a Next Line (.hasNextLine())
then we read that line of text into the variable line
finally we perform our logic on each line

In this logic, we only have to pass through all of the file once
IF by contrast we called the file_scanner inside each method, we then reserve memory space for three objects, garbage collect
on three objects, lock and unlock the file three times, and read all the data from the file each time. 

While code does not correlate 1:1 of lines/executions:cpu cycles, we can imagine it doing so for simplicity sake (The compiles
optimize into machine  code that may result in many instructions per cycle of the crystal oscillator)

So for example using the above soft-logic, we said each instruction was 1 unit of work, and the file is 5 lines, we could expect
a while loop to declare and read the scanner to perform 7 units of work. However, if we are performing that logic inside each function
that quickly becomes 24 units of work including the original method call.

'''
    private static int countCharacters(String line, int count) {
        for (char c: line.toLowerCase().toCharArray()) {
            count = count +1;
        }
        return count;
    }
'''
Finally the individual methods to support the answer are streamed down to very simple and manageable units of logic. 
Good methods should only do what they are supposed to do, and nothing more.

DRY principals are also a good aid (Don't Repeat Yourself) - If you find yourself copy-and-pasting the same code over and over
that particular block likely needs to be abstracted to its own function.