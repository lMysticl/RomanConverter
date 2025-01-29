# Roman Numeral Converter

Hey there! This is a simple and blazing-fast converter that transforms numbers to Roman numerals and back. It works instantly and supports numbers up to 10,000!

## Features

- **Instant Conversion**: Just start typing, and the conversion happens automatically
- **Works Both Ways**: 
  - Type regular numbers (1994 → MCMXCIV)
  - Or Roman numerals (MCMXCIV → 1994)
- **Supports Large Numbers** up to 10000, using special symbols:
  ```
  5000 = ↁ
  10000 = ↂ
  ```

## Quick Start

1. Make sure you have:
   - Java 21 (or newer)
   - Maven

2. Fire it up:
   ```bash
   # Clone the repo
   git clone https://github.com/yourusername/roman-converter.git
   cd roman-converter

   # Run it!
   ./mvnw spring-boot:run
   ```

3. Open in your browser:
   ```
   http://localhost:8080
   ```

## How to Use

1. **Just Type Away!** 
   - Want to know how to write 42 in Roman numerals? Type "42"
   - Can't read "MCMXCIV"? Type it in and get 1994

2. **Fun Examples:**
   ```
   42    ↔  XLII   (the answer to everything!)
   1994  ↔  MCMXCIV
   5000  ↔  ↁ
   9000  ↔  Mↂ
   ```

## For Developers

### REST API

We've got two simple endpoints:

1. **To Roman:**
   ```http
   POST /api/toRoman?number=1994
   # Returns: MCMXCIV
   ```

2. **From Roman:**
   ```http
   POST /api/fromRoman?roman=MCMXCIV
   # Returns: 1994
   ```

### How It Works

We use a smart pattern system for each digit position:

```java
// Units: 1-9
I, II, III, IV, V, VI, VII, VIII, IX

// Tens: 10-90
X, XX, XXX, XL, L, LX, LXX, LXXX, XC

// And so on...
```

This makes conversions lightning fast! 

### Project Structure

```
roman-converter/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/roman/converter/
│   │   │       ├── controller/     # API endpoints
│   │   │       ├── service/        # Conversion magic
│   │   │       └── Application.java
│   │   └── resources/
│   │       └── static/
│   │           └── index.html      # Web interface
```

## Want to Help?

1. Fork it
2. Create your feature branch (`git checkout -b feature/CoolFeature`)
3. Commit your changes (`git commit -m 'Added an awesome feature'`)
4. Push to the branch (`git push origin feature/CoolFeature`)
5. Create a Pull Request

## License

MIT License - do whatever you want, just keep the credits! 

## Found a Bug?

Create an issue or drop me a line! Let's make this converter even better together!
