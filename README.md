# ProbabilityArgumentation2021#
## Introduction to the Code and Operating Environment
This is the accompanying code for the paper "Iterative Decomposition-Based Characterization Probabilistic Argumentation Semantic Solution Method." To run this code, you need to install the following environment (for Windows):

- Python 3 (any version)
- Java Virtual Machine (any version), ensure to set up environment variables during installation

## Installing Python Dependencies
~~~
pip install networkx
~~~

## Solving Real-world Probabilistic Argumentation Frameworks
If you do not wish to conduct speed tests and merely want to solve the extension probability of a probabilistic argumentation framework using this algorithm without installing Java and related libraries, you can run the following code for a quick demonstration (download main.py):

~~~
from main import*
G,P,E=graphGenerator(20,2)
print(completeSolver(G,P,E)) # Solve and print complete extension probability
print(preferredSolver(G,P,E)) # Solve and print preferred extension probability
~~~

For a specific argumentation graph, construct a directed graph G using networkx, with node names as integers. Node probabilities are represented as lists (list) P, where P[i] represents the probability of node i. Extension E should be constructed as a set of integers. Alternatively, you can use the built-in sample generator in main.py for testing.

If you wish to conduct speed tests, please refer to the following guide.

## Modifying File Paths in the Code
You need to modify the file paths for cross-referencing in the code based on the directory where the code is saved. In the sixth line of the file "basicTest.py," you'll find:

~~~
command=''
~~~

Fill in the correct command according to the configured Java environment path. Below is a demonstration:

~~~
command=r'"C:\Program Files\Java\jdk-16.0.2\bin\java.exe" "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2\lib\idea_rt.jar=62512:C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2021.2\bin" -Dfile.encoding=UTF-8 -classpath D:\ProbabilityJLC2016\ProbabilityJLC2016\src\probabilisticArg\out\production\probabilisticArg probabilisticArg.Main'
~~~

Modify the paths in the demonstration, including the location of java.exe, IntelliJ, and the code storage location. If you are familiar with Java, you can fill it in by yourself.

## Running Tests
~~~
python Run.py
~~~

This test may take a long time and has an auto-save feature. If you need to terminate it, it will resume from the breakpoint automatically next time. If you want to shorten the test time, please modify the following lines:

~~~
    if j<=20:
        result = speedtest(j,rate,100)
    elif j<=25:
        result = speedtest(j,rate,50)
    else:
        result = speedtest(j,rate,20)
~~~
to

~~~
    if j<=20:
        result = speedtest(j,rate,10)
    elif j<=25:
        result = speedtest(j,rate,5)
    else:
        result = speedtest(j,rate,2)
~~~

This will reduce the test cases to 10%, greatly improving the test speed.

## Test Results
The author's test results can be found in **testdata/data.pkl**. For analysis and reading, please refer to **testdata/dataProcess.py**.

The environment and configuration used by the author are as follows:

- Operating System: Win10 x64 (Home Edition)
- Processor: i7 7700HQ (Frequency: 2.8G TDP: 56W)
- Memory: DDR4 32G (Frequency: 2667Mhz)
- Hard Drive: 1T Solid State Drive EX950
- GPU: Not used
- Multithreading: No
