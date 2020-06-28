# TaxCalculator
Instructions for build, deploy and execution are provided in the Readme.txt file
# Instructions for executing the 'Sales Tax' application (on Windows system)Open a MS-DOS (cmd) application
md c:\project 
cd c:\project
 git clone https://github.com/MaheshYV/TaxCalculator.git cd Taxcalculator\taxcalculator
 # Maven build and execution instructions mvn package

# Add taxcalculator.jar to the classpath
 java com.taxcalculator.test.TaxCalculatorApplicationTest

Execute Junit Test

Open the project taxcalculaor in any IDE (Eclipse, Spring Tool Suite, Intellij)
Run the com.taxcalculator.TaxCalculatorApplicationTest as Junit Test 
com.taxcalculator.TaxCalculatorApplicationTest.java is found in src/test/java folder
