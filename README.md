# rate-calculator

### Run Tests
"""
mvn verify
"""

### Build the application
'''
mvn clean package
'''

### Build the application without tests
'''
mvn clean package -DskipTests
'''

### Run the application
'''
java -jar ./target/rate-calculator-1.0-SNAPSHOT.jar [filename] [amount]
'''
