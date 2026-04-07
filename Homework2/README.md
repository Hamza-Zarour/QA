# JUnit5 Homework

## A quick note about the IDE

The original project was set up for **Eclipse**, but I did this homework using **IntelliJ IDEA 2025.2.2**, so I had to make a few adjustments to get everything working.

Since Eclipse and IntelliJ don't share project files, I removed the Eclipse-specific files (`.classpath`, `.project`, `.settings/`, and the `bin/` output folder) and added a `pom.xml` to turn it into a Maven project. This lets IntelliJ automatically pull in JUnit 5 and all the other dependencies without any manual setup.

The actual source code under `src/main/najah/` is untouched, only the project configuration changed.

## How to run it

1. Open the project in IntelliJ IDEA.
2. When IntelliJ asks about the `pom.xml`, click **Load** and let Maven sync.
3. Once it's done, open any test class and hit the green play button to run it.
4. To run everything at once, open `AllTestsSuite` and run it.
