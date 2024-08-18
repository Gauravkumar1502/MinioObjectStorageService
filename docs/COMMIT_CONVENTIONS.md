# Commit Message Conventions

This document provides guidelines for writing commit messages in this project. Following these conventions helps keep the commit history clean and organized, making it easier to understand the purpose of changes and maintain the project effectively.

## Commit Message Prefixes

- **chore**

  - **Purpose:** Changes that do not affect the application’s behavior but involve routine tasks or maintenance, such as setting up the project or updating dependencies.
  
  - **Example:** `chore: initialize project`
  - **Example:** `chore: update README`

- **feat**
  - **Purpose:** Introduces a new feature or functionality.
  - **Example:** `feat: add user authentication module`

- **fix**
  - **Purpose:** Addresses a bug or issue in the code.
  - **Example:** `fix: resolve login issue with incorrect password handling`

- **docs**
  - **Purpose:** Updates or changes documentation.
  - **Example:** `docs: update README with setup instructions`

- **style**
  - **Purpose:** Changes that affect the code style or formatting but do not change functionality (e.g., fixing typos, formatting code).
  - **Example:** `style: correct indentation in main.java`

- **refactor**
  - **Purpose:** Changes to the code that improve code structure or readability without fixing bugs or adding features.
  - **Example:** `refactor: simplify user service class`

- **test**
  - **Purpose:** Adds or updates tests.
  - **Example:** `test: add unit tests for user service`

- **build**
  - **Purpose:** Changes related to build tools or configuration (e.g., changes to `pom.xml` or `package.json`).
  - **Example:** `build: update Maven build configuration`

- **ci**
  - **Purpose:** Changes related to continuous integration/continuous deployment (CI/CD) configurations.
  - **Example:** `ci: configure GitHub Actions workflow`