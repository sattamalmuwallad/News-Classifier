# News Classifier

## Overview
The **News Classifier** is a Java-based application that categorizes news articles into distinct groups based on their semantic content. By leveraging **TF-IDF (Term Frequency-Inverse Document Frequency)** embedding and **cosine similarity**, the application evaluates the semantic closeness of news articles to determine their group classification. 

This project was developed as part of the Object-Oriented Programming coursework at the University of Birmingham.

---

## How It Works

### 1. **HTML Parsing**
   - Extracts news titles and article content from raw HTML files.
   - Titles and content are retrieved using string manipulation methods.

### 2. **Text Preprocessing**
   - **Cleaning**: Converts text to lowercase and removes special characters.
   - **Lemmatization**: Simplifies words to their base forms (e.g., "playing" -> "play").
   - **Stop Word Removal**: Filters out common words like "and," "the," and "is."

### 3. **TF-IDF Embedding**
   - Converts preprocessed text into vector representations.
   - Calculates the importance of words within the corpus using TF-IDF scores.

### 4. **Cosine Similarity**
   - Measures the semantic closeness between articles using cosine similarity.
   - Groups articles based on their similarity scores.

### 5. **Result Grouping**
   - News articles are divided into two distinct categories based on their similarity scores.

---

## Features
- Parses and processes news articles from HTML files.
- Preprocesses text to standardize content.
- Implements a vector-based representation of text using TF-IDF.
- Groups news articles into semantically similar categories.
- Provides modular classes for HTML parsing, NLP preprocessing, vector operations, and classification.

---

## Technologies Used
- **Java**: Programming language.
- **JUnit**: For testing individual components.

---

## Setup

### Prerequisites
- Install Java Development Kit (JDK 8 or higher).
- Use an Integrated Development Environment (IDE) like IntelliJ IDEA or Eclipse.

### Steps
1. Clone this repository:
   ```bash
   git clone https://github.com/sattamalmuwallad/News-Classifier.git
   ```
2. Open the project in your IDE.
3. Run the `NewsClassifier.java` file to execute the application.

---

## Usage
1. Place the HTML files containing news articles in the `resources` folder.
2. Modify the `main()` method if necessary to test specific functionalities.
3. Run the program to see grouped results of the news articles.

---

## Project Structure
- `HtmlParser.java`: Parses and extracts news titles and content from HTML.
- `NLP.java`: Handles text preprocessing tasks like cleaning, lemmatization, and stop word removal.
- `Vector.java`: Defines vector operations like addition, subtraction, and cosine similarity.
- `NewsClassifier.java`: Integrates all components and manages the classification process.

---

## Future Work
- Extend the classifier to handle larger datasets.
- Add support for more advanced text preprocessing techniques.
- Integrate machine learning models for improved classification accuracy.

---

## Contributors
- **Sattam Almuwallad**

---

## License
This project is for educational purposes and does not include a license for redistribution or commercial use.
